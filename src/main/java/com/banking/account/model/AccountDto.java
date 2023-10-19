package com.banking.account.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Class representing a account in banking application.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

	@ApiModelProperty(notes = "Unique identifier of the Account.", example = "1")
	private Long id;

	@ApiModelProperty(notes = "Account Number.", example = "100023456", required = true)
	@NotNull
	private Long accountNumber;

	@ApiModelProperty(notes = "Unique identifier of the Customer.", example = "3124567", required = true)
	@NotNull
	private Long customerId;

	@ApiModelProperty(notes = "Type of Account.", example = "Savings", required = true)
	@NotBlank
	private String accountType;

	@ApiModelProperty(notes = "Account Balance.", example = "50000.00", required = true)
	@NotNull
	private BigDecimal accountBalance;

	@ApiModelProperty(notes = "Debit Card Number.", example = "425607654532", required = true)
	@NotBlank
	private String debitCardNumber;

	@ApiModelProperty(notes = "Nominee Name.", example = "Abhishek")
	private String nominee;

}
