package uk.ac.mmu.advprog.assessment.browser;

public class Wine {
    public int id;
    public String name;
    public String type;
    public String winery;
    public String country;
    public String abv;
    public String blend;
    public String body;
    public String acidity;

    public Wine(int id, String name, String type, String winery, String country, String ABV) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.winery = winery;
        this.country = country;
        this.abv = ABV;
    }

    public Wine(int id, String name, String type, String winery, String country, String abv, String blend, String body, String acidity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.winery = winery;
        this.country = country;
        this.abv = abv;
        this.blend = blend;
        this.body = body;
        this.acidity = acidity;
    }
}

