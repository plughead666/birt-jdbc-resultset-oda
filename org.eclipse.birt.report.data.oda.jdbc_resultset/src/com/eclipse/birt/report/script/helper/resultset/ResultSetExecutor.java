package com.eclipse.birt.report.script.helper.resultset;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionContext;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionExecutor;
import org.eclipse.birt.report.engine.api.script.IReportContext;

public class ResultSetExecutor implements IScriptFunctionExecutor {

		public Object execute(Object[] args, IScriptFunctionContext scontext)
			throws BirtException {
		
		IReportContext context = (IReportContext) args[0];
		String query = (String) args[1];
		
		return QueryUtil.runQuery(context, query);
	}

	
}
