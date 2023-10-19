package com.banking.account.service;

import com.banking.account.model.AccountDto;

public interface AccountService {

	AccountDto getAccountById(long id);
	
	AccountDto getAccountByAccountNumber(long accountNo);

	AccountDto createAccount(AccountDto accountDto);

	AccountDto debitAccount(long id, AccountDto accountDto);

	AccountDto creditAccount(long id, AccountDto accountDto);
}
