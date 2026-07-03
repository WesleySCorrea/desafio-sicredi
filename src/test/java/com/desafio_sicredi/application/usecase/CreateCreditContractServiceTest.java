package com.desafio_sicredi.application.usecase;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.desafio_sicredi.domain.model.SegmentType;
import com.desafio_sicredi.domain.model.CreditContract;
import com.desafio_sicredi.domain.exception.NotFoundException;
import com.desafio_sicredi.domain.exception.BusinessException;
import com.desafio_sicredi.domain.ports.out.CreditValidationPort;
import com.desafio_sicredi.domain.ports.out.CreditContractRepository;
import com.desafio_sicredi.domain.ports.out.CreditOperationAssociationRepository;
import com.desafio_sicredi.adapters.out.creditvalidation.response.ValidationResponse;
import com.desafio_sicredi.adapters.in.web.controller.response.CreditContractResponse;
import com.desafio_sicredi.adapters.in.web.controller.request.CreateCreditContractRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateCreditContractServiceTest {

    @Mock
    private CreditValidationPort validationPort;

    @Mock
    private CreditContractRepository repository;

    @Mock
    private CreditOperationAssociationRepository associationRepository;

    @InjectMocks
    private CreateCreditContractService service;

    private void mockValidationOk() {
        when(validationPort.validate(any(), any(), any()))
                .thenReturn(new ValidationResponse(true));
    }

    @Test
    void deveCriarContratoAgroSemAssociacao() {

        CreateCreditContractRequest request = new CreateCreditContractRequest(
                1L,
                new BigDecimal("5000"),
                SegmentType.AGRO,
                "903C",
                "1234567890",
                new BigDecimal("10")
        );

        mockValidationOk();

        when(repository.save(any()))
                .thenAnswer(inv -> inv.getArgument(0));

        // WHEN
        var response = service.createContract(request);

        // THEN
        assertNotNull(response);

        verify(repository).save(any());
        verify(associationRepository, never()).save(any());
    }

    @Test
    void deveSalvarAssociacaoQuandoPJ() {

        CreateCreditContractRequest request = new CreateCreditContractRequest(
                1L,
                new BigDecimal("5000"),
                SegmentType.PJ,
                "903C",
                "1234567890",
                new BigDecimal("10")
        );

        mockValidationOk();

        CreditContract saved = new CreditContract(
                1L,
                1L,
                new BigDecimal("5000"),
                SegmentType.PJ,
                "903C",
                "1234567890",
                new BigDecimal("10"),
                LocalDateTime.now()
        );

        when(repository.save(any()))
                .thenReturn(saved);

        // WHEN
        service.createContract(request);

        // THEN
        verify(repository).save(any());
        verify(associationRepository).save(any());
    }

    @Test
    void deveFalharQuandoAreaBeneficiadaForNula() {

        // GIVEN
        CreateCreditContractRequest request = new CreateCreditContractRequest(
                1L,
                new BigDecimal("5000"),
                SegmentType.AGRO,
                "903C",
                "1234567890",
                null
        );

        // WHEN + THEN
        var ex = assertThrows(BusinessException.class,
                () -> service.createContract(request));

        assertEquals(
                "Área beneficiada é obrigatória para o segmento AGRO.",
                ex.getMessage()
        );

        verify(repository, never()).save(any());
    }

    @Test
    void deveFalharQuandoAreaBeneficiadaForZeroOuNegativa() {

        CreateCreditContractRequest request = new CreateCreditContractRequest(
                1L,
                new BigDecimal("5000"),
                SegmentType.AGRO,
                "903C",
                "1234567890",
                new BigDecimal("-10")
        );

        var ex = assertThrows(BusinessException.class,
                () -> service.createContract(request));

        assertEquals(
                "Área beneficiada deve ser maior que zero.",
                ex.getMessage()
        );

        verify(repository, never()).save(any());
    }

    @Test
    void deveFalharQuandoCreditoNaoAprovado() {

        CreateCreditContractRequest request = new CreateCreditContractRequest(
                1L,
                new BigDecimal("5000"),
                SegmentType.PF,
                "903C",
                "1234567890",
                new BigDecimal("10")
        );

        when(validationPort.validate(any(), any(), any()))
                .thenReturn(new ValidationResponse(false));

        var ex = assertThrows(BusinessException.class,
                () -> service.createContract(request));

        assertEquals("Credito não aprovado.", ex.getMessage());

        verify(repository, never()).save(any());
    }

    @Test
    void deveBuscarContratoPorIdComSucesso() {

        // GIVEN
        CreditContract contract = new CreditContract(
                1L,
                1L,
                new BigDecimal("5000"),
                SegmentType.AGRO,
                "903C",
                "1234567890",
                new BigDecimal("10"),
                LocalDateTime.now()
        );

        when(repository.findCreditById(1L))
                .thenReturn(Optional.of(contract));

        // WHEN
        CreditContractResponse response = service.findCreditById(1L);

        // THEN
        assertNotNull(response);
        assertEquals(1L, response.idOperacaoCredito());
        assertEquals(1L, response.idAssociado());
    }

    @Test
    void deveLancarNotFoundExceptionQuandoNaoExistirContrato() {

        // GIVEN
        when(repository.findCreditById(1L))
                .thenReturn(Optional.empty());

        // WHEN + THEN
        var ex = assertThrows(NotFoundException.class,
                () -> service.findCreditById(1L));

        assertEquals("Contrato não encontrado.", ex.getMessage());

        verify(repository).findCreditById(1L);
    }
}
