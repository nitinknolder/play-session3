# ---!Ups

create table usersprofile(
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name varchar(20),
 lname varchar(20),
 email varchar(30)
  );

  # --- !Downs

  DROP TABLE usersprofile;
