package com.example.demo.base.sns;

import com.example.demo.common.exception.MessageVarificationFailed;
import com.example.demo.hr.user.model.CertificationNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public void saveCertificationNumber(String id, String certificationNumber){
        CertificationNumber certificationNumber1 = CertificationNumber.builder()
                .id(id)
                .certificationNumber(certificationNumber)
                .build();

        messageRepository.save(certificationNumber1);
    }
    public boolean varificationMessage(CertificationNumber certificationNumber){
        Optional<CertificationNumber> isCertification = messageRepository.findById(certificationNumber.getId());
        if(isCertification.isPresent()){
            if(isCertification.get().getCertificationNumber().equals(certificationNumber.getCertificationNumber())){
                return true;
            }else{
                throw new MessageVarificationFailed("인증번호가 일치하지 않습니다.");
            }
        }else{
            throw new MessageVarificationFailed("인증번호를 전송해주세요");
        }
    }
}
