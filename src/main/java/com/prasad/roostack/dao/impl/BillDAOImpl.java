package com.prasad.roostack.dao.impl;

import com.prasad.roostack.bean.form.SearchBillForm;
import com.prasad.roostack.dao.BillDAO;
import com.prasad.roostack.dto.BillDTO;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Leeladurga Prasad Gunti on 17-Apr-2015 14:39.
 */
@Repository("billDAOImpl")
public class BillDAOImpl implements BillDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Logger logger = LoggerFactory.getLogger(BillDAOImpl.class);

    @Override
    public Integer addBill(BillDTO billDTO) {

        Integer billId = null;

        try {
            Session session = sessionFactory.getCurrentSession();
            billId = (Integer) session.save(billDTO);
        } catch (Exception e) {
            logger.error("unable to save bill ",e);
        }
        return billId;
    }

    @Override
    public BillDTO updateBill(BillDTO billDTO) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(billDTO);
        } catch (Exception e) {
            logger.error("unable to update bill ",e);
        }
        return billDTO;
    }

    @Override
    public void deleteBill(BillDTO billDTO) throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(billDTO);
        } catch (Exception e) {
            logger.error("unable to delete bill ",e);
        }
    }


    @Override
    public BillDTO getBill(int billId) {
        BillDTO billDTO = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(BillDTO.class);
            criteria.add(Restrictions.eq("billId", billId));
            billDTO = (BillDTO) criteria.list().get(0);
        } catch (Exception ex) {
            logger.error("unable to get bill by billId",ex);
        }
        return billDTO;
    }

    @Override
    public List<BillDTO> getAllBills(SearchBillForm searchBillForm) {
        List<BillDTO> billDTOs = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(BillDTO.class,"bill");
            criteria.createAlias("billOwner","billOwner");
            if(searchBillForm.getBillId() != 0) {
                criteria.add(Restrictions.eq("billId", searchBillForm.getBillId()));
            }
            if(searchBillForm.getBillCategory() != null ) {
                criteria.add(Restrictions.like("billCategory",searchBillForm.getBillCategory()));
            }
            if(searchBillForm.getBillComment() != null ) {
                criteria.add(Restrictions.like("billComment",searchBillForm.getBillComment()));
            }
            if(searchBillForm.getBillStartDate() != null ) {
                criteria.add(Restrictions.ge("billDate",searchBillForm.getBillStartDate()));
            }
            if(searchBillForm.getBillEndDate() != null ) {
                criteria.add(Restrictions.le("billDate",searchBillForm.getBillEndDate()));
            }
            if(searchBillForm.getBillOwner() != null) {

                if(searchBillForm.getBillOwner().getUserId() > 0) {
                    criteria.add(Restrictions.eq("billOwner.userId", searchBillForm.getBillOwner().getUserId()));
                } else if (StringUtils.isNotBlank(searchBillForm.getBillOwner().getUserName())) {
                    criteria.add(Restrictions.eq("billOwner.userName",searchBillForm.getBillOwner().getUserName()));
                }
            }
            billDTOs = criteria.list();
        } catch (Exception ex) {
            logger.error("unable to bills ",ex);
        }
        return billDTOs;
    }
}
