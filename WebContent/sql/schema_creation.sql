DROP DATABASE IF EXISTS recoeng;       -- Delete if it exists
CREATE DATABASE recoeng;               -- Create a new database

use recoeng;

CREATE TABLE movies
(
  movie_id int PRIMARY KEY,
  title varchar(255),
  genres varchar(255),
  no_of_ratings int,
  avg_rating double
);

CREATE TABLE ratings
(
  user_id int,
  movie_id int,
  rating float(2,1),
  time_stamp bigint,
  CONSTRAINT pk_RatingID PRIMARY KEY (user_id,movie_id)
);


