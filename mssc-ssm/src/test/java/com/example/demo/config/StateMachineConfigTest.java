package com.example.demo.config;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import com.example.demo.domain.PaymentEvent;
import com.example.demo.domain.PaymentState;

import reactor.core.publisher.Mono;

@SpringBootTest
class StateMachineConfigTest {

	@Autowired
	StateMachineFactory<PaymentState, PaymentEvent> factory;

	@Test
	void test() {
		StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine(UUID.randomUUID());
		
		sm.start();
		
		System.out.println(sm.getState().toString());
		sm.sendEvent(PaymentEvent.PRE_AUTHORIZE);
		
		System.out.println(sm.getState().toString());
		sm.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);
		System.out.println(sm.getState().toString());
		
		sm.sendEvent(PaymentEvent.PRE_AUTH_DECLINED);
		System.out.println(sm.getState().toString());
	}

}
