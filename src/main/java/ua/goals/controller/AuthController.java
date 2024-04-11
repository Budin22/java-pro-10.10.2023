package ua.goals.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.goals.model.dto.AuthSuccessDto;
import ua.goals.model.dto.LoginDto;
import ua.goals.model.dto.RegisterDto;
import ua.goals.model.dto.UserDto;
import ua.goals.service.AuthService;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("login")
    public ResponseEntity<AuthSuccessDto> login(@RequestBody LoginDto loginDto) throws Exception {
        AuthSuccessDto authUser = authService.authUser(loginDto);
        return ResponseEntity.ok(authUser);
    }

    @PostMapping("registration")
    public ResponseEntity<UserDto> registry(@RequestBody RegisterDto registerDto) throws Exception {
        UserDto user = authService.registerUser(registerDto);
        return ResponseEntity.ok(user);
    }
}

