package org.Testing.practic;

import org.flywaydb.core.Flyway;

import static org.Testing.practic.Database.*;

public class FlywayMigration {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource(URL,USER,PASSWORD).load();
        flyway.migrate();

    }
}
