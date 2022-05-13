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


PROJECT_PATH="/home/ubuntu/adg-services"
SERVER_PROJECT_PATH="$PROJECT_PATH/adg-server"

SOURCE="$SERVER_PROJECT_PATH/target"
LOG_DIR="$PROJECT_PATH/log"
LOGBACK_PATH="$SERVER_PROJECT_PATH/src/main/resources/logback.xml"

JAR_PATH="$SOURCE/adg-server-1.0-SNAPSHOT.jar"
LIB_PATH="$SOURCE/lib/*"

CORE_CLASSES="$PROJECT_PATH/adg-core/target/classes"
ACTIVE_PROFILE=$1
MODE=$2
CLASSES=$3

cd $PROJECT_PATH

echo ""
echo "RUNNING COMMAND: START ------------------------------------------------------------------------------------------------------------"

echo java \
  -Xms256M \
  -Xmx512M \
       -Dspring.profiles.active="$ACTIVE_PROFILE-$MODE" \
       -Dlogging.config="$LOGBACK_PATH" \
       -DACTIVE_PROFILE="$ACTIVE_PROFILE" \
       -DLOG_DIR="$LOG_DIR" \
       -classpath "$JAR_PATH:$LIB_PATH:$CORE_CLASSES:$CLASSES" \
       com.adg.server.AdgServerApplication "$ACTIVE_PROFILE"

echo "RUNNING COMMAND: END ------------------------------------------------------------------------------------------------------------"
echo ""

java \
  -Dspring.profiles.active="$ACTIVE_PROFILE-$MODE" \
  -Dlogging.config="$LOGBACK_PATH" \
  -DACTIVE_PROFILE="$ACTIVE_PROFILE" \
  -DLOG_DIR="$LOG_DIR" \
  -classpath "$JAR_PATH:$LIB_PATH:$CORE_CLASSES:$CLASSES" \
  com.adg.server.AdgServerApplication "$ACTIVE_PROFILE"

