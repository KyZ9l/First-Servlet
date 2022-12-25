package com.dmdev.http.mapper;

import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entyty.Gender;
import com.dmdev.http.entyty.Role;
import com.dmdev.http.entyty.User;
import com.dmdev.http.util.LocalDateFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private static final String IMAGE_FOLDER = "/resources";
    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }



    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .build();
    }
}
