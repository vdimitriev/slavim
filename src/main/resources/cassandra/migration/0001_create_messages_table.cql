CREATE TABLE IF NOT EXISTS  slavim_chat.messages (
     username   text,
     chatRoomId text,
     date       timestamp,
     fromUser   text,
     toUser     text,
     text       text,
     primary key ((username, chatRoomId), date)
);