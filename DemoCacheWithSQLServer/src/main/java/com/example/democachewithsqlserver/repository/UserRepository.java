package com.example.democachewithsqlserver.repository;

import com.example.democachewithsqlserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
