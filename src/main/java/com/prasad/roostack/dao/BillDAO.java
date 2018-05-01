package com.prasad.roostack.dao;

import com.prasad.roostack.bean.form.SearchBillForm;
import com.prasad.roostack.dto.BillDTO;

import java.util.List;

/**
 * Created by Leeladurga Prasad Gunti on 17-Apr-2015 14:36.
 */
public interface BillDAO {
    public Integer addBill(BillDTO billDTO);
    public BillDTO updateBill(BillDTO billDTO);
    public void deleteBill(BillDTO billDTO) throws Exception;
    public BillDTO getBill(int billId);
    public List<BillDTO> getAllBills(SearchBillForm searchBillForm);
}
