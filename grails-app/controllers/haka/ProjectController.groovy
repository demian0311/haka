package haka

class ProjectController {

    def projectService

    def index(){
        [projects: projectService.findAll()]
    }

    def show(){
        [project: projectService.findById(params.id)]
    }
}
