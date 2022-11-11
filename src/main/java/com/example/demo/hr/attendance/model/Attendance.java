package com.example.demo.hr.attendance.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="attendance")
public class Attendance {
    @Id
    @Column(name="attendance_code",updatable = false, insertable = false)
    private Long code;

    @Column(name="attendance_type",updatable = false, insertable = false)
    private String type;
}
