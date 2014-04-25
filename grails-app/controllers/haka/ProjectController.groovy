package haka

import com.neidetcher.hcbp.util.HystrixConfigurationUtility
import com.netflix.hystrix.HystrixCommand
import com.netflix.hystrix.HystrixCommandGroupKey

class ProjectController {

    //def grailsApplication
    //Random random = new Random()
    def projectService

    def index(){
        /*
        if(grailsApplication.config.haka.mischief){
            println("sleeping")
            Integer howLongToSleep = random.nextInt(400) + 100
            println("sleeping for $howLongToSleep")
            Thread.sleep(howLongToSleep)
        }*/

        [projects: projectService.findAll()]
    }

    /*
    class IndexCommand extends HystrixCommand{
        IndexCommand(){
            super(createHystrixCommandSetter())
        }

        static HystrixCommand.Setter createHystrixCommandSetter(){
            HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(this.class.name))
                    .andCommandPropertiesDefaults(
                    HystrixConfigurationUtility.createHystrixCommandPropertiesSetter().withCircuitBreakerEnabled(true)
                            .withCircuitBreakerSleepWindowInMilliseconds(1000))
        }

        @Override

    }*/

    def show(){
        /*
        println(">>" + grailsApplication.config.haka.mischief)
        if(grailsApplication.config.haka.mischief && random.nextInt() % 9 == 0){
            println("throwing an exception")
            throw new RuntimeException("data-center is smoking hole")
        }
        */
        [project: projectService.findById(params.id)]
        //[project: Project.get(params.id)]
    }
}
