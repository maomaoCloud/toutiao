package com.tiaotiaopoker.pojo;

import java.util.ArrayList;
import java.util.List;

public class MatchTeamMemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MatchTeamMemberExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTeamNumberIsNull() {
            addCriterion("team_number is null");
            return (Criteria) this;
        }

        public Criteria andTeamNumberIsNotNull() {
            addCriterion("team_number is not null");
            return (Criteria) this;
        }

        public Criteria andTeamNumberEqualTo(Integer value) {
            addCriterion("team_number =", value, "teamNumber");
            return (Criteria) this;
        }

        public Criteria andTeamNumberNotEqualTo(Integer value) {
            addCriterion("team_number <>", value, "teamNumber");
            return (Criteria) this;
        }

        public Criteria andTeamNumberGreaterThan(Integer value) {
            addCriterion("team_number >", value, "teamNumber");
            return (Criteria) this;
        }

        public Criteria andTeamNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_number >=", value, "teamNumber");
            return (Criteria) this;
        }

        public Criteria andTeamNumberLessThan(Integer value) {
            addCriterion("team_number <", value, "teamNumber");
            return (Criteria) this;
        }

        public Criteria andTeamNumberLessThanOrEqualTo(Integer value) {
            addCriterion("team_number <=", value, "teamNumber");
            return (Criteria) this;
        }

        public Criteria andTeamNumberIn(List<Integer> values) {
            addCriterion("team_number in", values, "teamNumber");
            return (Criteria) this;
        }

        public Criteria andTeamNumberNotIn(List<Integer> values) {
            addCriterion("team_number not in", values, "teamNumber");
            return (Criteria) this;
        }

        public Criteria andTeamNumberBetween(Integer value1, Integer value2) {
            addCriterion("team_number between", value1, value2, "teamNumber");
            return (Criteria) this;
        }

        public Criteria andTeamNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("team_number not between", value1, value2, "teamNumber");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneIsNull() {
            addCriterion("team_member_one is null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneIsNotNull() {
            addCriterion("team_member_one is not null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneEqualTo(String value) {
            addCriterion("team_member_one =", value, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNotEqualTo(String value) {
            addCriterion("team_member_one <>", value, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneGreaterThan(String value) {
            addCriterion("team_member_one >", value, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneGreaterThanOrEqualTo(String value) {
            addCriterion("team_member_one >=", value, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneLessThan(String value) {
            addCriterion("team_member_one <", value, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneLessThanOrEqualTo(String value) {
            addCriterion("team_member_one <=", value, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneLike(String value) {
            addCriterion("team_member_one like", value, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNotLike(String value) {
            addCriterion("team_member_one not like", value, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneIn(List<String> values) {
            addCriterion("team_member_one in", values, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNotIn(List<String> values) {
            addCriterion("team_member_one not in", values, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneBetween(String value1, String value2) {
            addCriterion("team_member_one between", value1, value2, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNotBetween(String value1, String value2) {
            addCriterion("team_member_one not between", value1, value2, "teamMemberOne");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoIsNull() {
            addCriterion("team_member_two is null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoIsNotNull() {
            addCriterion("team_member_two is not null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoEqualTo(String value) {
            addCriterion("team_member_two =", value, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNotEqualTo(String value) {
            addCriterion("team_member_two <>", value, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoGreaterThan(String value) {
            addCriterion("team_member_two >", value, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoGreaterThanOrEqualTo(String value) {
            addCriterion("team_member_two >=", value, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoLessThan(String value) {
            addCriterion("team_member_two <", value, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoLessThanOrEqualTo(String value) {
            addCriterion("team_member_two <=", value, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoLike(String value) {
            addCriterion("team_member_two like", value, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNotLike(String value) {
            addCriterion("team_member_two not like", value, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoIn(List<String> values) {
            addCriterion("team_member_two in", values, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNotIn(List<String> values) {
            addCriterion("team_member_two not in", values, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoBetween(String value1, String value2) {
            addCriterion("team_member_two between", value1, value2, "teamMemberTwo");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNotBetween(String value1, String value2) {
            addCriterion("team_member_two not between", value1, value2, "teamMemberTwo");
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