package uk.ac.mmu.advprog.assessment.importer;

import uk.ac.mmu.advprog.assessment.shared.Queries;

import java.time.Instant;

public class Main {

    public static void main(String[] args) {

        boolean enhancedLogging = false;

        System.out.println(Instant.now() + " Import Start");
        Queries queries = new Queries("jdbc:sqlite:data/winetime.db", enhancedLogging);

        ExtractIntoObjects objects = new ExtractIntoObjects("./data/XWines_Full_100K_wines.csv", enhancedLogging);

        objects.extractFromCSSV();

        if (enhancedLogging) {
            System.out.println(Instant.now() + " Data extracted - Start DB");
        }

        queries.populateDB(objects);

        System.out.println(Instant.now() + " Import Finish");

    }


}
