package com.eclipse.birt.report.script.helper.resultset;

import java.util.Iterator;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionContext;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionExecutor;
import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;

public class GetDataSetQuery implements IScriptFunctionExecutor {
	private static String QUERY_PROPERTY = "queryText";
	
	public Object execute(Object[] args, IScriptFunctionContext arg1)
			throws BirtException {
		IReportContext context = (IReportContext) args[0];
		String dataSetName = (String) args[1];
		
		ReportDesignHandle handle = context.getDesignHandle();
		
		DataSetHandle dataSet = QueryUtil.findDataSetHandle(handle, dataSetName);
			
		if (dataSet != null)
		{
			String query = (String) dataSet.getProperty(QUERY_PROPERTY);
			
			return query;
		}
		
		
		return null;
	}

}
