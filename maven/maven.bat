rem Registers the JTS in local repository
rem $Id$

rem The JAR is available from
rem http://www.vividsolutions.com/jts/jtshome.htm

mvn install:install-file -Dfile=jts-1.7.2.jar -DgroupId=com.vividsolutions -DartifactId=JTS -Dversion=1.7.2 -Dpackaging=jar -DgeneratePom=true
mvn deploy:deploy-file -Dfile=jts-1.7.2.jar -DgroupId=com.vividsolutions -DartifactId=JTS -Dversion=1.7.2 -Dpackaging=jar -DgeneratePom=true -DrepositoryId=doolin-repository -Durl=scp://doolin-guif.net/home/doolingu/public_html/repository
