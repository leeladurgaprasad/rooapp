package com.prasad.roostack.manager;

import com.prasad.roostack.bean.Bill;
import com.prasad.roostack.bean.form.SearchBillForm;

import java.util.List;

/**
 * Created by Leeladurga Prasad Gunti on 17-Apr-2015 15:23.
 */
public interface BillManager {
    public Integer addBill(Bill bill);
    public Bill updateBill(Bill bill);
    public void deleteBill(Bill bill) throws Exception;
    public Bill getBill(int billId);
    public List<Bill> getAllBills(SearchBillForm searchBillForm);
}
