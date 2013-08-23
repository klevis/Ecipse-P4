package org.ramo.klevis.p2.core.iservice;

import java.util.List;

import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;

public interface IInstallNewSoftwareService {
	String SUCESS_INSTALL = "Wait a minute and see to plugins folder if jar was added than restart application";

	List<IInstallableUnit> loadRepository(String uriString,
			IProvisioningAgent agent);

	String installNewSoftware(List<IInstallableUnit> listIInstallableUnits);

	String loadAndInstallNewSoftware(String uriString, IProvisioningAgent agent);

	String validate(List<IInstallableUnit> listIInstallableUnits);

	List<IInstallableUnit> extractFromCategory(IInstallableUnit category);

	boolean isCategory(IInstallableUnit installableUnit);

}
