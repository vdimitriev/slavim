#!/bin/bash
CQL="CREATE KEYSPACE IF NOT EXISTS slavim_chat WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};"

until echo $CQL | cqlsh; do
  echo "cqlsh: Cassandra is unavailable to initialize - will retry later"
  sleep 2
done &

exec /docker-entrypoint.sh "$@"
