#!/bin/bash


PROJECT_PATH="/Users/luan.phm/engineering/Projects/ADongGroup/adg-services"
SCRIPT_PATH="./scripts/start-application/dev/customized-run/clean-run-server.sh"

CLASSES="$PROJECT_PATH/adg-scheduler/target/classes"
cd $PROJECT_PATH

echo "Build then run adg-scheduler service"

sh $SCRIPT_PATH scheduler dev "$CLASSES"