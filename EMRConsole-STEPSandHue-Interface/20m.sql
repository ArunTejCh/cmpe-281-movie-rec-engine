
set mapreduce.job.reduces= 5;
set mapred.min.split.size=67108864;

CREATE EXTERNAL TABLE AvgRating (uid int,movie_id int,rating int) ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
LINES TERMINATED BY "\n" STORED AS TEXTFILE;


  LOAD DATA INPATH 's3://aravinda281proj/Input/ratings.csv' INTO TABLE AvgRating;

INSERT OVERWRITE DIRECTORY 's3://aravinda281proj/Output/' SELECT movie_id , AVG(rating) AS average FROM AvgRating GROUP BY movie_id;