package com.csvtodb.utils;

import com.csvtodb.helper.ClassPathResourceReader;
import com.csvtodb.model.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserItemWriter {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserItemPreparedStatementSetter statementSetter;
    public ItemWriter<User> write(String sql){
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(this.dataSource);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(sql);
        writer.setItemPreparedStatementSetter(statementSetter);
        return writer;
    }
}
