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

        boolean enhancedLogging = true;

        System.out.println(Instant.now() + " Import Start");
        Queries queries = new Queries("jdbc:sqlite:data/winetime.db", enhancedLogging);

        ExtractIntoObjects objects = new ExtractIntoObjects("./data/XWines_Full_100K_wines.csv", "./data/XWines_Full_21M_ratings.csv", enhancedLogging);

        objects.extractFromCSSV();

        if (enhancedLogging) {
            System.out.println(Instant.now() + " Data extracted - Start DB");
        }

        queries.populateDB(objects);

        objects.extractRatings();

        System.out.println(Instant.now() + " Ratings into Objects");

        try {
            queries.insertRatings(objects.getRatings());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(Instant.now() + " Import Finish");
    }

}
