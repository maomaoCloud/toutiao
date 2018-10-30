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

        public Criteria andTeamMemberOneNameIsNull() {
            addCriterion("team_member_one_name is null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameIsNotNull() {
            addCriterion("team_member_one_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameEqualTo(String value) {
            addCriterion("team_member_one_name =", value, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameNotEqualTo(String value) {
            addCriterion("team_member_one_name <>", value, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameGreaterThan(String value) {
            addCriterion("team_member_one_name >", value, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameGreaterThanOrEqualTo(String value) {
            addCriterion("team_member_one_name >=", value, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameLessThan(String value) {
            addCriterion("team_member_one_name <", value, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameLessThanOrEqualTo(String value) {
            addCriterion("team_member_one_name <=", value, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameLike(String value) {
            addCriterion("team_member_one_name like", value, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameNotLike(String value) {
            addCriterion("team_member_one_name not like", value, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameIn(List<String> values) {
            addCriterion("team_member_one_name in", values, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameNotIn(List<String> values) {
            addCriterion("team_member_one_name not in", values, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameBetween(String value1, String value2) {
            addCriterion("team_member_one_name between", value1, value2, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneNameNotBetween(String value1, String value2) {
            addCriterion("team_member_one_name not between", value1, value2, "teamMemberOneName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadIsNull() {
            addCriterion("team_member_one_head is null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadIsNotNull() {
            addCriterion("team_member_one_head is not null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadEqualTo(String value) {
            addCriterion("team_member_one_head =", value, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadNotEqualTo(String value) {
            addCriterion("team_member_one_head <>", value, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadGreaterThan(String value) {
            addCriterion("team_member_one_head >", value, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadGreaterThanOrEqualTo(String value) {
            addCriterion("team_member_one_head >=", value, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadLessThan(String value) {
            addCriterion("team_member_one_head <", value, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadLessThanOrEqualTo(String value) {
            addCriterion("team_member_one_head <=", value, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadLike(String value) {
            addCriterion("team_member_one_head like", value, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadNotLike(String value) {
            addCriterion("team_member_one_head not like", value, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadIn(List<String> values) {
            addCriterion("team_member_one_head in", values, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadNotIn(List<String> values) {
            addCriterion("team_member_one_head not in", values, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadBetween(String value1, String value2) {
            addCriterion("team_member_one_head between", value1, value2, "teamMemberOneHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberOneHeadNotBetween(String value1, String value2) {
            addCriterion("team_member_one_head not between", value1, value2, "teamMemberOneHead");
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

        public Criteria andTeamMemberTwoNameIsNull() {
            addCriterion("team_member_two_name is null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameIsNotNull() {
            addCriterion("team_member_two_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameEqualTo(String value) {
            addCriterion("team_member_two_name =", value, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameNotEqualTo(String value) {
            addCriterion("team_member_two_name <>", value, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameGreaterThan(String value) {
            addCriterion("team_member_two_name >", value, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameGreaterThanOrEqualTo(String value) {
            addCriterion("team_member_two_name >=", value, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameLessThan(String value) {
            addCriterion("team_member_two_name <", value, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameLessThanOrEqualTo(String value) {
            addCriterion("team_member_two_name <=", value, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameLike(String value) {
            addCriterion("team_member_two_name like", value, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameNotLike(String value) {
            addCriterion("team_member_two_name not like", value, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameIn(List<String> values) {
            addCriterion("team_member_two_name in", values, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameNotIn(List<String> values) {
            addCriterion("team_member_two_name not in", values, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameBetween(String value1, String value2) {
            addCriterion("team_member_two_name between", value1, value2, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoNameNotBetween(String value1, String value2) {
            addCriterion("team_member_two_name not between", value1, value2, "teamMemberTwoName");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadIsNull() {
            addCriterion("team_member_two_head is null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadIsNotNull() {
            addCriterion("team_member_two_head is not null");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadEqualTo(String value) {
            addCriterion("team_member_two_head =", value, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadNotEqualTo(String value) {
            addCriterion("team_member_two_head <>", value, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadGreaterThan(String value) {
            addCriterion("team_member_two_head >", value, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadGreaterThanOrEqualTo(String value) {
            addCriterion("team_member_two_head >=", value, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadLessThan(String value) {
            addCriterion("team_member_two_head <", value, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadLessThanOrEqualTo(String value) {
            addCriterion("team_member_two_head <=", value, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadLike(String value) {
            addCriterion("team_member_two_head like", value, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadNotLike(String value) {
            addCriterion("team_member_two_head not like", value, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadIn(List<String> values) {
            addCriterion("team_member_two_head in", values, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadNotIn(List<String> values) {
            addCriterion("team_member_two_head not in", values, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadBetween(String value1, String value2) {
            addCriterion("team_member_two_head between", value1, value2, "teamMemberTwoHead");
            return (Criteria) this;
        }

        public Criteria andTeamMemberTwoHeadNotBetween(String value1, String value2) {
            addCriterion("team_member_two_head not between", value1, value2, "teamMemberTwoHead");
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