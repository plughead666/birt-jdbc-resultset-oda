package com.eclipse.birt.report.script.helper.resultset;

import java.sql.SQLException;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionContext;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionExecutor;
import org.eclipse.birt.report.engine.api.script.IReportContext;

public class CloseConnection implements IScriptFunctionExecutor {

	public Object execute(Object[] args, IScriptFunctionContext arg1)
			throws BirtException {
		IReportContext context = (IReportContext) args[0];
		try {
			QueryUtil.closeConnection(context);
		} catch (SQLException e) {
			throw new BirtException(e.getMessage());
		}
		
		return null;
	}

}
