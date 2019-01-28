package com.xiaoye.iworks.basic.api.dto;
import com.xiaoye.iworks.api.input.Input;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 功能描述: 用户基本信息 查询入参
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserBasicInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 自增主键[auto_increment] **/
    private Long pkid;
    /** 用户主键 **/
    private Long userPkid;
    /** 用户编号 **/
    private String userNo;
    /** 用户名称 **/
    private String nickName;
    /** 性别 **/
    private String sex;
    /** 年龄 **/
    private String age;
    /** 生日 **/
    private String birthday;
    /** 手机号码 **/
    private String mobile;
    /** 邮箱账户 **/
    private String email;
    /** 数据签名 **/
    private String sign;
    /** 状态[1-正常；2-冻结；3-锁定；4-注销] **/
    private Integer state;
    /** 逻辑状态[1-正常；2-删除] **/
    private Integer lstate;
    /** 创建时间 **/
    private Date createTime;
    /** 最后修改时间 **/
    private Date modifyTime;
}