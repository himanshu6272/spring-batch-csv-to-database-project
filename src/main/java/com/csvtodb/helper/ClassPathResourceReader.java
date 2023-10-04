package com.csvtodb.helper;

import lombok.val;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Component
public class ClassPathResourceReader {
    public String read(String resourcePath){
        try {
            val resource = new ClassPathResource(resourcePath);
            return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
