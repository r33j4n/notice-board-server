package com.example.noticeboardserver.services;

import com.example.noticeboardserver.entities.Notice;
import com.example.noticeboardserver.repositories.NoticeRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public Notice saveDetails(Notice notice) {
        return noticeRepository.save(notice);
    }
    public List<Notice> getAllNoticeDetails()
    {
        return noticeRepository.findAll();
    }


    public void deleteNotice(int id) {
        noticeRepository.deleteById(id);
    }
}
