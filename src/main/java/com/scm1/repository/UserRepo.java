package com.scm1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.scm1.entities.User;


@Repository
public interface UserRepo extends JpaRepository <User,String>{
 Optional<User> findByEmail(String Email);
 Optional<User> findByEmailAndPassword(String email, String password);
 Optional<User> findByEmailToken(String id);
}
