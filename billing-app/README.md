# Project Name - BillingApp Standalone Java application  build on Java 1.8 / Maven

OBJECTIVE :
===========
A Java based application to generate a bill including a service charge for Café X so the customer does not have to work out how much to tip


HOW TO RUN THE PROGRAM :
========================

The below instructions are based on assumption that the application is running on a Windows OS.

Pre-requisites
--------------
1. Have maven installed (including maven set in system path variable)
2. Have GIT Installed


Steps to run the application (COMMAND PROMPT)
---------------------------------------------
1. Git clone the project to local drive (https://github.com/kapricanon/BillingApp.git)
2. Open a command prompt
3. Navigate to the folder where the project is cloned (to where pom.xml is located)
4. Run the command "mvn package"
5. Now run the command "java -cp .;target\BillingApp.jar;target\dependency-jars\commons-lang-2.6.jar com.myprojects.billingapp.BillingApp Cola Coffee"
6. You can change the menu items as needed in the above command
