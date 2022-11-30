package com.example.demo.store.checklist.model;

import com.example.demo.common.converter.BooleanToYNConverter;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "checklist")
@EntityListeners(AuditingEntityListener.class)
public class CheckList {
    @Id
    @Column(name="checklist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_store_id", nullable = false)
    private UserStore userStore;

    @Column(name="regist_date",nullable = false,updatable = false)
    @CreatedDate
    private LocalDateTime registDate;

    @Column(name="content")
    @NotEmpty(message = "체크리스트의 내용을 입력해 주세요")
    private String content;

}
