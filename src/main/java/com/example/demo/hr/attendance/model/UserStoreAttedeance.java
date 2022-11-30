package com.example.demo.hr.attendance.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name="user_store_attendance")
public class UserStoreAttedeance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_store_id",nullable = false)
    private UserStore userStore;

    @ManyToOne
    @JoinColumn(name="attendance_code")
    private Attendance attendance;


    @Column(name="work_start_time",nullable = false)
    @CreatedDate
    private LocalDateTime workStartTime;

    @Column(name="work_finish_time")
    private LocalDateTime workFinishTime;
}
