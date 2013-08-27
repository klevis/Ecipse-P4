package org.ramo.klevis.p4app.parts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.swing.JOptionPane;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.ramo.klevis.p2.core.iservice.IUninstallSoftwareService;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class SimpleUninstallPart {
	IUninstallSoftwareService uninstallSoftwareService;

	IWorkbench workbench;
	List<IInstallableUnit> installedSoftware;

	private Tree tree;

	public SimpleUninstallPart(
			IUninstallSoftwareService uninstallSoftwareService,
			IProvisioningAgent agen, IWorkbench workbench) {

		this.workbench = workbench;
		this.uninstallSoftwareService = uninstallSoftwareService;
		installedSoftware = uninstallSoftwareService
				.listInstalledSoftware(agen);
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(final Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Label lblListOfInstalled = new Label(parent, SWT.NONE);
		lblListOfInstalled.setFont(SWTResourceManager.getFont("Segoe UI", 12,
				SWT.BOLD));
		lblListOfInstalled.setText("List of installed software");

		tree = new Tree(parent, SWT.BORDER | SWT.MULTI);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		for (IInstallableUnit installed : installedSoftware) {

			TreeItem treeItem = new TreeItem(tree, 0);
			treeItem.setText(installed.getId());
		}
		Button btnUninstall = new Button(parent, SWT.NONE);
		/*
		 * btnUninstall.addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseDown(MouseEvent e) { uninstall(parent); }
		 * });
		 */
		GridData gd_btnUninstall = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnUninstall.widthHint = 557;
		btnUninstall.setLayoutData(gd_btnUninstall);
		btnUninstall.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				uninstall(parent);
			}

		});
		btnUninstall.setFont(SWTResourceManager.getFont("Segoe UI", 11,
				SWT.BOLD | SWT.ITALIC));
		btnUninstall.setText("Uninstall");
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO Set the focus to control
	}

	private void uninstall(final Composite parent) {

		TreeItem[] selection = tree.getSelection();

		if (selection.length == 0) {
			MessageDialog.openWarning((Shell) parent, "Warning",
					"Please select at least one");
			return;
		} else {

			List<IInstallableUnit> listToUninstall = new ArrayList<IInstallableUnit>();
			for (TreeItem tree : selection) {

				for (IInstallableUnit in : installedSoftware) {

					if (tree.getText().equals(in.getId())) {
						listToUninstall.add(in);

					}

				}
			}
			String uninstallSelected = "OK";
			try {
				uninstallSelected = uninstallSoftwareService
						.uninstallSelected(listToUninstall);
			} catch (Exception em) {
				JOptionPane.showMessageDialog(null, em.getMessage());
				return;
			}

			if (uninstallSelected == null) {
				boolean openConfirm = MessageDialog
						.openConfirm((Shell) parent, "",
								"Software Uninstalled!Do you want to restart so changes may take effect?");

				if (openConfirm) {

					workbench.restart();
				}
			}else{
				MessageDialog.openWarning((Shell) parent, "Info", uninstallSelected);
			}
		}
	}
}
