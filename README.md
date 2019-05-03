# Scentaur
Just Another Web-Based Code Smell Detector

## Setup Instructions. 
#### Scentaur.war file is in the home directory just above, all you need is to follow the following steps.

1) Install this : https://www-us.apache.org/dist/tomcat/tomcat-8/v8.5.40/bin/apache-tomcat-8.5.40.exe
2) Open installation directory of it and then open webapps, (C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapp)
3) Put Scentaur.war file in there
4) Open bin folder inside tomcat installation directory (Tomcat 8.5) ( C:\Program Files\Apache Software Foundation\Tomcat 8.5\) 
5) Open 'Tomcat8w'
6) Press on start, make sure port 8080 is open else it won't start!
7) Visit : http://localhost:8080/Scentaur/


## Setup Using Eclipse [Hard Method]
1) Download the following plugins
-	Buildship Gradle Integration 3.0
-	Eclipse Java EE Developer Tools 3.12
-	Eclipse JST Server Adapters (Apache Tomcat, Jon…
2)	Make sure that Eclipse can run Java Web projects. If not go to:
-	Help -> Install New Software -> Select a site and download
3)	On the web browser – Download Tomcat 8.0
-	Install it onto your Java JRE folder
-	If you can’t see Tomcat on your server
4)	Window preferences on Eclipse -> Search Tomcat -> config the server -> select version 8.5 -> server location, other fields will be automatically filled
5)	Importing Scentaur
-	File -> Import -> Gradle -> Existing Gradle Project -> Scentaur
-	Accept Everything
-	Right Click on Scentaur -> Gradle -> Refresh Gradle
-	Right Click Scentaur -> Properties -> Java Build Path -> Configure Build Path -> Libraries -> Add JARs -> Scentaur -> WebContent -> WEB-INF -> lib -> Everything -> Apply
-	Right Click Scentaur -> Properties -> Deployment Assembly -> Add -> Java Build Path Entries -> Select Everything -> Finish -> Apply
-	Right Click Scentaur -> Properties -> Project Facets (Make sure dynamic Web module is ticked and Tomcat v8.5 is applied in Runtimes
-	Right Click Scentaur -> Run as -> Run on Server, Select Tomcat v8.5 Server at localhost -> Finish
