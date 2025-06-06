package com.example.paradox.repository;

import com.example.paradox.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer Id);
}
