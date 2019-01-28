package com.xiaoye.iworks.basic.persistent.entity;
import com.google.common.collect.Lists;
import com.xiaoye.iworks.persistent.entity.Condition;
import com.xiaoye.iworks.persistent.entity.Entity;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.StringUtils;
import java.util.Date;
import java.util.List;

/**
 * 功能描述: 实体类查询条件【系统常量数据】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
public class AppConstantCriteria extends Condition {
    @Override
    public Class<? extends Entity> geEntityClass() {
        return AppConstantDO.class;
    }
    public AppConstantCriteria() {
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
        public GeneratedCriteria andConstantKeyIsNull() {
            addCriterion("`constant_key` is null");
            return this;
        }
        public GeneratedCriteria andConstantKeyIsNotNull() {
            addCriterion("`constant_key` is not null");
            return this;
        }
        public GeneratedCriteria andConstantKeyEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_key` =", value, "constantKey");
            return this;
        }
        public GeneratedCriteria andConstantKeyNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_key` <>", value, "constantKey");
            return this;
        }
        public GeneratedCriteria andConstantKeyLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_key` like", String.format("%s%s%s", "%", value, "%"), "constantKey");
            return this;
        }
        public GeneratedCriteria andConstantKeyNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_key` not like", String.format("%s%s%s", "%", value, "%"), "constantKey");
            return this;
        }
        public GeneratedCriteria andConstantKeyIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`constant_key` in", values, "constantKey");
            return this;
        }
        public GeneratedCriteria andConstantKeyNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`constant_key` not in", values, "constantKey");
            return this;
        }
        public GeneratedCriteria andConstantKeyBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`constant_key` between", value1, value2, "constantKey");
            return this;
        }
        public GeneratedCriteria andConstantKeyNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`constant_key` not between", value1, value2, "constantKey");
            return this;
        }
        public GeneratedCriteria andConstantValueIsNull() {
            addCriterion("`constant_value` is null");
            return this;
        }
        public GeneratedCriteria andConstantValueIsNotNull() {
            addCriterion("`constant_value` is not null");
            return this;
        }
        public GeneratedCriteria andConstantValueEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_value` =", value, "constantValue");
            return this;
        }
        public GeneratedCriteria andConstantValueNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_value` <>", value, "constantValue");
            return this;
        }
        public GeneratedCriteria andConstantValueLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_value` like", String.format("%s%s%s", "%", value, "%"), "constantValue");
            return this;
        }
        public GeneratedCriteria andConstantValueNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_value` not like", String.format("%s%s%s", "%", value, "%"), "constantValue");
            return this;
        }
        public GeneratedCriteria andConstantValueIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`constant_value` in", values, "constantValue");
            return this;
        }
        public GeneratedCriteria andConstantValueNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`constant_value` not in", values, "constantValue");
            return this;
        }
        public GeneratedCriteria andConstantValueBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`constant_value` between", value1, value2, "constantValue");
            return this;
        }
        public GeneratedCriteria andConstantValueNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`constant_value` not between", value1, value2, "constantValue");
            return this;
        }
        public GeneratedCriteria andConstantDescIsNull() {
            addCriterion("`constant_desc` is null");
            return this;
        }
        public GeneratedCriteria andConstantDescIsNotNull() {
            addCriterion("`constant_desc` is not null");
            return this;
        }
        public GeneratedCriteria andConstantDescEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_desc` =", value, "constantDesc");
            return this;
        }
        public GeneratedCriteria andConstantDescNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_desc` <>", value, "constantDesc");
            return this;
        }
        public GeneratedCriteria andConstantDescLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_desc` like", String.format("%s%s%s", "%", value, "%"), "constantDesc");
            return this;
        }
        public GeneratedCriteria andConstantDescNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_desc` not like", String.format("%s%s%s", "%", value, "%"), "constantDesc");
            return this;
        }
        public GeneratedCriteria andConstantDescIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`constant_desc` in", values, "constantDesc");
            return this;
        }
        public GeneratedCriteria andConstantDescNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`constant_desc` not in", values, "constantDesc");
            return this;
        }
        public GeneratedCriteria andConstantDescBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`constant_desc` between", value1, value2, "constantDesc");
            return this;
        }
        public GeneratedCriteria andConstantDescNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`constant_desc` not between", value1, value2, "constantDesc");
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
        public GeneratedCriteria andTagTypeIsNull() {
            addCriterion("`tag_type` is null");
            return this;
        }
        public GeneratedCriteria andTagTypeIsNotNull() {
            addCriterion("`tag_type` is not null");
            return this;
        }
        public GeneratedCriteria andTagTypeEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`tag_type` =", value, "tagType");
            return this;
        }
        public GeneratedCriteria andTagTypeNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`tag_type` <>", value, "tagType");
            return this;
        }
        public GeneratedCriteria andTagTypeLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`tag_type` like", String.format("%s%s%s", "%", value, "%"), "tagType");
            return this;
        }
        public GeneratedCriteria andTagTypeNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`tag_type` not like", String.format("%s%s%s", "%", value, "%"), "tagType");
            return this;
        }
        public GeneratedCriteria andTagTypeIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`tag_type` in", values, "tagType");
            return this;
        }
        public GeneratedCriteria andTagTypeNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`tag_type` not in", values, "tagType");
            return this;
        }
        public GeneratedCriteria andTagTypeBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`tag_type` between", value1, value2, "tagType");
            return this;
        }
        public GeneratedCriteria andTagTypeNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`tag_type` not between", value1, value2, "tagType");
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
        public GeneratedCriteria andCreateByIsNull() {
            addCriterion("`create_by` is null");
            return this;
        }
        public GeneratedCriteria andCreateByIsNotNull() {
            addCriterion("`create_by` is not null");
            return this;
        }
        public GeneratedCriteria andCreateByEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`create_by` =", value, "createBy");
            return this;
        }
        public GeneratedCriteria andCreateByNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`create_by` <>", value, "createBy");
            return this;
        }
        public GeneratedCriteria andCreateByLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`create_by` like", String.format("%s%s%s", "%", value, "%"), "createBy");
            return this;
        }
        public GeneratedCriteria andCreateByNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`create_by` not like", String.format("%s%s%s", "%", value, "%"), "createBy");
            return this;
        }
        public GeneratedCriteria andCreateByIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`create_by` in", values, "createBy");
            return this;
        }
        public GeneratedCriteria andCreateByNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`create_by` not in", values, "createBy");
            return this;
        }
        public GeneratedCriteria andCreateByBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`create_by` between", value1, value2, "createBy");
            return this;
        }
        public GeneratedCriteria andCreateByNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`create_by` not between", value1, value2, "createBy");
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
        public GeneratedCriteria andModifyByIsNull() {
            addCriterion("`modify_by` is null");
            return this;
        }
        public GeneratedCriteria andModifyByIsNotNull() {
            addCriterion("`modify_by` is not null");
            return this;
        }
        public GeneratedCriteria andModifyByEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`modify_by` =", value, "modifyBy");
            return this;
        }
        public GeneratedCriteria andModifyByNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`modify_by` <>", value, "modifyBy");
            return this;
        }
        public GeneratedCriteria andModifyByLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`modify_by` like", String.format("%s%s%s", "%", value, "%"), "modifyBy");
            return this;
        }
        public GeneratedCriteria andModifyByNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`modify_by` not like", String.format("%s%s%s", "%", value, "%"), "modifyBy");
            return this;
        }
        public GeneratedCriteria andModifyByIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`modify_by` in", values, "modifyBy");
            return this;
        }
        public GeneratedCriteria andModifyByNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`modify_by` not in", values, "modifyBy");
            return this;
        }
        public GeneratedCriteria andModifyByBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`modify_by` between", value1, value2, "modifyBy");
            return this;
        }
        public GeneratedCriteria andModifyByNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`modify_by` not between", value1, value2, "modifyBy");
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