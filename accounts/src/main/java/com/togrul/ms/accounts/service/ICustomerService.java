package com.togrul.ms.accounts.service;

import com.togrul.ms.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(final String mobileNumber);
}
