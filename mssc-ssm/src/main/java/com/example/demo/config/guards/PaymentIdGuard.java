package com.example.demo.config.guards;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import com.example.demo.domain.PaymentEvent;
import com.example.demo.domain.PaymentState;
import com.example.demo.services.PaymentServiceImpl;

@Component
public class PaymentIdGuard implements Guard<PaymentState, PaymentEvent> {

	@Override
	public boolean evaluate(StateContext<PaymentState, PaymentEvent> context) {
		return context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER) != null; 
	}
	
}
