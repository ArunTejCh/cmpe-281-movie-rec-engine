LOAD DATA INFILE 'D:\Masters\\CMPE 281\\Projects\\Project 2\\Movielens\\ml-20m\\movies.txt'
INTO TABLE movies
FIELDS TERMINATED BY '	'
IGNORE 1 LINES
(movie_id,title,genres);

LOAD DATA INFILE 'D:\Masters\\CMPE 281\\Projects\\Project 2\\Movielens\\ml-20m\\ratings.csv'
INTO TABLE ratings
FIELDS TERMINATED BY ','
IGNORE 1 LINES;

UPDATE movies, (select movie_id, no_of_ratings, rating from ratings group by movie_id) as t2
SET    movies.no_of_ratings = t2.no_of_ratings
, movies.avg_rating = t2.rating
WHERE  movies.movie_id = t2.movie_id;

#INSERT OVERWRITE LOCAL DIRECTORY '/home/aruntej/codebase/recoeng/temp.csv' 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
select movie_id, count(*) as count, AVG(rating) as rating from ratings group by movie_id;




