package com.example.demo.store.notice.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="notice")
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

    @Column(name="title",nullable = false)
    private String title;

    @Column(name = "content",nullable = false)
    private String content;

    @CreatedDate
    @Column(name="registe_date",updatable = false,nullable = false)
    private Date registeDate;

    @Column(name="modify_date")
    private Date modifyDate;
}
