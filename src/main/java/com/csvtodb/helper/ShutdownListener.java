package com.csvtodb.helper;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShutdownListener implements ApplicationListener<ContextClosedEvent> {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ClassPathResourceReader reader;
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        val deleteMaleTable = this.reader.read("/sql/deleteMaleTable.sql");
        val deleteFemaleTable = this.reader.read("/sql/deleteFemaleTable.sql");
        this.jdbcTemplate.execute(deleteMaleTable);
        this.jdbcTemplate.execute(deleteFemaleTable);

    }
}
