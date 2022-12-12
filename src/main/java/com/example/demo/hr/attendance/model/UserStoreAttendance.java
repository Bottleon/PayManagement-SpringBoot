package com.example.demo.hr.attendance.model;

import com.example.demo.hr.userstore.model.UserStore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name="user_store_attendance")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStoreAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_store_id",nullable = false)
    private UserStore userStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="attendance_code")
    private Attendance attendance;


    @Column(name="work_start_time",nullable = false)
    private String workStartTime;

    @Column(name="work_finish_time")
    private String workFinishTime;

    @PrePersist
    public void onPrePersist(){
        this.workStartTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    @PreUpdate
    public void onPreUpdate(){
       this.workFinishTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
