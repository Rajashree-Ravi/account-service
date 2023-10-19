package com.banking.account.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long accountNumber;

	@NotNull
	private Long customerId;

	@NotBlank
	private String accountType;

	@NotNull
	private BigDecimal accountBalance;

	@NotBlank
	private String debitCardNumber;

	private String nominee;

	public Account updateWith(Account account) {
		return new Account(this.id, account.accountNumber, account.customerId, account.accountType,
				account.accountBalance, account.debitCardNumber, account.nominee);
	}
}
