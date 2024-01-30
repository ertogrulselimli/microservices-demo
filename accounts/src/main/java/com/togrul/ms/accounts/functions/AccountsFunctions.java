package com.togrul.ms.accounts.functions;


import com.togrul.ms.accounts.service.IAccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class AccountsFunctions {




    @Bean
    public Consumer<Long> updateCommunication(IAccountsService accountsService) {
        return (accountNumber) -> {

            log.info("Getting accountNumber from message queue: {}",accountNumber);
            boolean isUpdated = accountsService.updateCommunicationSw(accountNumber);
            log.info("Updated {}",isUpdated);
        };
    }


}
