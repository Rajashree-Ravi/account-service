package com.banking.account.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.account.entity.Account;
import com.banking.account.model.AccountDto;
import com.banking.account.repository.AccountRepository;
import com.banking.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public AccountDto getAccountById(long id) {
		Optional<Account> account = accountRepository.findById(id);
		return (account.isPresent() ? mapper.map(account.get(), AccountDto.class) : null);
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = mapper.map(accountDto, Account.class);
		return mapper.map(accountRepository.save(account), AccountDto.class);
	}

	@Override
	public AccountDto debitAccount(long id, AccountDto accountDto) {
		Optional<Account> updatedAccount = accountRepository.findById(id).map(existingAccount -> {
			// Add logic specific to debit
			Account account = mapper.map(accountDto, Account.class);
			return accountRepository.save(existingAccount.updateWith(account));
		});

		return (updatedAccount.isPresent() ? mapper.map(updatedAccount.get(), AccountDto.class) : null);
	}

	@Override
	public AccountDto creditAccount(long id, AccountDto accountDto) {
		Optional<Account> updatedAccount = accountRepository.findById(id).map(existingAccount -> {
			// Add logic specific to credit
			Account account = mapper.map(accountDto, Account.class);
			return accountRepository.save(existingAccount.updateWith(account));
		});

		return (updatedAccount.isPresent() ? mapper.map(updatedAccount.get(), AccountDto.class) : null);
	}

	@Override
	public AccountDto getAccountByAccountNumber(long accountNo) {
		Optional<Account> account = accountRepository.findByAccountNumber(accountNo);
		return (account.isPresent() ? mapper.map(account.get(), AccountDto.class) : null);
	}

}
