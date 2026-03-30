package uk.ac.mmu.advprog.assessment.importer;

public class Winery {

    private int id;
    private String name;
    private String website;
    private Region region;

    public Winery(int id, String name, String website, Region region) {
        super();
        this.id = id;
        this.name = name;
        this.website = website;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public Region getRegion() {
        return region;
    }
}
