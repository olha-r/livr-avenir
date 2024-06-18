#!/bin/bash

exit_code=0

SCRIPT=$0

echo "----------"
echo "Executing script: ${SCRIPT}"
echo "Started at $(date +"%T.%3N %Z")"
echo "----------"

USR=$1
PWD=$2
HOST=$3
DB=$4

psql postgresql://$USR:$PWD@$HOST/$DB <<MULTILINE
\set ON_ERROR_STOP

\i ./schema.ddl.sql
\i ./data.dml.sql

\q
MULTILINE

psql_exit_status=$?

if [ $psql_exit_status -gt 0 ]; then
    exit_code=1
fi

echo "----------"
echo "Executed script: ${SCRIPT}"
echo "psql exit status=${psql_exit_status}"
echo "Ended at $(date +"%T.%3N %Z")"
echo "----------"

exit $exit_code