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
    /*
     * 무료 사용량은 모든 리전에서 매월 계산되어 청구서에 자동으로 적용됩니다. 무료 사용량은 누적되지 않습니다.
     ** AWS의 프리 티어를 사용하는 신규 AWS 고객은 모든 AWS 서비스를 합산해 1년 동안 매달 15GB의 데이터 전송을 무료로 제공받게 됩니다.
     *** 데이터 전송 요금 티어는 Amazon EC2, AWS Lambda, Amazon EBS, Amazon S3, Amazon Glacier, Amazon RDS, Amazon SimpleDB, Amazon SQS, Amazon SNS, Amazon DynamoDB, AWS Storage Gateway 및 Amazon VPC 전체에 걸친 아웃바운드 데이터 전송량을 합하여 계산합니다.
     */
    final DefaultMessageService messageService;
    public MessageController(){
        this.messageService = NurigoApp.INSTANCE.initialize("NCSLE4UA4SLMMTPD", "4Z80RYBPAROZYWDDLZIDUCXVW9KVZCVB", "https://api.coolsms.co.kr");
    }

    @PostMapping("/send-one")
    public SingleMessageSentResponse sendOne() {
        Message message = new Message();
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
