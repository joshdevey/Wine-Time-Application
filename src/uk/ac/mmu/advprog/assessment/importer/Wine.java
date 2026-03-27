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
	private ArrayList<Grape> grapes;
	private ArrayList<Pairing> pairings;
	private Region region;
	private Winery winery;
	private ArrayList<Integer> Vintages;
	
	public Wine(String id, String name, String type, String elaborate, String abv, String body, String acidity) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.elaborate = elaborate;
		this.abv = abv;
		this.body = body;
		this.acidity = acidity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getElaborate() {
		return elaborate;
	}

	public void setElaborate(String elaborate) {
		this.elaborate = elaborate;
	}

	public String getAbv() {
		return abv;
	}

	public void setAbv(String abv) {
		this.abv = abv;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAcidity() {
		return acidity;
	}

	public void setAcidity(String acidity) {
		this.acidity = acidity;
	}

	public ArrayList<Grape> getGrapes() {
		return grapes;
	}

	public void setGrapes(ArrayList<Grape> grapes) {
		this.grapes = grapes;
	}

	public ArrayList<Pairing> getPairings() {
		return pairings;
	}

	public void setPairings(ArrayList<Pairing> pairings) {
		this.pairings = pairings;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Winery getWinery() {
		return winery;
	}

	public void setWinery(Winery winery) {
		this.winery = winery;
	}


	public ArrayList<Integer> getVintages() {
		return Vintages;
	}

	public void setVintages(ArrayList<Integer> vintages) {
		Vintages = vintages;
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

}
