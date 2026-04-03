package com.prathina.money_manager.repository;

import com.prathina.money_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByUsername(String username);
}