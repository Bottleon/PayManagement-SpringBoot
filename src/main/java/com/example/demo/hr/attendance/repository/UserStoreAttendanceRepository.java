package com.example.demo.hr.attendance.repository;

import com.example.demo.hr.attendance.model.UserStoreAttendance;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStoreAttendanceRepository extends JpaRepository<UserStoreAttendance,Long> {
    Optional<UserStoreAttendance> findTopByUserStoreIdAndWorkFinishTimeIsNullOrderByWorkStartTimeDesc(Long userStore_id);
    
}
