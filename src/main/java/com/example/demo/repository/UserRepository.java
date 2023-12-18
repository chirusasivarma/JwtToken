package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.module.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByMobileNumber(long mobileNumber);

	Optional<User> findByName(String username);
}
