package williamwoodman

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CustomerController {

    CustomerService customerService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond customerService.list(params), model:[customerCount: customerService.count()]
    }

    def show(Long id) {
        respond customerService.get(id)
    }

    def save(Customer customer) {
        if (customer == null) {
            render status: NOT_FOUND
            return
        }

        try {
            customerService.save(customer)
        } catch (ValidationException e) {
            respond customer.errors, view:'create'
            return
        }

        respond customer, [status: CREATED, view:"show"]
    }

    def update(Customer customer) {
        if (customer == null) {
            render status: NOT_FOUND
            return
        }

        try {
            customerService.save(customer)
        } catch (ValidationException e) {
            respond customer.errors, view:'edit'
            return
        }

        respond customer, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        customerService.delete(id)

        render status: NO_CONTENT
    }
}
