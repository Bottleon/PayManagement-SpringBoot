package com.example.demo.store.checklist.model;

import com.example.demo.common.converter.BooleanToYNConverter;
import com.example.demo.hr.user.model.User;
import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "checklist")
public class CheckList {
    @Id
    @Column(name="checklist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="write_user_id",referencedColumnName = "user_id",nullable = false),
            @JoinColumn(name="store_id", referencedColumnName ="store_id",nullable = false)
    })
    private UserStore userStore;

    @ManyToOne
    @JoinColumn(name="checked_user_id",referencedColumnName = "user_id")
    private User user;

    @Column(name="regist_date",nullable = false,updatable = false)
    @CreatedDate
    private Date registDate;

    @Column(name="content",nullable = false)
    private String content;

}
