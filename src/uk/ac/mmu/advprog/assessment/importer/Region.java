package uk.ac.mmu.advprog.assessment.importer;

public class Region {

    private int id;
    private String name;
    private String country;

    public Region(int id, String name,
                  String country) {
        super();
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
