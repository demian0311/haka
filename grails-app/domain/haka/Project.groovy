package haka

class Project implements Serializable{
    static final long serialVersionUID = 42L;

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
