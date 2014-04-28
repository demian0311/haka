#!/bin/sh

~/opt/tomcat/bin/catalina.sh start &
memcached -d 
mysql.server start
#cd ~/code/haka
#./grailsw test run-app
