package haka

class ProjectController {

    def projectService

    def index(){
        [projects: projectService.findAll(), start: System.currentTimeMillis()]
    }

    def show(){
        [project: projectService.findById(params.id), start: System.currentTimeMillis()]
    }
}
