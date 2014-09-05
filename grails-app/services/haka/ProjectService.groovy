package haka

import com.codahale.metrics.health.HealthCheck
import grails.transaction.Transactional
import org.grails.plugins.metrics.groovy.Metered
import org.grails.plugins.metrics.groovy.Timed

@Transactional
class ProjectService extends HealthCheck {

    Random random = new Random()
    def grailsApplication
    def memcachedService

    HealthCheck.Result check() throws Exception{
        if(this.findAll().isEmpty()){
            HealthCheck.Result.unhealthy()
        } else {
            HealthCheck.Result.healthy()
        }
    }

    @Timed @Metered
    def List<Project> findAll() {
        (hystrix(group:'ProjectDatabase'){
            mischiefSleep()
            return memcachedService.get("Project.findAll()", {Project.findAll()})
        }).get()
    }

    @Timed @Metered
    def findById(id){
        (hystrix(group:'ProjectDatabase'){
            mischiefThrow()
            return memcachedService.get("Project.findById($id)", {Project.get(id)})
        }).get()
    }

    private def mischiefSleep(){
        if(grailsApplication.config.haka.mischief){
            Thread.sleep(random.nextInt(400) + 100)
        }
    }

    private void mischiefThrow() {
        if (grailsApplication.config.haka.mischief && random.nextInt() % 13 == 0) {
            println("throwing an exception")
            throw new RuntimeException("data-center is smoking hole")
        }
    }
}

