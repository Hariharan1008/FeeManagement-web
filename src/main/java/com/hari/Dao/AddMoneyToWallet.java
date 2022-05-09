package com.feesmanagement.logic;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import com.feesmanagement.dao.FindWalletBalanceDao;
import com.feesmanagement.dao.UpdateWalletDao;
import com.feesmanagement.dao.UpdateWalletTransactionDao;
import com.feesmanagement.model.Wallet;

public class AddMoneyToWallet {
	public int addMoney(long mobile,long amount) throws ClassNotFoundException, SQLException
	{
		FindWalletBalanceDao findBalance=new FindWalletBalanceDao();
		long balance=findBalance.findWalletBalance(mobile);
		long updatedBalance=balance+amount;
		UpdateWalletDao update=new UpdateWalletDao();
		int completed=update.updateMyWallet(mobile, updatedBalance);
		if(completed==1)
		{
			UpdateWalletTransactionDao walletUpdate=new UpdateWalletTransactionDao();
			Wallet wallet=new Wallet();
			wallet.setAmount(amount);
			wallet.setMobile(mobile);
			wallet.setOperation("Added");
			wallet.setDate(Date.valueOf(LocalDate.now()));
			int updated=walletUpdate.updateWalletTransaction(wallet);
			if(updated==1)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return 0;
		}
	}

}
