package com.pgm.shopserver.repository;

import com.pgm.shopserver.domain.Role;
import com.pgm.shopserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String username);

    @Modifying
    @Query("update User set role=:role where  username=:username")
    void updateUserRole(@Param("username")String username, @Param("role") Role role);
}
