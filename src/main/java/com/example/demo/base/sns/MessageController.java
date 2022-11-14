package com.example.demo.base.sns;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/sns")
public class MessageController {

    final DefaultMessageService messageService;
    public MessageController(){
        this.messageService = NurigoApp.INSTANCE.initialize("NCSLE4UA4SLMMTPD", "4Z80RYBPAROZYWDDLZIDUCXVW9KVZCVB", "https://api.coolsms.co.kr");
    }

    @PostMapping("/send-one")
    public SingleMessageSentResponse sendOne() {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        message.setFrom("01051216967");
        message.setTo("01051216967");
        message.setText("휴대폰인증 테스트 메시지 : 인증번호는" + "["+numStr+"]" + "입니다.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }
}
