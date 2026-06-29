package com.liftlog.liftlog.repo;

import com.liftlog.liftlog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
