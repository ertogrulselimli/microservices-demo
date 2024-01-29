package com.togrul.ms.accounts.config;


import com.togrul.ms.accounts.dto.AccountsContactInfoDto;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({AccountsContactInfoDto.class})
@Configuration
public class AccountContactsConfiguration {
}
