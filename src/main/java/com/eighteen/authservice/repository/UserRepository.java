package com.eighteen.authservice.repository;

import com.eighteen.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    public User findByUserId(String userId);
}
