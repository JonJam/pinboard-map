CATALINA_OPTS="$CATALINA_OPTS -Dport.http=80${APP_ID}"
CATALINA_OPTS="$CATALINA_OPTS -Dport.https=84${APP_ID}"

# Enabling JMX Remote (https://tomcat.apache.org/tomcat-9.0-doc/monitoring.html#Enabling_JMX_Remote)
CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote"
CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.port=70${APP_ID}"
CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.ssl=false"
CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.authenticate=false"

# Enabling Java Remote Debug (https://wiki.apache.org/tomcat/FAQ/Developing#Q1)
CATALINA_OPTS="$CATALINA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=*:40${APP_ID},server=y,suspend=n"