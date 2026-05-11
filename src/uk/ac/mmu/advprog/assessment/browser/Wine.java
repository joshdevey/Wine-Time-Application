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
    public String[] grapes;
    public String[] pairings;
    public int ratings;
    public float ratingAverage;

    public Wine(int id, String name, String type, String winery, String country, String ABV, int ratings, float ratingAverage) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.winery = winery;
        this.country = country;
        this.abv = ABV;
        this.ratings = ratings;
        this.ratingAverage = ratingAverage;
    }

    public Wine(int id, String name, String type, String winery, String country, String abv, String blend, String body, String acidity, String[] grapes, String[] pairings, int ratings, float ratingAverage) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.winery = winery;
        this.country = country;
        this.abv = abv;
        this.blend = blend;
        this.body = body;
        this.acidity = acidity;
        this.grapes = grapes;
        this.pairings = pairings;
        this.ratings = ratings;
        this.ratingAverage = ratingAverage;
    }
}

