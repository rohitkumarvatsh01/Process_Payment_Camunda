package com.camunda.process_payments;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;

@SpringBootApplication
public class PaymentApplication implements CommandLineRunner {

	private static final System.Logger logger = System.getLogger(PaymentApplication.class.getName());

	private final ZeebeClient zeebeClient;

	public PaymentApplication(ZeebeClient zeebeClient) {
		this.zeebeClient = zeebeClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@Override
	public void run(String... args) {
		startPaymentProcess();
	}

	private void startPaymentProcess() {
		var processInstance = zeebeClient.newCreateInstanceCommand()
				.bpmnProcessId("payment-process")
				.latestVersion()
				.variables(Map.of("amount", 100))
				.send()
				.join();

		logger.log(System.Logger.Level.INFO, "Started payment process with ID: " + processInstance.getProcessInstanceKey());
	}
}