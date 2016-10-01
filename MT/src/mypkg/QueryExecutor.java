package mypkg;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


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
            System.out.println(query);
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
    
    public List<UsedLeave> returnUserUsedLeaves(String useremail){
    	List<UsedLeave> ulc = new ArrayList<UsedLeave>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT idusedleaves, email,type,fromdate,todate,days,approved,idmanager FROM ebookshop.users, ebookshop.usedleaves, ebookshop.leavetype where ebookshop.leavetype.idleavetype=ebookshop.usedleaves.idleavetype and ebookshop.users.idusers=ebookshop.usedleaves.iduser and email='" + useremail + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	UsedLeave ul = new UsedLeave();
            	ul.setIdLeave(resultSet.getInt(1));
            	ul.setUserEmail(resultSet.getString(2));
            	ul.setType(resultSet.getString(3));
            	ul.setFromDate(resultSet.getString(4));
            	ul.setToDate(resultSet.getString(5));
            	ul.setDays(resultSet.getInt(6));
            	ul.setApproved(resultSet.getString(7));
            	ul.setIdManager(resultSet.getString(8));
            	ulc.add(ul);
            }
        	return ulc;
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	return ulc;
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
    
    public List<UnUsedLeave> returnUserUnUsedLeaves(String useremail){
    	List<UnUsedLeave> ulc = new ArrayList<UnUsedLeave>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT idleavebalance,email,type,balance,iduser,ebookshop.leavebalance.idleavetype FROM ebookshop.leavebalance,ebookshop.users,ebookshop.leavetype where ebookshop.leavebalance.iduser=ebookshop.users.idusers and ebookshop.leavebalance.idleavetype=ebookshop.leavetype.idleavetype and email='" + useremail + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	UnUsedLeave ul = new UnUsedLeave();
            	ul.setIdLeave(resultSet.getInt(1));
            	ul.setUserEmail(resultSet.getString(2));
            	ul.setType(resultSet.getString(3));
            	ul.setBalance(resultSet.getInt(4));
            	ul.setIdUser(String.valueOf(resultSet.getInt(5)));
            	ul.setIdLeaveType(String.valueOf(resultSet.getInt(6)));
            	ulc.add(ul);
            }
        	return ulc;
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	return ulc;
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

    public List<ApproveLeave> returnUserApproveLeaves(String useremail){
    	List<ApproveLeave> alc = new ArrayList<ApproveLeave>();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT idusedleaves, u1.email,u1.idusers,u1.idmanager,type,fromdate,todate,days,approved, u2.email,ebookshop.usedleaves.idleavetype FROM ebookshop.users u1, ebookshop.users u2, ebookshop.usedleaves, ebookshop.leavetype where u1.idmanager=u2.idusers and ebookshop.leavetype.idleavetype=ebookshop.usedleaves.idleavetype and u1.idusers=ebookshop.usedleaves.iduser and u2.email='" + useremail + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	ApproveLeave al = new ApproveLeave();
            	al.setIdLeave(resultSet.getInt(1));
            	al.setUserEmail(resultSet.getString(2));
            	al.setIdUser(resultSet.getString(3));
            	al.setIdManager(resultSet.getString(4));
            	al.setType(resultSet.getString(5));
            	al.setFromDate(resultSet.getString(6));
            	al.setToDate(resultSet.getString(7));
            	al.setDays(resultSet.getInt(8));
            	al.setApproved(resultSet.getString(9));
            	al.setIdLeaveType(resultSet.getString(11));
            	alc.add(al);
            }
        	return alc;
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	return alc;
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
    
    
    
    public void submitUserLeaves(UsedLeave usedLeaves){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "INSERT INTO usedleaves (iduser, idleavetype, days, fromdate, todate, approved) VALUES ('"+usedLeaves.getIdUser()+"','"+usedLeaves.getIdLeave()+"', '"+usedLeaves.getDays()+"','"+usedLeaves.getFromDate()+"','"+usedLeaves.getToDate()+"', 'N')";
            int rows = statement.executeUpdate(query);
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	}
        	finally {
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }    

    public void updateUserLeavesBalance(UnUsedLeave unUsedLeaves, int latestbalance){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "UPDATE leavebalance SET balance = '"+latestbalance+"' WHERE iduser='"+unUsedLeaves.getIdUser()+"' and idleavetype='"+unUsedLeaves.getIdLeaveType()+"'";
            int rows = statement.executeUpdate(query);
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	}
        	finally {
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }    

    public void approveUserLeaves(String[] idLeaves){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            for(int i=0;i<idLeaves.length;i++){
	            String query = "UPDATE usedleaves SET approved = 'Y' WHERE idusedleaves='"+idLeaves[i]+"'";
	            int rows = statement.executeUpdate(query);
            }
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	}
        	finally {
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }    

    public void rejectUserLeaves(String[] idLeaves){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            for(int i=0;i<idLeaves.length;i++){
	            String query = "UPDATE usedleaves SET approved = 'R' WHERE idusedleaves='"+idLeaves[i]+"'";
	            int rows = statement.executeUpdate(query);
            }
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	}
        	finally {
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }    
    public void updateUserRejectedLeavesBalance(ApproveLeave approveLeaves, int latestbalance){
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "UPDATE leavebalance SET balance = '"+latestbalance+"' WHERE iduser='"+approveLeaves.getIdUser()+"' and idleavetype='"+approveLeaves.getIdLeaveType()+"'";
            System.out.println(query);
            int rows = statement.executeUpdate(query);
        	} 
        	catch (SQLException e) {
            e.printStackTrace();
        	}
        	finally {
            try { 
            	if(null!=statement)statement.close();} catch (SQLException e){e.printStackTrace();}
            try { 
            	if(null!=connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }    

    public int getUserLeavesBalance(ApproveLeave approveLeaves){
    	int balance = 0;
    	ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String query = "SELECT balance FROM leavebalance where iduser='" + approveLeaves.getIdUser() + "' and idleavetype='" + approveLeaves.getIdLeaveType() + "'";
            System.out.println(query);            
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	balance = resultSet.getInt(1);
            }
            return balance;
        	}
            catch (SQLException e) {
            e.printStackTrace();
        	return balance;
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
