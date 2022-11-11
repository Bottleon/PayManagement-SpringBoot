package com.example.demo.hr.attendance.model;

import com.example.demo.hr.userstore.model.UserStore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="user_store_attendance")
public class UserStoreAttedeance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="user_id",nullable = false),
            @JoinColumn(name="store_id",nullable = false)
    })
    private UserStore userStore;

    @ManyToOne
    @JoinColumn(name="attendance_code",nullable = false)
    private Attendance attendance;

    @Column(name="work_start_time")
    private Date workStartTime;

    @Column(name="work_finish_time")
    private Date workFinishTime;
}
