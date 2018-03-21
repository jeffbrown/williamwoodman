package williamwoodman

class Customer {
    String name
    static hasMany = [sites: Site]
}
