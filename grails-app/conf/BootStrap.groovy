import haka.Project
import org.grails.plugins.metrics.groovy.HealthChecks
import org.grails.plugins.metrics.groovy.Metrics

class BootStrap {

    def projectService
    def memcachedService

    def init = { servletContext ->
        // TOOD-DLN: do env check
        Project services = new Project(name: "Services", description: "ReST JSON services")
        services.save()

        Project portal = new Project(name: "Portal", description: "Grails customer portals")
        portal.save()

        Project dataWarehouse = new Project(name: "Data Warehouse", description: "Aggregated historical data")
        dataWarehouse.save()

        Project billingConversion = new Project(name: "Billing Conversion", description: "Decomission old billing systems")
        billingConversion.save()

        // Metrics
        Metrics.startJmxReporter()
        HealthChecks.register("ProjectService", projectService)
        HealthChecks.register("MemcachedService", memcachedService)
    }
    def destroy = {

    }
}
