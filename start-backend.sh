#!/bin/bash

# Backend startup script
# This script checks if the backend application is already running, stops it if necessary, and then starts it

# Configuration
PORT=26859
BACKEND_DIR="/usr/local/share/data-case-6/auto-flowless/auto-flowless-backend"
JAR_NAME="web.jar"
LOG_FILE="/usr/local/share/data-case-6/auto-flowless/backend.log"

# Check if the application is already running
PID=$(lsof -t -i:$PORT 2>/dev/null)

if [ -n "$PID" ]; then
    echo "Backend application is already running on port $PORT with PID $PID. Stopping it..."
    kill -15 $PID
    sleep 5
    
    # Check if the process is still running
    PID=$(lsof -t -i:$PORT 2>/dev/null)
    if [ -n "$PID" ]; then
        echo "Failed to stop the application gracefully. Forcing shutdown..."
        kill -9 $PID
        sleep 2
    fi
    
    echo "Backend application stopped successfully"
fi

# Navigate to the backend directory
cd $BACKEND_DIR

# Set the JAR path to the target directory
JAR_PATH="web/target/$JAR_NAME"

# Check if the JAR file exists in the target directory
if [ -f "$JAR_PATH" ]; then
    echo "JAR file found in target directory. Using existing JAR file..."
else
    echo "JAR file not found in target directory. Building the backend application..."
    mvn clean package -DskipTests
    if [ $? -ne 0 ]; then
        echo "Build failed. Exiting..."
        exit 1
    fi
fi

# Start the application with the correct JAR path
echo "Starting backend application on port $PORT..."
nohup java --add-exports=java.desktop/sun.font=ALL-UNNAMED -jar "$JAR_PATH" --server.port=$PORT --spring.config.location=classpath:/application.yml > $LOG_FILE 2>&1 &

# Wait for the application to start
sleep 30

# Check if the application is running
PID=$(lsof -t -i:$PORT 2>/dev/null)
if [ -n "$PID" ]; then
    echo "Backend application started successfully on port $PORT with PID $PID"
    echo "Logs can be found at: $LOG_FILE"
else
    echo "Failed to start backend application. Check logs at: $LOG_FILE"
    exit 1
fi