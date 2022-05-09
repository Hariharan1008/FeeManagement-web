package com.hari.logic;

import java.sql.Date;
import java.time.LocalDate;

import com.hari.Dao.FeesUpdationDao;
import com.hari.Dao.TotalAndPaidFeesFinderDao;
import com.hari.model.FeesStructure;

public class TotalAndPaidFeesFinderForFees {
	public static int feeValidator(String email,int amount) throws Exception
	{
		FeesStructure fee=TotalAndPaidFeesFinderDao.totalFeesFinder(email);
		int pendingFee=Integer.parseInt(fee.getFeesPending());
		if(amount>pendingFee)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	public static int feeUpdator(String email,int amount) throws Exception
	{
		FeesStructure fee=TotalAndPaidFeesFinderDao.totalFeesFinder(email);
		int feePaid=Integer.parseInt(fee.getFeesPaid());
		int feePending=Integer.parseInt(fee.getFeesPending());
		Date paidOn=Date.valueOf(LocalDate.now());
		int lastPaid=amount;
		feePaid=feePaid+amount;
		feePending=feePending-amount;
		FeesStructure feesStructure=new FeesStructure();
		feesStructure.setFeesPaid(Integer.toString(feePaid));
		feesStructure.setFeesPending(Integer.toString(feePending));
		feesStructure.setPaidOn(paidOn);
		feesStructure.setPayingAmount(Integer.toString(lastPaid));
		feesStructure.setEmail(email);
		int completed=FeesUpdationDao.feesUpdation(feesStructure);
		return completed;
		
		
		
	}

}
