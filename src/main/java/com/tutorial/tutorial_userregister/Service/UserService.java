package com.tutorial.tutorial_userregister.Service;

import com.tutorial.tutorial_userregister.Dto.UserRequestDto;
import com.tutorial.tutorial_userregister.Entity.User;
import com.tutorial.tutorial_userregister.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor//생성자 자동 생성해 준다.
public class UserService {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);

    private final UserRepository userRepository;

    @Transactional//트랜잭션 보장이 된 메소드로 설정 해준다.
    public void  joinUser(UserRequestDto userRequestDto)
    {
        User user = new User(userRequestDto);
        user.Crypt();
        userRepository.save(user);
    }

    public boolean isVailMember(String userId, String password)
    {
        Optional<User> user = userRepository.findByUserid(userId);
        if (user.isPresent())
        {
            return user.get().getPw().equals(password);
        }
        return false;
    }

    public Long join(String userid, String pw)
    {
        return -1L;
    }
}
