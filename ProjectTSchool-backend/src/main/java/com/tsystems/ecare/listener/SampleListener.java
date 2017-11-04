package com.tsystems.ecare.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SampleListener{

//    @Autowired
//    JmsTemplate jmsTemplate;
//
//    @JmsListener(destination = "testQueue", containerFactory = "jmsListenerContainerFactory")
//    public void receiveMessage(String email) {
//        System.out.println("Received <" + email + ">");
//    }
}
