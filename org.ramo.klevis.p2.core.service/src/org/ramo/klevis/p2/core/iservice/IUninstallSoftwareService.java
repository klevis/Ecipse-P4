package org.ramo.klevis.p2.core.iservice;

import java.util.List;

import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;

public interface IUninstallSoftwareService {

	List<IInstallableUnit> listInstalledSoftware(IProvisioningAgent agen);

	String uninstallSelected(List<IInstallableUnit> listToUninstall);

	
	
	
}
