package com.tiaotiaopoker.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysHelpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysHelpExample() {
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

        public Criteria andHelpIdIsNull() {
            addCriterion("HELP_ID is null");
            return (Criteria) this;
        }

        public Criteria andHelpIdIsNotNull() {
            addCriterion("HELP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHelpIdEqualTo(String value) {
            addCriterion("HELP_ID =", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdNotEqualTo(String value) {
            addCriterion("HELP_ID <>", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdGreaterThan(String value) {
            addCriterion("HELP_ID >", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdGreaterThanOrEqualTo(String value) {
            addCriterion("HELP_ID >=", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdLessThan(String value) {
            addCriterion("HELP_ID <", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdLessThanOrEqualTo(String value) {
            addCriterion("HELP_ID <=", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdLike(String value) {
            addCriterion("HELP_ID like", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdNotLike(String value) {
            addCriterion("HELP_ID not like", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdIn(List<String> values) {
            addCriterion("HELP_ID in", values, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdNotIn(List<String> values) {
            addCriterion("HELP_ID not in", values, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdBetween(String value1, String value2) {
            addCriterion("HELP_ID between", value1, value2, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdNotBetween(String value1, String value2) {
            addCriterion("HELP_ID not between", value1, value2, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpTitleIsNull() {
            addCriterion("HELP_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andHelpTitleIsNotNull() {
            addCriterion("HELP_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andHelpTitleEqualTo(String value) {
            addCriterion("HELP_TITLE =", value, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleNotEqualTo(String value) {
            addCriterion("HELP_TITLE <>", value, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleGreaterThan(String value) {
            addCriterion("HELP_TITLE >", value, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleGreaterThanOrEqualTo(String value) {
            addCriterion("HELP_TITLE >=", value, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleLessThan(String value) {
            addCriterion("HELP_TITLE <", value, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleLessThanOrEqualTo(String value) {
            addCriterion("HELP_TITLE <=", value, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleLike(String value) {
            addCriterion("HELP_TITLE like", value, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleNotLike(String value) {
            addCriterion("HELP_TITLE not like", value, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleIn(List<String> values) {
            addCriterion("HELP_TITLE in", values, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleNotIn(List<String> values) {
            addCriterion("HELP_TITLE not in", values, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleBetween(String value1, String value2) {
            addCriterion("HELP_TITLE between", value1, value2, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpTitleNotBetween(String value1, String value2) {
            addCriterion("HELP_TITLE not between", value1, value2, "helpTitle");
            return (Criteria) this;
        }

        public Criteria andHelpStatusIsNull() {
            addCriterion("HELP_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andHelpStatusIsNotNull() {
            addCriterion("HELP_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andHelpStatusEqualTo(Integer value) {
            addCriterion("HELP_STATUS =", value, "helpStatus");
            return (Criteria) this;
        }

        public Criteria andHelpStatusNotEqualTo(Integer value) {
            addCriterion("HELP_STATUS <>", value, "helpStatus");
            return (Criteria) this;
        }

        public Criteria andHelpStatusGreaterThan(Integer value) {
            addCriterion("HELP_STATUS >", value, "helpStatus");
            return (Criteria) this;
        }

        public Criteria andHelpStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("HELP_STATUS >=", value, "helpStatus");
            return (Criteria) this;
        }

        public Criteria andHelpStatusLessThan(Integer value) {
            addCriterion("HELP_STATUS <", value, "helpStatus");
            return (Criteria) this;
        }

        public Criteria andHelpStatusLessThanOrEqualTo(Integer value) {
            addCriterion("HELP_STATUS <=", value, "helpStatus");
            return (Criteria) this;
        }

        public Criteria andHelpStatusIn(List<Integer> values) {
            addCriterion("HELP_STATUS in", values, "helpStatus");
            return (Criteria) this;
        }

        public Criteria andHelpStatusNotIn(List<Integer> values) {
            addCriterion("HELP_STATUS not in", values, "helpStatus");
            return (Criteria) this;
        }

        public Criteria andHelpStatusBetween(Integer value1, Integer value2) {
            addCriterion("HELP_STATUS between", value1, value2, "helpStatus");
            return (Criteria) this;
        }

        public Criteria andHelpStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("HELP_STATUS not between", value1, value2, "helpStatus");
            return (Criteria) this;
        }

        public Criteria andHelpColorIsNull() {
            addCriterion("HELP_COLOR is null");
            return (Criteria) this;
        }

        public Criteria andHelpColorIsNotNull() {
            addCriterion("HELP_COLOR is not null");
            return (Criteria) this;
        }

        public Criteria andHelpColorEqualTo(String value) {
            addCriterion("HELP_COLOR =", value, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorNotEqualTo(String value) {
            addCriterion("HELP_COLOR <>", value, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorGreaterThan(String value) {
            addCriterion("HELP_COLOR >", value, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorGreaterThanOrEqualTo(String value) {
            addCriterion("HELP_COLOR >=", value, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorLessThan(String value) {
            addCriterion("HELP_COLOR <", value, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorLessThanOrEqualTo(String value) {
            addCriterion("HELP_COLOR <=", value, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorLike(String value) {
            addCriterion("HELP_COLOR like", value, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorNotLike(String value) {
            addCriterion("HELP_COLOR not like", value, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorIn(List<String> values) {
            addCriterion("HELP_COLOR in", values, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorNotIn(List<String> values) {
            addCriterion("HELP_COLOR not in", values, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorBetween(String value1, String value2) {
            addCriterion("HELP_COLOR between", value1, value2, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpColorNotBetween(String value1, String value2) {
            addCriterion("HELP_COLOR not between", value1, value2, "helpColor");
            return (Criteria) this;
        }

        public Criteria andHelpSortIsNull() {
            addCriterion("HELP_SORT is null");
            return (Criteria) this;
        }

        public Criteria andHelpSortIsNotNull() {
            addCriterion("HELP_SORT is not null");
            return (Criteria) this;
        }

        public Criteria andHelpSortEqualTo(Integer value) {
            addCriterion("HELP_SORT =", value, "helpSort");
            return (Criteria) this;
        }

        public Criteria andHelpSortNotEqualTo(Integer value) {
            addCriterion("HELP_SORT <>", value, "helpSort");
            return (Criteria) this;
        }

        public Criteria andHelpSortGreaterThan(Integer value) {
            addCriterion("HELP_SORT >", value, "helpSort");
            return (Criteria) this;
        }

        public Criteria andHelpSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("HELP_SORT >=", value, "helpSort");
            return (Criteria) this;
        }

        public Criteria andHelpSortLessThan(Integer value) {
            addCriterion("HELP_SORT <", value, "helpSort");
            return (Criteria) this;
        }

        public Criteria andHelpSortLessThanOrEqualTo(Integer value) {
            addCriterion("HELP_SORT <=", value, "helpSort");
            return (Criteria) this;
        }

        public Criteria andHelpSortIn(List<Integer> values) {
            addCriterion("HELP_SORT in", values, "helpSort");
            return (Criteria) this;
        }

        public Criteria andHelpSortNotIn(List<Integer> values) {
            addCriterion("HELP_SORT not in", values, "helpSort");
            return (Criteria) this;
        }

        public Criteria andHelpSortBetween(Integer value1, Integer value2) {
            addCriterion("HELP_SORT between", value1, value2, "helpSort");
            return (Criteria) this;
        }

        public Criteria andHelpSortNotBetween(Integer value1, Integer value2) {
            addCriterion("HELP_SORT not between", value1, value2, "helpSort");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeIsNull() {
            addCriterion("HELP_CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeIsNotNull() {
            addCriterion("HELP_CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeEqualTo(Date value) {
            addCriterion("HELP_CREATE_TIME =", value, "helpCreateTime");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeNotEqualTo(Date value) {
            addCriterion("HELP_CREATE_TIME <>", value, "helpCreateTime");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeGreaterThan(Date value) {
            addCriterion("HELP_CREATE_TIME >", value, "helpCreateTime");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("HELP_CREATE_TIME >=", value, "helpCreateTime");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeLessThan(Date value) {
            addCriterion("HELP_CREATE_TIME <", value, "helpCreateTime");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("HELP_CREATE_TIME <=", value, "helpCreateTime");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeIn(List<Date> values) {
            addCriterion("HELP_CREATE_TIME in", values, "helpCreateTime");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeNotIn(List<Date> values) {
            addCriterion("HELP_CREATE_TIME not in", values, "helpCreateTime");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeBetween(Date value1, Date value2) {
            addCriterion("HELP_CREATE_TIME between", value1, value2, "helpCreateTime");
            return (Criteria) this;
        }

        public Criteria andHelpCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("HELP_CREATE_TIME not between", value1, value2, "helpCreateTime");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserIsNull() {
            addCriterion("HELP_CREATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserIsNotNull() {
            addCriterion("HELP_CREATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserEqualTo(String value) {
            addCriterion("HELP_CREATE_USER =", value, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserNotEqualTo(String value) {
            addCriterion("HELP_CREATE_USER <>", value, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserGreaterThan(String value) {
            addCriterion("HELP_CREATE_USER >", value, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("HELP_CREATE_USER >=", value, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserLessThan(String value) {
            addCriterion("HELP_CREATE_USER <", value, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserLessThanOrEqualTo(String value) {
            addCriterion("HELP_CREATE_USER <=", value, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserLike(String value) {
            addCriterion("HELP_CREATE_USER like", value, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserNotLike(String value) {
            addCriterion("HELP_CREATE_USER not like", value, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserIn(List<String> values) {
            addCriterion("HELP_CREATE_USER in", values, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserNotIn(List<String> values) {
            addCriterion("HELP_CREATE_USER not in", values, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserBetween(String value1, String value2) {
            addCriterion("HELP_CREATE_USER between", value1, value2, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpCreateUserNotBetween(String value1, String value2) {
            addCriterion("HELP_CREATE_USER not between", value1, value2, "helpCreateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeIsNull() {
            addCriterion("HELP_UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeIsNotNull() {
            addCriterion("HELP_UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeEqualTo(Date value) {
            addCriterion("HELP_UPDATE_TIME =", value, "helpUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeNotEqualTo(Date value) {
            addCriterion("HELP_UPDATE_TIME <>", value, "helpUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeGreaterThan(Date value) {
            addCriterion("HELP_UPDATE_TIME >", value, "helpUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("HELP_UPDATE_TIME >=", value, "helpUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeLessThan(Date value) {
            addCriterion("HELP_UPDATE_TIME <", value, "helpUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("HELP_UPDATE_TIME <=", value, "helpUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeIn(List<Date> values) {
            addCriterion("HELP_UPDATE_TIME in", values, "helpUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeNotIn(List<Date> values) {
            addCriterion("HELP_UPDATE_TIME not in", values, "helpUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("HELP_UPDATE_TIME between", value1, value2, "helpUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("HELP_UPDATE_TIME not between", value1, value2, "helpUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserIsNull() {
            addCriterion("HELP_UPDATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserIsNotNull() {
            addCriterion("HELP_UPDATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserEqualTo(String value) {
            addCriterion("HELP_UPDATE_USER =", value, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserNotEqualTo(String value) {
            addCriterion("HELP_UPDATE_USER <>", value, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserGreaterThan(String value) {
            addCriterion("HELP_UPDATE_USER >", value, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("HELP_UPDATE_USER >=", value, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserLessThan(String value) {
            addCriterion("HELP_UPDATE_USER <", value, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("HELP_UPDATE_USER <=", value, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserLike(String value) {
            addCriterion("HELP_UPDATE_USER like", value, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserNotLike(String value) {
            addCriterion("HELP_UPDATE_USER not like", value, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserIn(List<String> values) {
            addCriterion("HELP_UPDATE_USER in", values, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserNotIn(List<String> values) {
            addCriterion("HELP_UPDATE_USER not in", values, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserBetween(String value1, String value2) {
            addCriterion("HELP_UPDATE_USER between", value1, value2, "helpUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHelpUpdateUserNotBetween(String value1, String value2) {
            addCriterion("HELP_UPDATE_USER not between", value1, value2, "helpUpdateUser");
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