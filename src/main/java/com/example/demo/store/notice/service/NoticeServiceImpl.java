package com.example.demo.store.notice.service;

import com.example.demo.store.notice.model.Notice;
import com.example.demo.store.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public Notice saveNotice(Notice notice) {
        noticeRepository.save(notice);
        return notice;
    }

    @Override
    @Transactional
    public List<Notice> getAllNotice(Long userStoreId) {
        return noticeRepository.findNoticesByUserStoreId(userStoreId);
    }

    @Override
    public Notice getNotice(Long noticeId) {
        return noticeRepository.findById(noticeId).get();
    }
}
