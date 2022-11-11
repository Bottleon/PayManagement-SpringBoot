package com.example.demo.store.checklist.model;

import com.example.demo.common.converter.BooleanToYNConverter;
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
            @JoinColumn(name="user_id"),
            @JoinColumn(name="store_id")
    })
    private UserStore userStore;

    @Column(name="regist_date",nullable = false,updatable = false)
    @CreatedDate
    private Date registDate;

    @Column(name="content")
    private String content;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean checkStatus;
}
