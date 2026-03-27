package uk.ac.mmu.advprog.assessment.importer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;

public class Queries {

	public static void insertPairingIntoDB(ArrayList<Pairing> pairings) {
		System.out.println(Instant.now() + " - start insert pairings");
		try (Connection c = DriverManager.getConnection("jdbc:sqlite:data/winetime.db")) {
			for (Pairing pairing: pairings) {

				c.createStatement()
					.executeUpdate("INSERT INTO Pairing (food) VALUES ('"+pairing.getName()+"')");
			
			}
			 
		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println(Instant.now() + " - finish insert pairings");
	}

	public static void insertGrapeIntoDB(ArrayList<Grape> grapes) {
		System.out.println(Instant.now() + " - start insert grapes");
		try (Connection c = DriverManager.getConnection("jdbc:sqlite:data/winetime.db")) {
			for (Grape grape : grapes) {
				String sql = "INSERT INTO Grape (name) VALUES (?)";
				
				try (PreparedStatement stmt = c.prepareStatement(sql)) {
				    stmt.setString(1, grape.getName());
				    stmt.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println(Instant.now() + " - finished insert grapes");
	}

	public static void insertRegionIntoDB(ArrayList<Region> regions) {
		System.out.println(Instant.now() + " - start insert regions");
		try (Connection c = DriverManager.getConnection("jdbc:sqlite:data/winetime.db")) {
			for (Region region : regions) {
	
				String sql = "INSERT INTO Region (id, name, country) VALUES (?, ?, ?)";
				
				try (PreparedStatement stmt = c.prepareStatement(sql)) {
				    stmt.setInt(1, region.getId());
				    stmt.setString(2, region.getName());
				    stmt.setString(3, region.getCountry());
				    stmt.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println(Instant.now() + " - finish insert regions");
	}
	
	public static void insertWineryintoDB(ArrayList<Winery> wineries) {
		System.out.println(Instant.now() + " - start insert winery");
		int counter = 0;
		int alertCounter = 0;
		
		try (Connection c = DriverManager.getConnection("jdbc:sqlite:data/winetime.db")) {
			c.setAutoCommit(false);
			for (Winery winery : wineries) {
	
				counter += 1;
				alertCounter += 1;
				
				if(alertCounter == 10000 ) {
					System.out.println(Instant.now() + " - " + counter + " records inserted");
					alertCounter = 0;
				}
				
				String sql = "INSERT INTO Winery (id, name, region_id, website) VALUES (?, ?, ?, ?)";
				
				try (PreparedStatement stmt = c.prepareStatement(sql)) {
				    stmt.setInt(1, winery.getId());
				    stmt.setString(2, winery.getName());
				    stmt.setInt(3, winery.getRegion().getId());
				    stmt.setString(4, winery.getWebsite());
				    stmt.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			c.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		System.out.println(Instant.now() + " - finish insert winery");
	}

	public static void insertWineIntoDB(ArrayList<Wine> wines) {
		System.out.println(Instant.now() + " - start insert wines");
		int counter = 0;
		int alertCounter = 0;
		
		try (Connection c = DriverManager.getConnection("jdbc:sqlite:data/winetime.db")) {
		 c.setAutoCommit(false);

		for (Wine wine : wines) {
			
			counter += 1;
			alertCounter += 1;
			
			if(alertCounter == 10000 ) {
				System.out.println(Instant.now() + " - " + counter + " records out of " + wines.size() + " inserted");
				alertCounter = 0;
			}

				String sql = "INSERT INTO Wine (id, name, type, blend_type, ABV, acidity, body, winery_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				
				try (PreparedStatement stmt = c.prepareStatement(sql)) {
				    stmt.setString(1, wine.getId());
				    stmt.setString(2, wine.getName());
				    stmt.setString(3, wine.getType());
				    stmt.setString(4, wine.getElaborate());
				    stmt.setString(5, wine.getAbv());
				    stmt.setString(6, wine.getAcidity());
				    stmt.setString(7, wine.getBody());
				    stmt.setInt(8, wine.getWinery().getId());
				    stmt.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				for(Grape grape : wine.getGrapes()) {
					String sql2 = "SELECT id FROM Grape where Name = '" + grape.getName() + "'";
					
					ResultSet rs = c.createStatement().executeQuery(sql2);
					
					int grapeId = 0;
					
					while(rs.next()) {
						grapeId = rs.getInt("id");	
					}
					
					if(grapeId != 0) {
						String sql3 = "INSERT INTO Wine_Grape (Wine_id, Grape_id) values (?, ?)";
						
						try (PreparedStatement stmt = c.prepareStatement(sql3)) {
						    stmt.setString(1, wine.getId());
						    stmt.setInt(2, grapeId);
						    stmt.executeUpdate();
						} catch(Exception e) {
							e.printStackTrace();
						}
						
					}
					
				}

				for(Integer vintage : wine.getVintages()) {
					String sql4 = "INSERT INTO Wine_Vintage (Wine_id, Year) values (?, ?)";
					
					try (PreparedStatement stmt = c.prepareStatement(sql4)) {
					    stmt.setString(1, wine.getId());
					    stmt.setInt(2, vintage);
					    stmt.executeUpdate();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			c.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		System.out.println(Instant.now() + " - finish insert wines");
	}
	
	public static void createDB() {
		
		System.out.println(Instant.now() + " - Clean up");
		Path fileToDelete = Paths.get("./data/winetime.db");
		try {
			System.out.println(Instant.now() + " - Delete db");
			Files.delete(fileToDelete);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(Instant.now() + " - File does not exists, continue");
		}
		
		try (Connection c = DriverManager.getConnection("jdbc:sqlite:data/winetime.db")) {
		
			String createGrape = "CREATE TABLE 'Grape' ('id'	INTEGER, 'name'	TEXT, PRIMARY KEY('id' AUTOINCREMENT))";

			try (PreparedStatement stmt = c.prepareStatement(createGrape)) {
			    stmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(Instant.now() + " - Grape created");
			
			String createPairing = "CREATE TABLE 'Pairing' ('id'	INTEGER, 'food'	INTEGER,PRIMARY KEY('id' AUTOINCREMENT))";

			try (PreparedStatement stmt = c.prepareStatement(createPairing)) {
			    stmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(Instant.now() + " - Pairing created");

			String createRegion = "CREATE TABLE 'Region' ( 'id'	INTEGER, 'name'	TEXT, 'country'	TEXT, PRIMARY KEY('id' AUTOINCREMENT))";

			try (PreparedStatement stmt = c.prepareStatement(createRegion)) {
			    stmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			}

			System.out.println(Instant.now() + " - Region created");
			
			String createWine = "CREATE TABLE 'Wine' ('id'	INTEGER, 'name'	TEXT, 'type'	TEXT, 'blend_type'	TEXT, 'ABV'	REAL, 'acidity'	TEXT, 'body'	TEXT, 'winery_id'	INTEGER, PRIMARY KEY('id'), FOREIGN KEY('winery_id') REFERENCES '')";

			try (PreparedStatement stmt = c.prepareStatement(createWine)) {
			    stmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			}

			System.out.println(Instant.now() + " - Wine created");

			String createWineGrape = "CREATE TABLE 'Wine_Grape' ( 'wine_id'	INTEGER, 'Grape_id' 	INTEGER, 'id'	INTEGER, PRIMARY KEY('id' AUTOINCREMENT), FOREIGN KEY('wine_id') REFERENCES 'Wine'('id'))";

			try (PreparedStatement stmt = c.prepareStatement(createWineGrape)) {
			    stmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			}

			System.out.println(Instant.now() + " - Wine_Grape created");

			String createWineVintage = "CREATE TABLE 'Wine_Vintage' ( 'wine_id'	INTEGER, 'year'	INTEGER, 'id'	INTEGER, PRIMARY KEY('id' AUTOINCREMENT), FOREIGN KEY('wine_id') REFERENCES 'Winery'('id'))";

			try (PreparedStatement stmt = c.prepareStatement(createWineVintage)) {
			    stmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			}

			System.out.println(Instant.now() + " - Wine_Vintage created");

			String createWinery = "CREATE TABLE 'Winery' ( 'id'	INTEGER, 'name'	TEXT, 'region_id'	INTEGER, 'website'	TEXT, PRIMARY KEY('id'), FOREIGN KEY('region_id') REFERENCES '')";

			try (PreparedStatement stmt = c.prepareStatement(createWinery)) {
			    stmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			}

			System.out.println(Instant.now() + " - Winery created");
			System.out.println(Instant.now() + " - Tables created");
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
	}
	
}
