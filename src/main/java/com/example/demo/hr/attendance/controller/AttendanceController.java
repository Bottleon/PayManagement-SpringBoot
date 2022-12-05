package com.example.demo.hr.attendance.controller;

import com.example.demo.hr.attendance.model.UserStoreAttendance;
import com.example.demo.hr.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/attendance/*"})
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/save")
    public ResponseEntity<UserStoreAttendance> saveUserAttendance(@RequestBody UserStoreAttendance attendance){
        System.out.println("@@@@@@@@@@@"+attendance.getUserStore().getUser().getId());
        System.out.println("@@@@@@@@@@@"+attendance.getUserStore().getStore().getName());
        System.out.println("@@@@@@@@@@@"+attendance.getUserStore().getId());
        return ResponseEntity.ok(attendanceService.saveUserAttendance(attendance));
    }

    @PostMapping("/update")
    public ResponseEntity<UserStoreAttendance> updateUserAttendance(@RequestBody UserStoreAttendance attendance){
        return ResponseEntity.ok(attendanceService.updateUserAttendance(attendance));
    }
}
