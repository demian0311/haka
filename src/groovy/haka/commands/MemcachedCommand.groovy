package haka.commands

class MemcachedCommand extends HystrixClosureCommand{
    MemcachedCommand(Closure closureIn){
        super("Memcached", closureIn)
    }
}
