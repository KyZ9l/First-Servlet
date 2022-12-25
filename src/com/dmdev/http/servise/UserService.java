package com.dmdev.http.servise;

import com.dmdev.http.dao.UserDao;
import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.dto.UserDto;
import com.dmdev.http.entyty.User;
import com.dmdev.http.exaption.ValidationException;
import com.dmdev.http.mapper.CreateUserMapper;
import com.dmdev.http.mapper.UserMapper;
import com.dmdev.http.validator.CreateUserValidator;
import com.dmdev.http.validator.ValidationResult;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.Optional;

public class UserService {
    private UserService() {
    }


    private static final UserService INSTANCE = new UserService();


    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    private final ImageService imageService = ImageService.getInstance();

    public Optional<UserDto> login(String email, String password)
    {
        return userDao.findByEmailAndPassword(email,password)
                .map(userMapper::mapFrom);
    }


    @SneakyThrows
    public Integer create(CreateUserDto userDto)
    {
        //1 step validation
        //2 step map преобразовать ДТО в Сущность
        //3 step использовать userDao.save для сохранить преобразованую сущность
        //4 step return id вернуть id либо сому сущность (смотря что мы возвращаем)
        var validationResult = createUserValidator.isValid(userDto);
            if(!validationResult.isValid())
            {
                throw new ValidationException(validationResult.getErrors());
            }
        var userEntity = createUserMapper.mapFrom(userDto);
            imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
            userDao.save(userEntity);
            return userEntity.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
