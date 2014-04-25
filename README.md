Haka is a sandbox for the 4 things you should do for all outgoing posts.


## Test mode
You'll want to run it as `test` to see it reach out to all the different pieces.
```
./grailsw test run-app
```

When you run it in test mode it'll try to connect to memcached and MySQL.

## MySQL
```
create database haka;
create user 'haka'@'localhost' identified by 'akah';
GRANT ALL ON haka.* TO 'haka'@'localhost';
```
