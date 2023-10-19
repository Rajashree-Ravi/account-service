package com.banking.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.account.exception.BankingException;
import com.banking.account.model.AccountDto;
import com.banking.account.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(produces = "application/json", value = "Operations pertaining to manage account in banking application")
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieve specific account with the specified account id", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved account with the account id"),
			@ApiResponse(code = 404, message = "Account with specified account id not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	private ResponseEntity<AccountDto> getAccountById(@PathVariable("id") long id) {

		AccountDto account = accountService.getAccountById(id);
		if (account != null)
			return new ResponseEntity<>(account, HttpStatus.OK);
		else
			throw new BankingException("account-not-found", String.format("Account with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@PostMapping("/createAccount")
	@ApiOperation(value = "Create a new account", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created a account"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto account) {
		return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
	}

	@PutMapping("/debit/{id}")
	@ApiOperation(value = "Debit a account", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully debited the account"),
			@ApiResponse(code = 404, message = "Account with specified account id not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<AccountDto> debitAccount(@PathVariable("id") long id, @RequestBody AccountDto account) {

		AccountDto updatedAccount = accountService.debitAccount(id, account);
		if (updatedAccount != null)
			return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
		else
			throw new BankingException("account-not-found", String.format("Account with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@PutMapping("/credit/{id}")
	@ApiOperation(value = "Credit a account", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully credited the account"),
			@ApiResponse(code = 404, message = "Account with specified account id not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<AccountDto> creditAccount(@PathVariable("id") long id, @RequestBody AccountDto account) {

		AccountDto updatedAccount = accountService.creditAccount(id, account);
		if (updatedAccount != null)
			return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
		else
			throw new BankingException("account-not-found", String.format("Account with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}
}
