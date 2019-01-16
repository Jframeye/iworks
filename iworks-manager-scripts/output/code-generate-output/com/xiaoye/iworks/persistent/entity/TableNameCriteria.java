package com.xiaoye.iworks.persistent.entity;
import com.google.common.collect.Lists;
import com.xiaoye.iworks.persistent.entity.Condition;
import com.xiaoye.iworks.persistent.entity.Entity;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.StringUtils;
import java.util.Date;
import java.util.List;

/**
 * 功能描述: 实体类查询条件【系统常量信息】
 * @auther: auto create by python 
 * @date: 2019-01-10 13:34:22 
 */
public class TableNameCriteria extends Condition {
    @Override
    public Class<? extends Entity> geEntityClass() {
        return TableNameDO.class;
    }
    public TableNameCriteria() {
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
        public GeneratedCriteria andColumn1IsNull() {
            addCriterion("`column_1` is null");
            return this;
        }
        public GeneratedCriteria andColumn1IsNotNull() {
            addCriterion("`column_1` is not null");
            return this;
        }
        public GeneratedCriteria andColumn1EqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_1` =", value, "column1");
            return this;
        }
        public GeneratedCriteria andColumn1NotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_1` <>", value, "column1");
            return this;
        }
        public GeneratedCriteria andColumn1Like(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_1` like", String.format("%s%s%s", "%", value, "%"), "column1");
            return this;
        }
        public GeneratedCriteria andColumn1NotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_1` not like", String.format("%s%s%s", "%", value, "%"), "column1");
            return this;
        }
        public GeneratedCriteria andColumn1In(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`column_1` in", values, "column1");
            return this;
        }
        public GeneratedCriteria andColumn1NotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`column_1` not in", values, "column1");
            return this;
        }
        public GeneratedCriteria andColumn1Between(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`column_1` between", value1, value2, "column1");
            return this;
        }
        public GeneratedCriteria andColumn1NotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`column_1` not between", value1, value2, "column1");
            return this;
        }
        public GeneratedCriteria andColumn2IsNull() {
            addCriterion("`column_2` is null");
            return this;
        }
        public GeneratedCriteria andColumn2IsNotNull() {
            addCriterion("`column_2` is not null");
            return this;
        }
        public GeneratedCriteria andColumn2EqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_2` =", value, "column2");
            return this;
        }
        public GeneratedCriteria andColumn2NotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_2` <>", value, "column2");
            return this;
        }
        public GeneratedCriteria andColumn2Like(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_2` like", String.format("%s%s%s", "%", value, "%"), "column2");
            return this;
        }
        public GeneratedCriteria andColumn2NotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_2` not like", String.format("%s%s%s", "%", value, "%"), "column2");
            return this;
        }
        public GeneratedCriteria andColumn2In(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`column_2` in", values, "column2");
            return this;
        }
        public GeneratedCriteria andColumn2NotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`column_2` not in", values, "column2");
            return this;
        }
        public GeneratedCriteria andColumn2Between(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`column_2` between", value1, value2, "column2");
            return this;
        }
        public GeneratedCriteria andColumn2NotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`column_2` not between", value1, value2, "column2");
            return this;
        }
        public GeneratedCriteria andColumn3IsNull() {
            addCriterion("`column_3` is null");
            return this;
        }
        public GeneratedCriteria andColumn3IsNotNull() {
            addCriterion("`column_3` is not null");
            return this;
        }
        public GeneratedCriteria andColumn3EqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_3` =", value, "column3");
            return this;
        }
        public GeneratedCriteria andColumn3NotEqualTo(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_3` <>", value, "column3");
            return this;
        }
        public GeneratedCriteria andColumn3Like(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_3` like", String.format("%s%s%s", "%", value, "%"), "column3");
            return this;
        }
        public GeneratedCriteria andColumn3NotLike(String value) {
            if(StringUtils.isBlank(value)) return this;
            addCriterion("`column_3` not like", String.format("%s%s%s", "%", value, "%"), "column3");
            return this;
        }
        public GeneratedCriteria andColumn3In(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`column_3` in", values, "column3");
            return this;
        }
        public GeneratedCriteria andColumn3NotIn(List<String> values) {
            if(CollectionUtils.isEmpty(values)) return this;
            addCriterion("`column_3` not in", values, "column3");
            return this;
        }
        public GeneratedCriteria andColumn3Between(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`column_3` between", value1, value2, "column3");
            return this;
        }
        public GeneratedCriteria andColumn3NotBetween(String value1, String value2) {
            if(StringUtils.hasBlankString(value1, value2)) return this;
            addCriterion("`column_3` not between", value1, value2, "column3");
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