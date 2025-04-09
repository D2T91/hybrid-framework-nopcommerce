set ProjectPath=%~dp0
cd %ProjectPath%
echo %ProjectPath%
set p=%PATH%
java -javaagent:"%ProjectPath%\libAllure\aspectjweaver-1.9.22.jar" -classpath "%ProjectPath%out\production\hybrid-framework-nopcommerce;;%ProjectPath%libAllure\*;%ProjectPath%libExtent5\*;%ProjectPath%libLog4J2\*;%ProjectPath%libReportNG\*;%ProjectPath%libraries\*;" org.testng.TestNG "%ProjectPath%resources\nopCommerce.xml"
allure serve .\allure-results\
pause