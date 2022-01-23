package com.example.demo.services;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

import com.example.demo.domain.Payment;
import com.example.demo.domain.PaymentEvent;
import com.example.demo.domain.PaymentState;
import com.example.demo.repository.PaymentRepository;

@SpringBootTest
class PaymentServiceImplTest {

	@Autowired
	PaymentService paymentService;

	@Autowired
	PaymentRepository paymentRepository;

	Payment payment;

	@BeforeEach
	void setUp() throws Exception {
		payment = Payment.builder().amount(new BigDecimal("12.99")).build();
	}

	@Transactional
	@Test
	void preAuth() {
		Payment savedPayment = paymentService.newPayment(payment);

		System.out.println("SHOULD BE NEW: ");
		System.out.println(savedPayment.getState());

		StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());

		Payment preAuthPayment = paymentRepository.getById(savedPayment.getId());
		System.out.println("SHOULD BE PRE_AUTH or PRE_AUTH_ERROR: ");
		System.out.println(sm.getState().getId());

		System.out.println(preAuthPayment);
	}

	@Transactional
	@Test
	void testAuth() {
		Payment savedPayment = paymentService.newPayment(payment);

		StateMachine<PaymentState, PaymentEvent> preAuthSM = paymentService
				.preAuth(savedPayment.getId());
		
		if(preAuthSM.getState().getId() == PaymentState.PRE_AUTH) {
			System.out.println("Payment is pre authorized");
			
			StateMachine<PaymentState, PaymentEvent> authSM = 
					paymentService.authorizePayment(savedPayment.getId());
			System.out.println("Result of Auth: " + authSM.getState().getId());
		} else {
			System.out.println("Payment failed pre-auth...");
		}

	}

}
