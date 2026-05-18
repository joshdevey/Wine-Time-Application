package uk.ac.mmu.advprog.assessment.importer;

import uk.ac.mmu.advprog.assessment.shared.Queries;

import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        String databaseConnectionString = "jdbc:sqlite:data/winetime.db";
        boolean enhancedLogging = true;
        boolean includeRatings = true;

        System.out.println(Instant.now() + " Import Start");
        Queries queries = new Queries(databaseConnectionString, enhancedLogging);

        ExtractWines objects = new ExtractWines("./data/XWines_Full_100K_wines.csv", enhancedLogging);

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
