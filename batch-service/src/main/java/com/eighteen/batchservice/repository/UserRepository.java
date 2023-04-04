package com.eighteen.batchservice.repository;

import com.eighteen.batchservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    public User findByUserId(String userId);
}
