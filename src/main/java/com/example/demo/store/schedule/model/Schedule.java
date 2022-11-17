package com.example.demo.store.schedule.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
            @JoinColumn(name="user_id",nullable = false),
            @JoinColumn(name="store_id",nullable = false)
    })
    private UserStore userStore;

    @Column(name="start_time")
    @NotNull(message = "출근시간을 입력해 주세요")
    private LocalDateTime startTime;

    @Column(name="finish_time")
    @NotNull(message = "퇴근시간을 입력해 주세요")
    private LocalDateTime finishTime;

}
