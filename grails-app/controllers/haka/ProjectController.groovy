package haka

class ProjectController {

    def index(){
        [projects: Project.findAll()]
    }

    def show(){
        [project: Project.get(params.id)]
    }
}
