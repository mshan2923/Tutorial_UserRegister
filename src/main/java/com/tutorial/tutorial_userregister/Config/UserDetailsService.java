package com.tutorial.tutorial_userregister.Config;

import com.tutorial.tutorial_userregister.Entity.User;
import com.tutorial.tutorial_userregister.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService  implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserid(username)
                .orElseThrow(() ->
                {return new UsernameNotFoundException("해당 유저를 찾을수 없습니다.");});
        System.out.println("userDetails");
        return new UserDetail(user);
    }//작동 안함
}
