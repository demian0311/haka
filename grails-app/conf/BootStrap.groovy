import haka.Project

class BootStrap {

    def init = { servletContext ->
        Project services = new Project(name: "Services", description: "ReST JSON services")
        services.save()

        Project portal = new Project(name: "Portal", description: "Grails customer portals")
        portal.save()

        Project dataWarehouse = new Project(name: "Data Warehouse", description: "Aggregated historical data")
        dataWarehouse.save()

        Project billingConversion = new Project(name: "Billing Conversion", description: "Decomission old billing systems")
        billingConversion.save()
    }
    def destroy = {

    }
}
