package ua.goals.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goals.exception.UserNotAuthException;
import ua.goals.model.dto.AuthSuccessDto;
import ua.goals.model.dto.LoginDto;
import ua.goals.model.dto.RegisterDto;
import ua.goals.model.dto.UserDto;
import ua.goals.model.entity.User;
import ua.goals.model.mapper.UserMapper;
import ua.goals.repo.UserJpaRepo;
import ua.goals.security.model.GoalsUserDetails;
import ua.goals.security.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final UserMapper userMapper;
    private final UserJpaRepo userJpaRepo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDto registerUser(RegisterDto registerDto) throws Exception {
        String pass = encoder.encode(registerDto.getPassword());
        User user = new User();
        user.setName(registerDto.getName());
        user.setPassword(pass);

        if (userJpaRepo.findUserByName(registerDto.getName()) != null)
            throw new IllegalArgumentException("User with name: " + registerDto.getName() + " exist");

        User savedUser = userJpaRepo.save(user);
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public AuthSuccessDto authUser(LoginDto loginDto) throws Exception {

        UserDetails authUser = org.springframework.security.core.userdetails.User
                .withUsername(loginDto.getName())
                .password(loginDto.getPassword())
                .build();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUser, loginDto.getPassword()));
        GoalsUserDetails principal = (GoalsUserDetails) authentication.getPrincipal();
        if (principal != null) {
            String hashPassword = principal.getPassword();
            boolean matched = encoder.matches(loginDto.getPassword(), hashPassword);

            if (matched) {
                String token = jwtUtil.generateToken(principal);
                AuthSuccessDto authSuccessDto = new AuthSuccessDto();
                authSuccessDto.setUser(userMapper.userToUserDto(principal.getGoalsUser()));
                authSuccessDto.setToken(token);

                return authSuccessDto;
            }
        }
        throw new UserNotAuthException("Login or password is incorrect");
    }
}
