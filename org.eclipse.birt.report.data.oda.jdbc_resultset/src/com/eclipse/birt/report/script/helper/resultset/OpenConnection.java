package com.eclipse.birt.report.script.helper.resultset;

import java.sql.SQLException;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionContext;
import org.eclipse.birt.core.script.functionservice.IScriptFunctionExecutor;
import org.eclipse.birt.report.engine.api.script.IReportContext;

public class OpenConnection implements IScriptFunctionExecutor {

	public Object execute(Object[] args, IScriptFunctionContext arg1)
			throws BirtException {
		IReportContext context = (IReportContext) args[0];
		String JDBCName = (String) args[1];
		String JDBCUrl = (String) args[2];
		
		try {
			QueryUtil.openConnection(context, JDBCName, JDBCUrl);
		} catch (InstantiationException e) {
			throw new BirtException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new BirtException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new BirtException(e.getMessage());
		} catch (SQLException e) {
			throw new BirtException(e.getMessage());
		}
		
		return null;
	}

}
