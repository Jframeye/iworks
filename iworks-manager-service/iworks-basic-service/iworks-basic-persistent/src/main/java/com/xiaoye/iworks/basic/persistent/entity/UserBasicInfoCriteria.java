package com.xiaoye.iworks.basic.persistent.entity;
import com.google.common.collect.Lists;
import com.xiaoye.iworks.persistent.entity.Condition;
import com.xiaoye.iworks.persistent.entity.Entity;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.StringUtils;
import java.util.Date;
import java.util.List;

/**
 * 功能描述: 实体类查询条件【用户基本信息】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
public class UserBasicInfoCriteria extends Condition {
    @Override
    public Class<? extends Entity> geEntityClass() {
        return UserBasicInfoDO.class;
    }
    public UserBasicInfoCriteria() {
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
        public GeneratedCriteria andNickNameIsNull() {
            addCriterion("`nick_name` is null");
            return this;
        }
        public GeneratedCriteria andNickNameIsNotNull() {
            addCriterion("`nick_name` is not null");
            return this;
        }
        public GeneratedCriteria andNickNameEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`nick_name` =", value, "nickName");
            return this;
        }
        public GeneratedCriteria andNickNameNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`nick_name` <>", value, "nickName");
            return this;
        }
        public GeneratedCriteria andNickNameLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`nick_name` like", String.format("%s%s%s", "%", value, "%"), "nickName");
            return this;
        }
        public GeneratedCriteria andNickNameNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`nick_name` not like", String.format("%s%s%s", "%", value, "%"), "nickName");
            return this;
        }
        public GeneratedCriteria andNickNameIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`nick_name` in", values, "nickName");
            return this;
        }
        public GeneratedCriteria andNickNameNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`nick_name` not in", values, "nickName");
            return this;
        }
        public GeneratedCriteria andNickNameBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`nick_name` between", value1, value2, "nickName");
            return this;
        }
        public GeneratedCriteria andNickNameNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`nick_name` not between", value1, value2, "nickName");
            return this;
        }
        public GeneratedCriteria andSexIsNull() {
            addCriterion("`sex` is null");
            return this;
        }
        public GeneratedCriteria andSexIsNotNull() {
            addCriterion("`sex` is not null");
            return this;
        }
        public GeneratedCriteria andSexEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`sex` =", value, "sex");
            return this;
        }
        public GeneratedCriteria andSexNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`sex` <>", value, "sex");
            return this;
        }
        public GeneratedCriteria andSexLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`sex` like", String.format("%s%s%s", "%", value, "%"), "sex");
            return this;
        }
        public GeneratedCriteria andSexNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`sex` not like", String.format("%s%s%s", "%", value, "%"), "sex");
            return this;
        }
        public GeneratedCriteria andSexIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`sex` in", values, "sex");
            return this;
        }
        public GeneratedCriteria andSexNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`sex` not in", values, "sex");
            return this;
        }
        public GeneratedCriteria andSexBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`sex` between", value1, value2, "sex");
            return this;
        }
        public GeneratedCriteria andSexNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`sex` not between", value1, value2, "sex");
            return this;
        }
        public GeneratedCriteria andAgeIsNull() {
            addCriterion("`age` is null");
            return this;
        }
        public GeneratedCriteria andAgeIsNotNull() {
            addCriterion("`age` is not null");
            return this;
        }
        public GeneratedCriteria andAgeEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`age` =", value, "age");
            return this;
        }
        public GeneratedCriteria andAgeNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`age` <>", value, "age");
            return this;
        }
        public GeneratedCriteria andAgeLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`age` like", String.format("%s%s%s", "%", value, "%"), "age");
            return this;
        }
        public GeneratedCriteria andAgeNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`age` not like", String.format("%s%s%s", "%", value, "%"), "age");
            return this;
        }
        public GeneratedCriteria andAgeIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`age` in", values, "age");
            return this;
        }
        public GeneratedCriteria andAgeNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`age` not in", values, "age");
            return this;
        }
        public GeneratedCriteria andAgeBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`age` between", value1, value2, "age");
            return this;
        }
        public GeneratedCriteria andAgeNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`age` not between", value1, value2, "age");
            return this;
        }
        public GeneratedCriteria andBirthdayIsNull() {
            addCriterion("`birthday` is null");
            return this;
        }
        public GeneratedCriteria andBirthdayIsNotNull() {
            addCriterion("`birthday` is not null");
            return this;
        }
        public GeneratedCriteria andBirthdayEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`birthday` =", value, "birthday");
            return this;
        }
        public GeneratedCriteria andBirthdayNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`birthday` <>", value, "birthday");
            return this;
        }
        public GeneratedCriteria andBirthdayLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`birthday` like", String.format("%s%s%s", "%", value, "%"), "birthday");
            return this;
        }
        public GeneratedCriteria andBirthdayNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`birthday` not like", String.format("%s%s%s", "%", value, "%"), "birthday");
            return this;
        }
        public GeneratedCriteria andBirthdayIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`birthday` in", values, "birthday");
            return this;
        }
        public GeneratedCriteria andBirthdayNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`birthday` not in", values, "birthday");
            return this;
        }
        public GeneratedCriteria andBirthdayBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`birthday` between", value1, value2, "birthday");
            return this;
        }
        public GeneratedCriteria andBirthdayNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`birthday` not between", value1, value2, "birthday");
            return this;
        }
        public GeneratedCriteria andMobileIsNull() {
            addCriterion("`mobile` is null");
            return this;
        }
        public GeneratedCriteria andMobileIsNotNull() {
            addCriterion("`mobile` is not null");
            return this;
        }
        public GeneratedCriteria andMobileEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`mobile` =", value, "mobile");
            return this;
        }
        public GeneratedCriteria andMobileNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`mobile` <>", value, "mobile");
            return this;
        }
        public GeneratedCriteria andMobileLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`mobile` like", String.format("%s%s%s", "%", value, "%"), "mobile");
            return this;
        }
        public GeneratedCriteria andMobileNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`mobile` not like", String.format("%s%s%s", "%", value, "%"), "mobile");
            return this;
        }
        public GeneratedCriteria andMobileIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`mobile` in", values, "mobile");
            return this;
        }
        public GeneratedCriteria andMobileNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`mobile` not in", values, "mobile");
            return this;
        }
        public GeneratedCriteria andMobileBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`mobile` between", value1, value2, "mobile");
            return this;
        }
        public GeneratedCriteria andMobileNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`mobile` not between", value1, value2, "mobile");
            return this;
        }
        public GeneratedCriteria andEmailIsNull() {
            addCriterion("`email` is null");
            return this;
        }
        public GeneratedCriteria andEmailIsNotNull() {
            addCriterion("`email` is not null");
            return this;
        }
        public GeneratedCriteria andEmailEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`email` =", value, "email");
            return this;
        }
        public GeneratedCriteria andEmailNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`email` <>", value, "email");
            return this;
        }
        public GeneratedCriteria andEmailLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`email` like", String.format("%s%s%s", "%", value, "%"), "email");
            return this;
        }
        public GeneratedCriteria andEmailNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`email` not like", String.format("%s%s%s", "%", value, "%"), "email");
            return this;
        }
        public GeneratedCriteria andEmailIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`email` in", values, "email");
            return this;
        }
        public GeneratedCriteria andEmailNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`email` not in", values, "email");
            return this;
        }
        public GeneratedCriteria andEmailBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`email` between", value1, value2, "email");
            return this;
        }
        public GeneratedCriteria andEmailNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`email` not between", value1, value2, "email");
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