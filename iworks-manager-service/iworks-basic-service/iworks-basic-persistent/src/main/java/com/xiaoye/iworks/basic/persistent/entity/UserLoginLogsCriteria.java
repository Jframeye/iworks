package com.xiaoye.iworks.basic.persistent.entity;
import com.google.common.collect.Lists;
import com.xiaoye.iworks.persistent.entity.Condition;
import com.xiaoye.iworks.persistent.entity.Entity;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.StringUtils;
import java.util.Date;
import java.util.List;

/**
 * 功能描述: 实体类查询条件【系统用户登录日志】
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
public class UserLoginLogsCriteria extends Condition {
    @Override
    public Class<? extends Entity> geEntityClass() {
        return UserLoginLogsDO.class;
    }
    public UserLoginLogsCriteria() {
        criterias = Lists.newArrayList();
    }
    public void or(GeneratedCriteria GeneratedCriteria) {
        criterias.add(GeneratedCriteria);
    }
    public GeneratedCriteria or() {
        GeneratedCriteria generatedCriteria = createCriteriaInternal();
        criterias.add(generatedCriteria);
        return generatedCriteria;
    }
    public GeneratedCriteria createCriteriaInternal() {
        GeneratedCriteria generatedCriteria = new GeneratedCriteria();
        if (criterias.size() == 0) {
            criterias.add(generatedCriteria);
        }
        return generatedCriteria;
    }
    public class GeneratedCriteria extends Criteria {
        protected GeneratedCriteria() {
            super();
            criterions = Lists.newArrayList();
        }
        public GeneratedCriteria andPkidIsNull() {
            addCriterion("`pkid` is null");
            return this;
        }
        public GeneratedCriteria andPkidIsNotNull() {
            addCriterion("`pkid` is not null");
            return this;
        }
        public GeneratedCriteria andPkidEqualTo(Long value) {
            if(value == null) return this;
            addCriterion("`pkid` =", value, "pkid");
            return this;
        }
        public GeneratedCriteria andPkidNotEqualTo(Long value) {
            if(value == null) return this;
            addCriterion("`pkid` <>", value, "pkid");
            return this;
        }
        public GeneratedCriteria andPkidGreaterThan(Long value) {
            if(value == null) return this;
            addCriterion("`pkid` >", value, "pkid");
            return this;
        }
        public GeneratedCriteria andPkidGreaterThanOrEqualTo(Long value) {
            if(value == null) return this;
            addCriterion("`pkid` >=", value, "pkid");
            return this;
        }
        public GeneratedCriteria andPkidLessThan(Long value) {
            if(value == null) return this;
            addCriterion("`pkid` <", value, "pkid");
            return this;
        }
        public GeneratedCriteria andPkidLessThanOrEqualTo(Long value) {
            if(value == null) return this;
            addCriterion("`pkid` <=", value, "pkid");
            return this;
        }
        public GeneratedCriteria andPkidIn(List<Long> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`pkid` in", values, "pkid");
            return this;
        }
        public GeneratedCriteria andPkidNotIn(List<Long> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`pkid` not in", values, "pkid");
            return this;
        }
        public GeneratedCriteria andPkidBetween(Long value1, Long value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`pkid` between", value1, value2, "pkid");
            return this;
        }
        public GeneratedCriteria andPkidNotBetween(Long value1, Long value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`pkid` not between", value1, value2, "pkid");
            return this;
        }
        public GeneratedCriteria andUserPkidIsNull() {
            addCriterion("`user_pkid` is null");
            return this;
        }
        public GeneratedCriteria andUserPkidIsNotNull() {
            addCriterion("`user_pkid` is not null");
            return this;
        }
        public GeneratedCriteria andUserPkidEqualTo(Long value) {
            if(value == null) return this;
            addCriterion("`user_pkid` =", value, "userPkid");
            return this;
        }
        public GeneratedCriteria andUserPkidNotEqualTo(Long value) {
            if(value == null) return this;
            addCriterion("`user_pkid` <>", value, "userPkid");
            return this;
        }
        public GeneratedCriteria andUserPkidGreaterThan(Long value) {
            if(value == null) return this;
            addCriterion("`user_pkid` >", value, "userPkid");
            return this;
        }
        public GeneratedCriteria andUserPkidGreaterThanOrEqualTo(Long value) {
            if(value == null) return this;
            addCriterion("`user_pkid` >=", value, "userPkid");
            return this;
        }
        public GeneratedCriteria andUserPkidLessThan(Long value) {
            if(value == null) return this;
            addCriterion("`user_pkid` <", value, "userPkid");
            return this;
        }
        public GeneratedCriteria andUserPkidLessThanOrEqualTo(Long value) {
            if(value == null) return this;
            addCriterion("`user_pkid` <=", value, "userPkid");
            return this;
        }
        public GeneratedCriteria andUserPkidIn(List<Long> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`user_pkid` in", values, "userPkid");
            return this;
        }
        public GeneratedCriteria andUserPkidNotIn(List<Long> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`user_pkid` not in", values, "userPkid");
            return this;
        }
        public GeneratedCriteria andUserPkidBetween(Long value1, Long value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`user_pkid` between", value1, value2, "userPkid");
            return this;
        }
        public GeneratedCriteria andUserPkidNotBetween(Long value1, Long value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`user_pkid` not between", value1, value2, "userPkid");
            return this;
        }
        public GeneratedCriteria andUserNoIsNull() {
            addCriterion("`user_no` is null");
            return this;
        }
        public GeneratedCriteria andUserNoIsNotNull() {
            addCriterion("`user_no` is not null");
            return this;
        }
        public GeneratedCriteria andUserNoEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`user_no` =", value, "userNo");
            return this;
        }
        public GeneratedCriteria andUserNoNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`user_no` <>", value, "userNo");
            return this;
        }
        public GeneratedCriteria andUserNoLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`user_no` like", String.format("%s%s%s", "%", value, "%"), "userNo");
            return this;
        }
        public GeneratedCriteria andUserNoNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`user_no` not like", String.format("%s%s%s", "%", value, "%"), "userNo");
            return this;
        }
        public GeneratedCriteria andUserNoIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`user_no` in", values, "userNo");
            return this;
        }
        public GeneratedCriteria andUserNoNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`user_no` not in", values, "userNo");
            return this;
        }
        public GeneratedCriteria andUserNoBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`user_no` between", value1, value2, "userNo");
            return this;
        }
        public GeneratedCriteria andUserNoNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`user_no` not between", value1, value2, "userNo");
            return this;
        }
        public GeneratedCriteria andUserNameIsNull() {
            addCriterion("`user_name` is null");
            return this;
        }
        public GeneratedCriteria andUserNameIsNotNull() {
            addCriterion("`user_name` is not null");
            return this;
        }
        public GeneratedCriteria andUserNameEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`user_name` =", value, "userName");
            return this;
        }
        public GeneratedCriteria andUserNameNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`user_name` <>", value, "userName");
            return this;
        }
        public GeneratedCriteria andUserNameLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`user_name` like", String.format("%s%s%s", "%", value, "%"), "userName");
            return this;
        }
        public GeneratedCriteria andUserNameNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`user_name` not like", String.format("%s%s%s", "%", value, "%"), "userName");
            return this;
        }
        public GeneratedCriteria andUserNameIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`user_name` in", values, "userName");
            return this;
        }
        public GeneratedCriteria andUserNameNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`user_name` not in", values, "userName");
            return this;
        }
        public GeneratedCriteria andUserNameBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`user_name` between", value1, value2, "userName");
            return this;
        }
        public GeneratedCriteria andUserNameNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`user_name` not between", value1, value2, "userName");
            return this;
        }
        public GeneratedCriteria andIpIsNull() {
            addCriterion("`ip` is null");
            return this;
        }
        public GeneratedCriteria andIpIsNotNull() {
            addCriterion("`ip` is not null");
            return this;
        }
        public GeneratedCriteria andIpEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`ip` =", value, "ip");
            return this;
        }
        public GeneratedCriteria andIpNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`ip` <>", value, "ip");
            return this;
        }
        public GeneratedCriteria andIpLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`ip` like", String.format("%s%s%s", "%", value, "%"), "ip");
            return this;
        }
        public GeneratedCriteria andIpNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`ip` not like", String.format("%s%s%s", "%", value, "%"), "ip");
            return this;
        }
        public GeneratedCriteria andIpIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`ip` in", values, "ip");
            return this;
        }
        public GeneratedCriteria andIpNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`ip` not in", values, "ip");
            return this;
        }
        public GeneratedCriteria andIpBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`ip` between", value1, value2, "ip");
            return this;
        }
        public GeneratedCriteria andIpNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`ip` not between", value1, value2, "ip");
            return this;
        }
        public GeneratedCriteria andAddressIsNull() {
            addCriterion("`address` is null");
            return this;
        }
        public GeneratedCriteria andAddressIsNotNull() {
            addCriterion("`address` is not null");
            return this;
        }
        public GeneratedCriteria andAddressEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`address` =", value, "address");
            return this;
        }
        public GeneratedCriteria andAddressNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`address` <>", value, "address");
            return this;
        }
        public GeneratedCriteria andAddressLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`address` like", String.format("%s%s%s", "%", value, "%"), "address");
            return this;
        }
        public GeneratedCriteria andAddressNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`address` not like", String.format("%s%s%s", "%", value, "%"), "address");
            return this;
        }
        public GeneratedCriteria andAddressIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`address` in", values, "address");
            return this;
        }
        public GeneratedCriteria andAddressNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`address` not in", values, "address");
            return this;
        }
        public GeneratedCriteria andAddressBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`address` between", value1, value2, "address");
            return this;
        }
        public GeneratedCriteria andAddressNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`address` not between", value1, value2, "address");
            return this;
        }
        public GeneratedCriteria andBrowserIsNull() {
            addCriterion("`browser` is null");
            return this;
        }
        public GeneratedCriteria andBrowserIsNotNull() {
            addCriterion("`browser` is not null");
            return this;
        }
        public GeneratedCriteria andBrowserEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`browser` =", value, "browser");
            return this;
        }
        public GeneratedCriteria andBrowserNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`browser` <>", value, "browser");
            return this;
        }
        public GeneratedCriteria andBrowserLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`browser` like", String.format("%s%s%s", "%", value, "%"), "browser");
            return this;
        }
        public GeneratedCriteria andBrowserNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`browser` not like", String.format("%s%s%s", "%", value, "%"), "browser");
            return this;
        }
        public GeneratedCriteria andBrowserIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`browser` in", values, "browser");
            return this;
        }
        public GeneratedCriteria andBrowserNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`browser` not in", values, "browser");
            return this;
        }
        public GeneratedCriteria andBrowserBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`browser` between", value1, value2, "browser");
            return this;
        }
        public GeneratedCriteria andBrowserNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`browser` not between", value1, value2, "browser");
            return this;
        }
        public GeneratedCriteria andSystemIsNull() {
            addCriterion("`system` is null");
            return this;
        }
        public GeneratedCriteria andSystemIsNotNull() {
            addCriterion("`system` is not null");
            return this;
        }
        public GeneratedCriteria andSystemEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`system` =", value, "system");
            return this;
        }
        public GeneratedCriteria andSystemNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`system` <>", value, "system");
            return this;
        }
        public GeneratedCriteria andSystemLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`system` like", String.format("%s%s%s", "%", value, "%"), "system");
            return this;
        }
        public GeneratedCriteria andSystemNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`system` not like", String.format("%s%s%s", "%", value, "%"), "system");
            return this;
        }
        public GeneratedCriteria andSystemIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`system` in", values, "system");
            return this;
        }
        public GeneratedCriteria andSystemNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`system` not in", values, "system");
            return this;
        }
        public GeneratedCriteria andSystemBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`system` between", value1, value2, "system");
            return this;
        }
        public GeneratedCriteria andSystemNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`system` not between", value1, value2, "system");
            return this;
        }
        public GeneratedCriteria andMessageIsNull() {
            addCriterion("`message` is null");
            return this;
        }
        public GeneratedCriteria andMessageIsNotNull() {
            addCriterion("`message` is not null");
            return this;
        }
        public GeneratedCriteria andMessageEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`message` =", value, "message");
            return this;
        }
        public GeneratedCriteria andMessageNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`message` <>", value, "message");
            return this;
        }
        public GeneratedCriteria andMessageLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`message` like", String.format("%s%s%s", "%", value, "%"), "message");
            return this;
        }
        public GeneratedCriteria andMessageNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`message` not like", String.format("%s%s%s", "%", value, "%"), "message");
            return this;
        }
        public GeneratedCriteria andMessageIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`message` in", values, "message");
            return this;
        }
        public GeneratedCriteria andMessageNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`message` not in", values, "message");
            return this;
        }
        public GeneratedCriteria andMessageBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`message` between", value1, value2, "message");
            return this;
        }
        public GeneratedCriteria andMessageNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`message` not between", value1, value2, "message");
            return this;
        }
        public GeneratedCriteria andSignIsNull() {
            addCriterion("`sign` is null");
            return this;
        }
        public GeneratedCriteria andSignIsNotNull() {
            addCriterion("`sign` is not null");
            return this;
        }
        public GeneratedCriteria andSignEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`sign` =", value, "sign");
            return this;
        }
        public GeneratedCriteria andSignNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`sign` <>", value, "sign");
            return this;
        }
        public GeneratedCriteria andSignLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`sign` like", String.format("%s%s%s", "%", value, "%"), "sign");
            return this;
        }
        public GeneratedCriteria andSignNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`sign` not like", String.format("%s%s%s", "%", value, "%"), "sign");
            return this;
        }
        public GeneratedCriteria andSignIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`sign` in", values, "sign");
            return this;
        }
        public GeneratedCriteria andSignNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`sign` not in", values, "sign");
            return this;
        }
        public GeneratedCriteria andSignBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`sign` between", value1, value2, "sign");
            return this;
        }
        public GeneratedCriteria andSignNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`sign` not between", value1, value2, "sign");
            return this;
        }
        public GeneratedCriteria andStateIsNull() {
            addCriterion("`state` is null");
            return this;
        }
        public GeneratedCriteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return this;
        }
        public GeneratedCriteria andStateEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`state` =", value, "state");
            return this;
        }
        public GeneratedCriteria andStateNotEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`state` <>", value, "state");
            return this;
        }
        public GeneratedCriteria andStateGreaterThan(Integer value) {
            if(value == null) return this;
            addCriterion("`state` >", value, "state");
            return this;
        }
        public GeneratedCriteria andStateGreaterThanOrEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`state` >=", value, "state");
            return this;
        }
        public GeneratedCriteria andStateLessThan(Integer value) {
            if(value == null) return this;
            addCriterion("`state` <", value, "state");
            return this;
        }
        public GeneratedCriteria andStateLessThanOrEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`state` <=", value, "state");
            return this;
        }
        public GeneratedCriteria andStateIn(List<Integer> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`state` in", values, "state");
            return this;
        }
        public GeneratedCriteria andStateNotIn(List<Integer> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`state` not in", values, "state");
            return this;
        }
        public GeneratedCriteria andStateBetween(Integer value1, Integer value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`state` between", value1, value2, "state");
            return this;
        }
        public GeneratedCriteria andStateNotBetween(Integer value1, Integer value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`state` not between", value1, value2, "state");
            return this;
        }
        public GeneratedCriteria andLstateIsNull() {
            addCriterion("`lstate` is null");
            return this;
        }
        public GeneratedCriteria andLstateIsNotNull() {
            addCriterion("`lstate` is not null");
            return this;
        }
        public GeneratedCriteria andLstateEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`lstate` =", value, "lstate");
            return this;
        }
        public GeneratedCriteria andLstateNotEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`lstate` <>", value, "lstate");
            return this;
        }
        public GeneratedCriteria andLstateGreaterThan(Integer value) {
            if(value == null) return this;
            addCriterion("`lstate` >", value, "lstate");
            return this;
        }
        public GeneratedCriteria andLstateGreaterThanOrEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`lstate` >=", value, "lstate");
            return this;
        }
        public GeneratedCriteria andLstateLessThan(Integer value) {
            if(value == null) return this;
            addCriterion("`lstate` <", value, "lstate");
            return this;
        }
        public GeneratedCriteria andLstateLessThanOrEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`lstate` <=", value, "lstate");
            return this;
        }
        public GeneratedCriteria andLstateIn(List<Integer> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`lstate` in", values, "lstate");
            return this;
        }
        public GeneratedCriteria andLstateNotIn(List<Integer> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`lstate` not in", values, "lstate");
            return this;
        }
        public GeneratedCriteria andLstateBetween(Integer value1, Integer value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`lstate` between", value1, value2, "lstate");
            return this;
        }
        public GeneratedCriteria andLstateNotBetween(Integer value1, Integer value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`lstate` not between", value1, value2, "lstate");
            return this;
        }
        public GeneratedCriteria andCreateTimeIsNull() {
            addCriterion("`create_time` is null");
            return this;
        }
        public GeneratedCriteria andCreateTimeIsNotNull() {
            addCriterion("`create_time` is not null");
            return this;
        }
        public GeneratedCriteria andCreateTimeEqualTo(Date value) {
            if(value == null) return this;
            addCriterion("`create_time` =", value, "createTime");
            return this;
        }
        public GeneratedCriteria andCreateTimeNotEqualTo(Date value) {
            if(value == null) return this;
            addCriterion("`create_time` <>", value, "createTime");
            return this;
        }
        public GeneratedCriteria andCreateTimeGreaterThan(Date value) {
            if(value == null) return this;
            addCriterion("`create_time` >", value, "createTime");
            return this;
        }
        public GeneratedCriteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            if(value == null) return this;
            addCriterion("`create_time` >=", value, "createTime");
            return this;
        }
        public GeneratedCriteria andCreateTimeLessThan(Date value) {
            if(value == null) return this;
            addCriterion("`create_time` <", value, "createTime");
            return this;
        }
        public GeneratedCriteria andCreateTimeLessThanOrEqualTo(Date value) {
            if(value == null) return this;
            addCriterion("`create_time` <=", value, "createTime");
            return this;
        }
        public GeneratedCriteria andCreateTimeIn(List<Date> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`create_time` in", values, "createTime");
            return this;
        }
        public GeneratedCriteria andCreateTimeNotIn(List<Date> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`create_time` not in", values, "createTime");
            return this;
        }
        public GeneratedCriteria andCreateTimeBetween(Date value1, Date value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`create_time` between", value1, value2, "createTime");
            return this;
        }
        public GeneratedCriteria andCreateTimeNotBetween(Date value1, Date value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`create_time` not between", value1, value2, "createTime");
            return this;
        }
        public GeneratedCriteria andModifyTimeIsNull() {
            addCriterion("`modify_time` is null");
            return this;
        }
        public GeneratedCriteria andModifyTimeIsNotNull() {
            addCriterion("`modify_time` is not null");
            return this;
        }
        public GeneratedCriteria andModifyTimeEqualTo(Date value) {
            if(value == null) return this;
            addCriterion("`modify_time` =", value, "modifyTime");
            return this;
        }
        public GeneratedCriteria andModifyTimeNotEqualTo(Date value) {
            if(value == null) return this;
            addCriterion("`modify_time` <>", value, "modifyTime");
            return this;
        }
        public GeneratedCriteria andModifyTimeGreaterThan(Date value) {
            if(value == null) return this;
            addCriterion("`modify_time` >", value, "modifyTime");
            return this;
        }
        public GeneratedCriteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            if(value == null) return this;
            addCriterion("`modify_time` >=", value, "modifyTime");
            return this;
        }
        public GeneratedCriteria andModifyTimeLessThan(Date value) {
            if(value == null) return this;
            addCriterion("`modify_time` <", value, "modifyTime");
            return this;
        }
        public GeneratedCriteria andModifyTimeLessThanOrEqualTo(Date value) {
            if(value == null) return this;
            addCriterion("`modify_time` <=", value, "modifyTime");
            return this;
        }
        public GeneratedCriteria andModifyTimeIn(List<Date> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`modify_time` in", values, "modifyTime");
            return this;
        }
        public GeneratedCriteria andModifyTimeNotIn(List<Date> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`modify_time` not in", values, "modifyTime");
            return this;
        }
        public GeneratedCriteria andModifyTimeBetween(Date value1, Date value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`modify_time` between", value1, value2, "modifyTime");
            return this;
        }
        public GeneratedCriteria andModifyTimeNotBetween(Date value1, Date value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`modify_time` not between", value1, value2, "modifyTime");
            return this;
        }
    }
}