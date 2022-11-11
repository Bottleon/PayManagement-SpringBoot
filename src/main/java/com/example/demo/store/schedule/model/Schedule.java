package com.example.demo.store.schedule.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @Column(name="schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="user_id"),
            @JoinColumn(name="store_id")
    })
    private UserStore userStore;

    @Column(name="start_time",nullable = false)
    private Date startTime;

    @Column(name="finish_time",nullable = false)
    private Date finishTime;
}
