package uk.ac.mmu.advprog.assessment.importer;

import java.util.ArrayList;

public class Wine {

    private String id;
    private String name;
    private String type;
    private String elaborate; //blend_type
    private String abv;
    private String body;
    private String acidity;
    private ArrayList<String> grapes;
    private ArrayList<Pairing> pairings;
    private Winery winery;
    private ArrayList<Integer> vintages;
    private Region region;

    public Wine(String id, String name, String type, String elaborate, String abv, String body, String acidity, ArrayList<String> grapes, ArrayList<Pairing> pairings, Winery winery, ArrayList<Integer> vintages, Region region) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.elaborate = elaborate;
        this.abv = abv;
        this.body = body;
        this.acidity = acidity;
        this.grapes = grapes;
        this.pairings = pairings;
        this.winery = winery;
        this.vintages = vintages;
        this.region = region;
    }

    public Wine(String id, String name, String type, String elaborate, String abv, String body, String acidity) {
        super();
        this.id = id;
        this.name = cleanWineName(name);
        this.type = type;
        this.elaborate = elaborate;
        this.abv = abv;
        this.body = body;
        this.acidity = acidity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getElaborate() {
        return elaborate;
    }

    public String getAbv() {
        return abv;
    }

    public String getBody() {
        return body;
    }

    public String getAcidity() {
        return acidity;
    }

    public ArrayList<String> getGrapes() {
        return grapes;
    }

    public void setGrapes(ArrayList<String> grapes) {
        this.grapes = grapes;
    }

    public ArrayList<Pairing> getPairings() {
        return pairings;
    }

    public void setPairings(ArrayList<Pairing> pairings) {
        this.pairings = pairings;
    }

    public Winery getWinery() {
        return winery;
    }

    public void setWinery(Winery winery) {
        this.winery = winery;
    }

    public ArrayList<Integer> getVintages() {
        return vintages;
    }

    public void setVintages(ArrayList<Integer> vintages) {
        this.vintages = vintages;
    }

    public Region getRegion() {
        return this.region;
    }

    @Override
    public String toString() {

        return "Wine {" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", blend='" + elaborate + '\'' +
                ", abv='" + abv + '\'' +
                ", body='" + body + '\'' +
                ", acidity='" + acidity + '\'' +
                '}';

    }

    /**
     * Private function to clean the name of the wine
     *
     * @param wineName
     * @return String clean wineName
     */
    private String cleanWineName(String wineName) {

        String cleanWineName = wineName;

        if(cleanWineName.charAt(cleanWineName.length() - 1) == '"') {
            cleanWineName = cleanWineName.substring(0, cleanWineName.length() - 1);
        }

        if(cleanWineName.charAt(0) == '"') {
            cleanWineName = cleanWineName.substring(1, cleanWineName.length() - 1);
        }

        return cleanWineName;
    }

}
