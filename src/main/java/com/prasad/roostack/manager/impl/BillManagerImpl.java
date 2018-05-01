package com.prasad.roostack.manager.impl;

import com.prasad.roostack.bean.Bill;
import com.prasad.roostack.bean.form.SearchBillForm;
import com.prasad.roostack.dao.BillDAO;
import com.prasad.roostack.dto.BillDTO;
import com.prasad.roostack.manager.BillManager;
import com.prasad.roostack.mapper.config.BillDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leeladurga Prasad Gunti on 17-Apr-2015 15:23.
 */
@Service("billManager")
public class BillManagerImpl implements BillManager {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Integer addBill(Bill bill) {
        BillDTO billDTO = billDTOMapper.map(bill,BillDTO.class);
        return billDAO.addBill(billDTO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Bill updateBill(Bill bill) {
        BillDTO billDTO = billDTOMapper.map(bill,BillDTO.class);
        return billDTOMapper.map(billDAO.updateBill(billDTO),Bill.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteBill(Bill bill) throws Exception{
        BillDTO billDTO = billDTOMapper.map(bill,BillDTO.class);
        billDAO.deleteBill(billDTO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Bill getBill(int billId) {
        BillDTO billDTO = billDAO.getBill(billId);
        return billDTOMapper.map(billDTO,Bill.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Bill> getAllBills(SearchBillForm searchBillForm) {
        List<Bill> bills = new ArrayList<Bill>();
        List<BillDTO> billDTOs = billDAO.getAllBills(searchBillForm);
        for(BillDTO billDTO : billDTOs) {
            bills.add(billDTOMapper.map(billDTO,Bill.class));
        }
        return bills;
    }

    @Autowired
    private BillDTOMapper billDTOMapper;

    @Autowired
    private BillDAO billDAO;
}
