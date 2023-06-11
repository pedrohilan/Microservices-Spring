package com.micros.exam.mqueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micros.exam.repositories.ExamRepository;
import com.micros.exam.dtos.ExamDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CreateExamSubscriber {

    private static final Logger log = LoggerFactory.getLogger(CreateExamSubscriber.class);
    @Autowired
    final ExamRepository examRepository;

    public CreateExamSubscriber(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @RabbitListener(queues = "${mq.queues.create-exam}")
    public void receiveCreationExam(@Payload String payload){
        try{
            var mapper = new ObjectMapper();
            ExamDto examDto = mapper.readValue(payload, ExamDto.class);
            examRepository.save(examDto.toModel());
        }catch (Exception e){
            log.error("Erro ao receber solicitação de registro de prova: {}", e.getMessage());
        }
    }
}
