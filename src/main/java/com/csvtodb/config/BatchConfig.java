package com.csvtodb.config;

import com.csvtodb.helper.ClassPathResourceReader;
import com.csvtodb.model.User;
import com.csvtodb.utils.UserItemProcessor;
import com.csvtodb.utils.UserItemReader;
import com.csvtodb.utils.UserItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    ClassPathResourceReader resourceReader;
    @Autowired
    UserItemReader reader;
    @Autowired
    UserItemWriter writer;

    @Bean
    public Job importUserJob(){
        return jobBuilderFactory.get("USER-IMPORT-JOB")
                .incrementer(new RunIdIncrementer())
                .start(saveFemaleUser())
                .next(saveMaleUser())
                .build();
    }

    @Bean
    public Step saveFemaleUser() {

        return stepBuilderFactory.get("SAVE_FEMALE_USER")
                .<User, User>chunk(10)
                .reader(reader.reader(new FlatFileItemReader<>(), new ClassPathResource("UserDetails.csv")))
                .processor(new UserItemProcessor("Female"))
                .writer(writer.write(resourceReader.read("/sql/insertFemaleUser.sql")))
                .build();
    }

    @Bean
    public Step saveMaleUser() {
        return stepBuilderFactory.get("SAVE_MALE_USER")
                .<User, User>chunk(10)
                .reader(reader.reader(new FlatFileItemReader<>(), new ClassPathResource("UserDetails.csv")))
                .processor(new UserItemProcessor("Male"))
                .writer(writer.write(resourceReader.read("/sql/insertMaleUser.sql")))
                .build();
    }

}
