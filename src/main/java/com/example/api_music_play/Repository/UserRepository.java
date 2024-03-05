package com.example.api_music_play.Repository;

import com.example.api_music_play.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.phone = :phone and u.password = :password")
    User Login(@Param("phone") String phone, @Param("password") String password);
}
