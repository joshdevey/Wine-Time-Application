package uk.ac.mmu.advprog.assessment.browser;

import java.util.List;

public class SelectedWine extends Wine{

    public String website;
    public String region;
    public List<Rating> ratings;
    public List<Integer> vintages;

    public SelectedWine(int id, String name, String type, String winery, String country, String abv, String blend, String body, String acidity, String[] grapes, String website, String region, String[] pairings) {
        super(id, name, type, winery, country, abv, blend, body, acidity, grapes, pairings);
        this.website = website;
        this.region = region;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setVintages(List<Integer> vintages) {
        this.vintages = vintages;
    }
}
