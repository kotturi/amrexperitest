set projectLocation=E:\JK_Softwares\PCA_Projects\AMR_Hybrid
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause