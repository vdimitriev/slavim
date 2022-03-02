CREATE KEYSPACE slavim_chat WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

CREATE TABLE IF NOT EXISTS  slavim_chat.messages (
      username   text,
      chatRoomId text,
      date       timestamp,
      fromUser   text,
      toUser     text,
      text       text,
      primary key ((username, chatRoomId), date)
);