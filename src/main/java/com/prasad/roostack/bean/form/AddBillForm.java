package com.prasad.roostack.bean.form;

import com.prasad.roostack.bean.User;

import java.util.Date;

/**
 * Created by Leeladurga Prasad Gunti on 17-Apr-2015 15:42.
 */
public class AddBillForm {

    private int billId;
    private String billCategory;
    private int billAmount;
    private String billComment;
    private Date billDate;
    private User billOwner;

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

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public User getBillOwner() {
        return billOwner;
    }

    public void setBillOwner(User billOwner) {
        this.billOwner = billOwner;
    }
}
