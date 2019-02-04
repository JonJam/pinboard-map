# Current version of Jersey uses Servlet 3.0, so tomcat version uses 8.*.
# - https://tomcat.apache.org/whichversion.html
# - https://jersey.github.io/documentation/latest/deployment.html#deployment.servlet.3
FROM tomcat:9.0.14-jre11

WORKDIR $CATALINA_HOME/webapps/

# Making app the default: https://www.baeldung.com/tomcat-root-application
RUN rm -r ROOT/
COPY location-svc/build/libs/*.war ROOT.war

# TODO Definitely this version of TOMCAT ?
