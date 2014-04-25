package haka.commands

import com.neidetcher.hcbp.util.HystrixConfigurationUtility
import com.netflix.hystrix.HystrixCommand
import com.netflix.hystrix.HystrixCommandGroupKey

class ProjectCommand extends HystrixCommand{

    Closure closure

    //https://github.com/Netflix/Hystrix/wiki/Configuration
    static HystrixCommand.Setter createHystrixCommandSetter(){
        HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(this.class.name))
                .andCommandPropertiesDefaults(
                HystrixConfigurationUtility.createHystrixCommandPropertiesSetter().withCircuitBreakerEnabled(true)
                        .withCircuitBreakerErrorThresholdPercentage(20)
                        .withCircuitBreakerRequestVolumeThreshold(5)
                        .withCircuitBreakerSleepWindowInMilliseconds(1000))
    }

    ProjectCommand(Closure closureIn){
        super(createHystrixCommandSetter())
        closure = closureIn
    }

    @Override run(){
        closure.call()
    }
}
