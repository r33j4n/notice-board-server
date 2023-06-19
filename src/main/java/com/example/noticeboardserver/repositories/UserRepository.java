package com.example.noticeboardserver.repositories;

import com.example.noticeboardserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String > {

    User findByEmail(String email);
}
