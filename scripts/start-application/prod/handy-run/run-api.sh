#!/bin/bash


PROJECT_PATH="/home/ubuntu/adg-services"
SCRIPT_PATH="./scripts/start-application/prod/customized-run/run-server.sh"

CLASSES="$PROJECT_PATH/adg-api/target/classes"

cd $PROJECT_PATH

echo "Run adg-api service"

sh $SCRIPT_PATH api dev "$CLASSES"