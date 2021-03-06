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
import com.mvc.data.MT;
import com.mvc.data.Interest;


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
    	String userdata[] = new String[5];
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM USERS where email='" + useremail + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	userdata[0] = resultSet.getString(1);
            	userdata[1] = resultSet.getString(3);
            	userdata[2] = resultSet.getString(4);
            	userdata[3] = resultSet.getString(5);
            	userdata[4] = resultSet.getString(6);
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

    public List<MT> returnUserSubmittedMTs(String usereid){
    	List<MT> smtc = new ArrayList<MT>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT s1.idsubmittedmts, s1.idsubmittedby,s1.idfromcountry,s1.idtocountry,s1.beforedatemt,c1.name as fromcountry, c2.name as tocountry, u1.email, s1.amount, u2.email as interested, u2.idusers FROM submittedmts s1, submittedmts s2,countries c1, countries c2, users u1, users u2, interests i where c1.idcountry=s1.idfromcountry and c2.idcountry=s1.idtocountry and u1.idusers = s1.idsubmittedby and s1.idsubmittedby= "+ usereid +" and s2.idsubmittedmts = i.idsubmittedmt and i.removed = 0 and i.iduser = u2.idusers group by interested";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	MT smt = new MT();
            	smt.setIdMT(resultSet.getInt(1));
            	smt.setIdSubmittedBy(resultSet.getString(2));
            	smt.setIdFromCountry(resultSet.getString(3));
            	smt.setIdToCountry(resultSet.getString(4));
            	smt.setBeforeDateMT(resultSet.getString(5));
            	smt.setFromCountry(resultSet.getString(6));
            	smt.setToCountry(resultSet.getString(7));
            	smt.setEmailSubmittedBy(resultSet.getString(8));
            	smt.setAmount(resultSet.getInt(9));     
            	smt.setInterested(resultSet.getString(10));
            	smt.setIdInterested(resultSet.getString(11));             	
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
    
    public List<MT> returnUserMatchedMTs(String usereid){
    	List<MT> mmtc = new ArrayList<MT>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT s1.idsubmittedmts, s1.idSubmittedBy, s1.idFromCountry, s1.idToCountry, s1.beforeDateMT, c1.name, c2.name, u1.email, s1.amount from submittedmts s1,submittedmts s2, users u1, countries c1, countries c2 where s1.idSubmittedBy != "+usereid+" and s1.idFromCountry = s2.idToCountry and s1.idToCountry = s2.idFromCountry and u1.idusers = s1.idsubmittedby and s1.idfromcountry = c1.idcountry and s1.idtocountry = c2.idcountry";
            resultSet = statement.executeQuery(query);
            int num = 1;
            while (resultSet.next()) {
            	MT mmt = new MT();
            	mmt.setIdMT(resultSet.getInt(1));
            	mmt.setIdSubmittedBy(resultSet.getString(2));
            	mmt.setIdFromCountry(resultSet.getString(3));
            	mmt.setIdToCountry(resultSet.getString(4));
            	mmt.setBeforeDateMT(resultSet.getString(5));
            	mmt.setFromCountry(resultSet.getString(6));
            	mmt.setToCountry(resultSet.getString(7));
            	mmt.setEmailSubmittedBy(resultSet.getString(8));
            	mmt.setAmount(resultSet.getInt(9));            	
            	mmtc.add(mmt);
            	num = num +1;
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
    
    public List<MT> returnUserInterestShownMTs(String useremail){
    	List<MT> mmtc = new ArrayList<MT>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            //String query = "SELECT idusedleaves, email,type,fromdate,todate,days,approved,idmanager FROM ebookshop.users, ebookshop.usedleaves, ebookshop.leavetype where ebookshop.leavetype.idleavetype=ebookshop.usedleaves.idleavetype and ebookshop.users.idusers=ebookshop.usedleaves.iduser and email='" + useremail + "'";
            
            String query = "SELECT s2.idsubmittedmts as idmatchedmts, s2.idsubmittedby, s2.idfromcountry, s2.idtocountry, s2.beforedatemt,c1.name as fromcountry,c2.name as tocountry, u2.email, s2.amount FROM users u1, users u2, submittedmts s1,submittedmts s2, countries c1, countries c2 where s1.idfromcountry=s2.idtocountry and s1.idtocountry=s2.idfromcountry and u1.idusers=s1.idsubmittedby and u2.idusers = s2.idsubmittedby and u1.email = '" + useremail + "'";
            
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	MT mmt = new MT();
            	mmt.setIdMT(resultSet.getInt(1));
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
    
    public List<MT> returnUserInterestReceivedMTs(String useremail){
    	List<MT> mmtc = new ArrayList<MT>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            //String query = "SELECT idusedleaves, email,type,fromdate,todate,days,approved,idmanager FROM ebookshop.users, ebookshop.usedleaves, ebookshop.leavetype where ebookshop.leavetype.idleavetype=ebookshop.usedleaves.idleavetype and ebookshop.users.idusers=ebookshop.usedleaves.iduser and email='" + useremail + "'";
            
            String query = "SELECT s2.idsubmittedmts as idmatchedmts, s2.idsubmittedby, s2.idfromcountry, s2.idtocountry, s2.beforedatemt,c1.name as fromcountry,c2.name as tocountry, u2.email, s2.amount FROM users u1, users u2, submittedmts s1,submittedmts s2, countries c1, countries c2 where s1.idfromcountry=s2.idtocountry and s1.idtocountry=s2.idfromcountry and u1.idusers=s1.idsubmittedby and u2.idusers = s2.idsubmittedby and u1.email = '" + useremail + "'";
            
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	MT mmt = new MT();
            	mmt.setIdMT(resultSet.getInt(1));
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
            String query = "INSERT INTO users (firstname, lastname, email, password, idcountry) VALUES ('"+userData[1]+"','"+userData[2]+"', '"+userData[3]+"','"+userData[4]+"',"+userData[5]+")";
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

    public boolean submitShowInterestData(String idMT, String idUser){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "INSERT INTO interests (iduser, idsubmittedmt) VALUES ('"+idUser+"','"+idMT+"')";
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
    
    public boolean submitRemoveInterestData(String idMT, String idUser){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "UPDATE interests SET removed = 1 where iduser="+idUser+" and idsubmittedmt="+idMT;
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
    
    public List<Interest> returnUserInterests(String idUser) {
    	List<Interest> ic = new ArrayList<Interest>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            
            String query = "Select * from interests where iduser='"+ idUser +"' and removed=0";
            
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	Interest i = new Interest();
            	i.setIdInterest(resultSet.getInt(1));
            	i.setIdUser(resultSet.getInt(2));
            	i.setIdMT(resultSet.getInt(3));            	
            	ic.add(i);
            }
        	return ic;
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	return ic;
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
    
    public String[] submitSaveProfile(String userData[]){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "Update users set firstname='"+userData[1]+"', lastname='"+userData[2]+"', email='"+userData[3]+"', idcountry="+userData[4]+" where idusers="+userData[0]+"";
            int rows = statement.executeUpdate(query);
            String query1 = "SELECT * FROM USERS where idusers="+userData[0];
            ResultSet resultSet = statement.executeQuery(query1);
            String userdata[] = new String[5];
            while (resultSet.next()) {
            	userdata[0] = resultSet.getString(1);
            	userdata[1] = resultSet.getString(3);
            	userdata[2] = resultSet.getString(4);
            	userdata[3] = resultSet.getString(5);
            	userdata[4] = resultSet.getString(6);
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
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    } 

    public String[] returnUserProfile(String idProfile) {
    	String userdata[] = new String[6];
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            
            String query = "Select * from users where idusers="+ idProfile;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	userdata[0] = resultSet.getString(1);
            	userdata[1] = resultSet.getString(2);            	
            	userdata[2] = resultSet.getString(3);
            	userdata[3] = resultSet.getString(4);
            	userdata[4] = resultSet.getString(5);
            	userdata[5] = resultSet.getString(6);
            }
        	return userdata;
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
}
