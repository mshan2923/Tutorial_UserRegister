package com.tutorial.tutorial_userregister.Config;

import com.tutorial.tutorial_userregister.Entity.User;
import com.tutorial.tutorial_userregister.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsService  implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User member = userRepository.findByUserid(username)
        //        .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을수 없습니다."));
        Optional<User> findOne = userRepository.findByUserid(username);
        User member = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원"));

        System.out.println("----- userDetails : " + username);


        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getUserid())
                .password(member.getPw())
                .roles("USER", "ADMIN")
                .build();
    }//작동 안함
}
