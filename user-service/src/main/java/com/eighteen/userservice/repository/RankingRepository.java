package com.eighteen.userservice.repository;

import com.eighteen.userservice.entity.AgeGender;
import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.entity.Ranking;
import com.eighteen.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, String> {

    public List<Ranking> findByAgeGender(AgeGender ageGender);
}
