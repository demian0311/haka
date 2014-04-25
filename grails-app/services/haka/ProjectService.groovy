package haka

import grails.transaction.Transactional
import haka.commands.ProjectCommand
import org.grails.plugins.metrics.groovy.Metered
import org.grails.plugins.metrics.groovy.Timed

@Transactional
class ProjectService {

    Random random = new Random()
    def grailsApplication
    def memcachedService

    @Timed @Metered
    def findAll() {
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

