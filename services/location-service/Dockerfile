ARG VERSION=latest
FROM jonjam/pinboard-map-base-tomcat-container:$VERSION

ENV APP_ID 31

# Making this webapp the default (https://wiki.apache.org/tomcat/HowTo#How_do_I_make_my_web_application_be_the_Tomcat_default_application.3F)
COPY location-svc/build/libs/*.war $CATALINA_HOME/webapps/ROOT.war