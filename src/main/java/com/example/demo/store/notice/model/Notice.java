package com.example.demo.store.notice.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Entity
@Table(name="notice")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {
    @Id
    @Column(name="notice_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="user_store_id",nullable = false)
    private UserStore userStore;

    @Column(name="title")
    @NotNull(message = "제목을 입력해 주세요")
    private String title;

    @Column(name = "content")
    @NotNull(message = "내용을 적어주세요")
    private String content;

    @Column(name="registe_date",updatable = false,nullable = false)
    private String registeDate;

    @Column(name="modify_date")
    private String modifyDate;
    @PrePersist
    public void onPrePersist(){
        this.registeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    @PreUpdate
    public void onPreUpdate(){
        this.modifyDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
