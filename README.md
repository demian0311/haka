Haka is a sandbox for the 4 things you should do for all outgoing posts.


## Test mode
You'll want to run it as `test` to see it reach out to all the different pieces.
```
./grailsw test run-app
```

When it's in `test` mode you'll also see bad behavior.  This is by design so that
we can see interesting things happening.

When you run it in test mode it'll try to connect to memcached and MySQL.

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
- wrap ProjectService calls in circuit breaker
- observe circuit breaker working
- wrap database calls in memcache caching
- observe cache working
- wrap calls in metrics collection
- figure out how to report metrics collection
   - collectd
   - graphite
   - cactus
   - jmx
- what else can we monitor?
   - response codes?

### DONE
- pull in Hystrix Plugin
