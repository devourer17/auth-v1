CREATE TABLE users (
  id bigint primary key,
  user_name varchar(255) unique not null,
  password text not null,
  email varchar(512) unique not null,
  is_active boolean not null,
  authority text,
  created_by bigint not null,
  updated_by bigint not null,
  created_at timestamp not null default now(),
  updated_at timestamp not null default now(),
  version int not null
);