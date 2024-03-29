/***********************************************************************
 * Copyright (c) 2009 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 ***********************************************************************/

package org.eclipse.birt.report.data.oda.jdbc_resultset.ui.util.bidi.preference;

import org.eclipse.birt.report.data.bidi.utils.core.BidiFormat;
import org.eclipse.birt.report.data.bidi.utils.i18n.Messages;
import org.eclipse.birt.report.data.bidi.utils.ui.BidiGUIUtility;
import org.eclipse.birt.report.data.oda.jdbc_resultset.ui.JdbcPlugin;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * @author bidi_hcg
 *
 */
public class JDBCDataSourcePreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {
	public static final String EXTERNAL_BIDI_FORMAT = "report.data.oda.bidi.jdbc.ui.externalbidiformat";
	private Group externalBiDiFormatFrame;
	private String externalBiDiFormatStr;
	Preferences ps = null;
	public JDBCDataSourcePreferencePage() {
		super();
		setDescription(Messages.getString("preference.description"));
	}
	
	
	protected Control createContents(Composite parent) {
		Composite mainComposite = new Composite( parent, SWT.NONE );
		GridLayout twoColLayout = new GridLayout( );
		twoColLayout.numColumns = 2;
		twoColLayout.marginWidth = 10;
		twoColLayout.marginHeight = 10;
		mainComposite.setLayout( twoColLayout );
		
		externalBiDiFormatFrame = BidiGUIUtility.INSTANCE.addBiDiFormatFrame(
								mainComposite, Messages.getString("preference.bidiframe.title"), new BidiFormat(externalBiDiFormatStr) );

		return  mainComposite;
	}
	
	public void init( IWorkbench workbench ){
		ps = JdbcPlugin.getDefault( ).getPluginPreferences( );
		externalBiDiFormatStr = ps.getString(EXTERNAL_BIDI_FORMAT);
	}
	
	protected void performDefaults( ) {
		BidiGUIUtility.INSTANCE.performDefaults();
	}
	
	public boolean performOk( ) { 
		externalBiDiFormatStr = BidiGUIUtility.INSTANCE.getBiDiFormat(externalBiDiFormatFrame).getBiDiFormatString();
		ps.setValue(EXTERNAL_BIDI_FORMAT, externalBiDiFormatStr);
		return super.performOk( );
	}


}