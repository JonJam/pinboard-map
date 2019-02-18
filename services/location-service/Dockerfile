# Current version of Jersey supports servlet 3.* (https://jersey.github.io/documentation/latest/deployment.html#deployment.servlet.3),
# so should use tomcat version 8.* (according to https://tomcat.apache.org/whichversion.html). 
# 
# Using tomcat 9.* and the defacto image (https://hub.docker.com/_/tomcat)
FROM tomcat:9.0.14-jre11

# Removing default web applications (https://tomcat.apache.org/tomcat-9.0-doc/security-howto.html#Default_web_applications)
RUN rm -r $CATALINA_HOME/webapps

# Making this webapp the default (https://wiki.apache.org/tomcat/HowTo#How_do_I_make_my_web_application_be_the_Tomcat_default_application.3F)
COPY location-svc/build/libs/*.war $CATALINA_HOME/webapps/ROOT.war