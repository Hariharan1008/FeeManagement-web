package com.hari.logic;

import com.hari.Dao.FeesUpdationDao;
import com.hari.Dao.TotalAndPaidFeesFinder;
import com.hari.model.FeesStructure;

public class UpdateFeesAfterUsingWallet {
	public void adminTableUpdation(long amount) throws Exception
	{
		FeesStructure feesStructure=TotalAndPaidFeesFinder.totalFeesFinder();
		long paidFees=Long.parseLong(feesStructure.getFeesPaid());
		long pendingFees=Long.parseLong(feesStructure.getFeesPending());
		paidFees=paidFees+amount;
		pendingFees=pendingFees-amount;
		feesStructure.setFeesPaid(Long.toString(paidFees));
		feesStructure.setFeesPending(Long.toString(pendingFees));
		feesStructure.setPayingAmount(Long.toString(amount));
		FeesUpdationDao.feesUpdation(feesStructure);
	}

}
