package com.xiaoye.iworks.basic.core.support;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.basic.api.UserLoginInfoService;
import com.xiaoye.iworks.basic.api.constant.UserBasicInfoConstant;
import com.xiaoye.iworks.basic.api.constant.UserLoginInfoConstant;
import com.xiaoye.iworks.basic.api.dto.UserLoginInfoDto;
import com.xiaoye.iworks.basic.api.input.UserLoginInfoQueryInput;
import com.xiaoye.iworks.basic.persistent.entity.UserBasicInfoDO;
import com.xiaoye.iworks.basic.persistent.entity.UserLoginInfoDO;
import com.xiaoye.iworks.basic.persistent.mapper.UserBasicInfoMapper;
import com.xiaoye.iworks.basic.persistent.mapper.UserLoginInfoMapper;
import com.xiaoye.iworks.common.exception.BizServiceException;
import com.xiaoye.iworks.common.session.token.Token;
import com.xiaoye.iworks.common.session.token.TokenFactory;
import com.xiaoye.iworks.common.support.ResponseCheck;
import com.xiaoye.iworks.persistent.constant.PersistentConstant;
import com.xiaoye.iworks.service.RedisCacheService;
import com.xiaoye.iworks.utils.DateTimeUtils;
import com.xiaoye.iworks.utils.EncryptUtil;
import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.utils.WebUtils;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述: 用户登录辅助类
 * @auther: yehl
 * @date: 2019/1/28 16:44
 */
@Service
public class UserLoginAuxiliaryService {
    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private UserLoginInfoMapper userLoginInfoMapper;
    @Resource
    private UserBasicInfoMapper userBasicInfoMapper;

    @Autowired
    private TokenFactory tokenFactory;
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private UserLoginInfoService userLoginInfoService;

    /**
     * 功能描述: 创建Token
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/29 16:20
     */
    public DataResponse<String> createToken(UserLoginInfoDto infoDto, boolean refresh, HttpServletRequest request) {
        DataResponse<String> response = new DataResponse<>();
        if(refresh) {
            String token_str = tokenFactory.checkToken(request);
            if(StringUtils.isBlank(token_str)) {
                throw new BizServiceException(ServiceErrorCode.INVALID_LOGIN_ERROR, "登录失效，请重新登录");
            }
            // 获取缓存中的IP
            String token_cache_ip = redisCacheService.getString(token_str);
            // 获取user_pkid
            Long pkid = Long.parseLong(token_cache_ip.split("_")[0]);
            String cache_ip = token_cache_ip.split("_")[1];
            String ip = WebUtils.getRequestIp(request);
            if(!cache_ip.equals(ip)) {
                throw new BizServiceException(ServiceErrorCode.INVALID_LOGIN_ERROR, "登录失效，请重新登录");
            }
            UserLoginInfoQueryInput input = new UserLoginInfoQueryInput();
            input.setPkid(pkid);
            input.setCheckNull(true);
            DataResponse<UserLoginInfoDto> dataResponse = userLoginInfoService.findUserLoginInfo(input);
            ResponseCheck.checkSuccess(dataResponse);
            infoDto = dataResponse.getData();
        }
        // 生成token
        Token token = new Token(String.valueOf(infoDto.getPkid()), infoDto.getUserNo(), infoDto.getNickName(), infoDto.getUserName());
        token.setIp(WebUtils.getRequestIp(request));
        // TODO 查询权限集并设置

        response.setData(tokenFactory.createToken(token));
        return response;
    }

    /**
     * 功能描述: 创建新用户
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/28 13:57
     */
    @Transactional
    public DataResponse<Long> createUser(UserLoginInfoDto infoDto) {
        DataResponse<Long> response = new DataResponse<>();
        // 新增登陆账号
        UserLoginInfoDO userLoginInfoDO = new UserLoginInfoDO();
        BeanUtils.copyProperties(infoDto, userLoginInfoDO);
        userLoginInfoDO.setState(UserLoginInfoConstant.State.NORMAL);
        userLoginInfoDO.setLstate(PersistentConstant.Lstate.NORMAL);
        userLoginInfoDO.setCreateTime(DateTimeUtils.currentDate());
        userLoginInfoDO.setModifyTime(DateTimeUtils.currentDate());
        userLoginInfoMapper.insertSelective(userLoginInfoDO);
        Long pkid = userLoginInfoDO.getPkid();
        infoDto.setPkid(pkid);
        // 设置用户编码、密码盐、签名
        String user_no_prefix = DateTimeUtils.formatDateToString(DateTimeUtils.currentDate(), "yyyy");
        String user_no_suffix = String.valueOf(pkid % 1000000L);
        if(user_no_suffix.length() != 7) {
            user_no_suffix = "0000000".substring(user_no_suffix.length() + 1) + user_no_suffix;
        }
        userLoginInfoDO.setUserNo(String.format("%s%s", user_no_prefix, user_no_suffix));
        userLoginInfoDO.setPasssalt(EncryptUtil.MD5(userLoginInfoDO.getUserNo(), StringUtils.UUID32()));
        userLoginInfoDO.setPassword(EncryptUtil.SHA1(infoDto.getPassword(), userLoginInfoDO.getPasssalt()));
        userLoginInfoDO.setSign(EncryptUtil.MD5(userLoginInfoDO.genSign()));
        userLoginInfoMapper.updateSelective(userLoginInfoDO);
        // 新增用户基本信息数据
        UserBasicInfoDO userBasicInfoDO = new UserBasicInfoDO();
        userBasicInfoDO.setUserPkid(pkid);
        userBasicInfoDO.setUserNo(userLoginInfoDO.getUserNo());
        userBasicInfoDO.setNickName(infoDto.getNickName());
        userBasicInfoDO.setEmail(infoDto.getEmail());
        userBasicInfoDO.setMobile(infoDto.getMobile());
        userBasicInfoDO.setState(UserBasicInfoConstant.State.NORMAL);
        userBasicInfoDO.setLstate(PersistentConstant.Lstate.NORMAL);
        userBasicInfoDO.setCreateTime(DateTimeUtils.currentDate());
        userBasicInfoDO.setModifyTime(DateTimeUtils.currentDate());
        userBasicInfoDO.setSign(EncryptUtil.MD5(userBasicInfoDO.genSign()));
        userBasicInfoMapper.insertSelective(userBasicInfoDO);
        response.setData(pkid);
        return response;
    }
}
