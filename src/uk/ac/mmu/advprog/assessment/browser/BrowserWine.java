package uk.ac.mmu.advprog.assessment.browser;

public class BrowserWine extends Wine{

    public int ratings;
    public float ratingAverage;

    public BrowserWine(int id, String name, String type, String winery, String country, String ABV, int ratings, float ratingAverage) {
        super(id, name, type, winery, country, ABV);
    }
}
