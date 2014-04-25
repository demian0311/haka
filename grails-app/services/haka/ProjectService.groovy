package haka

import grails.transaction.Transactional
import haka.commands.ProjectCommand

@Transactional
class ProjectService {

    Random random = new Random()
    def grailsApplication

    def findAll() {
        ProjectCommand projectCommand = new ProjectCommand({
            if(grailsApplication.config.haka.mischief){
                Thread.sleep(random.nextInt(400) + 100)
            }

            Project.findAll()
        })

        projectCommand.execute()
    }

    def findById(id){
        ProjectCommand projectCommand = new ProjectCommand({
            if(grailsApplication.config.haka.mischief && random.nextInt() % 13 == 0){
                println("throwing an exception")
                throw new RuntimeException("data-center is smoking hole")
            }

            Project.get(id)
        })

        projectCommand.execute()
    }
}

