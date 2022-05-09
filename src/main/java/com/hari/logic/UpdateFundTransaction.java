package com.hari.logic;

import java.sql.SQLException;

import com.hari.Dao.UpdateFundTransferTransactionsDao;
import com.hari.model.FundTransfer;


public class UpdateFundTransaction {
	public void updateWallet(FundTransfer fund) throws ClassNotFoundException, SQLException
	{
//		long senderUpdatedBalance=fund.getSenderBalance()-fund.getAmount();
//		long receiverUpdatedBalance=fund.getReceiverBalance()+fund.getAmount();
		AddMoneyToWallet update=new AddMoneyToWallet();
		SubtractMoneyFromWallet subtract=new SubtractMoneyFromWallet();
		int sended=update.addMoney(fund.getReceiverMobile(),fund.getAmount());
		int received=subtract.walletUpdatiorAfterPayment(fund.getSenderMobile(),fund.getAmount());
		UpdateFundTransferTransactionsDao updateTransaction=new UpdateFundTransferTransactionsDao();
		int completed=updateTransaction.updateFundTransaction(fund);
		System.out.println("completed");
		
		
	}

}
