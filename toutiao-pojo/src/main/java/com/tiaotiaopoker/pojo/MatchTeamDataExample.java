package com.tiaotiaopoker.pojo;

import java.util.ArrayList;
import java.util.List;

public class MatchTeamDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MatchTeamDataExample() {
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

        public Criteria andTableNumberIsNull() {
            addCriterion("table_number is null");
            return (Criteria) this;
        }

        public Criteria andTableNumberIsNotNull() {
            addCriterion("table_number is not null");
            return (Criteria) this;
        }

        public Criteria andTableNumberEqualTo(Integer value) {
            addCriterion("table_number =", value, "tableNumber");
            return (Criteria) this;
        }

        public Criteria andTableNumberNotEqualTo(Integer value) {
            addCriterion("table_number <>", value, "tableNumber");
            return (Criteria) this;
        }

        public Criteria andTableNumberGreaterThan(Integer value) {
            addCriterion("table_number >", value, "tableNumber");
            return (Criteria) this;
        }

        public Criteria andTableNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("table_number >=", value, "tableNumber");
            return (Criteria) this;
        }

        public Criteria andTableNumberLessThan(Integer value) {
            addCriterion("table_number <", value, "tableNumber");
            return (Criteria) this;
        }

        public Criteria andTableNumberLessThanOrEqualTo(Integer value) {
            addCriterion("table_number <=", value, "tableNumber");
            return (Criteria) this;
        }

        public Criteria andTableNumberIn(List<Integer> values) {
            addCriterion("table_number in", values, "tableNumber");
            return (Criteria) this;
        }

        public Criteria andTableNumberNotIn(List<Integer> values) {
            addCriterion("table_number not in", values, "tableNumber");
            return (Criteria) this;
        }

        public Criteria andTableNumberBetween(Integer value1, Integer value2) {
            addCriterion("table_number between", value1, value2, "tableNumber");
            return (Criteria) this;
        }

        public Criteria andTableNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("table_number not between", value1, value2, "tableNumber");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdIsNull() {
            addCriterion("team_one_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdIsNotNull() {
            addCriterion("team_one_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdEqualTo(String value) {
            addCriterion("team_one_id =", value, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdNotEqualTo(String value) {
            addCriterion("team_one_id <>", value, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdGreaterThan(String value) {
            addCriterion("team_one_id >", value, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdGreaterThanOrEqualTo(String value) {
            addCriterion("team_one_id >=", value, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdLessThan(String value) {
            addCriterion("team_one_id <", value, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdLessThanOrEqualTo(String value) {
            addCriterion("team_one_id <=", value, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdLike(String value) {
            addCriterion("team_one_id like", value, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdNotLike(String value) {
            addCriterion("team_one_id not like", value, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdIn(List<String> values) {
            addCriterion("team_one_id in", values, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdNotIn(List<String> values) {
            addCriterion("team_one_id not in", values, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdBetween(String value1, String value2) {
            addCriterion("team_one_id between", value1, value2, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneIdNotBetween(String value1, String value2) {
            addCriterion("team_one_id not between", value1, value2, "teamOneId");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreIsNull() {
            addCriterion("team_one_score is null");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreIsNotNull() {
            addCriterion("team_one_score is not null");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreEqualTo(String value) {
            addCriterion("team_one_score =", value, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreNotEqualTo(String value) {
            addCriterion("team_one_score <>", value, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreGreaterThan(String value) {
            addCriterion("team_one_score >", value, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreGreaterThanOrEqualTo(String value) {
            addCriterion("team_one_score >=", value, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreLessThan(String value) {
            addCriterion("team_one_score <", value, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreLessThanOrEqualTo(String value) {
            addCriterion("team_one_score <=", value, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreLike(String value) {
            addCriterion("team_one_score like", value, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreNotLike(String value) {
            addCriterion("team_one_score not like", value, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreIn(List<String> values) {
            addCriterion("team_one_score in", values, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreNotIn(List<String> values) {
            addCriterion("team_one_score not in", values, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreBetween(String value1, String value2) {
            addCriterion("team_one_score between", value1, value2, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOneScoreNotBetween(String value1, String value2) {
            addCriterion("team_one_score not between", value1, value2, "teamOneScore");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointIsNull() {
            addCriterion("team_one_point is null");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointIsNotNull() {
            addCriterion("team_one_point is not null");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointEqualTo(Integer value) {
            addCriterion("team_one_point =", value, "teamOnePoint");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointNotEqualTo(Integer value) {
            addCriterion("team_one_point <>", value, "teamOnePoint");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointGreaterThan(Integer value) {
            addCriterion("team_one_point >", value, "teamOnePoint");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_one_point >=", value, "teamOnePoint");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointLessThan(Integer value) {
            addCriterion("team_one_point <", value, "teamOnePoint");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointLessThanOrEqualTo(Integer value) {
            addCriterion("team_one_point <=", value, "teamOnePoint");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointIn(List<Integer> values) {
            addCriterion("team_one_point in", values, "teamOnePoint");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointNotIn(List<Integer> values) {
            addCriterion("team_one_point not in", values, "teamOnePoint");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointBetween(Integer value1, Integer value2) {
            addCriterion("team_one_point between", value1, value2, "teamOnePoint");
            return (Criteria) this;
        }

        public Criteria andTeamOnePointNotBetween(Integer value1, Integer value2) {
            addCriterion("team_one_point not between", value1, value2, "teamOnePoint");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityIsNull() {
            addCriterion("team_one_disparity is null");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityIsNotNull() {
            addCriterion("team_one_disparity is not null");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityEqualTo(Integer value) {
            addCriterion("team_one_disparity =", value, "teamOneDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityNotEqualTo(Integer value) {
            addCriterion("team_one_disparity <>", value, "teamOneDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityGreaterThan(Integer value) {
            addCriterion("team_one_disparity >", value, "teamOneDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_one_disparity >=", value, "teamOneDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityLessThan(Integer value) {
            addCriterion("team_one_disparity <", value, "teamOneDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityLessThanOrEqualTo(Integer value) {
            addCriterion("team_one_disparity <=", value, "teamOneDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityIn(List<Integer> values) {
            addCriterion("team_one_disparity in", values, "teamOneDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityNotIn(List<Integer> values) {
            addCriterion("team_one_disparity not in", values, "teamOneDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityBetween(Integer value1, Integer value2) {
            addCriterion("team_one_disparity between", value1, value2, "teamOneDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamOneDisparityNotBetween(Integer value1, Integer value2) {
            addCriterion("team_one_disparity not between", value1, value2, "teamOneDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstIsNull() {
            addCriterion("team_one_total_exfirst is null");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstIsNotNull() {
            addCriterion("team_one_total_exfirst is not null");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstEqualTo(Integer value) {
            addCriterion("team_one_total_exfirst =", value, "teamOneTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstNotEqualTo(Integer value) {
            addCriterion("team_one_total_exfirst <>", value, "teamOneTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstGreaterThan(Integer value) {
            addCriterion("team_one_total_exfirst >", value, "teamOneTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_one_total_exfirst >=", value, "teamOneTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstLessThan(Integer value) {
            addCriterion("team_one_total_exfirst <", value, "teamOneTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstLessThanOrEqualTo(Integer value) {
            addCriterion("team_one_total_exfirst <=", value, "teamOneTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstIn(List<Integer> values) {
            addCriterion("team_one_total_exfirst in", values, "teamOneTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstNotIn(List<Integer> values) {
            addCriterion("team_one_total_exfirst not in", values, "teamOneTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstBetween(Integer value1, Integer value2) {
            addCriterion("team_one_total_exfirst between", value1, value2, "teamOneTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamOneTotalExfirstNotBetween(Integer value1, Integer value2) {
            addCriterion("team_one_total_exfirst not between", value1, value2, "teamOneTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdIsNull() {
            addCriterion("team_two_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdIsNotNull() {
            addCriterion("team_two_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdEqualTo(String value) {
            addCriterion("team_two_id =", value, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdNotEqualTo(String value) {
            addCriterion("team_two_id <>", value, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdGreaterThan(String value) {
            addCriterion("team_two_id >", value, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdGreaterThanOrEqualTo(String value) {
            addCriterion("team_two_id >=", value, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdLessThan(String value) {
            addCriterion("team_two_id <", value, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdLessThanOrEqualTo(String value) {
            addCriterion("team_two_id <=", value, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdLike(String value) {
            addCriterion("team_two_id like", value, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdNotLike(String value) {
            addCriterion("team_two_id not like", value, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdIn(List<String> values) {
            addCriterion("team_two_id in", values, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdNotIn(List<String> values) {
            addCriterion("team_two_id not in", values, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdBetween(String value1, String value2) {
            addCriterion("team_two_id between", value1, value2, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoIdNotBetween(String value1, String value2) {
            addCriterion("team_two_id not between", value1, value2, "teamTwoId");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreIsNull() {
            addCriterion("team_two_score is null");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreIsNotNull() {
            addCriterion("team_two_score is not null");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreEqualTo(String value) {
            addCriterion("team_two_score =", value, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreNotEqualTo(String value) {
            addCriterion("team_two_score <>", value, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreGreaterThan(String value) {
            addCriterion("team_two_score >", value, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreGreaterThanOrEqualTo(String value) {
            addCriterion("team_two_score >=", value, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreLessThan(String value) {
            addCriterion("team_two_score <", value, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreLessThanOrEqualTo(String value) {
            addCriterion("team_two_score <=", value, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreLike(String value) {
            addCriterion("team_two_score like", value, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreNotLike(String value) {
            addCriterion("team_two_score not like", value, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreIn(List<String> values) {
            addCriterion("team_two_score in", values, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreNotIn(List<String> values) {
            addCriterion("team_two_score not in", values, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreBetween(String value1, String value2) {
            addCriterion("team_two_score between", value1, value2, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoScoreNotBetween(String value1, String value2) {
            addCriterion("team_two_score not between", value1, value2, "teamTwoScore");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointIsNull() {
            addCriterion("team_two_point is null");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointIsNotNull() {
            addCriterion("team_two_point is not null");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointEqualTo(Integer value) {
            addCriterion("team_two_point =", value, "teamTwoPoint");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointNotEqualTo(Integer value) {
            addCriterion("team_two_point <>", value, "teamTwoPoint");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointGreaterThan(Integer value) {
            addCriterion("team_two_point >", value, "teamTwoPoint");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_two_point >=", value, "teamTwoPoint");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointLessThan(Integer value) {
            addCriterion("team_two_point <", value, "teamTwoPoint");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointLessThanOrEqualTo(Integer value) {
            addCriterion("team_two_point <=", value, "teamTwoPoint");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointIn(List<Integer> values) {
            addCriterion("team_two_point in", values, "teamTwoPoint");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointNotIn(List<Integer> values) {
            addCriterion("team_two_point not in", values, "teamTwoPoint");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointBetween(Integer value1, Integer value2) {
            addCriterion("team_two_point between", value1, value2, "teamTwoPoint");
            return (Criteria) this;
        }

        public Criteria andTeamTwoPointNotBetween(Integer value1, Integer value2) {
            addCriterion("team_two_point not between", value1, value2, "teamTwoPoint");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityIsNull() {
            addCriterion("team_two_disparity is null");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityIsNotNull() {
            addCriterion("team_two_disparity is not null");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityEqualTo(Integer value) {
            addCriterion("team_two_disparity =", value, "teamTwoDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityNotEqualTo(Integer value) {
            addCriterion("team_two_disparity <>", value, "teamTwoDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityGreaterThan(Integer value) {
            addCriterion("team_two_disparity >", value, "teamTwoDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_two_disparity >=", value, "teamTwoDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityLessThan(Integer value) {
            addCriterion("team_two_disparity <", value, "teamTwoDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityLessThanOrEqualTo(Integer value) {
            addCriterion("team_two_disparity <=", value, "teamTwoDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityIn(List<Integer> values) {
            addCriterion("team_two_disparity in", values, "teamTwoDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityNotIn(List<Integer> values) {
            addCriterion("team_two_disparity not in", values, "teamTwoDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityBetween(Integer value1, Integer value2) {
            addCriterion("team_two_disparity between", value1, value2, "teamTwoDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamTwoDisparityNotBetween(Integer value1, Integer value2) {
            addCriterion("team_two_disparity not between", value1, value2, "teamTwoDisparity");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstIsNull() {
            addCriterion("team_two_total_exfirst is null");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstIsNotNull() {
            addCriterion("team_two_total_exfirst is not null");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstEqualTo(Integer value) {
            addCriterion("team_two_total_exfirst =", value, "teamTwoTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstNotEqualTo(Integer value) {
            addCriterion("team_two_total_exfirst <>", value, "teamTwoTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstGreaterThan(Integer value) {
            addCriterion("team_two_total_exfirst >", value, "teamTwoTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_two_total_exfirst >=", value, "teamTwoTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstLessThan(Integer value) {
            addCriterion("team_two_total_exfirst <", value, "teamTwoTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstLessThanOrEqualTo(Integer value) {
            addCriterion("team_two_total_exfirst <=", value, "teamTwoTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstIn(List<Integer> values) {
            addCriterion("team_two_total_exfirst in", values, "teamTwoTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstNotIn(List<Integer> values) {
            addCriterion("team_two_total_exfirst not in", values, "teamTwoTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstBetween(Integer value1, Integer value2) {
            addCriterion("team_two_total_exfirst between", value1, value2, "teamTwoTotalExfirst");
            return (Criteria) this;
        }

        public Criteria andTeamTwoTotalExfirstNotBetween(Integer value1, Integer value2) {
            addCriterion("team_two_total_exfirst not between", value1, value2, "teamTwoTotalExfirst");
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