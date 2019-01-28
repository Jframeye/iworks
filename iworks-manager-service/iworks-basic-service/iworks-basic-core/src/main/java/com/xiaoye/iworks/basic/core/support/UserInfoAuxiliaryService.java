package com.xiaoye.iworks.basic.core.support;

import com.xiaoye.iworks.basic.api.constant.UserBasicInfoConstant;
import com.xiaoye.iworks.basic.api.constant.UserLoginInfoConstant;
import com.xiaoye.iworks.basic.api.dto.DictConstantDataDto;
import com.xiaoye.iworks.basic.api.dto.UserLoginInfoDto;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDataCriteria;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDataDO;
import com.xiaoye.iworks.basic.persistent.entity.UserBasicInfoDO;
import com.xiaoye.iworks.basic.persistent.entity.UserLoginInfoDO;
import com.xiaoye.iworks.basic.persistent.mapper.UserBasicInfoMapper;
import com.xiaoye.iworks.basic.persistent.mapper.UserLoginInfoMapper;
import com.xiaoye.iworks.persistent.constant.PersistentConstant;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.DateTimeUtils;
import com.xiaoye.iworks.utils.EncryptUtil;
import com.xiaoye.iworks.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述: 用户相关辅助类
 * @auther: yehl
 * @date: 2019/1/23 10:14
 */
@Service
public class UserInfoAuxiliaryService {
    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private UserLoginInfoMapper userLoginInfoMapper;
    @Resource
    private UserBasicInfoMapper userBasicInfoMapper;

    /**
     * 功能描述: 创建新用户
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/28 13:57
     */
    @Transactional
    public void createUser(UserLoginInfoDto dto) {
        // 新增登陆账号
        UserLoginInfoDO userLoginInfoDO = new UserLoginInfoDO();
        BeanUtils.copyProperties(dto, userLoginInfoDO);
        userLoginInfoDO.setState(UserLoginInfoConstant.State.NORMAL);
        userLoginInfoDO.setLstate(PersistentConstant.Lstate.NORMAL);
        userLoginInfoDO.setCreateTime(DateTimeUtils.currentDate());
        userLoginInfoDO.setModifyTime(DateTimeUtils.currentDate());
        userLoginInfoMapper.insertSelective(userLoginInfoDO);
        Long pkid = userLoginInfoDO.getPkid();
        dto.setPkid(pkid);
        // 设置用户编码、密码盐、签名
        String user_no_prefix = DateTimeUtils.formatDateToString(DateTimeUtils.currentDate(), "yyyy");
        String user_no_suffix = String.valueOf(pkid % 1000000L);
        if(user_no_suffix.length() != 7) {
            user_no_suffix = "0000000".substring(user_no_suffix.length() + 1) + user_no_suffix;
        }
        userLoginInfoDO.setUserNo(String.format("%s%s", user_no_prefix, user_no_suffix));
        userLoginInfoDO.setPasssalt(EncryptUtil.MD5(userLoginInfoDO.getUserNo(), StringUtils.UUID32()));
        userLoginInfoDO.setPassword(EncryptUtil.SHA1(dto.getPassword(), userLoginInfoDO.getPasssalt()));
        userLoginInfoDO.setSign(EncryptUtil.MD5(userLoginInfoDO.genSign()));
        userLoginInfoMapper.updateSelective(userLoginInfoDO);
        // 新增用户基本信息数据
        UserBasicInfoDO userBasicInfoDO = new UserBasicInfoDO();
        userBasicInfoDO.setUserPkid(pkid);
        userBasicInfoDO.setUserNo(userLoginInfoDO.getUserNo());
        userBasicInfoDO.setNickName(dto.getNickName());
        userBasicInfoDO.setEmail(dto.getEmail());
        userBasicInfoDO.setMobile(dto.getMobile());
        userBasicInfoDO.setState(UserBasicInfoConstant.State.NORMAL);
        userBasicInfoDO.setLstate(PersistentConstant.Lstate.NORMAL);
        userBasicInfoDO.setCreateTime(DateTimeUtils.currentDate());
        userBasicInfoDO.setModifyTime(DateTimeUtils.currentDate());
        userBasicInfoDO.setSign(EncryptUtil.MD5(userBasicInfoDO.genSign()));
        userBasicInfoMapper.insertSelective(userBasicInfoDO);
    }
}
