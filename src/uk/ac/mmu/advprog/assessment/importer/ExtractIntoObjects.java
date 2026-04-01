package uk.ac.mmu.advprog.assessment.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;

public class ExtractIntoObjects {
	
	private String csvPath;
	private ArrayList<Wine> wines;
	private ArrayList<String> grapes;
	private ArrayList<Pairing> pairing; 
	private ArrayList<Region> regions;
	private ArrayList<Winery> winerys;
	private boolean enhancedLogging;

	public ExtractIntoObjects(String csvPath, boolean enhancedLogging) {		
		super();
		
		this.wines = new ArrayList<>();
		this.grapes = new ArrayList<>();
		this.pairing = new ArrayList<>();
		this.regions = new ArrayList<>();
		this.winerys = new ArrayList<>();
		this.csvPath = csvPath;
		this.enhancedLogging = enhancedLogging;
	}
		
	public void extractFromCSSV() {
		int counter = 0; 
		
		ArrayList<Wine> wines = new ArrayList<>();
		ArrayList<String> uniqueGrapes = new ArrayList<>();
		ArrayList<Pairing> uniquePairing = new ArrayList<>();
		ArrayList<String> pairingAsString = new ArrayList<>();
		ArrayList<String> regionId = new ArrayList<>();
		ArrayList<Region> uniqueRegions = new ArrayList<>();
		ArrayList<Winery> uniqueWinerys = new ArrayList<>();
		ArrayList<Integer> uniqueWineryIds = new ArrayList<>();
		
		if(enhancedLogging) {
			System.out.println(Instant.now() + " - extract from CSV start");
		}
		
		try {
			Path readFile = Paths.get(this.csvPath);
	 
			InputStream is = Files.newInputStream(readFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String currentLine = "";
			
			while((currentLine = br.readLine()) != null) {

				if(counter != 0) {
					String[] splitString = splitString(currentLine);
					
					Wine wine = new Wine(splitString[0], splitString[1], splitString[2], splitString[3], splitString[6], splitString[7], splitString[8]);

					ArrayList<String> wineGrapes = new ArrayList<>();
					//grapes
					String grapesString = cleanUpArrayString(splitString[4]);
					String[] grapes = splitString(grapesString);
					
					for(String grape : grapes) {
						if(!wineGrapes.contains(grape.trim())) {
							wineGrapes.add(grape.trim());
						}
						if(!uniqueGrapes.contains(grape.trim())) {
							uniqueGrapes.add(grape.trim());
						}
					}
					
					wine.setGrapes(wineGrapes);
					
					//pairing
					String paringString = cleanUpArrayString(splitString[5]);
					String[] pairings = splitString(paringString);

					ArrayList<Pairing> winePairings = new ArrayList<>();

					for(String pairing : pairings) {
						
						Pairing pairingToAdd = new Pairing(pairing.trim());
						
						if(!pairingAsString.contains(pairingToAdd.getName())) {
							pairingAsString.add(pairingToAdd.getName());
							uniquePairing.add(pairingToAdd);
						}
						
						winePairings.add(pairingToAdd);
					}
					
					wine.setPairings(winePairings);
					
					Region region = new Region(Integer.parseInt(splitString[11]), splitString[12], splitString[10]);
					
					//region
					if(!regionId.contains(splitString[11])) {
						uniqueRegions.add(region);
						regionId.add(splitString[11]);
					}
					this.regions = uniqueRegions;

					//vintages
					String vintagesString = cleanUpArrayString(splitString[16]);
					
					String[] vintages = splitString(vintagesString);
					
					ArrayList<Integer> wineVintages = new ArrayList<>();
					
					for(String vintage : vintages) {
						try {				
							
							wineVintages.add(Integer.parseInt(vintage.trim()));
							
						} catch (NumberFormatException e) {
							wineVintages.add(-1);
						}
					}
					
					wine.setVintages(wineVintages);
					
					Winery winery = new Winery(Integer.parseInt(splitString[13]), splitString[14], splitString[15], region);
							
					if(!uniqueWineryIds.contains(winery.getId())) {
						uniqueWineryIds.add(winery.getId());
						uniqueWinerys.add(winery);
					}
					this.winerys = uniqueWinerys;
					
					wine.setWinery(winery);
					

					wines.add(wine);
					
				}
				counter++;
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.wines = wines;
		this.grapes = uniqueGrapes;
		this.pairing = uniquePairing;
		if(enhancedLogging) {
			System.out.println(Instant.now() + " - extract from CSV end. Wines extracted: " + this.wines.size());
		}
	}

	public ArrayList<Wine> getWines() {
		return wines;
	}

	public ArrayList<String> getGrapes() {
		return grapes;
	}

	public ArrayList<Pairing> getPairing() {
		return pairing;
	}
	
	public ArrayList<Region> getRegions() {
		return regions;
	}
	
	public ArrayList<Winery> getWinerys() {
		return winerys;
	}

	/**
	 * 
	 * @param dirtyString - String entry for array that could contain
	 * 						undesirable characters
	 * 
	 * @return cleanString - dirtyString minus the undesirables 
	 */
	public static String cleanUpArrayString(String dirtyString) {
		
		String cleanString = dirtyString;
		
		cleanString = dirtyString.replaceAll("\"", "");
		cleanString = cleanString.replace("[", "");
		cleanString = cleanString.replace("]", "");
		cleanString = cleanString.replace("'", "");
		
		return cleanString;
	}
	
	/**
	 * 
	 * @param arrayString - line entry from csv as a string
	 * 
	 * @return returnArray - Array of string
	 */
	public static String[] splitString(String arrayString) {
		
		String[] returnArray = {""};
		
		try {
			returnArray = arrayString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnArray;
	}
	
}
