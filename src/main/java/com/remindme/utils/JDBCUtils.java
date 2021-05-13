package com.remindme.utils;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.sql.DataSource;

import com.zaxxer.hikari.*;

public class JDBCUtils {
	
	public static Date getSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }
    
    public static Time getSQLTime(LocalTime time) {
    	return java.sql.Time.valueOf(time);
    }
   
    private static HikariDataSource dataSource;
	static{
		
		dataSource = new HikariDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hibernate");
		dataSource.setUsername("root");
		dataSource.setPassword("maha");
		
		dataSource.setMinimumIdle(100000);
		dataSource.setMaximumPoolSize(2000000);//The maximum number of connections, idle or busy, that can be present in the pool.
	   
	
	}
    
	public static DataSource getDataSource()
	   {
		return dataSource;
	   }
	
    public static void printSQLException(SQLException ex)
     {
    	 for(Throwable e : ex)
    	 {
    		 if(e instanceof SQLException)
    		 {
    			 e.printStackTrace(System.err);
    			 System.err.println("SQLState: "+ ((SQLException)e).getSQLState());
    			 System.err.println("Error Code: "+ ((SQLException)e).getErrorCode());
    			 System.err.println("Message: "+ e.getMessage());
    			 Throwable t = ex.getCause();
    			 while(t != null) {
    				 System.out.println("Cause: "+t);
    				 t = t.getCause();
    			 }
    		 }
    	 }
     }

}
