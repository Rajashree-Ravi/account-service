package com.banking.account.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.banking.account.model.AccountDto;
import com.banking.account.model.TransactionDto;
import com.banking.account.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

	@Value("${consumer.config.topic.name}")
	private String topicName;

	@Autowired
	AccountService accountService;

	@KafkaListener(topics = "${consumer.config.topic.name}", groupId = "${consumer.config.group-id}")
	public void consume(ConsumerRecord<String, TransactionDto> payload) {
		log.info("Topic : {}", topicName);
		log.info("Key : {}", payload.key());
		log.info("Headers : {}", payload.headers());
		log.info("Partion : {}", payload.partition());
		log.info("Transaction : {}", payload.value());

		TransactionDto failedTransaction = payload.value();

		// Saga pattern - Compensating the failure

		AccountDto account = accountService.getAccountByAccountNumber(failedTransaction.getAccountNumber());
		account.setAccountBalance(account.getAccountBalance().add(failedTransaction.getAmount()));
		accountService.creditAccount(account.getId(), account);
	}

}