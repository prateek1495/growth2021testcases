CREATE KEYSPACE cassandra_demo WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
USE cassandra_demo;
CREATE TABLE person ( id bigint PRIMARY KEY, full_name varchar, age int );
DROP KEYSPACE cassandra_demo;