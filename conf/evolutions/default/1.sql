# Tasks schema

# --- !Ups

CREATE TABLE tasks (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  label VARCHAR(255),
  done BOOLEAN default 'f'
);

# --- !Downs

DROP TABLE tasks;
