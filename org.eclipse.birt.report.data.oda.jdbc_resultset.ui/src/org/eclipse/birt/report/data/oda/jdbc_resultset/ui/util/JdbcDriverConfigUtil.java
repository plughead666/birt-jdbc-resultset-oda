/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Actuate Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.birt.report.data.oda.jdbc_resultset.ui.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.birt.report.data.oda.jdbc_resultset.OdaJdbcDriver;
import org.eclipse.birt.report.data.oda.jdbc_resultset.ui.JdbcPlugin;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.ui.PlatformUI;

public class JdbcDriverConfigUtil
{
	/** can not be instantiated */
	private JdbcDriverConfigUtil(){};

	//delete Jars
	//reset deleted Jar files map
	static
	{
		Map map =Utility.getPreferenceStoredMap( JdbcPlugin.DELETED_JAR_MAP_PREFERENCE_KEY);
		Set entrySet = map.entrySet( );
		Iterator it = entrySet.iterator( );
		if ( it.hasNext( ) )
		{
			Map.Entry entry = (Map.Entry) it.next( );

			it = entrySet.iterator( );

			JarFile jarFile;
			while ( it.hasNext( ) )
			{
				entry = (Map.Entry) it.next( );
				jarFile = (JarFile) entry.getValue( );
				jarFile.deleteJarFromODADir( );
			}
		}
		Utility.setPreferenceStoredMap( JdbcPlugin.DELETED_JAR_MAP_PREFERENCE_KEY,
				new HashMap( ) );
	}
	
	/**
	 * Gets a list of possible driver files under the oda.jdbc plugin's "drivers" directory
	 * Returned file list has been filtered by file type. Only JAR and ZIP files are expected
	 * @return driverFiles
	 */
	public static List getDriverFiles( )
	{
		try
		{
			// can not use filefilter,since the input is not a directory
			List fileList;
			fileList = OdaJdbcDriver.getDriverFileList( );

			Map deletedJars = Utility.getPreferenceStoredMap( JdbcPlugin.DELETED_JAR_MAP_PREFERENCE_KEY );
			List filteredFileList = new java.util.ArrayList( );
			for ( int i = 0; i < fileList.size( ); i++ )
			{
				File f = (File) fileList.get( i );
				if ( !deletedJars.containsKey( f.getName( ) ) )
				{
					filteredFileList.add( f );
				}
			}
			return filteredFileList;
		}
		catch ( OdaException e )
		{
			ExceptionHandler.showException( PlatformUI.getWorkbench( )
					.getDisplay( )
					.getActiveShell( ),
					JdbcPlugin.getResourceString( "exceptionHandler.title.error" ),
					e.getLocalizedMessage( ),
					e );

		}
		catch ( IOException e )
		{
			ExceptionHandler.showException( PlatformUI.getWorkbench( )
					.getDisplay( )
					.getActiveShell( ),
					JdbcPlugin.getResourceString( "exceptionHandler.title.error" ),
					e.getLocalizedMessage( ),
					e );

		}
		return null;
	}
}
