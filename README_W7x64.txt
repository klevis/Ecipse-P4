Note: This was done on a windows 7 64bit system using an Eclipse RCP x86_64 and a Java 64bit version.

 

1.       Download and Extract the p4 application from: https://github.com/klevis/Eclipse-P4

2.       Import the projects, from the extracted .zip file from step 1, into an instance of Eclipse RCP (Luna was used in this case).

3.       Download and extract:

a.       Equinox SDK (I used equinox-SDK-LunaM2.zip from: http://download.eclipse.org/equinox/drops/S-LunaM2-201309182000/index.php)

The Equinox SDK is used to fulfill the plugin dependencies for: org.eclipse.equinox.transforms.hook & org.eclipse.equinox.weaving.hook.

b.      Eclipse DeltaPack (I used eclipse-4.4M2-delta-pack.zip from: http://download.eclipse.org/eclipse/downloads/drops4/S-4.4M2-201309182000/#DeltaPack)

The DeltaPack is used to fulfill the various plugin dependencies for Platform Builds (.

4.       Install/Add the Equinox SDK to the Eclipse RCP (used in step 2) target platform. Do this by:

a.       Clicking Window

b.      Preferences

c.       Plug-in Development

d.      Target Platform

e.      Running Platform

f.        Edit

g.       While the Locations tab is selected press Add and select “Installation” in the Add Content dialog. Then click Next.

h.      Click Browse and point to the location where you extracted the Equinox SDK zip file. Click the Next button to make sure it has located plugins. If not, fix the path until the plugins are found before proceeding to the next step.

i.         Click Finish.

5.       Repeat step 4 for the Eclipse DeltaPack

6.       Open the org.ramo.klevis.p4app.product file within Eclipse RCP

a.       In the Dependencies tab make sure there are no Plug-ins/Fragments that are shown with a critical indicator next to them. If steps 4 and 5 were done then there should not be. If so, fix these before proceeding.

b.      In the overview tab under the Exporting section click “Eclipse Product export wizard”.

                                                               i.      Make sure Synchronize before exporting is selected

                                                             ii.      Choose a destination in the Directory field

                                                            iii.      Make sure the “Generate p2 repository” option is selected (to prevent “Profile id _SELF_ is not registered” errors).

                                                           iv.      My export also has the “Allow for binary cycles in target platform” option selected.

7.       eclipse and repository folders are created in the directory specified in 6ii. Go to the eclipse folder, run the eclipse.exe.

8.       Click Help -> Install New Software -> point to the …\Eclipse-P4-master\org.ramo.klevis.p4app.siteexample (in the extracted folder from step 1).

9.       Click OK.

10.   Click P4Example and click Install New Software

11.   Restart the application and notice the “New Menu”.