package haka

class ProjectController {

    def grailsApplication

    Random random = new Random()

    def index(){
        if(grailsApplication.config.haka.mischief){
            println("sleeping")
            Integer howLongToSleep = random.nextInt(400) + 100
            println("sleeping for $howLongToSleep")
            Thread.sleep(howLongToSleep)
        }

        [projects: Project.findAll()]
    }

    def show(){
        println(">>" + grailsApplication.config.haka.mischief)
        if(grailsApplication.config.haka.mischief && random.nextInt() % 9 == 0){
            println("throwing an exception")
            throw new RuntimeException("data-center is smoking hole")
        }
        [project: Project.get(params.id)]
    }
}
