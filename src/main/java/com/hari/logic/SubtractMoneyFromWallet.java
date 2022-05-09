package com.hari.logic;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import com.hari.Dao.FindWalletBalanceDao;
import com.hari.Dao.UpdateWalletTransactionDao;
import com.hari.Dao.WalletAndTransactionUpdatorDao;
import com.hari.model.Wallet;


public class SubtractMoneyFromWallet {
	public int walletUpdatiorAfterPayment(long mobile,long amount) throws ClassNotFoundException, SQLException
	{
		FindWalletBalanceDao findBalance=new FindWalletBalanceDao();
		long balance=findBalance.findWalletBalance(mobile);
		long updatedBalance=balance-amount;
		WalletAndTransactionUpdatorDao walletUpdate=new  WalletAndTransactionUpdatorDao();
		int completed=walletUpdate.walletUpdationAfterPayment(mobile, updatedBalance);
		if(completed==1)
		{
			UpdateWalletTransactionDao TransactionUpdate=new UpdateWalletTransactionDao();
			Wallet wallet=new Wallet();
			wallet.setAmount(amount);
			wallet.setMobile(mobile);
			wallet.setOperation("debited");
			wallet.setDate(Date.valueOf(LocalDate.now()));
			int updated=TransactionUpdate.updateWalletTransaction(wallet);
			return updated;
		}
		return 0;
	}

}
