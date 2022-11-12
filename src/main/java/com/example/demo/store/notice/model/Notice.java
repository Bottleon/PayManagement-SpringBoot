package com.example.demo.store.notice.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name="notice")
@EntityListeners(AuditingEntityListener.class)
public class Notice {
    @Id
    @Column(name="notice_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="user_id",nullable = false),
            @JoinColumn(name="store_id",nullable = false)
    })
    private UserStore userStore;

    @Column(name="title")
    @NotNull(message = "제목을 입력해 주세요")
    private String title;

    @Column(name = "content")
    @NotNull(message = "내용을 적어주세요")
    private String content;

    @CreatedDate
    @Column(name="registe_date",updatable = false,nullable = false)
    private LocalDateTime registeDate;

    @Column(name="modify_date")
    private LocalDateTime modifyDate;
}
