package com.csvtodb.utils;

import com.csvtodb.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class UserItemProcessor implements ItemProcessor<User, User> {

    @NonNull
    private String gender;

    @Override
    public User process(@NonNull User user) throws Exception {
        if (user.getGender().equals(gender)){
            return user;
        }else {
            return null;
        }
    }
}