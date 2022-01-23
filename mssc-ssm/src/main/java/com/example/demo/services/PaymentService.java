package com.example.demo.services;

import org.springframework.statemachine.StateMachine;

import com.example.demo.domain.Payment;
import com.example.demo.domain.PaymentEvent;
import com.example.demo.domain.PaymentState;


public interface PaymentService {
	
	Payment newPayment(Payment payment);
	StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);
	StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);
	StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId);
	
}
