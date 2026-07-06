package com.desafio_sicredi.domain.ports.in;

import com.desafio_sicredi.application.command.CreateCreditContractCommand;
import com.desafio_sicredi.adapters.in.web.controller.response.CreditContractResponse;
import com.desafio_sicredi.adapters.in.web.controller.response.CreateCreditContractResponse;

public interface CreateCreditContractUseCase {

    CreateCreditContractResponse createContract(CreateCreditContractCommand comand);

    CreditContractResponse findCreditById(Long id);
}
