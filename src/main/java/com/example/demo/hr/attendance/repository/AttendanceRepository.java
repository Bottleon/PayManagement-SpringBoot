package com.example.demo.hr.attendance.repository;

import com.example.demo.hr.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    public Attendance findAttendanceByType(String type);
}
