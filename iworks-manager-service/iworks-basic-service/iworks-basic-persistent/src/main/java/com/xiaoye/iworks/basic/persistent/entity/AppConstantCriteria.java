package com.xiaoye.iworks.basic.persistent.entity;

import com.google.common.collect.Lists;
import com.xiaoye.iworks.persistent.entity.Condition;
import com.xiaoye.iworks.persistent.entity.Entity;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * TODO
 * @author yehl
 * @date 2018年6月30日
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

        public GeneratedCriteria andPkidLike(Long value) {
            if(value == null) return this;
            addCriterion("`pkid` like", value, "pkid");
            return this;
        }

        public GeneratedCriteria andPkidNotLike(Long value) {
            if(value == null) return this;
            addCriterion("`pkid` not like", value, "pkid");
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

        public GeneratedCriteria andConstantKeyGreaterThan(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_key` >", value, "constantKey");
            return this;
        }

        public GeneratedCriteria andConstantKeyGreaterThanOrEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_key` >=", value, "constantKey");
            return this;
        }

        public GeneratedCriteria andConstantKeyLessThan(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_key` <", value, "constantKey");
            return this;
        }

        public GeneratedCriteria andConstantKeyLessThanOrEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`constant_key` <=", value, "constantKey");
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
            addCriterion("`constant_value` =", value, "constantValue");
            return this;
        }

        public GeneratedCriteria andConstantValueNotEqualTo(String value) {
            addCriterion("`constant_value` <>", value, "constantValue");
            return this;
        }

        public GeneratedCriteria andConstantValueGreaterThan(String value) {
            addCriterion("`constant_value` >", value, "constantValue");
            return this;
        }

        public GeneratedCriteria andConstantValueGreaterThanOrEqualTo(String value) {
            addCriterion("`constant_value` >=", value, "constantValue");
            return this;
        }

        public GeneratedCriteria andConstantValueLessThan(String value) {
            addCriterion("`constant_value` <", value, "constantValue");
            return this;
        }

        public GeneratedCriteria andConstantValueLessThanOrEqualTo(String value) {
            addCriterion("`constant_value` <=", value, "constantValue");
            return this;
        }

        public GeneratedCriteria andConstantValueLike(String value) {
            addCriterion("`constant_value` like", String.format("%s%s%s", "%", value, "%"), "constantValue");
            return this;
        }

        public GeneratedCriteria andConstantValueNotLike(String value) {
            addCriterion("`constant_value` not like", String.format("%s%s%s", "%", value, "%"), "constantValue");
            return this;
        }

        public GeneratedCriteria andConstantValueIn(List<String> values) {
            addCriterion("`constant_value` in", values, "constantValue");
            return this;
        }

        public GeneratedCriteria andConstantValueNotIn(List<String> values) {
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
            addCriterion("`constant_desc` =", value, "constantDesc");
            return this;
        }

        public GeneratedCriteria andConstantDescNotEqualTo(String value) {
            addCriterion("`constant_desc` <>", value, "constantDesc");
            return this;
        }

        public GeneratedCriteria andConstantDescGreaterThan(String value) {
            addCriterion("`constant_desc` >", value, "constantDesc");
            return this;
        }

        public GeneratedCriteria andConstantDescGreaterThanOrEqualTo(String value) {
            addCriterion("`constant_desc` >=", value, "constantDesc");
            return this;
        }

        public GeneratedCriteria andConstantDescLessThan(String value) {
            addCriterion("`constant_desc` <", value, "constantDesc");
            return this;
        }

        public GeneratedCriteria andConstantDescLessThanOrEqualTo(String value) {
            addCriterion("`constant_desc` <=", value, "constantDesc");
            return this;
        }

        public GeneratedCriteria andConstantDescLike(String value) {
            addCriterion("`constant_desc` like", String.format("%s%s%s", "%", value, "%"), "constantDesc");
            return this;
        }

        public GeneratedCriteria andConstantDescNotLike(String value) {
            addCriterion("`constant_desc` not like", String.format("%s%s%s", "%", value, "%"), "constantDesc");
            return this;
        }

        public GeneratedCriteria andConstantDescIn(List<String> values) {
            addCriterion("`constant_desc` in", values, "constantDesc");
            return this;
        }

        public GeneratedCriteria andConstantDescNotIn(List<String> values) {
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

        public GeneratedCriteria andStateIsNull() {
            addCriterion("`state` is null");
            return this;
        }

        public GeneratedCriteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return this;
        }

        public GeneratedCriteria andStateEqualTo(Integer value) {
            addCriterion("`state` =", value, "state");
            return this;
        }

        public GeneratedCriteria andStateNotEqualTo(Integer value) {
            addCriterion("`state` <>", value, "state");
            return this;
        }

        public GeneratedCriteria andStateGreaterThan(Integer value) {
            addCriterion("`state` >", value, "state");
            return this;
        }

        public GeneratedCriteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("`state` >=", value, "state");
            return this;
        }

        public GeneratedCriteria andStateLessThan(Integer value) {
            addCriterion("`state` <", value, "state");
            return this;
        }

        public GeneratedCriteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("`state` <=", value, "state");
            return this;
        }

        public GeneratedCriteria andStateLike(Integer value) {
            addCriterion("`state` like", String.format("%s%s%s", "%", value, "%"), "state");
            return this;
        }

        public GeneratedCriteria andStateNotLike(Integer value) {
            addCriterion("`state` not like", String.format("%s%s%s", "%", value, "%"), "state");
            return this;
        }

        public GeneratedCriteria andStateIn(List<Integer> values) {
            addCriterion("`state` in", values, "state");
            return this;
        }

        public GeneratedCriteria andStateNotIn(List<Integer> values) {
            addCriterion("`state` not in", values, "state");
            return this;
        }

        public GeneratedCriteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("`state` between", value1, value2, "state");
            return this;
        }

        public GeneratedCriteria andStateNotBetween(Integer value1, Integer value2) {
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
            addCriterion("`lstate` =", value, "lstate");
            return this;
        }

        public GeneratedCriteria andLstateNotEqualTo(Integer value) {
            addCriterion("`lstate` <>", value, "lstate");
            return this;
        }

        public GeneratedCriteria andLstateGreaterThan(Integer value) {
            addCriterion("`lstate` >", value, "lstate");
            return this;
        }

        public GeneratedCriteria andLstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("`lstate` >=", value, "lstate");
            return this;
        }

        public GeneratedCriteria andLstateLessThan(Integer value) {
            addCriterion("`lstate` <", value, "lstate");
            return this;
        }

        public GeneratedCriteria andLstateLessThanOrEqualTo(Integer value) {
            addCriterion("`lstate` <=", value, "lstate");
            return this;
        }

        public GeneratedCriteria andLstateLike(Integer value) {
            addCriterion("`lstate` like", String.format("%s%s%s", "%", value, "%"), "lstate");
            return this;
        }

        public GeneratedCriteria andLstateNotLike(Integer value) {
            addCriterion("`lstate` not like", String.format("%s%s%s", "%", value, "%"), "lstate");
            return this;
        }

        public GeneratedCriteria andLstateIn(List<Integer> values) {
            addCriterion("`lstate` in", values, "lstate");
            return this;
        }

        public GeneratedCriteria andLstateNotIn(List<Integer> values) {
            addCriterion("`lstate` not in", values, "lstate");
            return this;
        }

        public GeneratedCriteria andLstateBetween(Integer value1, Integer value2) {
            addCriterion("`lstate` between", value1, value2, "lstate");
            return this;
        }

        public GeneratedCriteria andLstateNotBetween(Integer value1, Integer value2) {
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
            addCriterion("`create_by` =", value, "createBy");
            return this;
        }

        public GeneratedCriteria andCreateByNotEqualTo(String value) {
            addCriterion("`create_by` <>", value, "createBy");
            return this;
        }

        public GeneratedCriteria andCreateByGreaterThan(String value) {
            addCriterion("`create_by` >", value, "createBy");
            return this;
        }

        public GeneratedCriteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("`create_by` >=", value, "createBy");
            return this;
        }

        public GeneratedCriteria andCreateByLessThan(String value) {
            addCriterion("`create_by` <", value, "createBy");
            return this;
        }

        public GeneratedCriteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("`create_by` <=", value, "createBy");
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
            addCriterion("`create_by` in", values, "createBy");
            return this;
        }

        public GeneratedCriteria andCreateByNotIn(List<String> values) {
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
            addCriterion("`create_time` =", value, "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeNotEqualTo(String value) {
            addCriterion("`create_time` <>", value, "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeGreaterThan(Date value) {
            addCriterion("`create_time` >", value, "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`create_time` >=", value, "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeLessThan(Date value) {
            addCriterion("`create_time` <", value, "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("`create_time` <=", value, "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeLike(Date value) {
            addCriterion("`create_time` like", String.format("%s%s%s", "%", value, "%"), "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeNotLike(Date value) {
            addCriterion("`create_time` not like", String.format("%s%s%s", "%", value, "%"), "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeIn(List<Date> values) {
            addCriterion("`create_time` in", values, "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("`create_time` not in", values, "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("`create_time` between", value1, value2, "createTime");
            return this;
        }

        public GeneratedCriteria andCreateTimeNotBetween(Date value1, Date value2) {
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
            addCriterion("`modify_by` =", value, "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByNotEqualTo(String value) {
            addCriterion("`modify_by` <>", value, "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByGreaterThan(String value) {
            addCriterion("`modify_by` >", value, "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByGreaterThanOrEqualTo(String value) {
            addCriterion("`modify_by` >=", value, "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByLessThan(String value) {
            addCriterion("`modify_by` <", value, "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByLessThanOrEqualTo(String value) {
            addCriterion("`modify_by` <=", value, "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`modify_by` like", String.format("%s%s%s", "%", value, "%"), "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`modify_by` not like", String.format("%s%s%s", "%", value, "%"), "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByIn(List<String> values) {
            addCriterion("`modify_by` in", values, "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByNotIn(List<String> values) {
            addCriterion("`modify_by` not in", values, "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByBetween(String value1, String value2) {
            addCriterion("`modify_by` between", value1, value2, "updateBy");
            return this;
        }

        public GeneratedCriteria andModifyByNotBetween(String value1, String value2) {
            addCriterion("`modify_by` not between", value1, value2, "updateBy");
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
            addCriterion("`modify_time` =", value, "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("`modify_time` <>", value, "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeGreaterThan(Date value) {
            addCriterion("`modify_time` >", value, "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`modify_time` >=", value, "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeLessThan(Date value) {
            addCriterion("`modify_time` <", value, "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("`modify_time` <=", value, "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeLike(Date value) {
            addCriterion("`modify_time` like", String.format("%s%s%s", "%", value, "%"), "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeNotLike(Date value) {
            addCriterion("`modify_time` not like", String.format("%s%s%s", "%", value, "%"), "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeIn(List<Date> values) {
            addCriterion("`modify_time` in", values, "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("`modify_time` not in", values, "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("`modify_time` between", value1, value2, "updateTime");
            return this;
        }

        public GeneratedCriteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("`modify_time` not between", value1, value2, "updateTime");
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
            addCriterion("`sign` =", value, "sign");
            return this;
        }

        public GeneratedCriteria andSignNotEqualTo(String value) {
            addCriterion("`sign` <>", value, "sign");
            return this;
        }

        public GeneratedCriteria andSignGreaterThan(String value) {
            addCriterion("`sign` >", value, "sign");
            return this;
        }

        public GeneratedCriteria andSignGreaterThanOrEqualTo(String value) {
            addCriterion("`sign` >=", value, "sign");
            return this;
        }

        public GeneratedCriteria andSignLessThan(String value) {
            addCriterion("`sign` <", value, "sign");
            return this;
        }

        public GeneratedCriteria andSignLessThanOrEqualTo(String value) {
            addCriterion("`sign` <=", value, "sign");
            return this;
        }

        public GeneratedCriteria andSignLike(String value) {
            addCriterion("`sign` like", String.format("%s%s%s", "%", value, "%"), "sign");
            return this;
        }

        public GeneratedCriteria andSignNotLike(String value) {
            addCriterion("`sign` not like", String.format("%s%s%s", "%", value, "%"), "sign");
            return this;
        }

        public GeneratedCriteria andSignIn(List<String> values) {
            addCriterion("`sign` in", values, "sign");
            return this;
        }

        public GeneratedCriteria andSignNotIn(List<String> values) {
            addCriterion("`sign` not in", values, "sign");
            return this;
        }

        public GeneratedCriteria andSignBetween(String value1, String value2) {
            addCriterion("`sign` between", value1, value2, "sign");
            return this;
        }

        public GeneratedCriteria andSignNotBetween(String value1, String value2) {
            addCriterion("`sign` not between", value1, value2, "sign");
            return this;
        }
    }
}
