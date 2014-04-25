package haka

class Project {

    String name
    String description

    static constraints = {
        name(nullable: false, blank: false, unique: true)
        name(nullable: false, blank: false)
    }

    String toString() {
        return this.name;
    }
}
