/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.birt.report.data.oda.jdbc_resultset.ui.editors;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.datatools.connectivity.oda.IAdvancedQuery;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;


/**
 * 
 * This class serves to provide the updated ParameterMetaData and ResultSetMetaData information
 * according to the specified updated query text
 *  
 */
class MetaDataRetriever
{
	private IResultSetMetaData resultMeta;
	private IParameterMetaData paramMeta;
	private IQuery query;
	
	private static Logger logger = Logger.getLogger( MetaDataRetriever.class.getName( ) );	

	MetaDataRetriever( OdaConnectionProvider odaConnectionProvider, DataSetDesign dataSetDesign )
	{
		try
		{
			IConnection connection = odaConnectionProvider.openConnection( );
			query = connection.newQuery( dataSetDesign.getOdaExtensionDataSetId( ) );
			query.prepare( dataSetDesign.getQueryText( ) );
			try 
			{
				paramMeta = query.getParameterMetaData( );
			}
			catch ( OdaException e )
			{
				logger.log( Level.WARNING, e.getLocalizedMessage( ), e );
			}
			if ( !( query instanceof IAdvancedQuery ) )
			{
				resultMeta = query.getMetaData( );
			}
			
		}
		catch ( OdaException e )
		{
			logger.log( Level.WARNING, e.getLocalizedMessage( ), e );
		}
	}
	
	/**
	 * Get the ParameterMetaData object
	 * 
	 * @return IParameterMetaData
	 */
	IParameterMetaData getParameterMetaData( )
	{
		return this.paramMeta;
	}
	
	/**
	 * Get the ResultSetMetaData object
	 * 
	 * @return IResultSetMetaData
	 */
	IResultSetMetaData getResultSetMetaData( )
	{
		return this.resultMeta;
	}
	
	/**
	 * Release
	 */
	void close( )
	{
		try
		{
			if ( query != null )
			{
				query.close( );
			}
		}
		catch ( OdaException e )
		{
			//ignore it
		}
		finally 
		{
			query = null;
		}
	}
}
