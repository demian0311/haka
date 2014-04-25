package haka

import org.grails.plugins.metrics.groovy.Metered
import org.grails.plugins.metrics.groovy.Timed

class ProjectController {

    def projectService

    @Timed @Metered
    def index(){
        def start = System.currentTimeMillis()
        [projects: projectService.findAll(), start: start]
    }

    @Timed @Metered
    def show(){
        def start = System.currentTimeMillis()
        [project: projectService.findById(params.id), start: start]
    }
}
