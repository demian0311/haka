package haka

import grails.transaction.Transactional
import haka.commands.MemcachedCommand
import net.spy.memcached.AddrUtil
import net.spy.memcached.MemcachedClient

import javax.annotation.PostConstruct

/**
 * Ol' Goodwill:
 * https://www.ibm.com/developerworks/library/j-memcached2/
 */
@Transactional
class MemcachedService {

    def grailsApplication
    def MemcachedClient memcachedClient
    Integer expirationInSeconds = 120

    @PostConstruct
    def void init() {
        String memcachedUrl = grailsApplication.config.haka.memcachedUrl
        println("memcachedUrl: " + memcachedUrl)

        if (memcachedUrl) {
            println("Caching enabled, using memcached at " + memcachedUrl)
            memcachedClient = new MemcachedClient(AddrUtil.getAddresses(memcachedUrl))
        } else {
            println("Nothing defined in haka.memcachedUrl so caching not enabled")
        }
    }

    private def set(String key, Object value) {
        if (memcachedClient) {
            memcachedClient.set(key, expirationInSeconds, value)
        }
    }

    def get(String key, Closure closure) {

        MemcachedCommand memcachedCommand = new MemcachedCommand({
            if (memcachedClient) {
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
            }
        })

        memcachedCommand.execute()
    }
}
