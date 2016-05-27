#!/bin/bash

source /etc/profile

cur_dir=$(cd "$(dirname "$0")"; pwd)
RAPIDCRON_HOME="${cur_dir}/../"

java -classpath "${RAPIDCRON_HOME}/lib/*" com.github.rapidcron.webservice.client.main.RapidCronClientMain
