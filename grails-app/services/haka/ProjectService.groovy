package haka

import grails.transaction.Transactional

@Transactional
class ProjectService {

    Random random = new Random()
    def grailsApplication

    def findAll() {
        if(grailsApplication.config.haka.mischief){
            println("sleeping")
            Integer howLongToSleep = random.nextInt(400) + 100
            println("sleeping for $howLongToSleep")
            Thread.sleep(howLongToSleep)
        }

        Project.findAll()
    }

    def findById(id){
        println(">>" + grailsApplication.config.haka.mischief)
        if(grailsApplication.config.haka.mischief && random.nextInt() % 9 == 0){
            println("throwing an exception")
            throw new RuntimeException("data-center is smoking hole")
        }

        Project.get(id)
    }

}
