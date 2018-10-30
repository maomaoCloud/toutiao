package com.tiaotiaopoker.pojo;

import java.util.ArrayList;
import java.util.List;

public class MatchRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MatchRuleExample() {
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

        public Criteria andMatchHostIsNull() {
            addCriterion("match_host is null");
            return (Criteria) this;
        }

        public Criteria andMatchHostIsNotNull() {
            addCriterion("match_host is not null");
            return (Criteria) this;
        }

        public Criteria andMatchHostEqualTo(String value) {
            addCriterion("match_host =", value, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostNotEqualTo(String value) {
            addCriterion("match_host <>", value, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostGreaterThan(String value) {
            addCriterion("match_host >", value, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostGreaterThanOrEqualTo(String value) {
            addCriterion("match_host >=", value, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostLessThan(String value) {
            addCriterion("match_host <", value, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostLessThanOrEqualTo(String value) {
            addCriterion("match_host <=", value, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostLike(String value) {
            addCriterion("match_host like", value, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostNotLike(String value) {
            addCriterion("match_host not like", value, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostIn(List<String> values) {
            addCriterion("match_host in", values, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostNotIn(List<String> values) {
            addCriterion("match_host not in", values, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostBetween(String value1, String value2) {
            addCriterion("match_host between", value1, value2, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchHostNotBetween(String value1, String value2) {
            addCriterion("match_host not between", value1, value2, "matchHost");
            return (Criteria) this;
        }

        public Criteria andMatchAddressIsNull() {
            addCriterion("match_address is null");
            return (Criteria) this;
        }

        public Criteria andMatchAddressIsNotNull() {
            addCriterion("match_address is not null");
            return (Criteria) this;
        }

        public Criteria andMatchAddressEqualTo(String value) {
            addCriterion("match_address =", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressNotEqualTo(String value) {
            addCriterion("match_address <>", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressGreaterThan(String value) {
            addCriterion("match_address >", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressGreaterThanOrEqualTo(String value) {
            addCriterion("match_address >=", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressLessThan(String value) {
            addCriterion("match_address <", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressLessThanOrEqualTo(String value) {
            addCriterion("match_address <=", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressLike(String value) {
            addCriterion("match_address like", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressNotLike(String value) {
            addCriterion("match_address not like", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressIn(List<String> values) {
            addCriterion("match_address in", values, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressNotIn(List<String> values) {
            addCriterion("match_address not in", values, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressBetween(String value1, String value2) {
            addCriterion("match_address between", value1, value2, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressNotBetween(String value1, String value2) {
            addCriterion("match_address not between", value1, value2, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchDateIsNull() {
            addCriterion("match_date is null");
            return (Criteria) this;
        }

        public Criteria andMatchDateIsNotNull() {
            addCriterion("match_date is not null");
            return (Criteria) this;
        }

        public Criteria andMatchDateEqualTo(String value) {
            addCriterion("match_date =", value, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateNotEqualTo(String value) {
            addCriterion("match_date <>", value, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateGreaterThan(String value) {
            addCriterion("match_date >", value, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateGreaterThanOrEqualTo(String value) {
            addCriterion("match_date >=", value, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateLessThan(String value) {
            addCriterion("match_date <", value, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateLessThanOrEqualTo(String value) {
            addCriterion("match_date <=", value, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateLike(String value) {
            addCriterion("match_date like", value, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateNotLike(String value) {
            addCriterion("match_date not like", value, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateIn(List<String> values) {
            addCriterion("match_date in", values, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateNotIn(List<String> values) {
            addCriterion("match_date not in", values, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateBetween(String value1, String value2) {
            addCriterion("match_date between", value1, value2, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchDateNotBetween(String value1, String value2) {
            addCriterion("match_date not between", value1, value2, "matchDate");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeIsNull() {
            addCriterion("match_referee is null");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeIsNotNull() {
            addCriterion("match_referee is not null");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeEqualTo(String value) {
            addCriterion("match_referee =", value, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeNotEqualTo(String value) {
            addCriterion("match_referee <>", value, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeGreaterThan(String value) {
            addCriterion("match_referee >", value, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeGreaterThanOrEqualTo(String value) {
            addCriterion("match_referee >=", value, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeLessThan(String value) {
            addCriterion("match_referee <", value, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeLessThanOrEqualTo(String value) {
            addCriterion("match_referee <=", value, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeLike(String value) {
            addCriterion("match_referee like", value, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeNotLike(String value) {
            addCriterion("match_referee not like", value, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeIn(List<String> values) {
            addCriterion("match_referee in", values, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeNotIn(List<String> values) {
            addCriterion("match_referee not in", values, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeBetween(String value1, String value2) {
            addCriterion("match_referee between", value1, value2, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchRefereeNotBetween(String value1, String value2) {
            addCriterion("match_referee not between", value1, value2, "matchReferee");
            return (Criteria) this;
        }

        public Criteria andMatchLogoIsNull() {
            addCriterion("match_logo is null");
            return (Criteria) this;
        }

        public Criteria andMatchLogoIsNotNull() {
            addCriterion("match_logo is not null");
            return (Criteria) this;
        }

        public Criteria andMatchLogoEqualTo(String value) {
            addCriterion("match_logo =", value, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoNotEqualTo(String value) {
            addCriterion("match_logo <>", value, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoGreaterThan(String value) {
            addCriterion("match_logo >", value, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoGreaterThanOrEqualTo(String value) {
            addCriterion("match_logo >=", value, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoLessThan(String value) {
            addCriterion("match_logo <", value, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoLessThanOrEqualTo(String value) {
            addCriterion("match_logo <=", value, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoLike(String value) {
            addCriterion("match_logo like", value, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoNotLike(String value) {
            addCriterion("match_logo not like", value, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoIn(List<String> values) {
            addCriterion("match_logo in", values, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoNotIn(List<String> values) {
            addCriterion("match_logo not in", values, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoBetween(String value1, String value2) {
            addCriterion("match_logo between", value1, value2, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchLogoNotBetween(String value1, String value2) {
            addCriterion("match_logo not between", value1, value2, "matchLogo");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyIsNull() {
            addCriterion("match_technology is null");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyIsNotNull() {
            addCriterion("match_technology is not null");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyEqualTo(String value) {
            addCriterion("match_technology =", value, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyNotEqualTo(String value) {
            addCriterion("match_technology <>", value, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyGreaterThan(String value) {
            addCriterion("match_technology >", value, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyGreaterThanOrEqualTo(String value) {
            addCriterion("match_technology >=", value, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyLessThan(String value) {
            addCriterion("match_technology <", value, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyLessThanOrEqualTo(String value) {
            addCriterion("match_technology <=", value, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyLike(String value) {
            addCriterion("match_technology like", value, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyNotLike(String value) {
            addCriterion("match_technology not like", value, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyIn(List<String> values) {
            addCriterion("match_technology in", values, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyNotIn(List<String> values) {
            addCriterion("match_technology not in", values, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyBetween(String value1, String value2) {
            addCriterion("match_technology between", value1, value2, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchTechnologyNotBetween(String value1, String value2) {
            addCriterion("match_technology not between", value1, value2, "matchTechnology");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgIsNull() {
            addCriterion("match_champion_img is null");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgIsNotNull() {
            addCriterion("match_champion_img is not null");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgEqualTo(String value) {
            addCriterion("match_champion_img =", value, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgNotEqualTo(String value) {
            addCriterion("match_champion_img <>", value, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgGreaterThan(String value) {
            addCriterion("match_champion_img >", value, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgGreaterThanOrEqualTo(String value) {
            addCriterion("match_champion_img >=", value, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgLessThan(String value) {
            addCriterion("match_champion_img <", value, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgLessThanOrEqualTo(String value) {
            addCriterion("match_champion_img <=", value, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgLike(String value) {
            addCriterion("match_champion_img like", value, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgNotLike(String value) {
            addCriterion("match_champion_img not like", value, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgIn(List<String> values) {
            addCriterion("match_champion_img in", values, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgNotIn(List<String> values) {
            addCriterion("match_champion_img not in", values, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgBetween(String value1, String value2) {
            addCriterion("match_champion_img between", value1, value2, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionImgNotBetween(String value1, String value2) {
            addCriterion("match_champion_img not between", value1, value2, "matchChampionImg");
            return (Criteria) this;
        }

        public Criteria andMatchChampionIsNull() {
            addCriterion("match_champion is null");
            return (Criteria) this;
        }

        public Criteria andMatchChampionIsNotNull() {
            addCriterion("match_champion is not null");
            return (Criteria) this;
        }

        public Criteria andMatchChampionEqualTo(String value) {
            addCriterion("match_champion =", value, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionNotEqualTo(String value) {
            addCriterion("match_champion <>", value, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionGreaterThan(String value) {
            addCriterion("match_champion >", value, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionGreaterThanOrEqualTo(String value) {
            addCriterion("match_champion >=", value, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionLessThan(String value) {
            addCriterion("match_champion <", value, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionLessThanOrEqualTo(String value) {
            addCriterion("match_champion <=", value, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionLike(String value) {
            addCriterion("match_champion like", value, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionNotLike(String value) {
            addCriterion("match_champion not like", value, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionIn(List<String> values) {
            addCriterion("match_champion in", values, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionNotIn(List<String> values) {
            addCriterion("match_champion not in", values, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionBetween(String value1, String value2) {
            addCriterion("match_champion between", value1, value2, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchChampionNotBetween(String value1, String value2) {
            addCriterion("match_champion not between", value1, value2, "matchChampion");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgIsNull() {
            addCriterion("match_second_img is null");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgIsNotNull() {
            addCriterion("match_second_img is not null");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgEqualTo(String value) {
            addCriterion("match_second_img =", value, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgNotEqualTo(String value) {
            addCriterion("match_second_img <>", value, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgGreaterThan(String value) {
            addCriterion("match_second_img >", value, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgGreaterThanOrEqualTo(String value) {
            addCriterion("match_second_img >=", value, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgLessThan(String value) {
            addCriterion("match_second_img <", value, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgLessThanOrEqualTo(String value) {
            addCriterion("match_second_img <=", value, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgLike(String value) {
            addCriterion("match_second_img like", value, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgNotLike(String value) {
            addCriterion("match_second_img not like", value, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgIn(List<String> values) {
            addCriterion("match_second_img in", values, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgNotIn(List<String> values) {
            addCriterion("match_second_img not in", values, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgBetween(String value1, String value2) {
            addCriterion("match_second_img between", value1, value2, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondImgNotBetween(String value1, String value2) {
            addCriterion("match_second_img not between", value1, value2, "matchSecondImg");
            return (Criteria) this;
        }

        public Criteria andMatchSecondIsNull() {
            addCriterion("match_second is null");
            return (Criteria) this;
        }

        public Criteria andMatchSecondIsNotNull() {
            addCriterion("match_second is not null");
            return (Criteria) this;
        }

        public Criteria andMatchSecondEqualTo(String value) {
            addCriterion("match_second =", value, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondNotEqualTo(String value) {
            addCriterion("match_second <>", value, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondGreaterThan(String value) {
            addCriterion("match_second >", value, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondGreaterThanOrEqualTo(String value) {
            addCriterion("match_second >=", value, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondLessThan(String value) {
            addCriterion("match_second <", value, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondLessThanOrEqualTo(String value) {
            addCriterion("match_second <=", value, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondLike(String value) {
            addCriterion("match_second like", value, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondNotLike(String value) {
            addCriterion("match_second not like", value, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondIn(List<String> values) {
            addCriterion("match_second in", values, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondNotIn(List<String> values) {
            addCriterion("match_second not in", values, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondBetween(String value1, String value2) {
            addCriterion("match_second between", value1, value2, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchSecondNotBetween(String value1, String value2) {
            addCriterion("match_second not between", value1, value2, "matchSecond");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgIsNull() {
            addCriterion("match_third_img is null");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgIsNotNull() {
            addCriterion("match_third_img is not null");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgEqualTo(String value) {
            addCriterion("match_third_img =", value, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgNotEqualTo(String value) {
            addCriterion("match_third_img <>", value, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgGreaterThan(String value) {
            addCriterion("match_third_img >", value, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgGreaterThanOrEqualTo(String value) {
            addCriterion("match_third_img >=", value, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgLessThan(String value) {
            addCriterion("match_third_img <", value, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgLessThanOrEqualTo(String value) {
            addCriterion("match_third_img <=", value, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgLike(String value) {
            addCriterion("match_third_img like", value, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgNotLike(String value) {
            addCriterion("match_third_img not like", value, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgIn(List<String> values) {
            addCriterion("match_third_img in", values, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgNotIn(List<String> values) {
            addCriterion("match_third_img not in", values, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgBetween(String value1, String value2) {
            addCriterion("match_third_img between", value1, value2, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdImgNotBetween(String value1, String value2) {
            addCriterion("match_third_img not between", value1, value2, "matchThirdImg");
            return (Criteria) this;
        }

        public Criteria andMatchThirdIsNull() {
            addCriterion("match_third is null");
            return (Criteria) this;
        }

        public Criteria andMatchThirdIsNotNull() {
            addCriterion("match_third is not null");
            return (Criteria) this;
        }

        public Criteria andMatchThirdEqualTo(String value) {
            addCriterion("match_third =", value, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdNotEqualTo(String value) {
            addCriterion("match_third <>", value, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdGreaterThan(String value) {
            addCriterion("match_third >", value, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdGreaterThanOrEqualTo(String value) {
            addCriterion("match_third >=", value, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdLessThan(String value) {
            addCriterion("match_third <", value, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdLessThanOrEqualTo(String value) {
            addCriterion("match_third <=", value, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdLike(String value) {
            addCriterion("match_third like", value, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdNotLike(String value) {
            addCriterion("match_third not like", value, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdIn(List<String> values) {
            addCriterion("match_third in", values, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdNotIn(List<String> values) {
            addCriterion("match_third not in", values, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdBetween(String value1, String value2) {
            addCriterion("match_third between", value1, value2, "matchThird");
            return (Criteria) this;
        }

        public Criteria andMatchThirdNotBetween(String value1, String value2) {
            addCriterion("match_third not between", value1, value2, "matchThird");
            return (Criteria) this;
        }

        public Criteria andRuleTurnIsNull() {
            addCriterion("rule_turn is null");
            return (Criteria) this;
        }

        public Criteria andRuleTurnIsNotNull() {
            addCriterion("rule_turn is not null");
            return (Criteria) this;
        }

        public Criteria andRuleTurnEqualTo(Integer value) {
            addCriterion("rule_turn =", value, "ruleTurn");
            return (Criteria) this;
        }

        public Criteria andRuleTurnNotEqualTo(Integer value) {
            addCriterion("rule_turn <>", value, "ruleTurn");
            return (Criteria) this;
        }

        public Criteria andRuleTurnGreaterThan(Integer value) {
            addCriterion("rule_turn >", value, "ruleTurn");
            return (Criteria) this;
        }

        public Criteria andRuleTurnGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_turn >=", value, "ruleTurn");
            return (Criteria) this;
        }

        public Criteria andRuleTurnLessThan(Integer value) {
            addCriterion("rule_turn <", value, "ruleTurn");
            return (Criteria) this;
        }

        public Criteria andRuleTurnLessThanOrEqualTo(Integer value) {
            addCriterion("rule_turn <=", value, "ruleTurn");
            return (Criteria) this;
        }

        public Criteria andRuleTurnIn(List<Integer> values) {
            addCriterion("rule_turn in", values, "ruleTurn");
            return (Criteria) this;
        }

        public Criteria andRuleTurnNotIn(List<Integer> values) {
            addCriterion("rule_turn not in", values, "ruleTurn");
            return (Criteria) this;
        }

        public Criteria andRuleTurnBetween(Integer value1, Integer value2) {
            addCriterion("rule_turn between", value1, value2, "ruleTurn");
            return (Criteria) this;
        }

        public Criteria andRuleTurnNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_turn not between", value1, value2, "ruleTurn");
            return (Criteria) this;
        }

        public Criteria andRulePairIsNull() {
            addCriterion("rule_pair is null");
            return (Criteria) this;
        }

        public Criteria andRulePairIsNotNull() {
            addCriterion("rule_pair is not null");
            return (Criteria) this;
        }

        public Criteria andRulePairEqualTo(Integer value) {
            addCriterion("rule_pair =", value, "rulePair");
            return (Criteria) this;
        }

        public Criteria andRulePairNotEqualTo(Integer value) {
            addCriterion("rule_pair <>", value, "rulePair");
            return (Criteria) this;
        }

        public Criteria andRulePairGreaterThan(Integer value) {
            addCriterion("rule_pair >", value, "rulePair");
            return (Criteria) this;
        }

        public Criteria andRulePairGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_pair >=", value, "rulePair");
            return (Criteria) this;
        }

        public Criteria andRulePairLessThan(Integer value) {
            addCriterion("rule_pair <", value, "rulePair");
            return (Criteria) this;
        }

        public Criteria andRulePairLessThanOrEqualTo(Integer value) {
            addCriterion("rule_pair <=", value, "rulePair");
            return (Criteria) this;
        }

        public Criteria andRulePairIn(List<Integer> values) {
            addCriterion("rule_pair in", values, "rulePair");
            return (Criteria) this;
        }

        public Criteria andRulePairNotIn(List<Integer> values) {
            addCriterion("rule_pair not in", values, "rulePair");
            return (Criteria) this;
        }

        public Criteria andRulePairBetween(Integer value1, Integer value2) {
            addCriterion("rule_pair between", value1, value2, "rulePair");
            return (Criteria) this;
        }

        public Criteria andRulePairNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_pair not between", value1, value2, "rulePair");
            return (Criteria) this;
        }

        public Criteria andRuleTimeIsNull() {
            addCriterion("rule_time is null");
            return (Criteria) this;
        }

        public Criteria andRuleTimeIsNotNull() {
            addCriterion("rule_time is not null");
            return (Criteria) this;
        }

        public Criteria andRuleTimeEqualTo(Integer value) {
            addCriterion("rule_time =", value, "ruleTime");
            return (Criteria) this;
        }

        public Criteria andRuleTimeNotEqualTo(Integer value) {
            addCriterion("rule_time <>", value, "ruleTime");
            return (Criteria) this;
        }

        public Criteria andRuleTimeGreaterThan(Integer value) {
            addCriterion("rule_time >", value, "ruleTime");
            return (Criteria) this;
        }

        public Criteria andRuleTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_time >=", value, "ruleTime");
            return (Criteria) this;
        }

        public Criteria andRuleTimeLessThan(Integer value) {
            addCriterion("rule_time <", value, "ruleTime");
            return (Criteria) this;
        }

        public Criteria andRuleTimeLessThanOrEqualTo(Integer value) {
            addCriterion("rule_time <=", value, "ruleTime");
            return (Criteria) this;
        }

        public Criteria andRuleTimeIn(List<Integer> values) {
            addCriterion("rule_time in", values, "ruleTime");
            return (Criteria) this;
        }

        public Criteria andRuleTimeNotIn(List<Integer> values) {
            addCriterion("rule_time not in", values, "ruleTime");
            return (Criteria) this;
        }

        public Criteria andRuleTimeBetween(Integer value1, Integer value2) {
            addCriterion("rule_time between", value1, value2, "ruleTime");
            return (Criteria) this;
        }

        public Criteria andRuleTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_time not between", value1, value2, "ruleTime");
            return (Criteria) this;
        }

        public Criteria andRuleWinIsNull() {
            addCriterion("rule_win is null");
            return (Criteria) this;
        }

        public Criteria andRuleWinIsNotNull() {
            addCriterion("rule_win is not null");
            return (Criteria) this;
        }

        public Criteria andRuleWinEqualTo(Integer value) {
            addCriterion("rule_win =", value, "ruleWin");
            return (Criteria) this;
        }

        public Criteria andRuleWinNotEqualTo(Integer value) {
            addCriterion("rule_win <>", value, "ruleWin");
            return (Criteria) this;
        }

        public Criteria andRuleWinGreaterThan(Integer value) {
            addCriterion("rule_win >", value, "ruleWin");
            return (Criteria) this;
        }

        public Criteria andRuleWinGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_win >=", value, "ruleWin");
            return (Criteria) this;
        }

        public Criteria andRuleWinLessThan(Integer value) {
            addCriterion("rule_win <", value, "ruleWin");
            return (Criteria) this;
        }

        public Criteria andRuleWinLessThanOrEqualTo(Integer value) {
            addCriterion("rule_win <=", value, "ruleWin");
            return (Criteria) this;
        }

        public Criteria andRuleWinIn(List<Integer> values) {
            addCriterion("rule_win in", values, "ruleWin");
            return (Criteria) this;
        }

        public Criteria andRuleWinNotIn(List<Integer> values) {
            addCriterion("rule_win not in", values, "ruleWin");
            return (Criteria) this;
        }

        public Criteria andRuleWinBetween(Integer value1, Integer value2) {
            addCriterion("rule_win between", value1, value2, "ruleWin");
            return (Criteria) this;
        }

        public Criteria andRuleWinNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_win not between", value1, value2, "ruleWin");
            return (Criteria) this;
        }

        public Criteria andRuleFailIsNull() {
            addCriterion("rule_fail is null");
            return (Criteria) this;
        }

        public Criteria andRuleFailIsNotNull() {
            addCriterion("rule_fail is not null");
            return (Criteria) this;
        }

        public Criteria andRuleFailEqualTo(Integer value) {
            addCriterion("rule_fail =", value, "ruleFail");
            return (Criteria) this;
        }

        public Criteria andRuleFailNotEqualTo(Integer value) {
            addCriterion("rule_fail <>", value, "ruleFail");
            return (Criteria) this;
        }

        public Criteria andRuleFailGreaterThan(Integer value) {
            addCriterion("rule_fail >", value, "ruleFail");
            return (Criteria) this;
        }

        public Criteria andRuleFailGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_fail >=", value, "ruleFail");
            return (Criteria) this;
        }

        public Criteria andRuleFailLessThan(Integer value) {
            addCriterion("rule_fail <", value, "ruleFail");
            return (Criteria) this;
        }

        public Criteria andRuleFailLessThanOrEqualTo(Integer value) {
            addCriterion("rule_fail <=", value, "ruleFail");
            return (Criteria) this;
        }

        public Criteria andRuleFailIn(List<Integer> values) {
            addCriterion("rule_fail in", values, "ruleFail");
            return (Criteria) this;
        }

        public Criteria andRuleFailNotIn(List<Integer> values) {
            addCriterion("rule_fail not in", values, "ruleFail");
            return (Criteria) this;
        }

        public Criteria andRuleFailBetween(Integer value1, Integer value2) {
            addCriterion("rule_fail between", value1, value2, "ruleFail");
            return (Criteria) this;
        }

        public Criteria andRuleFailNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_fail not between", value1, value2, "ruleFail");
            return (Criteria) this;
        }

        public Criteria andRuleDrawIsNull() {
            addCriterion("rule_draw is null");
            return (Criteria) this;
        }

        public Criteria andRuleDrawIsNotNull() {
            addCriterion("rule_draw is not null");
            return (Criteria) this;
        }

        public Criteria andRuleDrawEqualTo(Integer value) {
            addCriterion("rule_draw =", value, "ruleDraw");
            return (Criteria) this;
        }

        public Criteria andRuleDrawNotEqualTo(Integer value) {
            addCriterion("rule_draw <>", value, "ruleDraw");
            return (Criteria) this;
        }

        public Criteria andRuleDrawGreaterThan(Integer value) {
            addCriterion("rule_draw >", value, "ruleDraw");
            return (Criteria) this;
        }

        public Criteria andRuleDrawGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_draw >=", value, "ruleDraw");
            return (Criteria) this;
        }

        public Criteria andRuleDrawLessThan(Integer value) {
            addCriterion("rule_draw <", value, "ruleDraw");
            return (Criteria) this;
        }

        public Criteria andRuleDrawLessThanOrEqualTo(Integer value) {
            addCriterion("rule_draw <=", value, "ruleDraw");
            return (Criteria) this;
        }

        public Criteria andRuleDrawIn(List<Integer> values) {
            addCriterion("rule_draw in", values, "ruleDraw");
            return (Criteria) this;
        }

        public Criteria andRuleDrawNotIn(List<Integer> values) {
            addCriterion("rule_draw not in", values, "ruleDraw");
            return (Criteria) this;
        }

        public Criteria andRuleDrawBetween(Integer value1, Integer value2) {
            addCriterion("rule_draw between", value1, value2, "ruleDraw");
            return (Criteria) this;
        }

        public Criteria andRuleDrawNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_draw not between", value1, value2, "ruleDraw");
            return (Criteria) this;
        }

        public Criteria andRuleSeatIsNull() {
            addCriterion("rule_seat is null");
            return (Criteria) this;
        }

        public Criteria andRuleSeatIsNotNull() {
            addCriterion("rule_seat is not null");
            return (Criteria) this;
        }

        public Criteria andRuleSeatEqualTo(Integer value) {
            addCriterion("rule_seat =", value, "ruleSeat");
            return (Criteria) this;
        }

        public Criteria andRuleSeatNotEqualTo(Integer value) {
            addCriterion("rule_seat <>", value, "ruleSeat");
            return (Criteria) this;
        }

        public Criteria andRuleSeatGreaterThan(Integer value) {
            addCriterion("rule_seat >", value, "ruleSeat");
            return (Criteria) this;
        }

        public Criteria andRuleSeatGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_seat >=", value, "ruleSeat");
            return (Criteria) this;
        }

        public Criteria andRuleSeatLessThan(Integer value) {
            addCriterion("rule_seat <", value, "ruleSeat");
            return (Criteria) this;
        }

        public Criteria andRuleSeatLessThanOrEqualTo(Integer value) {
            addCriterion("rule_seat <=", value, "ruleSeat");
            return (Criteria) this;
        }

        public Criteria andRuleSeatIn(List<Integer> values) {
            addCriterion("rule_seat in", values, "ruleSeat");
            return (Criteria) this;
        }

        public Criteria andRuleSeatNotIn(List<Integer> values) {
            addCriterion("rule_seat not in", values, "ruleSeat");
            return (Criteria) this;
        }

        public Criteria andRuleSeatBetween(Integer value1, Integer value2) {
            addCriterion("rule_seat between", value1, value2, "ruleSeat");
            return (Criteria) this;
        }

        public Criteria andRuleSeatNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_seat not between", value1, value2, "ruleSeat");
            return (Criteria) this;
        }

        public Criteria andRuleResultIsNull() {
            addCriterion("rule_result is null");
            return (Criteria) this;
        }

        public Criteria andRuleResultIsNotNull() {
            addCriterion("rule_result is not null");
            return (Criteria) this;
        }

        public Criteria andRuleResultEqualTo(String value) {
            addCriterion("rule_result =", value, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultNotEqualTo(String value) {
            addCriterion("rule_result <>", value, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultGreaterThan(String value) {
            addCriterion("rule_result >", value, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultGreaterThanOrEqualTo(String value) {
            addCriterion("rule_result >=", value, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultLessThan(String value) {
            addCriterion("rule_result <", value, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultLessThanOrEqualTo(String value) {
            addCriterion("rule_result <=", value, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultLike(String value) {
            addCriterion("rule_result like", value, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultNotLike(String value) {
            addCriterion("rule_result not like", value, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultIn(List<String> values) {
            addCriterion("rule_result in", values, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultNotIn(List<String> values) {
            addCriterion("rule_result not in", values, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultBetween(String value1, String value2) {
            addCriterion("rule_result between", value1, value2, "ruleResult");
            return (Criteria) this;
        }

        public Criteria andRuleResultNotBetween(String value1, String value2) {
            addCriterion("rule_result not between", value1, value2, "ruleResult");
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