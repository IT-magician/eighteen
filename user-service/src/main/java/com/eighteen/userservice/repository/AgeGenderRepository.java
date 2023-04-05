package com.eighteen.userservice.repository;

import com.eighteen.userservice.entity.AgeGender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeGenderRepository extends JpaRepository<AgeGender, String> {

    public AgeGender findByAgId(String agId);
}
