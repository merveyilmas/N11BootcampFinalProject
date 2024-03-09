package com.merveyilmaz.userservice.dao;

import com.merveyilmaz.userservice.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
