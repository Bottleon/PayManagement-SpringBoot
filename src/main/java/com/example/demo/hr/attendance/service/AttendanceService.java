package com.example.demo.hr.attendance.service;

import com.example.demo.hr.attendance.model.UserStoreAttendance;

public interface AttendanceService {
    public UserStoreAttendance saveUserAttendance(UserStoreAttendance attendance);
    public UserStoreAttendance updateUserAttendance(UserStoreAttendance attendance);
}
