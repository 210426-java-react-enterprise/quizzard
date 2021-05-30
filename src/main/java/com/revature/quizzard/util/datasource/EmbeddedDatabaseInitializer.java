package com.revature.quizzard.util.datasource;

import com.revature.quizzard.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Objects;

public class EmbeddedDatabaseInitializer {

    private static Logger logger = Logger.getLogger();

    private EmbeddedDatabaseInitializer() { }

    public static void initializeEmbeddedDatabase(String sqlFileName) {

        logger.info("Initializing embedded H2 database using file: %s", sqlFileName);

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            InputStream fileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(sqlFileName);
            BufferedReader sqlFileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(fileInputStream)));

            boolean statementEnded = false;
            String line;
            StringBuilder statementBuilder = new StringBuilder();
            while ((line = sqlFileReader.readLine()) != null) {

                line = line.trim();

                if (statementEnded) {
                    statementBuilder = new StringBuilder();
                }

                if (!line.isEmpty()) {

                    statementEnded = false;
                    statementBuilder.append(line).append(" ");

                    if (line.contains(";")) {
                        statementEnded = true;
                        logger.info("Executing SQL statement: %s", statementBuilder.toString());
                        conn.createStatement().execute(statementBuilder.toString());
                    }

                }

            }
        } catch (Exception e) {
            logger.warn("%s thrown, check logs for more details", e.getClass().getSimpleName());
            e.printStackTrace();
        }

        logger.info("Initialization of embedded H2 database complete!");

    }

}
