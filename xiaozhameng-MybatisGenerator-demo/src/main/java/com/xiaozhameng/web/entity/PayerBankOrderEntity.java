package com.xiaozhameng.web.entity;

import com.xiaozhameng.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class PayerBankOrderEntity extends BaseEntity {
    private Long id;

    private String payerOrderNo;

    private String payeeOrderNo;

    private String tradeSerialNo;

    private String routerInterfaceCode;

    private String payInterface;

    private String payWay;

    private BigDecimal orderAmount;

    private BigDecimal payAmount;

    private String cardType;

    private String bankCode;

    private String transactionNo;

    private String voucherNo;

    private String orderStatus;

    private String yeepayMerchantNo;

    private String yeepayMerchantName;

    private String driverType;

    private String couponInfo;

    private String platform;

    private String returnCode;

    private String returnMsg;

    private String bizCode;

    private String bizMsg;

    private Date createTime;

    private Date paySuccessTime;

    private Date bankSuccessTime;

    private Date lastModifyTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayerOrderNo() {
        return payerOrderNo;
    }

    public void setPayerOrderNo(String payerOrderNo) {
        this.payerOrderNo = payerOrderNo == null ? null : payerOrderNo.trim();
    }

    public String getPayeeOrderNo() {
        return payeeOrderNo;
    }

    public void setPayeeOrderNo(String payeeOrderNo) {
        this.payeeOrderNo = payeeOrderNo == null ? null : payeeOrderNo.trim();
    }

    public String getTradeSerialNo() {
        return tradeSerialNo;
    }

    public void setTradeSerialNo(String tradeSerialNo) {
        this.tradeSerialNo = tradeSerialNo == null ? null : tradeSerialNo.trim();
    }

    public String getRouterInterfaceCode() {
        return routerInterfaceCode;
    }

    public void setRouterInterfaceCode(String routerInterfaceCode) {
        this.routerInterfaceCode = routerInterfaceCode == null ? null : routerInterfaceCode.trim();
    }

    public String getPayInterface() {
        return payInterface;
    }

    public void setPayInterface(String payInterface) {
        this.payInterface = payInterface == null ? null : payInterface.trim();
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo == null ? null : transactionNo.trim();
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo == null ? null : voucherNo.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getYeepayMerchantNo() {
        return yeepayMerchantNo;
    }

    public void setYeepayMerchantNo(String yeepayMerchantNo) {
        this.yeepayMerchantNo = yeepayMerchantNo == null ? null : yeepayMerchantNo.trim();
    }

    public String getYeepayMerchantName() {
        return yeepayMerchantName;
    }

    public void setYeepayMerchantName(String yeepayMerchantName) {
        this.yeepayMerchantName = yeepayMerchantName == null ? null : yeepayMerchantName.trim();
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType == null ? null : driverType.trim();
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo == null ? null : couponInfo.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg == null ? null : returnMsg.trim();
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode == null ? null : bizCode.trim();
    }

    public String getBizMsg() {
        return bizMsg;
    }

    public void setBizMsg(String bizMsg) {
        this.bizMsg = bizMsg == null ? null : bizMsg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaySuccessTime() {
        return paySuccessTime;
    }

    public void setPaySuccessTime(Date paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    public Date getBankSuccessTime() {
        return bankSuccessTime;
    }

    public void setBankSuccessTime(Date bankSuccessTime) {
        this.bankSuccessTime = bankSuccessTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", payerOrderNo=").append(payerOrderNo);
        sb.append(", payeeOrderNo=").append(payeeOrderNo);
        sb.append(", tradeSerialNo=").append(tradeSerialNo);
        sb.append(", routerInterfaceCode=").append(routerInterfaceCode);
        sb.append(", payInterface=").append(payInterface);
        sb.append(", payWay=").append(payWay);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", cardType=").append(cardType);
        sb.append(", bankCode=").append(bankCode);
        sb.append(", transactionNo=").append(transactionNo);
        sb.append(", voucherNo=").append(voucherNo);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", yeepayMerchantNo=").append(yeepayMerchantNo);
        sb.append(", yeepayMerchantName=").append(yeepayMerchantName);
        sb.append(", driverType=").append(driverType);
        sb.append(", couponInfo=").append(couponInfo);
        sb.append(", platform=").append(platform);
        sb.append(", returnCode=").append(returnCode);
        sb.append(", returnMsg=").append(returnMsg);
        sb.append(", bizCode=").append(bizCode);
        sb.append(", bizMsg=").append(bizMsg);
        sb.append(", createTime=").append(createTime);
        sb.append(", paySuccessTime=").append(paySuccessTime);
        sb.append(", bankSuccessTime=").append(bankSuccessTime);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PayerBankOrderEntity other = (PayerBankOrderEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPayerOrderNo() == null ? other.getPayerOrderNo() == null : this.getPayerOrderNo().equals(other.getPayerOrderNo()))
            && (this.getPayeeOrderNo() == null ? other.getPayeeOrderNo() == null : this.getPayeeOrderNo().equals(other.getPayeeOrderNo()))
            && (this.getTradeSerialNo() == null ? other.getTradeSerialNo() == null : this.getTradeSerialNo().equals(other.getTradeSerialNo()))
            && (this.getRouterInterfaceCode() == null ? other.getRouterInterfaceCode() == null : this.getRouterInterfaceCode().equals(other.getRouterInterfaceCode()))
            && (this.getPayInterface() == null ? other.getPayInterface() == null : this.getPayInterface().equals(other.getPayInterface()))
            && (this.getPayWay() == null ? other.getPayWay() == null : this.getPayWay().equals(other.getPayWay()))
            && (this.getOrderAmount() == null ? other.getOrderAmount() == null : this.getOrderAmount().equals(other.getOrderAmount()))
            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
            && (this.getCardType() == null ? other.getCardType() == null : this.getCardType().equals(other.getCardType()))
            && (this.getBankCode() == null ? other.getBankCode() == null : this.getBankCode().equals(other.getBankCode()))
            && (this.getTransactionNo() == null ? other.getTransactionNo() == null : this.getTransactionNo().equals(other.getTransactionNo()))
            && (this.getVoucherNo() == null ? other.getVoucherNo() == null : this.getVoucherNo().equals(other.getVoucherNo()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getYeepayMerchantNo() == null ? other.getYeepayMerchantNo() == null : this.getYeepayMerchantNo().equals(other.getYeepayMerchantNo()))
            && (this.getYeepayMerchantName() == null ? other.getYeepayMerchantName() == null : this.getYeepayMerchantName().equals(other.getYeepayMerchantName()))
            && (this.getDriverType() == null ? other.getDriverType() == null : this.getDriverType().equals(other.getDriverType()))
            && (this.getCouponInfo() == null ? other.getCouponInfo() == null : this.getCouponInfo().equals(other.getCouponInfo()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getReturnCode() == null ? other.getReturnCode() == null : this.getReturnCode().equals(other.getReturnCode()))
            && (this.getReturnMsg() == null ? other.getReturnMsg() == null : this.getReturnMsg().equals(other.getReturnMsg()))
            && (this.getBizCode() == null ? other.getBizCode() == null : this.getBizCode().equals(other.getBizCode()))
            && (this.getBizMsg() == null ? other.getBizMsg() == null : this.getBizMsg().equals(other.getBizMsg()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getPaySuccessTime() == null ? other.getPaySuccessTime() == null : this.getPaySuccessTime().equals(other.getPaySuccessTime()))
            && (this.getBankSuccessTime() == null ? other.getBankSuccessTime() == null : this.getBankSuccessTime().equals(other.getBankSuccessTime()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPayerOrderNo() == null) ? 0 : getPayerOrderNo().hashCode());
        result = prime * result + ((getPayeeOrderNo() == null) ? 0 : getPayeeOrderNo().hashCode());
        result = prime * result + ((getTradeSerialNo() == null) ? 0 : getTradeSerialNo().hashCode());
        result = prime * result + ((getRouterInterfaceCode() == null) ? 0 : getRouterInterfaceCode().hashCode());
        result = prime * result + ((getPayInterface() == null) ? 0 : getPayInterface().hashCode());
        result = prime * result + ((getPayWay() == null) ? 0 : getPayWay().hashCode());
        result = prime * result + ((getOrderAmount() == null) ? 0 : getOrderAmount().hashCode());
        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
        result = prime * result + ((getCardType() == null) ? 0 : getCardType().hashCode());
        result = prime * result + ((getBankCode() == null) ? 0 : getBankCode().hashCode());
        result = prime * result + ((getTransactionNo() == null) ? 0 : getTransactionNo().hashCode());
        result = prime * result + ((getVoucherNo() == null) ? 0 : getVoucherNo().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getYeepayMerchantNo() == null) ? 0 : getYeepayMerchantNo().hashCode());
        result = prime * result + ((getYeepayMerchantName() == null) ? 0 : getYeepayMerchantName().hashCode());
        result = prime * result + ((getDriverType() == null) ? 0 : getDriverType().hashCode());
        result = prime * result + ((getCouponInfo() == null) ? 0 : getCouponInfo().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getReturnCode() == null) ? 0 : getReturnCode().hashCode());
        result = prime * result + ((getReturnMsg() == null) ? 0 : getReturnMsg().hashCode());
        result = prime * result + ((getBizCode() == null) ? 0 : getBizCode().hashCode());
        result = prime * result + ((getBizMsg() == null) ? 0 : getBizMsg().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getPaySuccessTime() == null) ? 0 : getPaySuccessTime().hashCode());
        result = prime * result + ((getBankSuccessTime() == null) ? 0 : getBankSuccessTime().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        return result;
    }
}