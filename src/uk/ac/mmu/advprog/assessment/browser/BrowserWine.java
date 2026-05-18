package uk.ac.mmu.advprog.assessment.browser;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BrowserWine extends Wine{

    public int ratings;
    public double ratingAverage;

    public BrowserWine(int id, String name, String type, String winery, String country, String ABV, int ratings, float ratingAverage) {
        super(id, name, type, winery, country, ABV);

        this.ratings = ratings;

        this.ratingAverage = roundAverage(ratingAverage);
    }

    private static double roundAverage(double value) {

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
