package com.mvc.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mvc.data.Country;
import com.mvc.data.MatchedMT;
import com.mvc.data.SubmittedMT;


public class QueryExecutor {
    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
    
    public QueryExecutor(){
    	try {
            Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            this.dataSource = (DataSource)envContext.lookup("jdbc/moneytransfer");
        	} 
    		catch (NamingException e) {
            e.printStackTrace();
    		}
    }
    public String[] returnUserData(String useremail){
    	String userdata[] = new String[2];
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM USERS where email='" + useremail + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	userdata[0] = resultSet.getString(1);
            	userdata[1] = resultSet.getString(3);
            }
            if(userdata[0] == null) return null;
            else return userdata;
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	return null;
        	}
        	finally {
        	try { 
        		if(null!=resultSet)resultSet.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }

    public List<SubmittedMT> returnUserSubmittedMTs(String useremail){
    	List<SubmittedMT> smtc = new ArrayList<SubmittedMT>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            //String query = "SELECT idusedleaves, email,type,fromdate,todate,days,approved,idmanager FROM ebookshop.users, ebookshop.usedleaves, ebookshop.leavetype where ebookshop.leavetype.idleavetype=ebookshop.usedleaves.idleavetype and ebookshop.users.idusers=ebookshop.usedleaves.iduser and email='" + useremail + "'";
            
            String query = "SELECT idsubmittedmts, idsubmittedby,idfromcountry,idtocountry,beforedatemt,c1.name as fromcountry,c2.name as tocountry, email, amount FROM users, submittedmts, countries c1, countries c2 where users.idusers=submittedmts.idsubmittedby and c1.idcountry=submittedmts.idfromcountry and c2.idcountry=submittedmts.idtocountry and email='" + useremail + "'";
            
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	SubmittedMT smt = new SubmittedMT();
            	smt.setIdSubmittedMT(resultSet.getInt(1));
            	smt.setIdSubmittedBy(resultSet.getString(2));
            	smt.setIdFromCountry(resultSet.getString(3));
            	smt.setIdToCountry(resultSet.getString(4));
            	smt.setBeforeDateMT(resultSet.getString(5));
            	smt.setFromCountry(resultSet.getString(6));
            	smt.setToCountry(resultSet.getString(7));
            	smt.setEmailSubmittedBy(resultSet.getString(8));
            	smt.setAmount(resultSet.getInt(9));            	
            	smtc.add(smt);
            }
        	return smtc;
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	return smtc;
        	}
        	finally {
        	try { 
        		if(null!=resultSet)resultSet.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    } 
    
    public List<MatchedMT> returnUserMatchedMTs(String useremail){
    	List<MatchedMT> mmtc = new ArrayList<MatchedMT>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            //String query = "SELECT idusedleaves, email,type,fromdate,todate,days,approved,idmanager FROM ebookshop.users, ebookshop.usedleaves, ebookshop.leavetype where ebookshop.leavetype.idleavetype=ebookshop.usedleaves.idleavetype and ebookshop.users.idusers=ebookshop.usedleaves.iduser and email='" + useremail + "'";
            
            String query = "SELECT s2.idsubmittedmts as idmatchedmts, s2.idsubmittedby, s2.idfromcountry, s2.idtocountry, s2.beforedatemt,c1.name as fromcountry,c2.name as tocountry, u2.email, s2.amount FROM users u1, users u2, submittedmts s1,submittedmts s2, countries c1, countries c2 where s1.idfromcountry=s2.idtocountry and s1.idtocountry=s2.idfromcountry and u1.idusers=s1.idsubmittedby and u2.idusers = s2.idsubmittedby and u1.email = '" + useremail + "'";
            
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	MatchedMT mmt = new MatchedMT();
            	mmt.setIdMatchedMT(resultSet.getInt(1));
            	mmt.setIdSubmittedBy(resultSet.getString(2));
            	mmt.setIdFromCountry(resultSet.getString(3));
            	mmt.setIdToCountry(resultSet.getString(4));
            	mmt.setBeforeDateMT(resultSet.getString(5));
            	mmt.setFromCountry(resultSet.getString(6));
            	mmt.setToCountry(resultSet.getString(7));
            	mmt.setEmailSubmittedBy(resultSet.getString(8));
            	mmt.setAmount(resultSet.getInt(9));            	
            	mmtc.add(mmt);
            }
        	return mmtc;
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	return mmtc;
        	}
        	finally {
        	try { 
        		if(null!=resultSet)resultSet.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }
    
    public List<Country> returnCountries() {
    	List<Country> cc = new ArrayList<Country>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            //String query = "SELECT idusedleaves, email,type,fromdate,todate,days,approved,idmanager FROM ebookshop.users, ebookshop.usedleaves, ebookshop.leavetype where ebookshop.leavetype.idleavetype=ebookshop.usedleaves.idleavetype and ebookshop.users.idusers=ebookshop.usedleaves.iduser and email='" + useremail + "'";
            
            String query = "Select * from countries";
            
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	Country c = new Country();
            	c.setIdCountry(resultSet.getInt(1));
            	c.setNameCountry(resultSet.getString(2));
            	cc.add(c);
            }
        	return cc;
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	return cc;
        	}
        	finally {
        	try { 
        		if(null!=resultSet)resultSet.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }     

    public boolean submitSignUpData(String userData[]){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "INSERT INTO users (firstname, lastname, email, password) VALUES ('"+userData[1]+"','"+userData[2]+"', '"+userData[3]+"','"+userData[4]+"')";
            int rows = statement.executeUpdate(query);
            return true;
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
            return false;
        	}
        	finally {
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }  

    public boolean submitCreateMTData(String createMTData[]){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "INSERT INTO submittedmts (amount, idFromCountry, idToCountry, beforeDateMT, idSubmittedBy) VALUES ('"+createMTData[0]+"','"+createMTData[1]+"', '"+createMTData[2]+"','"+createMTData[3]+"','"+createMTData[4]+"')";
            int rows = statement.executeUpdate(query);
            return true;
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
            return false;
        	}
        	finally {
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }     
}
