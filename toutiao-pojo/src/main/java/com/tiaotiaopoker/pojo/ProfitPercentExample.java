package com.tiaotiaopoker.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfitPercentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProfitPercentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andProfitIdIsNull() {
            addCriterion("profit_id is null");
            return (Criteria) this;
        }

        public Criteria andProfitIdIsNotNull() {
            addCriterion("profit_id is not null");
            return (Criteria) this;
        }

        public Criteria andProfitIdEqualTo(String value) {
            addCriterion("profit_id =", value, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdNotEqualTo(String value) {
            addCriterion("profit_id <>", value, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdGreaterThan(String value) {
            addCriterion("profit_id >", value, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdGreaterThanOrEqualTo(String value) {
            addCriterion("profit_id >=", value, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdLessThan(String value) {
            addCriterion("profit_id <", value, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdLessThanOrEqualTo(String value) {
            addCriterion("profit_id <=", value, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdLike(String value) {
            addCriterion("profit_id like", value, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdNotLike(String value) {
            addCriterion("profit_id not like", value, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdIn(List<String> values) {
            addCriterion("profit_id in", values, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdNotIn(List<String> values) {
            addCriterion("profit_id not in", values, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdBetween(String value1, String value2) {
            addCriterion("profit_id between", value1, value2, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitIdNotBetween(String value1, String value2) {
            addCriterion("profit_id not between", value1, value2, "profitId");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineIsNull() {
            addCriterion("profit_high_line is null");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineIsNotNull() {
            addCriterion("profit_high_line is not null");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineEqualTo(Integer value) {
            addCriterion("profit_high_line =", value, "profitHighLine");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineNotEqualTo(Integer value) {
            addCriterion("profit_high_line <>", value, "profitHighLine");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineGreaterThan(Integer value) {
            addCriterion("profit_high_line >", value, "profitHighLine");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("profit_high_line >=", value, "profitHighLine");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineLessThan(Integer value) {
            addCriterion("profit_high_line <", value, "profitHighLine");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineLessThanOrEqualTo(Integer value) {
            addCriterion("profit_high_line <=", value, "profitHighLine");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineIn(List<Integer> values) {
            addCriterion("profit_high_line in", values, "profitHighLine");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineNotIn(List<Integer> values) {
            addCriterion("profit_high_line not in", values, "profitHighLine");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineBetween(Integer value1, Integer value2) {
            addCriterion("profit_high_line between", value1, value2, "profitHighLine");
            return (Criteria) this;
        }

        public Criteria andProfitHighLineNotBetween(Integer value1, Integer value2) {
            addCriterion("profit_high_line not between", value1, value2, "profitHighLine");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineIsNull() {
            addCriterion("profit_down_line is null");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineIsNotNull() {
            addCriterion("profit_down_line is not null");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineEqualTo(Integer value) {
            addCriterion("profit_down_line =", value, "profitDownLine");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineNotEqualTo(Integer value) {
            addCriterion("profit_down_line <>", value, "profitDownLine");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineGreaterThan(Integer value) {
            addCriterion("profit_down_line >", value, "profitDownLine");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("profit_down_line >=", value, "profitDownLine");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineLessThan(Integer value) {
            addCriterion("profit_down_line <", value, "profitDownLine");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineLessThanOrEqualTo(Integer value) {
            addCriterion("profit_down_line <=", value, "profitDownLine");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineIn(List<Integer> values) {
            addCriterion("profit_down_line in", values, "profitDownLine");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineNotIn(List<Integer> values) {
            addCriterion("profit_down_line not in", values, "profitDownLine");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineBetween(Integer value1, Integer value2) {
            addCriterion("profit_down_line between", value1, value2, "profitDownLine");
            return (Criteria) this;
        }

        public Criteria andProfitDownLineNotBetween(Integer value1, Integer value2) {
            addCriterion("profit_down_line not between", value1, value2, "profitDownLine");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeIsNull() {
            addCriterion("profit_create_time is null");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeIsNotNull() {
            addCriterion("profit_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeEqualTo(Date value) {
            addCriterion("profit_create_time =", value, "profitCreateTime");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeNotEqualTo(Date value) {
            addCriterion("profit_create_time <>", value, "profitCreateTime");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeGreaterThan(Date value) {
            addCriterion("profit_create_time >", value, "profitCreateTime");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("profit_create_time >=", value, "profitCreateTime");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeLessThan(Date value) {
            addCriterion("profit_create_time <", value, "profitCreateTime");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("profit_create_time <=", value, "profitCreateTime");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeIn(List<Date> values) {
            addCriterion("profit_create_time in", values, "profitCreateTime");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeNotIn(List<Date> values) {
            addCriterion("profit_create_time not in", values, "profitCreateTime");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeBetween(Date value1, Date value2) {
            addCriterion("profit_create_time between", value1, value2, "profitCreateTime");
            return (Criteria) this;
        }

        public Criteria andProfitCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("profit_create_time not between", value1, value2, "profitCreateTime");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserIsNull() {
            addCriterion("profit_create_user is null");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserIsNotNull() {
            addCriterion("profit_create_user is not null");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserEqualTo(String value) {
            addCriterion("profit_create_user =", value, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserNotEqualTo(String value) {
            addCriterion("profit_create_user <>", value, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserGreaterThan(String value) {
            addCriterion("profit_create_user >", value, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("profit_create_user >=", value, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserLessThan(String value) {
            addCriterion("profit_create_user <", value, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserLessThanOrEqualTo(String value) {
            addCriterion("profit_create_user <=", value, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserLike(String value) {
            addCriterion("profit_create_user like", value, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserNotLike(String value) {
            addCriterion("profit_create_user not like", value, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserIn(List<String> values) {
            addCriterion("profit_create_user in", values, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserNotIn(List<String> values) {
            addCriterion("profit_create_user not in", values, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserBetween(String value1, String value2) {
            addCriterion("profit_create_user between", value1, value2, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitCreateUserNotBetween(String value1, String value2) {
            addCriterion("profit_create_user not between", value1, value2, "profitCreateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeIsNull() {
            addCriterion("profit_update_time is null");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeIsNotNull() {
            addCriterion("profit_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeEqualTo(Date value) {
            addCriterion("profit_update_time =", value, "profitUpdateTime");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeNotEqualTo(Date value) {
            addCriterion("profit_update_time <>", value, "profitUpdateTime");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeGreaterThan(Date value) {
            addCriterion("profit_update_time >", value, "profitUpdateTime");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("profit_update_time >=", value, "profitUpdateTime");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeLessThan(Date value) {
            addCriterion("profit_update_time <", value, "profitUpdateTime");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("profit_update_time <=", value, "profitUpdateTime");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeIn(List<Date> values) {
            addCriterion("profit_update_time in", values, "profitUpdateTime");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeNotIn(List<Date> values) {
            addCriterion("profit_update_time not in", values, "profitUpdateTime");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("profit_update_time between", value1, value2, "profitUpdateTime");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("profit_update_time not between", value1, value2, "profitUpdateTime");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserIsNull() {
            addCriterion("profit_update_user is null");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserIsNotNull() {
            addCriterion("profit_update_user is not null");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserEqualTo(String value) {
            addCriterion("profit_update_user =", value, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserNotEqualTo(String value) {
            addCriterion("profit_update_user <>", value, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserGreaterThan(String value) {
            addCriterion("profit_update_user >", value, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("profit_update_user >=", value, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserLessThan(String value) {
            addCriterion("profit_update_user <", value, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("profit_update_user <=", value, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserLike(String value) {
            addCriterion("profit_update_user like", value, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserNotLike(String value) {
            addCriterion("profit_update_user not like", value, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserIn(List<String> values) {
            addCriterion("profit_update_user in", values, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserNotIn(List<String> values) {
            addCriterion("profit_update_user not in", values, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserBetween(String value1, String value2) {
            addCriterion("profit_update_user between", value1, value2, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitUpdateUserNotBetween(String value1, String value2) {
            addCriterion("profit_update_user not between", value1, value2, "profitUpdateUser");
            return (Criteria) this;
        }

        public Criteria andProfitPercentIsNull() {
            addCriterion("profit_percent is null");
            return (Criteria) this;
        }

        public Criteria andProfitPercentIsNotNull() {
            addCriterion("profit_percent is not null");
            return (Criteria) this;
        }

        public Criteria andProfitPercentEqualTo(Integer value) {
            addCriterion("profit_percent =", value, "profitPercent");
            return (Criteria) this;
        }

        public Criteria andProfitPercentNotEqualTo(Integer value) {
            addCriterion("profit_percent <>", value, "profitPercent");
            return (Criteria) this;
        }

        public Criteria andProfitPercentGreaterThan(Integer value) {
            addCriterion("profit_percent >", value, "profitPercent");
            return (Criteria) this;
        }

        public Criteria andProfitPercentGreaterThanOrEqualTo(Integer value) {
            addCriterion("profit_percent >=", value, "profitPercent");
            return (Criteria) this;
        }

        public Criteria andProfitPercentLessThan(Integer value) {
            addCriterion("profit_percent <", value, "profitPercent");
            return (Criteria) this;
        }

        public Criteria andProfitPercentLessThanOrEqualTo(Integer value) {
            addCriterion("profit_percent <=", value, "profitPercent");
            return (Criteria) this;
        }

        public Criteria andProfitPercentIn(List<Integer> values) {
            addCriterion("profit_percent in", values, "profitPercent");
            return (Criteria) this;
        }

        public Criteria andProfitPercentNotIn(List<Integer> values) {
            addCriterion("profit_percent not in", values, "profitPercent");
            return (Criteria) this;
        }

        public Criteria andProfitPercentBetween(Integer value1, Integer value2) {
            addCriterion("profit_percent between", value1, value2, "profitPercent");
            return (Criteria) this;
        }

        public Criteria andProfitPercentNotBetween(Integer value1, Integer value2) {
            addCriterion("profit_percent not between", value1, value2, "profitPercent");
            return (Criteria) this;
        }

        public Criteria andProfitStateIsNull() {
            addCriterion("profit_state is null");
            return (Criteria) this;
        }

        public Criteria andProfitStateIsNotNull() {
            addCriterion("profit_state is not null");
            return (Criteria) this;
        }

        public Criteria andProfitStateEqualTo(Integer value) {
            addCriterion("profit_state =", value, "profitState");
            return (Criteria) this;
        }

        public Criteria andProfitStateNotEqualTo(Integer value) {
            addCriterion("profit_state <>", value, "profitState");
            return (Criteria) this;
        }

        public Criteria andProfitStateGreaterThan(Integer value) {
            addCriterion("profit_state >", value, "profitState");
            return (Criteria) this;
        }

        public Criteria andProfitStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("profit_state >=", value, "profitState");
            return (Criteria) this;
        }

        public Criteria andProfitStateLessThan(Integer value) {
            addCriterion("profit_state <", value, "profitState");
            return (Criteria) this;
        }

        public Criteria andProfitStateLessThanOrEqualTo(Integer value) {
            addCriterion("profit_state <=", value, "profitState");
            return (Criteria) this;
        }

        public Criteria andProfitStateIn(List<Integer> values) {
            addCriterion("profit_state in", values, "profitState");
            return (Criteria) this;
        }

        public Criteria andProfitStateNotIn(List<Integer> values) {
            addCriterion("profit_state not in", values, "profitState");
            return (Criteria) this;
        }

        public Criteria andProfitStateBetween(Integer value1, Integer value2) {
            addCriterion("profit_state between", value1, value2, "profitState");
            return (Criteria) this;
        }

        public Criteria andProfitStateNotBetween(Integer value1, Integer value2) {
            addCriterion("profit_state not between", value1, value2, "profitState");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}