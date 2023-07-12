FROM eclipse-temurin:17-jre-alpine as build
MAINTAINER Pavel Kazhukov <sanpavkaz@gmail.com>
RUN apk add --no-cache curl

FROM build
EXPOSE 8003
COPY target/analysis-service-1.0-SNAPSHOT.jar /

