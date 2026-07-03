package com.desafio_sicredi.application.usecase;

import org.springframework.stereotype.Service;
import com.desafio_sicredi.domain.model.SegmentType;
import com.desafio_sicredi.domain.model.CreditContract;
import com.desafio_sicredi.domain.exception.BusinessException;
import com.desafio_sicredi.domain.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import com.desafio_sicredi.domain.ports.out.CreditValidationPort;
import com.desafio_sicredi.domain.model.CreditOperationAssociation;
import com.desafio_sicredi.domain.ports.out.CreditContractRepository;
import com.desafio_sicredi.domain.ports.in.CreateCreditContractUseCase;
import com.desafio_sicredi.domain.ports.out.CreditOperationAssociationRepository;
import com.desafio_sicredi.adapters.out.creditvalidation.response.ValidationResponse;
import com.desafio_sicredi.adapters.in.web.controller.response.CreditContractResponse;
import com.desafio_sicredi.adapters.in.web.controller.request.CreateCreditContractRequest;
import com.desafio_sicredi.adapters.in.web.controller.response.CreateCreditContractResponse;

@Service
public class CreateCreditContractService implements CreateCreditContractUseCase {

    private final CreditValidationPort validationPort;
    private final CreditContractRepository repository;
    private final CreditOperationAssociationRepository associationRepository;

    public CreateCreditContractService(CreditValidationPort validationPort, CreditContractRepository repository, CreditOperationAssociationRepository associationRepository) {
        this.validationPort = validationPort;
        this.repository = repository;
        this.associationRepository = associationRepository;
    }

    @Override
    @Transactional
    public CreateCreditContractResponse createContract(CreateCreditContractRequest request) {

        if (request.segmento() == SegmentType.AGRO) {
            validateAgroCredit(request);
        }

        validateCreditContract(request);

        CreditContract contract = buildContract(request);

        CreditContract saved = saveContract(contract);

        if (saved.getSegmento() == SegmentType.PJ) {
            saveCreditOperationAssociation(saved);
        }

        return CreateCreditContractResponse.from(saved);
    }

    @Override
    public CreditContractResponse findCreditById(Long id) {

        CreditContract contract = repository.findCreditById(id)
                .orElseThrow(() -> new NotFoundException("Contrato não encontrado."));

        return CreditContractResponse.from(contract);
    }

    private void validateAgroCredit(CreateCreditContractRequest request) {

        if (request.areaBeneficiadaHa() == null) {
            throw new BusinessException(
                    "Área beneficiada é obrigatória para o segmento AGRO."
            );
        }

        if (request.areaBeneficiadaHa().signum() <= 0) {
            throw new BusinessException(
                    "Área beneficiada deve ser maior que zero."
            );
        }
    }

    private void validateCreditContract(CreateCreditContractRequest request) {

        ValidationResponse response = validationPort.validate(
                        request.codigoProdutoCredito(),
                        request.segmento(),
                        request.valorOperacao()
                );

        if (!response.permiteContratar()) {
            throw new BusinessException("Credito não aprovado.");
        }
    }

    private CreditContract buildContract(CreateCreditContractRequest contract) {

        return new CreditContract(
                contract.idAssociado(),
                contract.valorOperacao(),
                contract.segmento(),
                contract.codigoProdutoCredito(),
                contract.codigoConta(),
                contract.areaBeneficiadaHa()
        );
    }

    private CreditContract saveContract(CreditContract contract) {

        return repository.save(contract);
    }

    private void saveCreditOperationAssociation(CreditContract contract) {

        CreditOperationAssociation operationAssociation = buildOperationAssociation(contract);

        associationRepository.save(operationAssociation);
    }

    private CreditOperationAssociation buildOperationAssociation(CreditContract contract) {

        return new CreditOperationAssociation(contract.getIdOperacaoCredito(), contract.getIdAssociado());
    }
}
