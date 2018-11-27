package com.xiaozhameng.web.entity;

import com.xiaozhameng.base.BaseEntity;

public class AsyncTaskRecords extends BaseEntity {
    private String taskRuleName;

    private String taskExecuteCount;

    private String taskNextExeTime;

    private String taskExecuteState;

    private String msgCommunicateCode;

    private String msgSerialNo;

    private String msgBankOrderNo;

    private String msgOrderCreateTime;

    private static final long serialVersionUID = 1L;

    public String getTaskRuleName() {
        return taskRuleName;
    }

    public void setTaskRuleName(String taskRuleName) {
        this.taskRuleName = taskRuleName == null ? null : taskRuleName.trim();
    }

    public String getTaskExecuteCount() {
        return taskExecuteCount;
    }

    public void setTaskExecuteCount(String taskExecuteCount) {
        this.taskExecuteCount = taskExecuteCount == null ? null : taskExecuteCount.trim();
    }

    public String getTaskNextExeTime() {
        return taskNextExeTime;
    }

    public void setTaskNextExeTime(String taskNextExeTime) {
        this.taskNextExeTime = taskNextExeTime == null ? null : taskNextExeTime.trim();
    }

    public String getTaskExecuteState() {
        return taskExecuteState;
    }

    public void setTaskExecuteState(String taskExecuteState) {
        this.taskExecuteState = taskExecuteState == null ? null : taskExecuteState.trim();
    }

    public String getMsgCommunicateCode() {
        return msgCommunicateCode;
    }

    public void setMsgCommunicateCode(String msgCommunicateCode) {
        this.msgCommunicateCode = msgCommunicateCode == null ? null : msgCommunicateCode.trim();
    }

    public String getMsgSerialNo() {
        return msgSerialNo;
    }

    public void setMsgSerialNo(String msgSerialNo) {
        this.msgSerialNo = msgSerialNo == null ? null : msgSerialNo.trim();
    }

    public String getMsgBankOrderNo() {
        return msgBankOrderNo;
    }

    public void setMsgBankOrderNo(String msgBankOrderNo) {
        this.msgBankOrderNo = msgBankOrderNo == null ? null : msgBankOrderNo.trim();
    }

    public String getMsgOrderCreateTime() {
        return msgOrderCreateTime;
    }

    public void setMsgOrderCreateTime(String msgOrderCreateTime) {
        this.msgOrderCreateTime = msgOrderCreateTime == null ? null : msgOrderCreateTime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskRuleName=").append(taskRuleName);
        sb.append(", taskExecuteCount=").append(taskExecuteCount);
        sb.append(", taskNextExeTime=").append(taskNextExeTime);
        sb.append(", taskExecuteState=").append(taskExecuteState);
        sb.append(", msgCommunicateCode=").append(msgCommunicateCode);
        sb.append(", msgSerialNo=").append(msgSerialNo);
        sb.append(", msgBankOrderNo=").append(msgBankOrderNo);
        sb.append(", msgOrderCreateTime=").append(msgOrderCreateTime);
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
        AsyncTaskRecords other = (AsyncTaskRecords) that;
        return (this.getTaskRuleName() == null ? other.getTaskRuleName() == null : this.getTaskRuleName().equals(other.getTaskRuleName()))
            && (this.getTaskExecuteCount() == null ? other.getTaskExecuteCount() == null : this.getTaskExecuteCount().equals(other.getTaskExecuteCount()))
            && (this.getTaskNextExeTime() == null ? other.getTaskNextExeTime() == null : this.getTaskNextExeTime().equals(other.getTaskNextExeTime()))
            && (this.getTaskExecuteState() == null ? other.getTaskExecuteState() == null : this.getTaskExecuteState().equals(other.getTaskExecuteState()))
            && (this.getMsgCommunicateCode() == null ? other.getMsgCommunicateCode() == null : this.getMsgCommunicateCode().equals(other.getMsgCommunicateCode()))
            && (this.getMsgSerialNo() == null ? other.getMsgSerialNo() == null : this.getMsgSerialNo().equals(other.getMsgSerialNo()))
            && (this.getMsgBankOrderNo() == null ? other.getMsgBankOrderNo() == null : this.getMsgBankOrderNo().equals(other.getMsgBankOrderNo()))
            && (this.getMsgOrderCreateTime() == null ? other.getMsgOrderCreateTime() == null : this.getMsgOrderCreateTime().equals(other.getMsgOrderCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskRuleName() == null) ? 0 : getTaskRuleName().hashCode());
        result = prime * result + ((getTaskExecuteCount() == null) ? 0 : getTaskExecuteCount().hashCode());
        result = prime * result + ((getTaskNextExeTime() == null) ? 0 : getTaskNextExeTime().hashCode());
        result = prime * result + ((getTaskExecuteState() == null) ? 0 : getTaskExecuteState().hashCode());
        result = prime * result + ((getMsgCommunicateCode() == null) ? 0 : getMsgCommunicateCode().hashCode());
        result = prime * result + ((getMsgSerialNo() == null) ? 0 : getMsgSerialNo().hashCode());
        result = prime * result + ((getMsgBankOrderNo() == null) ? 0 : getMsgBankOrderNo().hashCode());
        result = prime * result + ((getMsgOrderCreateTime() == null) ? 0 : getMsgOrderCreateTime().hashCode());
        return result;
    }
}