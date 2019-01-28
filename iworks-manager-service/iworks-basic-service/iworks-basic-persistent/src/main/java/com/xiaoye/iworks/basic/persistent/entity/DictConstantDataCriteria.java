package com.xiaoye.iworks.basic.persistent.entity;
import com.google.common.collect.Lists;
import com.xiaoye.iworks.persistent.entity.Condition;
import com.xiaoye.iworks.persistent.entity.Entity;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.StringUtils;
import java.util.Date;
import java.util.List;

/**
 * 功能描述: 实体类查询条件【字典分类数据】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
public class DictConstantDataCriteria extends Condition {
    @Override
    public Class<? extends Entity> geEntityClass() {
        return DictConstantDataDO.class;
    }
    public DictConstantDataCriteria() {
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
        public GeneratedCriteria andDictCodeIsNull() {
            addCriterion("`dict_code` is null");
            return this;
        }
        public GeneratedCriteria andDictCodeIsNotNull() {
            addCriterion("`dict_code` is not null");
            return this;
        }
        public GeneratedCriteria andDictCodeEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_code` =", value, "dictCode");
            return this;
        }
        public GeneratedCriteria andDictCodeNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_code` <>", value, "dictCode");
            return this;
        }
        public GeneratedCriteria andDictCodeLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_code` like", String.format("%s%s%s", "%", value, "%"), "dictCode");
            return this;
        }
        public GeneratedCriteria andDictCodeNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_code` not like", String.format("%s%s%s", "%", value, "%"), "dictCode");
            return this;
        }
        public GeneratedCriteria andDictCodeIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`dict_code` in", values, "dictCode");
            return this;
        }
        public GeneratedCriteria andDictCodeNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`dict_code` not in", values, "dictCode");
            return this;
        }
        public GeneratedCriteria andDictCodeBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`dict_code` between", value1, value2, "dictCode");
            return this;
        }
        public GeneratedCriteria andDictCodeNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`dict_code` not between", value1, value2, "dictCode");
            return this;
        }
        public GeneratedCriteria andDictKeyIsNull() {
            addCriterion("`dict_key` is null");
            return this;
        }
        public GeneratedCriteria andDictKeyIsNotNull() {
            addCriterion("`dict_key` is not null");
            return this;
        }
        public GeneratedCriteria andDictKeyEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_key` =", value, "dictKey");
            return this;
        }
        public GeneratedCriteria andDictKeyNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_key` <>", value, "dictKey");
            return this;
        }
        public GeneratedCriteria andDictKeyLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_key` like", String.format("%s%s%s", "%", value, "%"), "dictKey");
            return this;
        }
        public GeneratedCriteria andDictKeyNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_key` not like", String.format("%s%s%s", "%", value, "%"), "dictKey");
            return this;
        }
        public GeneratedCriteria andDictKeyIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`dict_key` in", values, "dictKey");
            return this;
        }
        public GeneratedCriteria andDictKeyNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`dict_key` not in", values, "dictKey");
            return this;
        }
        public GeneratedCriteria andDictKeyBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`dict_key` between", value1, value2, "dictKey");
            return this;
        }
        public GeneratedCriteria andDictKeyNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`dict_key` not between", value1, value2, "dictKey");
            return this;
        }
        public GeneratedCriteria andDictValueIsNull() {
            addCriterion("`dict_value` is null");
            return this;
        }
        public GeneratedCriteria andDictValueIsNotNull() {
            addCriterion("`dict_value` is not null");
            return this;
        }
        public GeneratedCriteria andDictValueEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_value` =", value, "dictValue");
            return this;
        }
        public GeneratedCriteria andDictValueNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_value` <>", value, "dictValue");
            return this;
        }
        public GeneratedCriteria andDictValueLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_value` like", String.format("%s%s%s", "%", value, "%"), "dictValue");
            return this;
        }
        public GeneratedCriteria andDictValueNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_value` not like", String.format("%s%s%s", "%", value, "%"), "dictValue");
            return this;
        }
        public GeneratedCriteria andDictValueIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`dict_value` in", values, "dictValue");
            return this;
        }
        public GeneratedCriteria andDictValueNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`dict_value` not in", values, "dictValue");
            return this;
        }
        public GeneratedCriteria andDictValueBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`dict_value` between", value1, value2, "dictValue");
            return this;
        }
        public GeneratedCriteria andDictValueNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`dict_value` not between", value1, value2, "dictValue");
            return this;
        }
        public GeneratedCriteria andDictOrderIsNull() {
            addCriterion("`dict_order` is null");
            return this;
        }
        public GeneratedCriteria andDictOrderIsNotNull() {
            addCriterion("`dict_order` is not null");
            return this;
        }
        public GeneratedCriteria andDictOrderEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`dict_order` =", value, "dictOrder");
            return this;
        }
        public GeneratedCriteria andDictOrderNotEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`dict_order` <>", value, "dictOrder");
            return this;
        }
        public GeneratedCriteria andDictOrderGreaterThan(Integer value) {
            if(value == null) return this;
            addCriterion("`dict_order` >", value, "dictOrder");
            return this;
        }
        public GeneratedCriteria andDictOrderGreaterThanOrEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`dict_order` >=", value, "dictOrder");
            return this;
        }
        public GeneratedCriteria andDictOrderLessThan(Integer value) {
            if(value == null) return this;
            addCriterion("`dict_order` <", value, "dictOrder");
            return this;
        }
        public GeneratedCriteria andDictOrderLessThanOrEqualTo(Integer value) {
            if(value == null) return this;
            addCriterion("`dict_order` <=", value, "dictOrder");
            return this;
        }
        public GeneratedCriteria andDictOrderIn(List<Integer> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`dict_order` in", values, "dictOrder");
            return this;
        }
        public GeneratedCriteria andDictOrderNotIn(List<Integer> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`dict_order` not in", values, "dictOrder");
            return this;
        }
        public GeneratedCriteria andDictOrderBetween(Integer value1, Integer value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`dict_order` between", value1, value2, "dictOrder");
            return this;
        }
        public GeneratedCriteria andDictOrderNotBetween(Integer value1, Integer value2) {
            if(value1 == null || value2 == null) return this;
            addCriterion("`dict_order` not between", value1, value2, "dictOrder");
            return this;
        }
        public GeneratedCriteria andDictDescIsNull() {
            addCriterion("`dict_desc` is null");
            return this;
        }
        public GeneratedCriteria andDictDescIsNotNull() {
            addCriterion("`dict_desc` is not null");
            return this;
        }
        public GeneratedCriteria andDictDescEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_desc` =", value, "dictDesc");
            return this;
        }
        public GeneratedCriteria andDictDescNotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_desc` <>", value, "dictDesc");
            return this;
        }
        public GeneratedCriteria andDictDescLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_desc` like", String.format("%s%s%s", "%", value, "%"), "dictDesc");
            return this;
        }
        public GeneratedCriteria andDictDescNotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`dict_desc` not like", String.format("%s%s%s", "%", value, "%"), "dictDesc");
            return this;
        }
        public GeneratedCriteria andDictDescIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`dict_desc` in", values, "dictDesc");
            return this;
        }
        public GeneratedCriteria andDictDescNotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`dict_desc` not in", values, "dictDesc");
            return this;
        }
        public GeneratedCriteria andDictDescBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`dict_desc` between", value1, value2, "dictDesc");
            return this;
        }
        public GeneratedCriteria andDictDescNotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`dict_desc` not between", value1, value2, "dictDesc");
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