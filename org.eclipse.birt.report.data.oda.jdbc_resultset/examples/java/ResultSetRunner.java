package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.engine.api.ReportEngine;

public class ResultSetRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			
			Connection con = DriverManager.getConnection("jdbc:derby:jar:(C:/eclipse/birt_2_6_2/eclipse/plugins/org.eclipse.birt.report.data.oda.sampledb_2.6.2.r262_v20110127/db/BirtSample.jar)BirtSample", null);
			Statement stat = con.createStatement();
			stat.execute("select * from CLASSICMODELS.EMPLOYEES where EMPLOYEENUMBER = 1002");
			
			ResultSet resultSet = stat.getResultSet();
						
			EngineConfig config = new EngineConfig();
			config.setBIRTHome("C:/Libraries/birt-runtime-2_6_2/ReportEngine");
			Platform.startup(config);
			
			ReportEngine engine = new ReportEngine(config);
			
			IReportRunnable reportRunnable = engine.openReportDesign("C:/contracts/runtime-EclipseApplication/Drill to Details/CustomerOrdersFinal.rptdesign");
			IRunAndRenderTask task = engine.createRunAndRenderTask(reportRunnable);
			
			HashMap<String, Object> appContext = new HashMap<String, Object>();
			
			appContext.put("OdaJDBCResultSet", resultSet);
			task.setAppContext(appContext);
			
			HTMLRenderOption option = new HTMLRenderOption();
			option.setOutputFormat(RenderOption.OUTPUT_FORMAT_HTML);
			option.setOutputFileName("C:/TEMP/temp.htm");
			
			task.setRenderOption(option);
			task.run();
			
			task.close();
			
			Platform.shutdown();
		} catch (EngineException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (BirtException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
