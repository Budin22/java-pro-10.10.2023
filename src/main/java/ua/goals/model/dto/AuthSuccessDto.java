package ua.goals.model.dto;

import lombok.Data;

@Data
public class AuthSuccessDto {
    private UserDto user;
    private String token;
}
