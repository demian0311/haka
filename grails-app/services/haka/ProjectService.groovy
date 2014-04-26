package haka

import com.codahale.metrics.health.HealthCheck
import grails.transaction.Transactional
import haka.commands.ProjectCommand
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
        ProjectCommand projectCommand = new ProjectCommand({
            if(grailsApplication.config.haka.mischief){
                Thread.sleep(random.nextInt(400) + 100)
            }

            def closure = {Project.findAll()}

            try{
                return memcachedService.get("Project.findAll", closure)
            } catch (Throwable e){
                println("***********" + e)
            }

            return closure()
        })

        projectCommand.execute()
    }

    @Timed @Metered
    def findById(id){
        ProjectCommand projectCommand = new ProjectCommand({
            if(grailsApplication.config.haka.mischief && random.nextInt() % 13 == 0){
                println("throwing an exception")
                throw new RuntimeException("data-center is smoking hole")
            }

            def closure = {Project.get(id)}

            try{
                return memcachedService.get("Project.findById($id)", closure)
            } catch (Throwable e){
                println("***********" + e)
            }

            return closure()
        })

        projectCommand.execute()
    }
}

