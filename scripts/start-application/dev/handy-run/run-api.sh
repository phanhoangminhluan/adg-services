#!/bin/bash


PROJECT_PATH="/Users/luan.phm/engineering/Projects/ADongGroup/adg-services"
SCRIPT_PATH="./scripts/start-application/dev/customized-run/run-server.sh"

CLASSES="$PROJECT_PATH/adg-api/target/classes"

cd $PROJECT_PATH

echo "Run adg-api service"

sh $SCRIPT_PATH api dev "$CLASSES"