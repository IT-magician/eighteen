package com.eighteen.userservice.repository;

import com.eighteen.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    public User findByUserId(String userId);
}
