package haka

class ProjectController {

    def projectService

    def index(){
        def start = System.currentTimeMillis()
        [projects: projectService.findAll(), start: start]
    }

    def show(){
        def start = System.currentTimeMillis()
        [project: projectService.findById(params.id), start: start]
    }
}
