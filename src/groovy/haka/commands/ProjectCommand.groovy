package haka.commands

class ProjectCommand extends HystrixClosureCommand{
    ProjectCommand(Closure closureIn){
        super("ProjectDatabase", closureIn)
    }
}
