package haka.commands

import com.neidetcher.hcbp.util.HystrixConfigurationUtility
import com.netflix.hystrix.HystrixCommand
import com.netflix.hystrix.HystrixCommandGroupKey

class HystrixClosureCommand extends HystrixCommand {
    Closure closure

    // https://github.com/Netflix/Hystrix/wiki/Configuration
    def HystrixCommand.Setter createHystrixCommandSetter(String key){
        HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(key))
                .andCommandPropertiesDefaults(
                HystrixConfigurationUtility.createHystrixCommandPropertiesSetter().withCircuitBreakerEnabled(true)
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        .withCircuitBreakerRequestVolumeThreshold(5)
                        .withCircuitBreakerSleepWindowInMilliseconds(500)
                        .withExecutionIsolationThreadTimeoutInMilliseconds(5000))
    }

    HystrixClosureCommand(String key, Closure closureIn){
        super(createHystrixCommandSetter("foo"))
        closure = closureIn
    }

    @Override run(){
        closure.call()
    }

}
