package com.eclipse.birt.report.script.helper.resultset;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionExecutor;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionFactory;

public class ResultSetHelperFactory implements IScriptFunctionFactory {
	private static String RESULT_SET_EXECUTOR = "ExecuteFromQuery";
	private static String SET_CONTEXT = "SetContext";
	private static String OPEN_CONN = "OpenConnection";
	private static String CLOSE_CONN = "CloseConnection";
	private static String DATA_SOURCE_NAME = "GetJDBCNameFromDataSource";
	private static String DATA_SOURCE_URL = "GetJDBCURLFromDataSource";
	private static String DATA_SET_QUERY = "GetQueryFromDataSet";
	
	public ResultSetHelperFactory() {
		
	}

	public IScriptFunctionExecutor getFunctionExecutor(String functionName)
			throws BirtException {
		
		if (functionName.equalsIgnoreCase(RESULT_SET_EXECUTOR))
		{
			return new ResultSetExecutor();
		}
		
		if (functionName.equalsIgnoreCase(SET_CONTEXT))
		{
			return new SetContext();
		}
		
		if (functionName.equalsIgnoreCase(OPEN_CONN))
		{
			return new OpenConnection();
		}
		
		if (functionName.equalsIgnoreCase(CLOSE_CONN))
		{
			return new CloseConnection();
		}
		
		if (functionName.equalsIgnoreCase(DATA_SOURCE_NAME))
		{
			return new GetJDBCName();
		}
		
		if (functionName.equalsIgnoreCase(DATA_SOURCE_URL))
		{
			return new GetJDBCUrl();
		}
		
		if (functionName.equalsIgnoreCase(DATA_SET_QUERY))
		{
			return new GetDataSetQuery();
		}
		return null;
	}

}
