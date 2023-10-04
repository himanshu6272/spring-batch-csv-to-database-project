package com.csvtodb.helper;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements ApplicationRunner {
    @Autowired
    ClassPathResourceReader reader;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        val deleteDb = reader.read("/sql/deletedb.sql");
//        val createDb = reader.read("/sql/createdb.sql");
        val createMaleTable = reader.read("/sql/createMaleTable.sql");
        val createFemaleTable = reader.read("/sql/createFemaleTable.sql");
//        jdbcTemplate.execute(deleteDb);
//        jdbcTemplate.execute(createDb);
        jdbcTemplate.execute(createMaleTable);
        jdbcTemplate.execute(createFemaleTable);
    }
}
