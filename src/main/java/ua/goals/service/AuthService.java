package ua.goals.service;

import ua.goals.model.dto.AuthSuccessDto;
import ua.goals.model.dto.LoginDto;
import ua.goals.model.dto.RegisterDto;
import ua.goals.model.dto.UserDto;

public interface AuthService {
    UserDto registerUser(RegisterDto registerDto) throws Exception;

    AuthSuccessDto authUser(LoginDto loginDto) throws Exception;
}
