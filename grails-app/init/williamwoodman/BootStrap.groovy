package williamwoodman

class BootStrap {

    def init = { servletContext ->
        Customer c = new Customer(name: 'Customer One')
        c.addToSites(name: 'Site One')
        c.save()
    }
    def destroy = {
    }
}
