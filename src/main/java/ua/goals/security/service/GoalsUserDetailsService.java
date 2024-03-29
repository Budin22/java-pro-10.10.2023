package ua.goals.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.goals.model.entity.User;
import ua.goals.repo.UserJpaRepo;
import ua.goals.security.model.GoalsUserDetails;

@RequiredArgsConstructor
public class GoalsUserDetailsService implements UserDetailsService {
    private final UserJpaRepo userJpaRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userJpaRepo.findUserByName(username);
        if (user == null) throw new UsernameNotFoundException("Don't have user with name " + username);
        return new GoalsUserDetails(user);
    }
}
