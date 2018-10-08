package demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PartTimeUser {
    private Long id;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startJobDate;

    private String partQq;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date recentOrderDate;

    private Integer getOrderNumber;

    private String problemRate;

    private Integer outSettleCount;

    private Integer outDeliveryCount;

    private String totalReward;

    private String major;

    private String englishLevel;

    private String acceptableSubject;

    private String education;

    private String school;

    private Integer age;

    private String partPhone;

    private String partAlipay;

    private String referrer;

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

    public String getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(String totalReward) {
        this.totalReward = totalReward == null ? null : totalReward.trim();
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
}