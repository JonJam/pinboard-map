ARG VERSION
FROM jonjam/pinboard-map-base-tomcat-container:$VERSION

# This is to support integration tests using testcontainers.
ARG APP_ID
ENV APP_ID ${APP_ID:-32}

ENV SERVICE_NAME "web"

# Making this webapp the default (https://wiki.apache.org/tomcat/HowTo#How_do_I_make_my_web_application_be_the_Tomcat_default_application.3F)
COPY build/libs/*.war $CATALINA_HOME/webapps/ROOT.war