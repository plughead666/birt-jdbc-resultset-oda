package com.eclipse.birt.report.script.helper.resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.DataSourceHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;

public class QueryUtil {
	private static String CONNECTION = "JDBC_CONNECTION_ID";
	
	public static DataSourceHandle findDataSourceHandle(ReportDesignHandle handle, String nameToFind)
	{
		Iterator i = handle.getDataSources().iterator();
		
		DataSourceHandle dataSet = null;
		
		while (i.hasNext())
		{
			Object set = i.next();
			DataSourceHandle tempDataSet = null;
			if (set instanceof DataSourceHandle)
			{
				tempDataSet = (DataSourceHandle) set;
				
				if (tempDataSet.getName().equals(nameToFind))
				{
					dataSet = tempDataSet;
				}
			}
		}
		
		return dataSet;
	}
	
	public static DataSetHandle findDataSetHandle(ReportDesignHandle handle, String nameToFind)
	{
		Iterator i = handle.getDataSets().iterator();
		
		DataSetHandle dataSet = null;
		
		while (i.hasNext())
		{
			Object set = i.next();
			DataSetHandle tempDataSet = null;
			if (set instanceof DataSetHandle)
			{
				tempDataSet = (DataSetHandle) set;
				
				if (tempDataSet.getName().equals(nameToFind))
				{
					dataSet = tempDataSet;
				}
			}
		}
		
		return dataSet;
	}
	
	/**
	 * Opens a connection, and stores the value in the app context
	 * @param context
	 * @param jdbcDriverName
	 * @param url
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void openConnection(IReportContext context, String jdbcDriverName, String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(jdbcDriverName).newInstance();
		
		Connection con = DriverManager.getConnection(url, null);
		
		context.getAppContext().put(CONNECTION, con);
	}
	
	/**
	 * Utility method to get a connection from the app context
	 * @param context
	 * @return
	 */
	private static Connection getCon(IReportContext context)
	{
		return (Connection) context.getAppContext().get(CONNECTION);
	}
	
	/**
	 * Close an open connection
	 * @param context
	 * @throws SQLException
	 */
	public static void closeConnection(IReportContext context) throws SQLException
	{
		getCon(context).close();
	}
	
	/**
	 * Execute a query. Assumes open connection has been called first
	 * @param context
	 * @param query
	 * @return
	 * @throws BirtException
	 */
	public static ResultSet runQuery(IReportContext context, String query) throws BirtException
	{
		try {
			
			Statement stat = getCon(context).createStatement();
			stat.execute(query);
			
			ResultSet resultSet = stat.getResultSet();
			
			return resultSet;
		} catch (SQLException e) {
			throw new BirtException(e.getMessage());
		}
	}
}
