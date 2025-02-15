#!/bin/bash

#(cd backend && mvn clean install && mvn spring-boot:run) & (cd frontend && yarn install && yarn dev)

echo "Starting DSCommerce Application..."

# forcing all processes to stop when Ctrl+C is pressed
cleanup() {
    echo "Stopping Application..."
    kill $BACKEND_PID $FRONTEND_PID
    exit 0
}

# cleanup function that will be called when the script is terminated. In this case, when the user presses Ctrl+C.
trap cleanup SIGINT # when Ctrl+C is pressed, the cleanup function is called to stop the backend and frontend processes gracefully

echo "Setting up backend..."
cd backend || { echo "Backend directory not found!"; exit 1; }
if ! mvn clean install; then
    echo "Maven build failed!"
    exit 1
fi
mvn spring-boot:run &
BACKEND_PID=$! # storing the process ID
cd ..

echo "Setting up frontend..."
cd frontend || { echo "Frontend directory not found!"; exit 1; }
if ! yarn install; then
    echo "Yarn installation failed!"
    exit 1
fi
yarn dev &
FRONTEND_PID=$! # storing the process ID
cd ..

# keep script running to allow Ctrl+C to be caught
wait
