package uk.ac.mmu.advprog.assessment.browser;

public class QueryBuilder {
    private String name;
    private String wineryName;
    private String type;
    private String country;
    private String blend;
    private String grape;
    private int abv;
    private String acidity;
    private String body;
    private String sortColumn;
    private Boolean ascending;
    private float minAverageRating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWineryName() {
        return wineryName;
    }

    public void setWineryName(String wineryName) {
        this.wineryName = wineryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBlend() {
        return blend;
    }

    public void setBlend(String blend) {
        this.blend = blend;
    }

    public String getGrape() {
        return grape;
    }

    public void setGrape(String grape) {
        this.grape = grape;
    }

    public int getAbv() {
        return abv;
    }

    public void setAbv(int abv) {
        this.abv = abv;
    }

    public String getAcidity() {
        return acidity;
    }

    public void setAcidity(String acidity) {
        this.acidity = acidity;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        if(this.sortColumn != null && this.sortColumn.equals(sortColumn)) {
            this.ascending = !this.ascending;
        } else {
            this.ascending = true;
        }
        this.sortColumn = sortColumn;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }

    public String getNameQueryString() {
        if (this.name.isEmpty()) {
            return null;
        } else {
            return "w.name = \"" + this.name + "\"";
        }
    }

    public String getWineryNameQueryString() {
        if (this.wineryName.isEmpty()) {
            return null;
        } else {
            return "wy.name = \"" + this.wineryName + "\"";
        }
    }

    public String getTypeQueryString() {
        if (this.type.equals("Any")) {
            return null;
        } else {
            return "w.type = \"" + this.type + "\"";
        }
    }

    public String getCountryQueryString() {
        if (this.country.equals("Any")) {
            return null;
        } else {
            return "r.country = \"" + this.country + "\"";
        }
    }

    public String getBlendQueryString() {
        if (this.blend.equals("Any")) {
            return null;
        } else {
            return "w.blend_type = \"" + this.blend + "\"";
        }
    }

    public String getGrapeQueryString() {
        if (this.grape.isEmpty()) {
            return null;
        } else {
            return "g.name = \"" + this.grape + "\"";
        }
    }

    public String getAcidityQueryString() {
        if (this.acidity.equals("Any")) {
            return null;
        } else {
            return "w.acidity = \"" + this.acidity + "\"";
        }
    }

    public String getBodyQueryString() {
        if (this.body.equals("Any")) {
            return null;
        } else {
            return "w.body = \"" + this.body + "\"";
        }
    }

    public float getMinAverageRating() {
        return minAverageRating;
    }

    public void setMinAverageRating(float minAverageRating) {
        this.minAverageRating = minAverageRating;
    }

    public String getMinAverageRatingQueryString() {
        if(this.minAverageRating == 0) {
            return null;
        } else {
            return "rt.avg_ratings > " + this.minAverageRating;
        }
    }

    @Override
    public String toString() {
        return "QueryBuilder{" +
                "name='" + name + '\'' +
                ", wineryName='" + wineryName + '\'' +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", blend='" + blend + '\'' +
                ", grape='" + grape + '\'' +
                ", abv=" + abv +
                ", acidity='" + acidity + '\'' +
                ", body='" + body + '\'' +
                ", sort column='" + sortColumn + '\'' +
                ", ascending='" + ascending + '\'' +
                '}';
    }
}
