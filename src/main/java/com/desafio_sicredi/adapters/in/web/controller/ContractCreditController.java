package com.desafio_sicredi.adapters.in.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.desafio_sicredi.domain.ports.in.CreateCreditContractUseCase;
import com.desafio_sicredi.application.command.CreateCreditContractCommand;
import com.desafio_sicredi.adapters.in.web.controller.response.CreditContractResponse;
import com.desafio_sicredi.adapters.in.web.controller.request.CreateCreditContractRequest;
import com.desafio_sicredi.adapters.in.web.controller.response.CreateCreditContractResponse;

@RestController
@RequestMapping("/contract-credit")
public class ContractCreditController {

    private final CreateCreditContractUseCase useCase;

    public ContractCreditController(CreateCreditContractUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<CreateCreditContractResponse> create(
            @Valid @RequestBody CreateCreditContractRequest request
    ) {

        CreateCreditContractCommand command =
                new CreateCreditContractCommand(
                        request.idAssociado(),
                        request.valorOperacao(),
                        request.segmento(),
                        request.codigoProdutoCredito(),
                        request.codigoConta(),
                        request.areaBeneficiadaHa()
                );

        CreateCreditContractResponse response =
                useCase.createContract(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditContractResponse> findCreditById(@PathVariable Long id) {

        CreditContractResponse response = useCase.findCreditById(id);

        return ResponseEntity.ok(response);
    }
}
