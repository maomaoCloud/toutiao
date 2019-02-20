package com.tiaotiaopoker.pojo;

import java.util.ArrayList;
import java.util.List;

public class MatchAuthorizationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MatchAuthorizationExample() {
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

        public Criteria andMatchIdIsNull() {
            addCriterion("match_id is null");
            return (Criteria) this;
        }

        public Criteria andMatchIdIsNotNull() {
            addCriterion("match_id is not null");
            return (Criteria) this;
        }

        public Criteria andMatchIdEqualTo(String value) {
            addCriterion("match_id =", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotEqualTo(String value) {
            addCriterion("match_id <>", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdGreaterThan(String value) {
            addCriterion("match_id >", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("match_id >=", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdLessThan(String value) {
            addCriterion("match_id <", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdLessThanOrEqualTo(String value) {
            addCriterion("match_id <=", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdLike(String value) {
            addCriterion("match_id like", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotLike(String value) {
            addCriterion("match_id not like", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdIn(List<String> values) {
            addCriterion("match_id in", values, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotIn(List<String> values) {
            addCriterion("match_id not in", values, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdBetween(String value1, String value2) {
            addCriterion("match_id between", value1, value2, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotBetween(String value1, String value2) {
            addCriterion("match_id not between", value1, value2, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchNameIsNull() {
            addCriterion("match_name is null");
            return (Criteria) this;
        }

        public Criteria andMatchNameIsNotNull() {
            addCriterion("match_name is not null");
            return (Criteria) this;
        }

        public Criteria andMatchNameEqualTo(String value) {
            addCriterion("match_name =", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameNotEqualTo(String value) {
            addCriterion("match_name <>", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameGreaterThan(String value) {
            addCriterion("match_name >", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameGreaterThanOrEqualTo(String value) {
            addCriterion("match_name >=", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameLessThan(String value) {
            addCriterion("match_name <", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameLessThanOrEqualTo(String value) {
            addCriterion("match_name <=", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameLike(String value) {
            addCriterion("match_name like", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameNotLike(String value) {
            addCriterion("match_name not like", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameIn(List<String> values) {
            addCriterion("match_name in", values, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameNotIn(List<String> values) {
            addCriterion("match_name not in", values, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameBetween(String value1, String value2) {
            addCriterion("match_name between", value1, value2, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameNotBetween(String value1, String value2) {
            addCriterion("match_name not between", value1, value2, "matchName");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameIsNull() {
            addCriterion("user_true_name is null");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameIsNotNull() {
            addCriterion("user_true_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameEqualTo(String value) {
            addCriterion("user_true_name =", value, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameNotEqualTo(String value) {
            addCriterion("user_true_name <>", value, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameGreaterThan(String value) {
            addCriterion("user_true_name >", value, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_true_name >=", value, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameLessThan(String value) {
            addCriterion("user_true_name <", value, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameLessThanOrEqualTo(String value) {
            addCriterion("user_true_name <=", value, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameLike(String value) {
            addCriterion("user_true_name like", value, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameNotLike(String value) {
            addCriterion("user_true_name not like", value, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameIn(List<String> values) {
            addCriterion("user_true_name in", values, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameNotIn(List<String> values) {
            addCriterion("user_true_name not in", values, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameBetween(String value1, String value2) {
            addCriterion("user_true_name between", value1, value2, "userTrueName");
            return (Criteria) this;
        }

        public Criteria andUserTrueNameNotBetween(String value1, String value2) {
            addCriterion("user_true_name not between", value1, value2, "userTrueName");
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