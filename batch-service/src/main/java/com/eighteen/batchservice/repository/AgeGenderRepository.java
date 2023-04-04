package com.eighteen.batchservice.repository;

import com.eighteen.batchservice.entity.AgeGender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeGenderRepository extends JpaRepository<AgeGender, String> {

    public AgeGender findByAgId(String agId);
}
