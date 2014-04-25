Haka is a sandbox for the 4 things you should do for all outgoing posts.


## Test mode
You'll want to run it as `test` to see it reach out to all the different pieces.
```
./grailsw test run-app
```

When it's in `test` mode you'll also see bad behavior.  This is by design so that
we can see interesting things happening.

When you run it in test mode it'll try to connect to memcached and MySQL.

## Memcached
To start it:
```
memcached
```

To see that it's up and look at stats.
```
telnet localhost 11211
Trying ::1...
Connected to localhost.
Escape character is '^]'.
stats
STAT get_hits 782
STAT get_misses 886
STAT bytes 2209
STAT curr_items 5
STAT total_items 26
STAT evictions 0
END
```

## MySQL
```
create database haka;
create user 'haka'@'localhost' identified by 'akah';
GRANT ALL ON haka.* TO 'haka'@'localhost';
```

## Hystrix Dashboard
If Hystrix Gradle build isn't working for you, you can always get the war file from here.
http://mvnrepository.com/artifact/com.netflix.hystrix/hystrix-dashboard/1.2.16

## Process
### TODO
- metrics
   - health checks
   - reporting elsewhere?

### DONE
- metrics
   - jmx reporting
   - timers
   - meters
- observe cache working
- wrap database calls in memcache caching
- observe circuit breaker working
- wrap ProjectService calls in circuit breaker
- pull in Hystrix Plugin
