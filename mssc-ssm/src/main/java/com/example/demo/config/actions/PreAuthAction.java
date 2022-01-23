package com.example.demo.config.actions;

import java.util.Random;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.example.demo.domain.PaymentEvent;
import com.example.demo.domain.PaymentState;
import com.example.demo.services.PaymentServiceImpl;

@Component
public class PreAuthAction implements Action<PaymentState, PaymentEvent>{

	@SuppressWarnings("deprecation")
	@Override
	public void execute(StateContext<PaymentState, PaymentEvent> context) {
		System.out.println("PreAuth was called");
		if (new Random().nextInt(10) < 8) {
			System.out.println("Pre Auth Approved");
			context.getStateMachine()
					.sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_APPROVED)
							.setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context
									.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
							.build());
		} else {
			System.out.println("Pre Auth Declined! No credit!!!");
			context.getStateMachine()
					.sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_DECLINED)
							.setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context
									.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
							.build());

		}
	}
	
}
