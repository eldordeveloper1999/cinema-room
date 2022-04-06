package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room.model.ReservedHall;
import uz.pdp.cinema_room.model.Role;
import uz.pdp.cinema_room.model.User;
import uz.pdp.cinema_room.repository.UserRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

//    @Override
//    public User save(UserRegistrationDto userRegistrationDto) {
//        User user = new User(
//                userRegistrationDto.getFirstName(),
//                userRegistrationDto.getLastName(),
//                userRegistrationDto.getEmail(),
//                new BCryptPasswordEncoder().encode(userRegistrationDto.getPassword()),
//                Arrays.asList(new Role("ROLE_USER"))
//        );
//
//        return userRepository.save(user);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()
        -> new IllegalStateException("User not found"));
    }


    public User getUserById(UUID user_id) {
        return userRepository.getById(user_id);
    }
}
