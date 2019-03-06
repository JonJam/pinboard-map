# Current version of Jersey supports servlet 3.* (https://jersey.github.io/documentation/latest/deployment.html#deployment.servlet.3),
# so should use tomcat version 8.* (according to https://tomcat.apache.org/whichversion.html). 
# 
# Using tomcat 9.* and the defacto image (https://hub.docker.com/_/tomcat)
FROM tomcat:9.0.14-jre11

ARG env=prod

COPY docker/tomcat/$env/setenv.sh $CATALINA_HOME/bin
# Configure SecurityManager (https://tomcat.apache.org/tomcat-9.0-doc/security-manager-howto.html)
COPY docker/tomcat/catalina.policy $CATALINA_HOME/conf/catalina.policy
COPY docker/tomcat/catalina.properties $CATALINA_HOME/conf/catalina.properties
# Configure server.xml (https://tomcat.apache.org/tomcat-9.0-doc/security-howto.html#server.xml)
COPY docker/tomcat/server.xml $CATALINA_HOME/conf/server.xml
# Configure logging (https://tomcat.apache.org/tomcat-9.0-doc/logging.html#Considerations_for_production_usage)
COPY docker/tomcat/logging.properties $CATALINA_HOME/conf/logging.properties
# Configure HTTPS. Used keytool to generate self cert. (https://tomcat.apache.org/tomcat-9.0-doc/ssl-howto.html)
COPY docker/tomcat/$env/localhost-keystore $CATALINA_HOME/conf/.localhost-keystore

# Removing default web applications (https://tomcat.apache.org/tomcat-9.0-doc/security-howto.html#Default_web_applications)
RUN rm -r $CATALINA_HOME/webapps

# Running as non-root user (https://tomcat.apache.org/tomcat-9.0-doc/security-howto.html#Non-Tomcat_settings)
# Helpful links:
# - https://github.com/docker-library/tomcat/commit/6f1c628fa92feea8b28e98e56a990500f2326936
# - https://github.com/docker-library/tomcat/issues/35
RUN groupadd -r tomcatGroup && useradd -r -g tomcatGroup tomcat	
RUN chown -R tomcat /usr/local/tomcat
USER tomcat

# Starting with SecurityManager (https://tomcat.apache.org/tomcat-9.0-doc/security-manager-howto.html)
CMD ["catalina.sh", "run", "-security"]