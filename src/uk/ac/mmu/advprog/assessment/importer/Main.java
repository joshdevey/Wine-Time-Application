package uk.ac.mmu.advprog.assessment.importer;

import uk.ac.mmu.advprog.assessment.shared.Queries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.Instant;

import static uk.ac.mmu.advprog.assessment.importer.ExtractIntoObjects.splitString;

public class Main {

    public static void main(String[] args) {
        String databaseConnectionString = "jdbc:sqlite:data/winetime.db";
        boolean enhancedLogging = true;
        boolean includeRatings = true;

        System.out.println(Instant.now() + " Import Start");
        Queries queries = new Queries(databaseConnectionString, enhancedLogging);

        ExtractIntoObjects objects = new ExtractIntoObjects("./data/XWines_Full_100K_wines.csv", enhancedLogging);

        objects.extractFromCSSV();

        if (enhancedLogging) {
            System.out.println(Instant.now() + " Data extracted - Start DB");
        }

        queries.populateDB(objects);

        if (includeRatings) {
            if (enhancedLogging) {
                System.out.println(Instant.now() + " Ratings");
            }

            RatingsExtraction ratingsExtraction = new RatingsExtraction("./data/XWines_Full_21M_ratings.csv", databaseConnectionString);

            ratingsExtraction.extractRatings(enhancedLogging);
        }

        System.out.println(Instant.now() + " Import Finish");
    }

}
