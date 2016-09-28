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
