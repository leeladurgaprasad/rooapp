package com.prasad.roostack.bean.form;

import com.prasad.roostack.bean.User;

import java.util.Date;

/**
 * Created by Leeladurga Prasad Gunti on 17-Apr-2015 15:42.
 */
public class SearchBillForm {

    private int billId;
    private String billCategory;
    private int billAmount;
    private String billComment;
    private Date billStartDate;
    private Date billEndDate;
    private int billMonth;
    private User billOwner;
    private boolean getBillTotal;
    private boolean getBillSummary;


    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillCategory() {
        return billCategory;
    }

    public void setBillCategory(String billCategory) {
        this.billCategory = billCategory;
    }

    public int getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillComment() {
        return billComment;
    }

    public void setBillComment(String billComment) {
        this.billComment = billComment;
    }

    public Date getBillStartDate() {
        return billStartDate;
    }

    public void setBillStartDate(Date billStartDate) {
        this.billStartDate = billStartDate;
    }

    public User getBillOwner() {
        return billOwner;
    }

    public void setBillOwner(User billOwner) {
        this.billOwner = billOwner;
    }

    public Date getBillEndDate() {
        return billEndDate;
    }

    public void setBillEndDate(Date billEndDate) {
        this.billEndDate = billEndDate;
    }

    public int getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(int billMonth) {
        this.billMonth = billMonth;
    }

    public boolean isGetBillTotal() {
        return getBillTotal;
    }

    public void setGetBillTotal(boolean getBillTotal) {
        this.getBillTotal = getBillTotal;
    }

    public boolean isGetBillSummary() {
        return getBillSummary;
    }

    public void setGetBillSummary(boolean getBillSummary) {
        this.getBillSummary = getBillSummary;
    }
}
