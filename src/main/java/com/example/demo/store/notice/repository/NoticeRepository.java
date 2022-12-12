package com.example.demo.store.notice.repository;

import com.example.demo.store.notice.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findNoticesByUserStoreId(Long userStore_id);
}
