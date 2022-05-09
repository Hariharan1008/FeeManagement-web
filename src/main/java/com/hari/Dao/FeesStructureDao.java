package com.hari.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hari.Dao.ConnectionUtil;
import com.hari.model.FeesStructure;


public class FeesStructureDao {
	
	public static FeesStructure getFeesStructure(int year,String branch) throws Exception
	{
		Connection connection ;
	    PreparedStatement statement;
	    ResultSet rs = null;
	    connection = ConnectionUtil.databaseConnection();
		String query = "SELECT First_Semester_Fees,Second_Semester_Fees,Hostel_Fees,Transport_Fees FROM Fees_Structure WHERE Year=? AND Dept=?";
		statement = connection.prepareStatement(query);
        statement.setInt(1, year); 
        statement.setString(2, branch); 
        rs = statement.executeQuery();
		String firstSemesterFees=null;
		String secondSemesterFees=null;
		String hostelFees=null;
		String transportFees=null;
		
		FeesStructure fees=null;
		if(rs.next())
		{
			 fees = new FeesStructure();
			 firstSemesterFees=rs.getString("First_Semester_Fees");
			 secondSemesterFees=rs.getString("Second_Semester_Fees");
			 hostelFees=rs.getString("Hostel_Fees");
			 transportFees=rs.getString("Transport_Fees");
			 
			 
		}
		 fees.setFirstSemesterFees(firstSemesterFees);
		 fees.setSecondSemesterFees(firstSemesterFees);
		 fees.setHotelFees(hostelFees);
		 fees.setTransportFees(transportFees);
		 connection.close();
		 return fees;
		 
	}

}
