package com.eighteen.batchservice.repository;

import com.eighteen.batchservice.entity.MyEighteen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyEighteenRepository extends JpaRepository<MyEighteen, Integer> {
}
