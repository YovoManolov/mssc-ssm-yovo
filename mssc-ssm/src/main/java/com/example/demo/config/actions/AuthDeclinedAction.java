package com.example.demo.config.actions;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.example.demo.domain.PaymentEvent;
import com.example.demo.domain.PaymentState;

@Component
public class AuthDeclinedAction implements Action<PaymentState, PaymentEvent> {

	@Override
	public void execute(StateContext<PaymentState, PaymentEvent> context) {
		System.out.println("Sending notification of Auth DECLINED");	
	}

}
