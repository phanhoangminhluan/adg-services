#!/bin/bash

############ HOW TO USE ############
# Run without build
# sh run-server.sh <profile> <mode>
# Ex: sh run-server.sh scheduler dev
#
# Before using this script. Please:
# - Specify PROJECT_PATH: path to your project
# - Create folder named 'log' in PROJECT_PATH
####################################


PROJECT_PATH="/Users/luan.phm/engineering/Projects/ADongGroup/adg-services"
SERVER_PROJECT_PATH="$PROJECT_PATH/adg-server"

SOURCE="$SERVER_PROJECT_PATH/target"
LOG_DIR="$PROJECT_PATH/log"
LOGBACK_PATH="$SERVER_PROJECT_PATH/src/main/resources/logback.xml"

JAR_PATH="$SOURCE/adg-server-1.0-SNAPSHOT.jar"
LIB_PATH="$SOURCE/lib/*"

ACTIVE_PROFILE=$1
MODE=$2

cd $PROJECT_PATH

mvn clean install

java -cp "$JAR_PATH:$LIB_PATH" \
  -jar -Dspring.profiles.active="$ACTIVE_PROFILE-$MODE" \
  -Dlogging.config="$LOGBACK_PATH" \
  -DACTIVE_PROFILE="$ACTIVE_PROFILE" \
  -DLOG_DIR="$LOG_DIR" \
  $JAR_PATH "$ACTIVE_PROFILE"
