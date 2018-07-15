package java.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartTimeEntityExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    public PartTimeEntityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPartQqIsNull() {
            addCriterion("part__qq is null");
            return (Criteria) this;
        }

        public Criteria andPartQqIsNotNull() {
            addCriterion("part__qq is not null");
            return (Criteria) this;
        }

        public Criteria andPartQqEqualTo(Date value) {
            addCriterion("part__qq =", value, "partQq");
            return (Criteria) this;
        }

        public Criteria andPartQqNotEqualTo(Date value) {
            addCriterion("part__qq <>", value, "partQq");
            return (Criteria) this;
        }

        public Criteria andPartQqGreaterThan(Date value) {
            addCriterion("part__qq >", value, "partQq");
            return (Criteria) this;
        }

        public Criteria andPartQqGreaterThanOrEqualTo(Date value) {
            addCriterion("part__qq >=", value, "partQq");
            return (Criteria) this;
        }

        public Criteria andPartQqLessThan(Date value) {
            addCriterion("part__qq <", value, "partQq");
            return (Criteria) this;
        }

        public Criteria andPartQqLessThanOrEqualTo(Date value) {
            addCriterion("part__qq <=", value, "partQq");
            return (Criteria) this;
        }

        public Criteria andPartQqIn(List<Date> values) {
            addCriterion("part__qq in", values, "partQq");
            return (Criteria) this;
        }

        public Criteria andPartQqNotIn(List<Date> values) {
            addCriterion("part__qq not in", values, "partQq");
            return (Criteria) this;
        }

        public Criteria andPartQqBetween(Date value1, Date value2) {
            addCriterion("part__qq between", value1, value2, "partQq");
            return (Criteria) this;
        }

        public Criteria andPartQqNotBetween(Date value1, Date value2) {
            addCriterion("part__qq not between", value1, value2, "partQq");
            return (Criteria) this;
        }

        public Criteria andSubmitStateIsNull() {
            addCriterion("submit_state is null");
            return (Criteria) this;
        }

        public Criteria andSubmitStateIsNotNull() {
            addCriterion("submit_state is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitStateEqualTo(String value) {
            addCriterion("submit_state =", value, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateNotEqualTo(String value) {
            addCriterion("submit_state <>", value, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateGreaterThan(String value) {
            addCriterion("submit_state >", value, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateGreaterThanOrEqualTo(String value) {
            addCriterion("submit_state >=", value, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateLessThan(String value) {
            addCriterion("submit_state <", value, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateLessThanOrEqualTo(String value) {
            addCriterion("submit_state <=", value, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateLike(String value) {
            addCriterion("submit_state like", value, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateNotLike(String value) {
            addCriterion("submit_state not like", value, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateIn(List<String> values) {
            addCriterion("submit_state in", values, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateNotIn(List<String> values) {
            addCriterion("submit_state not in", values, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateBetween(String value1, String value2) {
            addCriterion("submit_state between", value1, value2, "submitState");
            return (Criteria) this;
        }

        public Criteria andSubmitStateNotBetween(String value1, String value2) {
            addCriterion("submit_state not between", value1, value2, "submitState");
            return (Criteria) this;
        }

        public Criteria andPartPhoneIsNull() {
            addCriterion("part__phone is null");
            return (Criteria) this;
        }

        public Criteria andPartPhoneIsNotNull() {
            addCriterion("part__phone is not null");
            return (Criteria) this;
        }

        public Criteria andPartPhoneEqualTo(Date value) {
            addCriterion("part__phone =", value, "partPhone");
            return (Criteria) this;
        }

        public Criteria andPartPhoneNotEqualTo(Date value) {
            addCriterion("part__phone <>", value, "partPhone");
            return (Criteria) this;
        }

        public Criteria andPartPhoneGreaterThan(Date value) {
            addCriterion("part__phone >", value, "partPhone");
            return (Criteria) this;
        }

        public Criteria andPartPhoneGreaterThanOrEqualTo(Date value) {
            addCriterion("part__phone >=", value, "partPhone");
            return (Criteria) this;
        }

        public Criteria andPartPhoneLessThan(Date value) {
            addCriterion("part__phone <", value, "partPhone");
            return (Criteria) this;
        }

        public Criteria andPartPhoneLessThanOrEqualTo(Date value) {
            addCriterion("part__phone <=", value, "partPhone");
            return (Criteria) this;
        }

        public Criteria andPartPhoneIn(List<Date> values) {
            addCriterion("part__phone in", values, "partPhone");
            return (Criteria) this;
        }

        public Criteria andPartPhoneNotIn(List<Date> values) {
            addCriterion("part__phone not in", values, "partPhone");
            return (Criteria) this;
        }

        public Criteria andPartPhoneBetween(Date value1, Date value2) {
            addCriterion("part__phone between", value1, value2, "partPhone");
            return (Criteria) this;
        }

        public Criteria andPartPhoneNotBetween(Date value1, Date value2) {
            addCriterion("part__phone not between", value1, value2, "partPhone");
            return (Criteria) this;
        }

        public Criteria andPartAlipayIsNull() {
            addCriterion("part_alipay is null");
            return (Criteria) this;
        }

        public Criteria andPartAlipayIsNotNull() {
            addCriterion("part_alipay is not null");
            return (Criteria) this;
        }

        public Criteria andPartAlipayEqualTo(String value) {
            addCriterion("part_alipay =", value, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayNotEqualTo(String value) {
            addCriterion("part_alipay <>", value, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayGreaterThan(String value) {
            addCriterion("part_alipay >", value, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayGreaterThanOrEqualTo(String value) {
            addCriterion("part_alipay >=", value, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayLessThan(String value) {
            addCriterion("part_alipay <", value, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayLessThanOrEqualTo(String value) {
            addCriterion("part_alipay <=", value, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayLike(String value) {
            addCriterion("part_alipay like", value, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayNotLike(String value) {
            addCriterion("part_alipay not like", value, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayIn(List<String> values) {
            addCriterion("part_alipay in", values, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayNotIn(List<String> values) {
            addCriterion("part_alipay not in", values, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayBetween(String value1, String value2) {
            addCriterion("part_alipay between", value1, value2, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartAlipayNotBetween(String value1, String value2) {
            addCriterion("part_alipay not between", value1, value2, "partAlipay");
            return (Criteria) this;
        }

        public Criteria andPartMoneyIsNull() {
            addCriterion("part_money is null");
            return (Criteria) this;
        }

        public Criteria andPartMoneyIsNotNull() {
            addCriterion("part_money is not null");
            return (Criteria) this;
        }

        public Criteria andPartMoneyEqualTo(String value) {
            addCriterion("part_money =", value, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyNotEqualTo(String value) {
            addCriterion("part_money <>", value, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyGreaterThan(String value) {
            addCriterion("part_money >", value, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("part_money >=", value, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyLessThan(String value) {
            addCriterion("part_money <", value, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyLessThanOrEqualTo(String value) {
            addCriterion("part_money <=", value, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyLike(String value) {
            addCriterion("part_money like", value, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyNotLike(String value) {
            addCriterion("part_money not like", value, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyIn(List<String> values) {
            addCriterion("part_money in", values, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyNotIn(List<String> values) {
            addCriterion("part_money not in", values, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyBetween(String value1, String value2) {
            addCriterion("part_money between", value1, value2, "partMoney");
            return (Criteria) this;
        }

        public Criteria andPartMoneyNotBetween(String value1, String value2) {
            addCriterion("part_money not between", value1, value2, "partMoney");
            return (Criteria) this;
        }

        public Criteria andDeductIsNull() {
            addCriterion("deduct is null");
            return (Criteria) this;
        }

        public Criteria andDeductIsNotNull() {
            addCriterion("deduct is not null");
            return (Criteria) this;
        }

        public Criteria andDeductEqualTo(String value) {
            addCriterion("deduct =", value, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductNotEqualTo(String value) {
            addCriterion("deduct <>", value, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductGreaterThan(String value) {
            addCriterion("deduct >", value, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductGreaterThanOrEqualTo(String value) {
            addCriterion("deduct >=", value, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductLessThan(String value) {
            addCriterion("deduct <", value, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductLessThanOrEqualTo(String value) {
            addCriterion("deduct <=", value, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductLike(String value) {
            addCriterion("deduct like", value, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductNotLike(String value) {
            addCriterion("deduct not like", value, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductIn(List<String> values) {
            addCriterion("deduct in", values, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductNotIn(List<String> values) {
            addCriterion("deduct not in", values, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductBetween(String value1, String value2) {
            addCriterion("deduct between", value1, value2, "deduct");
            return (Criteria) this;
        }

        public Criteria andDeductNotBetween(String value1, String value2) {
            addCriterion("deduct not between", value1, value2, "deduct");
            return (Criteria) this;
        }

        public Criteria andSettleDateIsNull() {
            addCriterion("settle_date is null");
            return (Criteria) this;
        }

        public Criteria andSettleDateIsNotNull() {
            addCriterion("settle_date is not null");
            return (Criteria) this;
        }

        public Criteria andSettleDateEqualTo(Date value) {
            addCriterion("settle_date =", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateNotEqualTo(Date value) {
            addCriterion("settle_date <>", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateGreaterThan(Date value) {
            addCriterion("settle_date >", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateGreaterThanOrEqualTo(Date value) {
            addCriterion("settle_date >=", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateLessThan(Date value) {
            addCriterion("settle_date <", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateLessThanOrEqualTo(Date value) {
            addCriterion("settle_date <=", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateIn(List<Date> values) {
            addCriterion("settle_date in", values, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateNotIn(List<Date> values) {
            addCriterion("settle_date not in", values, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateBetween(Date value1, Date value2) {
            addCriterion("settle_date between", value1, value2, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateNotBetween(Date value1, Date value2) {
            addCriterion("settle_date not between", value1, value2, "settleDate");
            return (Criteria) this;
        }

        public Criteria andPartRemarkIsNull() {
            addCriterion("part_remark is null");
            return (Criteria) this;
        }

        public Criteria andPartRemarkIsNotNull() {
            addCriterion("part_remark is not null");
            return (Criteria) this;
        }

        public Criteria andPartRemarkEqualTo(String value) {
            addCriterion("part_remark =", value, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkNotEqualTo(String value) {
            addCriterion("part_remark <>", value, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkGreaterThan(String value) {
            addCriterion("part_remark >", value, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("part_remark >=", value, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkLessThan(String value) {
            addCriterion("part_remark <", value, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkLessThanOrEqualTo(String value) {
            addCriterion("part_remark <=", value, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkLike(String value) {
            addCriterion("part_remark like", value, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkNotLike(String value) {
            addCriterion("part_remark not like", value, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkIn(List<String> values) {
            addCriterion("part_remark in", values, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkNotIn(List<String> values) {
            addCriterion("part_remark not in", values, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkBetween(String value1, String value2) {
            addCriterion("part_remark between", value1, value2, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartRemarkNotBetween(String value1, String value2) {
            addCriterion("part_remark not between", value1, value2, "partRemark");
            return (Criteria) this;
        }

        public Criteria andPartAuditIsNull() {
            addCriterion("part_audit is null");
            return (Criteria) this;
        }

        public Criteria andPartAuditIsNotNull() {
            addCriterion("part_audit is not null");
            return (Criteria) this;
        }

        public Criteria andPartAuditEqualTo(String value) {
            addCriterion("part_audit =", value, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditNotEqualTo(String value) {
            addCriterion("part_audit <>", value, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditGreaterThan(String value) {
            addCriterion("part_audit >", value, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditGreaterThanOrEqualTo(String value) {
            addCriterion("part_audit >=", value, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditLessThan(String value) {
            addCriterion("part_audit <", value, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditLessThanOrEqualTo(String value) {
            addCriterion("part_audit <=", value, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditLike(String value) {
            addCriterion("part_audit like", value, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditNotLike(String value) {
            addCriterion("part_audit not like", value, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditIn(List<String> values) {
            addCriterion("part_audit in", values, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditNotIn(List<String> values) {
            addCriterion("part_audit not in", values, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditBetween(String value1, String value2) {
            addCriterion("part_audit between", value1, value2, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartAuditNotBetween(String value1, String value2) {
            addCriterion("part_audit not between", value1, value2, "partAudit");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateIsNull() {
            addCriterion("part_settle_state is null");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateIsNotNull() {
            addCriterion("part_settle_state is not null");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateEqualTo(String value) {
            addCriterion("part_settle_state =", value, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateNotEqualTo(String value) {
            addCriterion("part_settle_state <>", value, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateGreaterThan(String value) {
            addCriterion("part_settle_state >", value, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateGreaterThanOrEqualTo(String value) {
            addCriterion("part_settle_state >=", value, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateLessThan(String value) {
            addCriterion("part_settle_state <", value, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateLessThanOrEqualTo(String value) {
            addCriterion("part_settle_state <=", value, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateLike(String value) {
            addCriterion("part_settle_state like", value, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateNotLike(String value) {
            addCriterion("part_settle_state not like", value, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateIn(List<String> values) {
            addCriterion("part_settle_state in", values, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateNotIn(List<String> values) {
            addCriterion("part_settle_state not in", values, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateBetween(String value1, String value2) {
            addCriterion("part_settle_state between", value1, value2, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andPartSettleStateNotBetween(String value1, String value2) {
            addCriterion("part_settle_state not between", value1, value2, "partSettleState");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkIsNull() {
            addCriterion("finance_remark is null");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkIsNotNull() {
            addCriterion("finance_remark is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkEqualTo(String value) {
            addCriterion("finance_remark =", value, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkNotEqualTo(String value) {
            addCriterion("finance_remark <>", value, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkGreaterThan(String value) {
            addCriterion("finance_remark >", value, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("finance_remark >=", value, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkLessThan(String value) {
            addCriterion("finance_remark <", value, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkLessThanOrEqualTo(String value) {
            addCriterion("finance_remark <=", value, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkLike(String value) {
            addCriterion("finance_remark like", value, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkNotLike(String value) {
            addCriterion("finance_remark not like", value, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkIn(List<String> values) {
            addCriterion("finance_remark in", values, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkNotIn(List<String> values) {
            addCriterion("finance_remark not in", values, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkBetween(String value1, String value2) {
            addCriterion("finance_remark between", value1, value2, "financeRemark");
            return (Criteria) this;
        }

        public Criteria andFinanceRemarkNotBetween(String value1, String value2) {
            addCriterion("finance_remark not between", value1, value2, "financeRemark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table oa_part_time
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table oa_part_time
     *
     * @mbg.generated
     */
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