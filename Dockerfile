FROM tomcat:jre21-temurin-noble
COPY target/freelanceExchange-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]