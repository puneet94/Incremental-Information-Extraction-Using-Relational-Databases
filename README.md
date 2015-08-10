# Incremental-Information-Extraction-Using-Relational-Databases

This is a JAVA based project. This project uses Relational Databases to improve searching capabilities of search engines. 
I selected this project to get a hands-on experience on Java-SWING and Relational Database(MySql). Search engines tend to do the 
search again even if the previous serious and currently used search keywords are almost similar. To make the process faster 
when previous keywords are almost similar to current keywords, we stored the intermediate results ina database to avoid 
doing the whole process again.

To make the process faster when previous keywords are almost similar to current keywords we stored the intermediate results in a 
database to avoid doing the whole process again. The problem was to store the intermediate results in RDBMS and retrieving them 
for a search whenever the keywords matched and displaying file to user.

I took the filename and location from the search engine after a search was done successfully. I stored the name and location 
in a database table. Whenever a search process was initiated, my program would look into the database to see if the keywords 
match any previous search. If yes, then the filename and location would be retrieved and displayed to the user. 
If not, then the process would be done by the underlying search engine and if the search is successful then the keywords, 
filename, and location would be stored in the database table for future reference.

Database used was MySql
Search engine was Lucene(Java based file system search engine)
