package com.prasad.roostack.dto;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Leeladurga Prasad Gunti on 17-Apr-2015 14:26.
 */
@Entity
@Table(name="bills")
public class BillDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "billId")
    private int billId;

    @Column(name = "billCategory")
    private String billCategory;

    @Column(name = "billAmount")
    private int billAmount;

    @Column(name = "billComment")
    private String billComment;

    @Column(name = "billDate")
    private Date billDate;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    private UserDTO billOwner;

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

    public UserDTO getBillOwner() {
        return billOwner;
    }

    public void setBillOwner(UserDTO billOwner) {
        this.billOwner = billOwner;
    }
}
