package demo.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;

public class PartTimeUser {
    private Long id;

    private Date startJobDate;

    private String partQq;

    private Date recentOrderDate;

    private Integer getOrderNumber;

    private String problemRate;

    private String problemRateStr;

    private Integer outSettleCount;

    private Integer outDeliveryCount;

    private BigDecimal totalReward;

    private String major;

    private String englishLevel;

    private String acceptableSubject;

    private String education;

    private String school;

    private Integer age;

    private String partPhone;

    private String partAlipay;

    private String referrer;

    private Boolean masterHand;

    private String masterHandStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartJobDate() {
        return startJobDate;
    }

    public void setStartJobDate(Date startJobDate) {
        this.startJobDate = startJobDate;
    }

    public String getPartQq() {
        return partQq;
    }

    public void setPartQq(String partQq) {
        this.partQq = partQq == null ? null : partQq.trim();
    }

    public Date getRecentOrderDate() {
        return recentOrderDate;
    }

    public void setRecentOrderDate(Date recentOrderDate) {
        this.recentOrderDate = recentOrderDate;
    }

    public Integer getGetOrderNumber() {
        return getOrderNumber;
    }

    public void setGetOrderNumber(Integer getOrderNumber) {
        this.getOrderNumber = getOrderNumber;
    }

    public String getProblemRate() {
        return problemRate;
    }

    public void setProblemRate(String problemRate) {
        this.problemRate = problemRate == null ? null : problemRate.trim();
        if (problemRate != null) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            String percent = (numberFormat.format(Float.valueOf(problemRate) * 100)) + "%";
            this.problemRateStr = percent;
        }
    }

    public String getProblemRateStr() {
        return problemRateStr;
    }

    public Integer getOutSettleCount() {
        return outSettleCount;
    }

    public void setOutSettleCount(Integer outSettleCount) {
        this.outSettleCount = outSettleCount;
    }

    public Integer getOutDeliveryCount() {
        return outDeliveryCount;
    }

    public void setOutDeliveryCount(Integer outDeliveryCount) {
        this.outDeliveryCount = outDeliveryCount;
    }

    public BigDecimal getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(BigDecimal totalReward) {
        this.totalReward = totalReward.stripTrailingZeros();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel == null ? null : englishLevel.trim();
    }

    public String getAcceptableSubject() {
        return acceptableSubject;
    }

    public void setAcceptableSubject(String acceptableSubject) {
        this.acceptableSubject = acceptableSubject == null ? null : acceptableSubject.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPartPhone() {
        return partPhone;
    }

    public void setPartPhone(String partPhone) {
        this.partPhone = partPhone == null ? null : partPhone.trim();
    }

    public String getPartAlipay() {
        return partAlipay;
    }

    public void setPartAlipay(String partAlipay) {
        this.partAlipay = partAlipay == null ? null : partAlipay.trim();
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer == null ? null : referrer.trim();
    }

    public Boolean getMasterHand() {
        return masterHand;
    }

    public void setMasterHand(Boolean masterHand) {
        this.masterHand = masterHand;
        this.masterHandStr = masterHand ? "是" : "否";
    }

    public String getMasterHandStr() {
        return masterHandStr;
    }

    public void setMasterHandStr(String masterHandStr) {
        this.masterHandStr = masterHandStr;
    }
}