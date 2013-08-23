package org.ramo.klevis.p4app.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.swt.widgets.Shell;
import org.ramo.klevis.p2.core.iservice.IInstallNewSoftwareService;
import org.ramo.klevis.p4app.parts.SimpleInstallPart;

public class InstallNewSoftwareHandler {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,IProvisioningAgent agem,IInstallNewSoftwareService installNewSoftwareService) {
		// TODO Your code goes here

		System.err.println("Ok");
		
		Shell shell2 = new Shell(shell,1);
		
		new SimpleInstallPart(installNewSoftwareService, agem).createControls(shell2);
		
		shell2.open();
		;

		
		
	}

}