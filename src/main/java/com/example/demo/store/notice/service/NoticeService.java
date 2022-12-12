package com.example.demo.store.notice.service;

import com.example.demo.store.notice.model.Notice;

import java.util.List;

public interface NoticeService {
    public Notice saveNotice(Notice notice);

    public List<Notice> getAllNotice(Long userStoreId);

    public Notice getNotice(Long noticeId);
}
