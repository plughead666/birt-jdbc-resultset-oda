package com.eclipse.birt.report.script.helper.resultset;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionContext;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionExecutor;
import org.eclipse.birt.report.data.oda.jdbc_resultset.IConnectionFactory;
import org.eclipse.birt.report.engine.api.script.IReportContext;

public class SetContext implements IScriptFunctionExecutor {

	public Object execute(Object[] args, IScriptFunctionContext arg1)
			throws BirtException {
		
		IReportContext context = (IReportContext) args[0];
		ResultSet set = (ResultSet) args[1];
		
		Map appContext = context.getAppContext();
		
		appContext.put(IConnectionFactory.EXTERNAL_JDBC_RESULTSET, set);
		
		return null;
	}

}
