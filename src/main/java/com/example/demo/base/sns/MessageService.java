package com.example.demo.base.sns;

import com.example.demo.common.exception.IDNotExistException;
import com.example.demo.common.redis.RedisUtil;
import com.example.demo.common.exception.MessageVarificationFailed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final RedisUtil redisUtil;
    private final MessageRepository messageRepository;
    public void saveCertificationNumber(String id, String certificationNumber){
        UserMessage userMessageDto = UserMessage.builder()
                .id(id)
                .messageNumber(certificationNumber)
                .build();
        messageRepository.save(userMessageDto);
    }
    public UserMessage varificationMessage(UserMessage um){
        UserMessage getUm = messageRepository.findById(um.getId()).orElseThrow(()-> new IDNotExistException("메세지 전송을 다시 해주세요"));

        if(um.getMessageNumber().equals(getUm.getMessageNumber())){
            return um;
        }else{
            throw new MessageVarificationFailed("인증번호가 일치하지 않습니다.");
        }
    }
    /*public void saveCertificationNumber(String id, String certificationNumber){
        redisUtil.setDataExpire(id,certificationNumber,60*5L); //5분동안 인증 유효
    }*/
    /*public CertificationNumber varificationMessage(CertificationNumber certificationNumber){
        String redisCertificationNumber = redisUtil.getData(certificationNumber.getId());
        if(redisCertificationNumber==null||redisCertificationNumber.isEmpty()){
            throw new MessageVarificationFailed("인증번호를 전송해주세요");
        }else{
            if(redisCertificationNumber.equals(certificationNumber.getCertificationNumber())){
                return certificationNumber;
            }else{
                throw new MessageVarificationFailed("인증번호가 일치하지 않습니다.");
            }
        }
    }*/
}
