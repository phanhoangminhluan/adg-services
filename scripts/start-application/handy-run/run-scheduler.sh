#!/bin/bash


PROJECT_PATH="/Users/luan.phm/engineering/Projects/ADongGroup/adg-services"
SCRIPT_PATH="./scripts/start-application/customized-run/run-server.sh"

CLASSES="$PROJECT_PATH/adg-scheduler/target/classes"

cd $PROJECT_PATH

echo "Run adg-scheduler service"

sh $SCRIPT_PATH scheduler dev "$CLASSES"