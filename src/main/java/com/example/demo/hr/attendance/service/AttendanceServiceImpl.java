package com.example.demo.hr.attendance.service;

import com.example.demo.common.exception.FinishTimeException;
import com.example.demo.common.exception.StartTimeException;
import com.example.demo.hr.attendance.model.Attendance;
import com.example.demo.hr.attendance.model.UserStoreAttendance;
import com.example.demo.hr.attendance.repository.AttendanceRepository;
import com.example.demo.hr.attendance.repository.UserStoreAttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService{
    private final UserStoreAttendanceRepository userStoreAttendanceRepository;
    private final AttendanceRepository attendanceRepository;
    @Override
    @Transactional
    public UserStoreAttendance saveUserAttendance(UserStoreAttendance attendance) {
        Optional<UserStoreAttendance> opt = userStoreAttendanceRepository.findTopByUserStoreIdAndWorkFinishTimeIsNullOrderByWorkStartTimeDesc(attendance.getUserStore().getId());
        if(opt.isPresent()){
            throw new FinishTimeException("이미 출근기록이 있습니다.");
        }
        attendance.setAttendance(attendanceRepository.findAttendanceByType(attendance.getAttendance().getType()));
        return userStoreAttendanceRepository.save(attendance);
    }

    @Override
    @Transactional
    public UserStoreAttendance updateUserAttendance(UserStoreAttendance attendance) {

        UserStoreAttendance userStoreAttendance= userStoreAttendanceRepository.findTopByUserStoreIdAndWorkFinishTimeIsNullOrderByWorkStartTimeDesc(attendance.getUserStore().getId())
                .orElseThrow(()-> new StartTimeException("출근기록이 없습니다."));
        userStoreAttendance.setWorkFinishTime(attendance.getWorkFinishTime());
        userStoreAttendance.setAttendance(attendanceRepository.findAttendanceByType(attendance.getAttendance().getType()));
        UserStoreAttendance usa = userStoreAttendanceRepository.saveAndFlush(userStoreAttendance);
        attendance.setWorkFinishTime((usa.getWorkFinishTime()));
        return attendance;
    }
}
