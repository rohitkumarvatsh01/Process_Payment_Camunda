package com.camunda.process_payments.process;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment {

    private static final System.Logger logger = System.getLogger(CreditCardPayment.class.getName());

    @JobWorker(type = "process-payment")
    public void processPayment(@Variable(name = "amount") Double amount) {
        logger.log(System.Logger.Level.INFO, "Processing payment of: " + amount);
    }
}