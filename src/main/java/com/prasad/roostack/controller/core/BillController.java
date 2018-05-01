package com.prasad.roostack.controller.core;

import com.prasad.roostack.bean.Bill;
import com.prasad.roostack.bean.Response;
import com.prasad.roostack.bean.User;
import com.prasad.roostack.bean.form.AddBillForm;
import com.prasad.roostack.bean.form.SearchBillForm;
import com.prasad.roostack.controller.SuperController;
import com.prasad.roostack.manager.BillManager;
import com.prasad.roostack.manager.UserManager;
import com.prasad.roostack.mapper.config.AddBillFormBillMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import static com.prasad.roostack.constants.CommonConstants.RESPONSE_MODEL;
import static com.prasad.roostack.constants.CommonConstants.USER_MODEL;
import static com.prasad.roostack.constants.PageConstants.GLOBAL_JSON_VIEW;

import static com.prasad.roostack.constants.URLConstants.*;
import static javax.servlet.http.HttpServletResponse.*;

/**
 * Created by Leeladurga Prasad Gunti on 17-Apr-2015 14:21.
 */

@Controller
@RequestMapping(BILL)
public class BillController extends SuperController {

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET},value = {ADD_BILL})
    public String processAddBill(HttpServletRequest request,
                                 @ModelAttribute(RESPONSE_MODEL)Response<Object> objectResponse,
                                 @ModelAttribute AddBillForm addBillForm,
                                 @ModelAttribute(USER_MODEL)User sessionUser,
                                 ModelMap modelMap) {

        if(sessionUser == null ) {
            throw new RuntimeException("Session User Don't exists ");
        }

        Bill bill = addBillFormBillMapper.map(addBillForm,Bill.class);

        bill.setBillOwner(sessionUser);
        bill.setBillDate(new Date());

        Integer billId = billManager.addBill(bill);
        objectResponse.setResponseObject("Bill added : "+billId);

        return GLOBAL_JSON_VIEW;
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET},value = {UPDATE_BILL})
    public String processUpdateBill(HttpServletRequest request,
                                    @ModelAttribute(RESPONSE_MODEL)Response<Object> objectResponse,
                                    @ModelAttribute AddBillForm updateBillForm,
                                    @ModelAttribute(USER_MODEL)User sessionUser) {
        if(sessionUser == null ) {
            throw new RuntimeException("Session User Don't exists ");
        }

        Bill bill = addBillFormBillMapper.map(updateBillForm,Bill.class);

        if(!(bill.getBillId() > 0)) {
            resultMessages.errorResultMessage("update.bill.invalid");
            objectResponse.setStatusCode(SC_BAD_REQUEST);
            return GLOBAL_JSON_VIEW;
        }

        Bill originalBill = billManager.getBill(bill.getBillId());

        if(null == originalBill) {
            resultMessages.errorResultMessage("update.bill.notfound",bill.getBillId());
            objectResponse.setStatusCode(SC_BAD_REQUEST);
            return GLOBAL_JSON_VIEW;
        }

        if(bill.getBillAmount() != originalBill.getBillAmount()) {
            originalBill.setBillAmount(bill.getBillAmount());
        }

        if(StringUtils.isNotEmpty(bill.getBillCategory())) {
            originalBill.setBillCategory(bill.getBillCategory());
        }

        if(StringUtils.isNotEmpty(bill.getBillComment())) {
            originalBill.setBillComment(bill.getBillComment());
        }

        Bill updatedBill = billManager.updateBill(originalBill);
        objectResponse.setStatusCode(SC_OK);
        objectResponse.setResponseObject(updatedBill);
        return GLOBAL_JSON_VIEW;
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET},value = {DELETE_BILL})
    public String processDeleteBill(HttpServletRequest request,
                                    @ModelAttribute(RESPONSE_MODEL)Response<Object> objectResponse,
                                    @ModelAttribute AddBillForm deleteBillForm,
                                    @ModelAttribute(USER_MODEL)User sessionUser) {
        if(sessionUser == null ) {
            throw new RuntimeException("Session User Don't exists ");
        }

        Bill bill = addBillFormBillMapper.map(deleteBillForm,Bill.class);

        if(!(bill.getBillId() > 0)) {
            resultMessages.errorResultMessage("delete.bill.invalid");
            objectResponse.setStatusCode(SC_BAD_REQUEST);
            return GLOBAL_JSON_VIEW;
        }

        Bill originalBill = billManager.getBill(bill.getBillId());

        if(null == originalBill) {
            resultMessages.errorResultMessage("delete.bill.notfound",bill.getBillId());
            objectResponse.setStatusCode(SC_BAD_REQUEST);
            return GLOBAL_JSON_VIEW;
        }

        try {
            billManager.deleteBill(originalBill);
        } catch (Exception e) {
            resultMessages.errorResultMessage("delete.bill.failed",bill.getBillId());
        }

        objectResponse.setStatusCode(SC_OK);
        return GLOBAL_JSON_VIEW;
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET},value = {SEARCH_BILL})
    public String getBillList(HttpServletRequest request,
                                 @ModelAttribute(RESPONSE_MODEL)Response<Object> objectResponse,
                                 @ModelAttribute SearchBillForm searchBillForm,
                                 @ModelAttribute(USER_MODEL)User sessionUser,
                                 ModelMap modelMap) {

        int month = searchBillForm.getBillMonth();
        if(month != 0 && !(month > 13)) {
            Date date = new Date();
            Calendar calendar =  Calendar.getInstance();
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH,1);
            Date monthStartDate = new Date(calendar.getTime().getTime());
            //calendar.set(Calendar.MONTH, month);
            calendar.roll(Calendar.DATE,-1);
            Date monthEndDate = new Date(calendar.getTime().getTime());
            searchBillForm.setBillStartDate(monthStartDate);
            searchBillForm.setBillEndDate(monthEndDate);
        }
        objectResponse.setStatusCode(SC_OK);
        List<Bill> bills =  billManager.getAllBills(searchBillForm);
        if(searchBillForm.isGetBillSummary() || searchBillForm.isGetBillTotal()) {
            processBillSummary(bills, searchBillForm.isGetBillTotal(), searchBillForm.isGetBillSummary());
        }
        objectResponse.setResponseObject(bills);
        return GLOBAL_JSON_VIEW;

    }

    private List<Bill> processBillSummary(List<Bill> bills, boolean getBillTotal, boolean getBillSummary) {
        int totalBillAmount = 0;
        Date currentBillDate = null;

        if(null == bills) {
            return bills;
        }

        List<User> users = userManager.getAllUsers();
        Map<String,Integer> userSummary = new HashMap<String, Integer>();
        for(User user : users) {
            userSummary.put(user.getUserName(),0);
        }

        if(CollectionUtils.isNotEmpty(bills)) {
            for (Bill bill : bills) {
                totalBillAmount += bill.getBillAmount();
                String userName = bill.getBillOwner().getUserName();
                int userAmount = bill.getBillAmount();
                if(userSummary.containsKey(userName)) {
                    userAmount += userSummary.get(userName);
                    userSummary.put(userName,userAmount);
                }

            }
        }



        if(CollectionUtils.isNotEmpty(bills) && null != bills.get(0)) {
            Bill firstBill = bills.get(0);
            if(null != firstBill.getBillDate()) {
                currentBillDate = firstBill.getBillDate();

                Calendar billCal = Calendar.getInstance();
                billCal.setTime(currentBillDate);

                Calendar currentCal = Calendar.getInstance();
                if(billCal.get(Calendar.MONTH) == currentCal.get(Calendar.MONTH)) {
                    currentBillDate = new Date();
                } else {
                    billCal.set(Calendar.DAY_OF_MONTH,billCal.getActualMaximum(Calendar.DAY_OF_MONTH));
                    currentBillDate = billCal.getTime();
                }

            }
        }

        if (getBillTotal) {
            Bill totalBill = new Bill();
            totalBill.setBillAmount(totalBillAmount);
            totalBill.setBillCategory("::: TOTAL :::");
            totalBill.setBillDate(currentBillDate);
            bills.add(totalBill);
        }

        if(!getBillSummary) {
            return bills;
        }



        for(Map.Entry<String,Integer> userEntry : userSummary.entrySet()) {
            Bill userSpendBill = new Bill();
            userSpendBill.setBillId(0);
            userSpendBill.setBillDate(currentBillDate);
            userSpendBill.setBillAmount(userEntry.getValue());
            userSpendBill.setBillCategory("Spent by " + userEntry.getKey().toUpperCase());
            bills.add(userSpendBill);
        }

        int totalPerPerson = totalBillAmount/userSummary.size();
        for(Map.Entry<String,Integer> userEntry : userSummary.entrySet()) {
            Bill userNeedToPay = new Bill();
            userNeedToPay.setBillId(0);
            userNeedToPay.setBillDate(currentBillDate);
            userNeedToPay.setBillAmount(totalPerPerson-userEntry.getValue());
            userNeedToPay.setBillCategory("Bill for " + userEntry.getKey().toUpperCase());
            bills.add(userNeedToPay);
        }

        return bills;
    }

    @Autowired
    private AddBillFormBillMapper addBillFormBillMapper;

    @Autowired
    private BillManager billManager;

    @Autowired
    private UserManager userManager;

}
