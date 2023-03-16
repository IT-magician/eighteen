package com.eighteen.userservice.repository;

import com.eighteen.userservice.entity.Music;
import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MyEighteenRepository extends JpaRepository<MyEighteen, Integer> {


    public List<MyEighteen> findByUser(User user);

    public Page<MyEighteen> findPageByUser(User user, Pageable pageable);

    public MyEighteen findByUserAndMusic(User user, Music music);

}
