package com.csvtodb.utils;

import com.csvtodb.model.User;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class UserItemReader {

    public ItemReader<User> reader(FlatFileItemReader<User> reader, ClassPathResource resource){
//        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(resource);
        reader.setLineMapper(getLineMapper());
        reader.setLinesToSkip(1);
        return reader;
    }

    public LineMapper<User> getLineMapper() {
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"Id", "First Name", "Last Name", "Gender", "Email","Phone"});
        tokenizer.setIncludedFields(new int[] {1, 2, 3, 4, 5, 6});
        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setDistanceLimit(2);
        fieldSetMapper.setTargetType(User.class);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }


}
