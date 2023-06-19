package com.example.noticeboardserver.repositories;

import com.example.noticeboardserver.entities.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Integer > {
}
