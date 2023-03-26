package com.eighteen.userservice.repository;

import com.eighteen.userservice.entity.Music;
import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyEighteenRepository extends JpaRepository<MyEighteen, Integer> {


    public List<MyEighteen> findByUser(User user);

    public MyEighteen findByUserAndMusic(User user, Music music);

}
