package uk.ac.mmu.advprog.assessment.importer;

public class Rating {
    int wine_id;
    int vintage;
    double rating;

    public Rating(int wine_id, int vintage, Double rating) {
        this.wine_id = wine_id;
        this.vintage = vintage;
        this.rating = rating;
    }

    public int getWine_id() {
        return wine_id;
    }

    public int getVintage() {
        return vintage;
    }

    public Double getRating() {
        return rating;
    }
}
