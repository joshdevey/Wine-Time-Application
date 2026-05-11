package uk.ac.mmu.advprog.assessment.browser;

import java.util.List;

public class SelectedWine extends Wine{

    public List<Rating> ratings;

    public SelectedWine(int id, String name, String type, String winery, String country, String abv, String blend, String body, String acidity, String[] grapes, String[] pairings) {
        super(id, name, type, winery, country, abv, blend, body, acidity, grapes, pairings);
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
