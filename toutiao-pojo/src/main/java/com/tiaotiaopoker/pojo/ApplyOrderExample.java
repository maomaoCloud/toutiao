package com.tiaotiaopoker.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplyOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApplyOrderExample() {
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

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("user_phone is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("user_phone is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("user_phone =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("user_phone <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("user_phone >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("user_phone >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("user_phone <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("user_phone <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("user_phone like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("user_phone not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("user_phone in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("user_phone not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("user_phone between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("user_phone not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIsNull() {
            addCriterion("partner_id is null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIsNotNull() {
            addCriterion("partner_id is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdEqualTo(String value) {
            addCriterion("partner_id =", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotEqualTo(String value) {
            addCriterion("partner_id <>", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdGreaterThan(String value) {
            addCriterion("partner_id >", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdGreaterThanOrEqualTo(String value) {
            addCriterion("partner_id >=", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLessThan(String value) {
            addCriterion("partner_id <", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLessThanOrEqualTo(String value) {
            addCriterion("partner_id <=", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLike(String value) {
            addCriterion("partner_id like", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotLike(String value) {
            addCriterion("partner_id not like", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIn(List<String> values) {
            addCriterion("partner_id in", values, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotIn(List<String> values) {
            addCriterion("partner_id not in", values, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdBetween(String value1, String value2) {
            addCriterion("partner_id between", value1, value2, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotBetween(String value1, String value2) {
            addCriterion("partner_id not between", value1, value2, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerNameIsNull() {
            addCriterion("partner_name is null");
            return (Criteria) this;
        }

        public Criteria andPartnerNameIsNotNull() {
            addCriterion("partner_name is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerNameEqualTo(String value) {
            addCriterion("partner_name =", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotEqualTo(String value) {
            addCriterion("partner_name <>", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameGreaterThan(String value) {
            addCriterion("partner_name >", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("partner_name >=", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameLessThan(String value) {
            addCriterion("partner_name <", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameLessThanOrEqualTo(String value) {
            addCriterion("partner_name <=", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameLike(String value) {
            addCriterion("partner_name like", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotLike(String value) {
            addCriterion("partner_name not like", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameIn(List<String> values) {
            addCriterion("partner_name in", values, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotIn(List<String> values) {
            addCriterion("partner_name not in", values, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameBetween(String value1, String value2) {
            addCriterion("partner_name between", value1, value2, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotBetween(String value1, String value2) {
            addCriterion("partner_name not between", value1, value2, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneIsNull() {
            addCriterion("partner_phone is null");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneIsNotNull() {
            addCriterion("partner_phone is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneEqualTo(String value) {
            addCriterion("partner_phone =", value, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneNotEqualTo(String value) {
            addCriterion("partner_phone <>", value, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneGreaterThan(String value) {
            addCriterion("partner_phone >", value, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("partner_phone >=", value, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneLessThan(String value) {
            addCriterion("partner_phone <", value, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneLessThanOrEqualTo(String value) {
            addCriterion("partner_phone <=", value, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneLike(String value) {
            addCriterion("partner_phone like", value, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneNotLike(String value) {
            addCriterion("partner_phone not like", value, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneIn(List<String> values) {
            addCriterion("partner_phone in", values, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneNotIn(List<String> values) {
            addCriterion("partner_phone not in", values, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneBetween(String value1, String value2) {
            addCriterion("partner_phone between", value1, value2, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andPartnerPhoneNotBetween(String value1, String value2) {
            addCriterion("partner_phone not between", value1, value2, "partnerPhone");
            return (Criteria) this;
        }

        public Criteria andHasPartnerIsNull() {
            addCriterion("has_partner is null");
            return (Criteria) this;
        }

        public Criteria andHasPartnerIsNotNull() {
            addCriterion("has_partner is not null");
            return (Criteria) this;
        }

        public Criteria andHasPartnerEqualTo(Integer value) {
            addCriterion("has_partner =", value, "hasPartner");
            return (Criteria) this;
        }

        public Criteria andHasPartnerNotEqualTo(Integer value) {
            addCriterion("has_partner <>", value, "hasPartner");
            return (Criteria) this;
        }

        public Criteria andHasPartnerGreaterThan(Integer value) {
            addCriterion("has_partner >", value, "hasPartner");
            return (Criteria) this;
        }

        public Criteria andHasPartnerGreaterThanOrEqualTo(Integer value) {
            addCriterion("has_partner >=", value, "hasPartner");
            return (Criteria) this;
        }

        public Criteria andHasPartnerLessThan(Integer value) {
            addCriterion("has_partner <", value, "hasPartner");
            return (Criteria) this;
        }

        public Criteria andHasPartnerLessThanOrEqualTo(Integer value) {
            addCriterion("has_partner <=", value, "hasPartner");
            return (Criteria) this;
        }

        public Criteria andHasPartnerIn(List<Integer> values) {
            addCriterion("has_partner in", values, "hasPartner");
            return (Criteria) this;
        }

        public Criteria andHasPartnerNotIn(List<Integer> values) {
            addCriterion("has_partner not in", values, "hasPartner");
            return (Criteria) this;
        }

        public Criteria andHasPartnerBetween(Integer value1, Integer value2) {
            addCriterion("has_partner between", value1, value2, "hasPartner");
            return (Criteria) this;
        }

        public Criteria andHasPartnerNotBetween(Integer value1, Integer value2) {
            addCriterion("has_partner not between", value1, value2, "hasPartner");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPayMoneyIsNull() {
            addCriterion("pay_money is null");
            return (Criteria) this;
        }

        public Criteria andPayMoneyIsNotNull() {
            addCriterion("pay_money is not null");
            return (Criteria) this;
        }

        public Criteria andPayMoneyEqualTo(BigDecimal value) {
            addCriterion("pay_money =", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyNotEqualTo(BigDecimal value) {
            addCriterion("pay_money <>", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyGreaterThan(BigDecimal value) {
            addCriterion("pay_money >", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_money >=", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyLessThan(BigDecimal value) {
            addCriterion("pay_money <", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_money <=", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyIn(List<BigDecimal> values) {
            addCriterion("pay_money in", values, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyNotIn(List<BigDecimal> values) {
            addCriterion("pay_money not in", values, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_money between", value1, value2, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_money not between", value1, value2, "payMoney");
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

        public Criteria andUserHeadIsNull() {
            addCriterion("user_head is null");
            return (Criteria) this;
        }

        public Criteria andUserHeadIsNotNull() {
            addCriterion("user_head is not null");
            return (Criteria) this;
        }

        public Criteria andUserHeadEqualTo(String value) {
            addCriterion("user_head =", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadNotEqualTo(String value) {
            addCriterion("user_head <>", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadGreaterThan(String value) {
            addCriterion("user_head >", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadGreaterThanOrEqualTo(String value) {
            addCriterion("user_head >=", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadLessThan(String value) {
            addCriterion("user_head <", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadLessThanOrEqualTo(String value) {
            addCriterion("user_head <=", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadLike(String value) {
            addCriterion("user_head like", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadNotLike(String value) {
            addCriterion("user_head not like", value, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadIn(List<String> values) {
            addCriterion("user_head in", values, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadNotIn(List<String> values) {
            addCriterion("user_head not in", values, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadBetween(String value1, String value2) {
            addCriterion("user_head between", value1, value2, "userHead");
            return (Criteria) this;
        }

        public Criteria andUserHeadNotBetween(String value1, String value2) {
            addCriterion("user_head not between", value1, value2, "userHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadIsNull() {
            addCriterion("partner_head is null");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadIsNotNull() {
            addCriterion("partner_head is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadEqualTo(String value) {
            addCriterion("partner_head =", value, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadNotEqualTo(String value) {
            addCriterion("partner_head <>", value, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadGreaterThan(String value) {
            addCriterion("partner_head >", value, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadGreaterThanOrEqualTo(String value) {
            addCriterion("partner_head >=", value, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadLessThan(String value) {
            addCriterion("partner_head <", value, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadLessThanOrEqualTo(String value) {
            addCriterion("partner_head <=", value, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadLike(String value) {
            addCriterion("partner_head like", value, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadNotLike(String value) {
            addCriterion("partner_head not like", value, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadIn(List<String> values) {
            addCriterion("partner_head in", values, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadNotIn(List<String> values) {
            addCriterion("partner_head not in", values, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadBetween(String value1, String value2) {
            addCriterion("partner_head between", value1, value2, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andPartnerHeadNotBetween(String value1, String value2) {
            addCriterion("partner_head not between", value1, value2, "partnerHead");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andPayStatueIsNull() {
            addCriterion("pay_statue is null");
            return (Criteria) this;
        }

        public Criteria andPayStatueIsNotNull() {
            addCriterion("pay_statue is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatueEqualTo(Integer value) {
            addCriterion("pay_statue =", value, "payStatue");
            return (Criteria) this;
        }

        public Criteria andPayStatueNotEqualTo(Integer value) {
            addCriterion("pay_statue <>", value, "payStatue");
            return (Criteria) this;
        }

        public Criteria andPayStatueGreaterThan(Integer value) {
            addCriterion("pay_statue >", value, "payStatue");
            return (Criteria) this;
        }

        public Criteria andPayStatueGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_statue >=", value, "payStatue");
            return (Criteria) this;
        }

        public Criteria andPayStatueLessThan(Integer value) {
            addCriterion("pay_statue <", value, "payStatue");
            return (Criteria) this;
        }

        public Criteria andPayStatueLessThanOrEqualTo(Integer value) {
            addCriterion("pay_statue <=", value, "payStatue");
            return (Criteria) this;
        }

        public Criteria andPayStatueIn(List<Integer> values) {
            addCriterion("pay_statue in", values, "payStatue");
            return (Criteria) this;
        }

        public Criteria andPayStatueNotIn(List<Integer> values) {
            addCriterion("pay_statue not in", values, "payStatue");
            return (Criteria) this;
        }

        public Criteria andPayStatueBetween(Integer value1, Integer value2) {
            addCriterion("pay_statue between", value1, value2, "payStatue");
            return (Criteria) this;
        }

        public Criteria andPayStatueNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_statue not between", value1, value2, "payStatue");
            return (Criteria) this;
        }

        public Criteria andSharePercentIsNull() {
            addCriterion("share_percent is null");
            return (Criteria) this;
        }

        public Criteria andSharePercentIsNotNull() {
            addCriterion("share_percent is not null");
            return (Criteria) this;
        }

        public Criteria andSharePercentEqualTo(BigDecimal value) {
            addCriterion("share_percent =", value, "sharePercent");
            return (Criteria) this;
        }

        public Criteria andSharePercentNotEqualTo(BigDecimal value) {
            addCriterion("share_percent <>", value, "sharePercent");
            return (Criteria) this;
        }

        public Criteria andSharePercentGreaterThan(BigDecimal value) {
            addCriterion("share_percent >", value, "sharePercent");
            return (Criteria) this;
        }

        public Criteria andSharePercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("share_percent >=", value, "sharePercent");
            return (Criteria) this;
        }

        public Criteria andSharePercentLessThan(BigDecimal value) {
            addCriterion("share_percent <", value, "sharePercent");
            return (Criteria) this;
        }

        public Criteria andSharePercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("share_percent <=", value, "sharePercent");
            return (Criteria) this;
        }

        public Criteria andSharePercentIn(List<BigDecimal> values) {
            addCriterion("share_percent in", values, "sharePercent");
            return (Criteria) this;
        }

        public Criteria andSharePercentNotIn(List<BigDecimal> values) {
            addCriterion("share_percent not in", values, "sharePercent");
            return (Criteria) this;
        }

        public Criteria andSharePercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("share_percent between", value1, value2, "sharePercent");
            return (Criteria) this;
        }

        public Criteria andSharePercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("share_percent not between", value1, value2, "sharePercent");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNull() {
            addCriterion("pay_date is null");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNotNull() {
            addCriterion("pay_date is not null");
            return (Criteria) this;
        }

        public Criteria andPayDateEqualTo(Date value) {
            addCriterion("pay_date =", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotEqualTo(Date value) {
            addCriterion("pay_date <>", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThan(Date value) {
            addCriterion("pay_date >", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_date >=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThan(Date value) {
            addCriterion("pay_date <", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThanOrEqualTo(Date value) {
            addCriterion("pay_date <=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateIn(List<Date> values) {
            addCriterion("pay_date in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotIn(List<Date> values) {
            addCriterion("pay_date not in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateBetween(Date value1, Date value2) {
            addCriterion("pay_date between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotBetween(Date value1, Date value2) {
            addCriterion("pay_date not between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusIsNull() {
            addCriterion("user_sign_status is null");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusIsNotNull() {
            addCriterion("user_sign_status is not null");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusEqualTo(Integer value) {
            addCriterion("user_sign_status =", value, "userSignStatus");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusNotEqualTo(Integer value) {
            addCriterion("user_sign_status <>", value, "userSignStatus");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusGreaterThan(Integer value) {
            addCriterion("user_sign_status >", value, "userSignStatus");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_sign_status >=", value, "userSignStatus");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusLessThan(Integer value) {
            addCriterion("user_sign_status <", value, "userSignStatus");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusLessThanOrEqualTo(Integer value) {
            addCriterion("user_sign_status <=", value, "userSignStatus");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusIn(List<Integer> values) {
            addCriterion("user_sign_status in", values, "userSignStatus");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusNotIn(List<Integer> values) {
            addCriterion("user_sign_status not in", values, "userSignStatus");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusBetween(Integer value1, Integer value2) {
            addCriterion("user_sign_status between", value1, value2, "userSignStatus");
            return (Criteria) this;
        }

        public Criteria andUserSignStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("user_sign_status not between", value1, value2, "userSignStatus");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueIsNull() {
            addCriterion("partner_sign_statue is null");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueIsNotNull() {
            addCriterion("partner_sign_statue is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueEqualTo(Integer value) {
            addCriterion("partner_sign_statue =", value, "partnerSignStatue");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueNotEqualTo(Integer value) {
            addCriterion("partner_sign_statue <>", value, "partnerSignStatue");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueGreaterThan(Integer value) {
            addCriterion("partner_sign_statue >", value, "partnerSignStatue");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueGreaterThanOrEqualTo(Integer value) {
            addCriterion("partner_sign_statue >=", value, "partnerSignStatue");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueLessThan(Integer value) {
            addCriterion("partner_sign_statue <", value, "partnerSignStatue");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueLessThanOrEqualTo(Integer value) {
            addCriterion("partner_sign_statue <=", value, "partnerSignStatue");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueIn(List<Integer> values) {
            addCriterion("partner_sign_statue in", values, "partnerSignStatue");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueNotIn(List<Integer> values) {
            addCriterion("partner_sign_statue not in", values, "partnerSignStatue");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueBetween(Integer value1, Integer value2) {
            addCriterion("partner_sign_statue between", value1, value2, "partnerSignStatue");
            return (Criteria) this;
        }

        public Criteria andPartnerSignStatueNotBetween(Integer value1, Integer value2) {
            addCriterion("partner_sign_statue not between", value1, value2, "partnerSignStatue");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeIsNull() {
            addCriterion("patner_code is null");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeIsNotNull() {
            addCriterion("patner_code is not null");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeEqualTo(String value) {
            addCriterion("patner_code =", value, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeNotEqualTo(String value) {
            addCriterion("patner_code <>", value, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeGreaterThan(String value) {
            addCriterion("patner_code >", value, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeGreaterThanOrEqualTo(String value) {
            addCriterion("patner_code >=", value, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeLessThan(String value) {
            addCriterion("patner_code <", value, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeLessThanOrEqualTo(String value) {
            addCriterion("patner_code <=", value, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeLike(String value) {
            addCriterion("patner_code like", value, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeNotLike(String value) {
            addCriterion("patner_code not like", value, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeIn(List<String> values) {
            addCriterion("patner_code in", values, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeNotIn(List<String> values) {
            addCriterion("patner_code not in", values, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeBetween(String value1, String value2) {
            addCriterion("patner_code between", value1, value2, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andPatnerCodeNotBetween(String value1, String value2) {
            addCriterion("patner_code not between", value1, value2, "patnerCode");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeIsNull() {
            addCriterion("user_sign_datetime is null");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeIsNotNull() {
            addCriterion("user_sign_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeEqualTo(Date value) {
            addCriterion("user_sign_datetime =", value, "userSignDatetime");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeNotEqualTo(Date value) {
            addCriterion("user_sign_datetime <>", value, "userSignDatetime");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeGreaterThan(Date value) {
            addCriterion("user_sign_datetime >", value, "userSignDatetime");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_sign_datetime >=", value, "userSignDatetime");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeLessThan(Date value) {
            addCriterion("user_sign_datetime <", value, "userSignDatetime");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("user_sign_datetime <=", value, "userSignDatetime");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeIn(List<Date> values) {
            addCriterion("user_sign_datetime in", values, "userSignDatetime");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeNotIn(List<Date> values) {
            addCriterion("user_sign_datetime not in", values, "userSignDatetime");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeBetween(Date value1, Date value2) {
            addCriterion("user_sign_datetime between", value1, value2, "userSignDatetime");
            return (Criteria) this;
        }

        public Criteria andUserSignDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("user_sign_datetime not between", value1, value2, "userSignDatetime");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeIsNull() {
            addCriterion("partner_sign_datetime is null");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeIsNotNull() {
            addCriterion("partner_sign_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeEqualTo(Date value) {
            addCriterion("partner_sign_datetime =", value, "partnerSignDatetime");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeNotEqualTo(Date value) {
            addCriterion("partner_sign_datetime <>", value, "partnerSignDatetime");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeGreaterThan(Date value) {
            addCriterion("partner_sign_datetime >", value, "partnerSignDatetime");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("partner_sign_datetime >=", value, "partnerSignDatetime");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeLessThan(Date value) {
            addCriterion("partner_sign_datetime <", value, "partnerSignDatetime");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("partner_sign_datetime <=", value, "partnerSignDatetime");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeIn(List<Date> values) {
            addCriterion("partner_sign_datetime in", values, "partnerSignDatetime");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeNotIn(List<Date> values) {
            addCriterion("partner_sign_datetime not in", values, "partnerSignDatetime");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeBetween(Date value1, Date value2) {
            addCriterion("partner_sign_datetime between", value1, value2, "partnerSignDatetime");
            return (Criteria) this;
        }

        public Criteria andPartnerSignDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("partner_sign_datetime not between", value1, value2, "partnerSignDatetime");
            return (Criteria) this;
        }

        public Criteria andUserIdCardIsNull() {
            addCriterion("user_id_card is null");
            return (Criteria) this;
        }

        public Criteria andUserIdCardIsNotNull() {
            addCriterion("user_id_card is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdCardEqualTo(String value) {
            addCriterion("user_id_card =", value, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardNotEqualTo(String value) {
            addCriterion("user_id_card <>", value, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardGreaterThan(String value) {
            addCriterion("user_id_card >", value, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("user_id_card >=", value, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardLessThan(String value) {
            addCriterion("user_id_card <", value, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardLessThanOrEqualTo(String value) {
            addCriterion("user_id_card <=", value, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardLike(String value) {
            addCriterion("user_id_card like", value, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardNotLike(String value) {
            addCriterion("user_id_card not like", value, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardIn(List<String> values) {
            addCriterion("user_id_card in", values, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardNotIn(List<String> values) {
            addCriterion("user_id_card not in", values, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardBetween(String value1, String value2) {
            addCriterion("user_id_card between", value1, value2, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andUserIdCardNotBetween(String value1, String value2) {
            addCriterion("user_id_card not between", value1, value2, "userIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardIsNull() {
            addCriterion("partner_id_card is null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardIsNotNull() {
            addCriterion("partner_id_card is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardEqualTo(String value) {
            addCriterion("partner_id_card =", value, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardNotEqualTo(String value) {
            addCriterion("partner_id_card <>", value, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardGreaterThan(String value) {
            addCriterion("partner_id_card >", value, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("partner_id_card >=", value, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardLessThan(String value) {
            addCriterion("partner_id_card <", value, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardLessThanOrEqualTo(String value) {
            addCriterion("partner_id_card <=", value, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardLike(String value) {
            addCriterion("partner_id_card like", value, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardNotLike(String value) {
            addCriterion("partner_id_card not like", value, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardIn(List<String> values) {
            addCriterion("partner_id_card in", values, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardNotIn(List<String> values) {
            addCriterion("partner_id_card not in", values, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardBetween(String value1, String value2) {
            addCriterion("partner_id_card between", value1, value2, "partnerIdCard");
            return (Criteria) this;
        }

        public Criteria andPartnerIdCardNotBetween(String value1, String value2) {
            addCriterion("partner_id_card not between", value1, value2, "partnerIdCard");
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