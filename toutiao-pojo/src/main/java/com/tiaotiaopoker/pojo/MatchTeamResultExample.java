package com.tiaotiaopoker.pojo;

import java.util.ArrayList;
import java.util.List;

public class MatchTeamResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MatchTeamResultExample() {
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

        public Criteria andTeamIdIsNull() {
            addCriterion("team_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNotNull() {
            addCriterion("team_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamIdEqualTo(String value) {
            addCriterion("team_id =", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotEqualTo(String value) {
            addCriterion("team_id <>", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThan(String value) {
            addCriterion("team_id >", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThanOrEqualTo(String value) {
            addCriterion("team_id >=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThan(String value) {
            addCriterion("team_id <", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThanOrEqualTo(String value) {
            addCriterion("team_id <=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLike(String value) {
            addCriterion("team_id like", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotLike(String value) {
            addCriterion("team_id not like", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdIn(List<String> values) {
            addCriterion("team_id in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotIn(List<String> values) {
            addCriterion("team_id not in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdBetween(String value1, String value2) {
            addCriterion("team_id between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotBetween(String value1, String value2) {
            addCriterion("team_id not between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTurnNumberIsNull() {
            addCriterion("turn_number is null");
            return (Criteria) this;
        }

        public Criteria andTurnNumberIsNotNull() {
            addCriterion("turn_number is not null");
            return (Criteria) this;
        }

        public Criteria andTurnNumberEqualTo(Integer value) {
            addCriterion("turn_number =", value, "turnNumber");
            return (Criteria) this;
        }

        public Criteria andTurnNumberNotEqualTo(Integer value) {
            addCriterion("turn_number <>", value, "turnNumber");
            return (Criteria) this;
        }

        public Criteria andTurnNumberGreaterThan(Integer value) {
            addCriterion("turn_number >", value, "turnNumber");
            return (Criteria) this;
        }

        public Criteria andTurnNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("turn_number >=", value, "turnNumber");
            return (Criteria) this;
        }

        public Criteria andTurnNumberLessThan(Integer value) {
            addCriterion("turn_number <", value, "turnNumber");
            return (Criteria) this;
        }

        public Criteria andTurnNumberLessThanOrEqualTo(Integer value) {
            addCriterion("turn_number <=", value, "turnNumber");
            return (Criteria) this;
        }

        public Criteria andTurnNumberIn(List<Integer> values) {
            addCriterion("turn_number in", values, "turnNumber");
            return (Criteria) this;
        }

        public Criteria andTurnNumberNotIn(List<Integer> values) {
            addCriterion("turn_number not in", values, "turnNumber");
            return (Criteria) this;
        }

        public Criteria andTurnNumberBetween(Integer value1, Integer value2) {
            addCriterion("turn_number between", value1, value2, "turnNumber");
            return (Criteria) this;
        }

        public Criteria andTurnNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("turn_number not between", value1, value2, "turnNumber");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(String value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(String value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(String value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(String value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(String value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(String value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLike(String value) {
            addCriterion("score like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotLike(String value) {
            addCriterion("score not like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<String> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<String> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(String value1, String value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(String value1, String value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andPointIsNull() {
            addCriterion("point is null");
            return (Criteria) this;
        }

        public Criteria andPointIsNotNull() {
            addCriterion("point is not null");
            return (Criteria) this;
        }

        public Criteria andPointEqualTo(Integer value) {
            addCriterion("point =", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotEqualTo(Integer value) {
            addCriterion("point <>", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThan(Integer value) {
            addCriterion("point >", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("point >=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThan(Integer value) {
            addCriterion("point <", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThanOrEqualTo(Integer value) {
            addCriterion("point <=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointIn(List<Integer> values) {
            addCriterion("point in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotIn(List<Integer> values) {
            addCriterion("point not in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointBetween(Integer value1, Integer value2) {
            addCriterion("point between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotBetween(Integer value1, Integer value2) {
            addCriterion("point not between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andDisparityIsNull() {
            addCriterion("disparity is null");
            return (Criteria) this;
        }

        public Criteria andDisparityIsNotNull() {
            addCriterion("disparity is not null");
            return (Criteria) this;
        }

        public Criteria andDisparityEqualTo(Integer value) {
            addCriterion("disparity =", value, "disparity");
            return (Criteria) this;
        }

        public Criteria andDisparityNotEqualTo(Integer value) {
            addCriterion("disparity <>", value, "disparity");
            return (Criteria) this;
        }

        public Criteria andDisparityGreaterThan(Integer value) {
            addCriterion("disparity >", value, "disparity");
            return (Criteria) this;
        }

        public Criteria andDisparityGreaterThanOrEqualTo(Integer value) {
            addCriterion("disparity >=", value, "disparity");
            return (Criteria) this;
        }

        public Criteria andDisparityLessThan(Integer value) {
            addCriterion("disparity <", value, "disparity");
            return (Criteria) this;
        }

        public Criteria andDisparityLessThanOrEqualTo(Integer value) {
            addCriterion("disparity <=", value, "disparity");
            return (Criteria) this;
        }

        public Criteria andDisparityIn(List<Integer> values) {
            addCriterion("disparity in", values, "disparity");
            return (Criteria) this;
        }

        public Criteria andDisparityNotIn(List<Integer> values) {
            addCriterion("disparity not in", values, "disparity");
            return (Criteria) this;
        }

        public Criteria andDisparityBetween(Integer value1, Integer value2) {
            addCriterion("disparity between", value1, value2, "disparity");
            return (Criteria) this;
        }

        public Criteria andDisparityNotBetween(Integer value1, Integer value2) {
            addCriterion("disparity not between", value1, value2, "disparity");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstIsNull() {
            addCriterion("total_exfirst is null");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstIsNotNull() {
            addCriterion("total_exfirst is not null");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstEqualTo(Integer value) {
            addCriterion("total_exfirst =", value, "totalExfirst");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstNotEqualTo(Integer value) {
            addCriterion("total_exfirst <>", value, "totalExfirst");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstGreaterThan(Integer value) {
            addCriterion("total_exfirst >", value, "totalExfirst");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_exfirst >=", value, "totalExfirst");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstLessThan(Integer value) {
            addCriterion("total_exfirst <", value, "totalExfirst");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstLessThanOrEqualTo(Integer value) {
            addCriterion("total_exfirst <=", value, "totalExfirst");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstIn(List<Integer> values) {
            addCriterion("total_exfirst in", values, "totalExfirst");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstNotIn(List<Integer> values) {
            addCriterion("total_exfirst not in", values, "totalExfirst");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstBetween(Integer value1, Integer value2) {
            addCriterion("total_exfirst between", value1, value2, "totalExfirst");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstNotBetween(Integer value1, Integer value2) {
            addCriterion("total_exfirst not between", value1, value2, "totalExfirst");
            return (Criteria) this;
        }

        public Criteria andTotalPointIsNull() {
            addCriterion("total_point is null");
            return (Criteria) this;
        }

        public Criteria andTotalPointIsNotNull() {
            addCriterion("total_point is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPointEqualTo(Integer value) {
            addCriterion("total_point =", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointNotEqualTo(Integer value) {
            addCriterion("total_point <>", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointGreaterThan(Integer value) {
            addCriterion("total_point >", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_point >=", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointLessThan(Integer value) {
            addCriterion("total_point <", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointLessThanOrEqualTo(Integer value) {
            addCriterion("total_point <=", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointIn(List<Integer> values) {
            addCriterion("total_point in", values, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointNotIn(List<Integer> values) {
            addCriterion("total_point not in", values, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointBetween(Integer value1, Integer value2) {
            addCriterion("total_point between", value1, value2, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointNotBetween(Integer value1, Integer value2) {
            addCriterion("total_point not between", value1, value2, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllIsNull() {
            addCriterion("total_exfirst_all is null");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllIsNotNull() {
            addCriterion("total_exfirst_all is not null");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllEqualTo(Integer value) {
            addCriterion("total_exfirst_all =", value, "totalExfirstAll");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllNotEqualTo(Integer value) {
            addCriterion("total_exfirst_all <>", value, "totalExfirstAll");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllGreaterThan(Integer value) {
            addCriterion("total_exfirst_all >", value, "totalExfirstAll");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_exfirst_all >=", value, "totalExfirstAll");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllLessThan(Integer value) {
            addCriterion("total_exfirst_all <", value, "totalExfirstAll");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllLessThanOrEqualTo(Integer value) {
            addCriterion("total_exfirst_all <=", value, "totalExfirstAll");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllIn(List<Integer> values) {
            addCriterion("total_exfirst_all in", values, "totalExfirstAll");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllNotIn(List<Integer> values) {
            addCriterion("total_exfirst_all not in", values, "totalExfirstAll");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllBetween(Integer value1, Integer value2) {
            addCriterion("total_exfirst_all between", value1, value2, "totalExfirstAll");
            return (Criteria) this;
        }

        public Criteria andTotalExfirstAllNotBetween(Integer value1, Integer value2) {
            addCriterion("total_exfirst_all not between", value1, value2, "totalExfirstAll");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityIsNull() {
            addCriterion("total_disparity is null");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityIsNotNull() {
            addCriterion("total_disparity is not null");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityEqualTo(Integer value) {
            addCriterion("total_disparity =", value, "totalDisparity");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityNotEqualTo(Integer value) {
            addCriterion("total_disparity <>", value, "totalDisparity");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityGreaterThan(Integer value) {
            addCriterion("total_disparity >", value, "totalDisparity");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_disparity >=", value, "totalDisparity");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityLessThan(Integer value) {
            addCriterion("total_disparity <", value, "totalDisparity");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityLessThanOrEqualTo(Integer value) {
            addCriterion("total_disparity <=", value, "totalDisparity");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityIn(List<Integer> values) {
            addCriterion("total_disparity in", values, "totalDisparity");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityNotIn(List<Integer> values) {
            addCriterion("total_disparity not in", values, "totalDisparity");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityBetween(Integer value1, Integer value2) {
            addCriterion("total_disparity between", value1, value2, "totalDisparity");
            return (Criteria) this;
        }

        public Criteria andTotalDisparityNotBetween(Integer value1, Integer value2) {
            addCriterion("total_disparity not between", value1, value2, "totalDisparity");
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