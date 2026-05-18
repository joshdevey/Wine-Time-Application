package uk.ac.mmu.advprog.assessment.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;

import static uk.ac.mmu.advprog.assessment.importer.ExtractWines.splitString;

public class ExtractRatings {
    private final String csvPath;
    private final String connectionString;

    public ExtractRatings(String csvPath, String connectionString) {
        this.csvPath = csvPath;
        this.connectionString = connectionString;
    }

    public void extractRatings(boolean enhancedLogging) {

        try {
            if (this.csvPath.isEmpty()) {
                throw new EmptyCSVPath("Empty CSV Path");
            }

            Connection c = null;
            int counter = 0;
            int alertCounter = 0;

            if (enhancedLogging) {
                System.out.println(Instant.now() + " Import ratings - Start");
            }
            if (csvPath == null || csvPath.isEmpty()) {
                System.out.println("Invalid import path");
                return;
            }

            Path readFile = Paths.get(csvPath);

            InputStream is = Files.newInputStream(readFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String currentLine = "";

            try {
                c = DriverManager.getConnection(connectionString);
                c.setAutoCommit(false);

                while ((currentLine = br.readLine()) != null) {
                    if (counter != 0) {
                        String[] splitString = splitString(currentLine);
                        double rating_double;
                        int rating_vintage;

                        try {
                            rating_double = Double.parseDouble(splitString[4]);
                        } catch (NumberFormatException e) {
                            rating_double = 0.0;
                        }

                        try {
                            rating_vintage = Integer.parseInt(splitString[3]);
                        } catch (NumberFormatException e) {
                            rating_vintage = -1;
                        }

                        if (alertCounter == 100000) {
                            System.out.println(Instant.now() + " - " + counter + " inserted");
                            alertCounter = 0;
                        }

                        String sql = "INSERT INTO Rating (wine_id, vintage, rating) VALUES (?, ?, ?)";

                        try (PreparedStatement stmt = c.prepareStatement(sql)) {
                            stmt.setInt(1, Integer.parseInt(splitString[2]));
                            stmt.setInt(2, rating_vintage);
                            stmt.setDouble(3, rating_double);
                            stmt.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }

                    alertCounter++;
                    counter++;
                }

                String sql = "CREATE INDEX idx_wine_rating ON Rating(wine_id, rating);";

                try (PreparedStatement stmt = c.prepareStatement(sql)) {
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                c.commit();
            } catch (SQLException se) {
                if (c != null) {
                    c.rollback();
                }
                se.printStackTrace();
            } finally {
                if (c != null) {
                    c.close();
                }
            }

            br.close();
        } catch (IOException | SQLException | EmptyCSVPath e) {
            e.printStackTrace();
        }
    }
}
