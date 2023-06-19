package com.example.noticeboardserver.controllers;

import com.example.noticeboardserver.entities.Notice;
import com.example.noticeboardserver.repositories.NoticeRepository;
import com.example.noticeboardserver.services.NoticeService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @PostMapping("/saveNotice")
    public Notice postDetails(@RequestBody Notice notice)
    {
        return noticeService.saveDetails(notice);
    }
    @GetMapping("/getAllNotice")
    public List<Notice> getAllNoticeDetails()
    {
        return noticeService.getAllNoticeDetails();
    }
    @DeleteMapping("/deleteNotice/{id}")
    public void deleteNotice(@PathVariable("id") Integer id)
    {
        noticeService.deleteNotice(id);
    }


}
