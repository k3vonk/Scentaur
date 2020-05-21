# Scentaur
Just Another Web-Based Code Smell Detector sending .wars to fight wars.

# Local Install

1) Install: https://www-us.apache.org/dist/tomcat/tomcat-8/v8.5.40/bin/apache-tomcat-8.5.40.exe
2) ```cd C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapp```
3) Open webapp.
3) Place ```Scentaur.war``` in webapp.
4) ```cd C:\Program Files\Apache Software Foundation\Tomcat 8.5\```
5) Open ```Tomcat8w```
6) Press ```start```, ensure port ```8080``` is available or it won't start!
7) Visit: http://localhost:8080/Scentaur/

# Different Local Install Approach [Difficult]

### Download dependencies
* ```Buildship Gradle Integration 3.0```
* ```Eclipse Java EE Developer Tools 3.12```
* ```Eclipse JST Server Adapters (Apache Tomcat, ...)```

### Requirements if Eclipse cannot run Java Web Projects
* ```Help -> Install New Software -> Select a site and download```
* Download ```Tomcat 8.0``` and install to Java JRE folder

### If Tomcat is not visible on your server
```Window preferences on Eclipse -> Search Tomcat -> config the server -> select version 8.5 -> server location (other fields are automatically filled) ```

### Importing Scentaur
* ``` File -> Import -> Gradle -> Existing Gradle Project -> Scentaur ```
* Accept Everything
* ``` Right click Scentaur -> Gradle -> Refresh Gradle ```
* ``` Right click Scentaur -> Properties -> Java Build Path -> Configure Build Path -> Libraries -> Add JARs -> Scentaur -> WebContent -> WEB-INF -> lib -> Everything -> Apply ```
* ``` Right click Scentaur -> Properties -> Deployment Assembly -> Add -> Java Build Path Entries -> Select Everything -> Finish -> Apply```
* ``` Right click Scentaur -> Properties -> Project Facets (Make sure dynamic Web Module is checked & Tomcat v8.5 is applied in Runtimes)```
* ``` Right click Scentaur -> Run as -> Run on Server, Select Tomcat v8.5 Server at localhost```
