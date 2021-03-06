#!/bin/bash


PROJECT_PATH="/Users/luan.phm/engineering/Projects/ADongGroup/adg-services"
SCRIPT_PATH="./scripts/start-application/dev/customized-run/run-server.sh"

CLASSES="$PROJECT_PATH/adg-loader/target/classes"

cd $PROJECT_PATH

echo "Run adg-loader service"

sh $SCRIPT_PATH loader dev "$CLASSES"