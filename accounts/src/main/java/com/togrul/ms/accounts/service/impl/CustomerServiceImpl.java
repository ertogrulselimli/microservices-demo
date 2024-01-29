package com.togrul.ms.accounts.service.impl;

import com.togrul.ms.accounts.dto.AccountsDto;
import com.togrul.ms.accounts.dto.CardsDto;
import com.togrul.ms.accounts.dto.CustomerDetailsDto;
import com.togrul.ms.accounts.entity.Accounts;
import com.togrul.ms.accounts.entity.Customer;
import com.togrul.ms.accounts.exception.ResourceNotFoundException;
import com.togrul.ms.accounts.mapper.AccountsMapper;
import com.togrul.ms.accounts.mapper.CustomerMapper;
import com.togrul.ms.accounts.repository.AccountsRepository;
import com.togrul.ms.accounts.repository.CustomerRepository;
import com.togrul.ms.accounts.service.ICustomerService;
import com.togrul.ms.accounts.service.client.CardsFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {



    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;





    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        if(cardsDtoResponseEntity!=null)
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        return customerDetailsDto;
    }

}
