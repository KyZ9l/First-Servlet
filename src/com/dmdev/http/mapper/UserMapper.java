package com.dmdev.http.mapper;

import com.dmdev.http.dto.UserDto;
import com.dmdev.http.entyty.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {


    private static final UserMapper INSTANCE = new UserMapper();
    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .image(object.getImage())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .role(object.getRole())
                .gender(object.getGender())
                .build();
    }


    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
