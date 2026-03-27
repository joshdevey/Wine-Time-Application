package uk.ac.mmu.advprog.assessment.importer;

import java.time.Instant;

public class Main {

	public static void main(String[] args) {
		Instant start = Instant.now();
		System.out.println(start + " Start");
		Queries.createDB();
		ExtractIntoObjects objects = new ExtractIntoObjects();
		objects.extractFromCSSV();
		System.out.println(Instant.now() + " Import Start");
		Queries.insertGrapeIntoDB(objects.getGrapes());
		Queries.insertPairingIntoDB(objects.getPairing());
		Queries.insertRegionIntoDB(objects.getRegions());
		Queries.insertWineryintoDB(objects.getWinerys());
		Queries.insertWineIntoDB(objects.getWines());
		Instant end = Instant.now();
		System.out.println(end + " Import Finish");
		
	}
	
	
	
}
