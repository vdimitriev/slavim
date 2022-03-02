#!/bin/bash
CQL="CREATE KEYSPACE IF NOT EXISTS slavim_chat WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};
CREATE TABLE IF NOT EXISTS slavim_chat.messages (username text, chatRoomId text, date timestamp, fromUser text, toUser text, text text, PRIMARY KEY ((username, chatRoomId), date));"

until echo $CQL | cqlsh; do
  echo "cqlsh: Cassandra is unavailable to initialize - will retry later"
  sleep 2
done &

exec /docker-entrypoint.sh "$@"
