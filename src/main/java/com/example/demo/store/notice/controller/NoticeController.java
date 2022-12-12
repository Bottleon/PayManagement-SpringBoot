package com.example.demo.store.notice.controller;

import com.example.demo.hr.userstore.model.UserStore;
import com.example.demo.store.notice.model.Notice;
import com.example.demo.store.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value={"/notice/*"})
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/one")
    public ResponseEntity<Notice> getNotice(@RequestParam("noticeId") Long noticeId){
        return ResponseEntity.ok(noticeService.getNotice(noticeId));
    }

    @PostMapping("/save")
    public ResponseEntity<Notice> saveNotice(@RequestBody @Valid Notice notice){
        return ResponseEntity.ok(noticeService.saveNotice(notice));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Notice>> getAllNotice(@RequestParam("userStoreId") Long userStoreId){
        return ResponseEntity.ok(noticeService.getAllNotice(userStoreId));
    }

}
