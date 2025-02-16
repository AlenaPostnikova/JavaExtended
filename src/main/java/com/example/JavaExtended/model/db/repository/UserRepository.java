package com.example.JavaExtended.model.db.repository;

import com.example.JavaExtended.model.db.entity.User;
import com.example.JavaExtended.model.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByAgeAndEmailAndStatus(Integer age, String email, UserStatus status);
}
