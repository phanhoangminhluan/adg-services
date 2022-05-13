#!/bin/bash


PROJECT_PATH="/home/ubuntu/adg-services"
SCRIPT_PATH="./scripts/start-application/prod/customized-run/clean-run-server.sh"

CLASSES="$PROJECT_PATH/adg-api/target/classes"

cd $PROJECT_PATH

echo "Build then run adg-api service"

sh $SCRIPT_PATH api dev "$CLASSES"