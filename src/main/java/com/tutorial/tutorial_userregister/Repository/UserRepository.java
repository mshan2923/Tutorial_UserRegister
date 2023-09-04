package com.tutorial.tutorial_userregister.Repository;

import com.tutorial.tutorial_userregister.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//DB와 연결
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //List<Users> findFirst2ByUsernameLikeOrderByIDDesc(String name);//정렬된 리스트(?)
    Optional<User> findByUserid(String user_id);//엔티티의 파라미터 써야 되는데 대소문자 구분이 안됨!!
}
