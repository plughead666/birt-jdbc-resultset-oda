package com.eclipse.birt.report.script.helper.resultset;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionContext;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionExecutor;
import org.eclipse.birt.report.engine.api.script.IReportContext;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.DataSourceHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;

public class GetJDBCUrl implements IScriptFunctionExecutor {
	private static String URL_PROPERTY = "odaURL";
	
	public Object execute(Object[] args, IScriptFunctionContext arg1)
			throws BirtException {
		IReportContext context = (IReportContext) args[0];
		String dataSourceName = (String) args[1];
		
		ReportDesignHandle handle = context.getDesignHandle();
		DataSourceHandle dataSource = QueryUtil.findDataSourceHandle(handle, dataSourceName);
		
		if (dataSource != null)
		{
			String driverClass = (String) dataSource.getProperty(URL_PROPERTY);

			return driverClass;
		}
		
		return null;
	}

}
