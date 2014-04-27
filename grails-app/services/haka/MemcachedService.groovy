package haka

import com.codahale.metrics.health.HealthCheck
import grails.transaction.Transactional
import haka.commands.MemcachedCommand
import net.spy.memcached.AddrUtil
import net.spy.memcached.MemcachedClient
import org.grails.plugins.metrics.groovy.Metered
import org.grails.plugins.metrics.groovy.Timed

import javax.annotation.PostConstruct

@Transactional
class MemcachedService extends HealthCheck {

    def grailsApplication
    def MemcachedClient memcachedClient
    Integer expirationInSeconds = 120

    HealthCheck.Result check() throws Exception {
        Map<SocketAddress, String> versions = memcachedClient.versions
        for(key in versions.keySet()){
            String version = versions[key]
            if(version == null){
                return HealthCheck.Result.unhealthy("unable to get stats from ${key}")
            }
        }

        return HealthCheck.Result.healthy()
    }

    @PostConstruct
    def void init() {
        String memcachedUrl = grailsApplication.config.haka.memcachedUrl
        log.debug("memcachedUrl: " + memcachedUrl)

        if (memcachedUrl) {
            println("Caching enabled, using memcached at " + memcachedUrl)
            memcachedClient = new MemcachedClient(AddrUtil.getAddresses(memcachedUrl))
        } else {
            println("Nothing defined in haka.memcachedUrl so caching not enabled")
        }
    }

    //@Timed
    //@Metered
    private def set(String key, Object value) {
        if (memcachedClient) {
            memcachedClient.set(key, expirationInSeconds, value)
        }
    }

    @Timed
    @Metered
    def get(String key, Closure closure) {
        if (check().equals(HealthCheck.Result.healthy())) {
            MemcachedCommand memcachedCommand = new MemcachedCommand({
                def fromCache = memcachedClient.get(key)
                if (fromCache) {
                    return fromCache
                } else {
                    def fromClosure = closure()
                    if (fromClosure) {
                        set(key, fromClosure)
                    }

                    return fromClosure
                }
            })

            memcachedCommand.execute()
        } else {
            log.error("memcached not healthy, not caching, key: $key")
            closure()
        }
    }
}
