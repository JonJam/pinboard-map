# Current version of Jersey supports servlet 3.* (https://jersey.github.io/documentation/latest/deployment.html#deployment.servlet.3),
# so should use tomcat version 8.* (according to https://tomcat.apache.org/whichversion.html). 
# 
# Using tomcat 9.* and the defacto image (https://hub.docker.com/_/tomcat)
FROM tomcat:9.0.14-jre11

WORKDIR $CATALINA_HOME/webapps/

# Making this webapp the default (see https://www.baeldung.com/tomcat-root-application)
RUN rm -r ROOT/
COPY location-svc/build/libs/*.war ROOT.war