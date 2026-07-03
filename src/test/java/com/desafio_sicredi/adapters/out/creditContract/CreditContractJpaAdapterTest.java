package com.desafio_sicredi.adapters.out.creditContract;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.desafio_sicredi.domain.model.SegmentType;
import com.desafio_sicredi.domain.model.CreditContract;
import com.desafio_sicredi.adapters.out.persistence.creditContract.CreditContractEntity;
import com.desafio_sicredi.adapters.out.persistence.creditContract.CreditContractJpaAdapter;
import com.desafio_sicredi.adapters.out.persistence.creditContract.CreditContractJpaRepository;

import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditContractJpaAdapterTest {

    @Mock
    private CreditContractJpaRepository jpaRepository;

    @InjectMocks
    private CreditContractJpaAdapter adapter;

    @Test
    void deveSalvarContrato() {

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

        CreditContractEntity entity = new CreditContractEntity(contract);
        CreditContractEntity savedEntity = new CreditContractEntity(contract);

        when(jpaRepository.save(any()))
                .thenReturn(savedEntity);

        // WHEN
        CreditContract result = adapter.save(contract);

        // THEN
        ArgumentCaptor<CreditContractEntity> captor =
                ArgumentCaptor.forClass(CreditContractEntity.class);

        verify(jpaRepository).save(captor.capture());

        CreditContractEntity captured = captor.getValue();

        assertNotNull(result);
    }

    @Test
    void deveBuscarContratoPorId() {

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

        CreditContractEntity entity = new CreditContractEntity(contract);

        when(jpaRepository.findById(1L))
                .thenReturn(Optional.of(entity));

        // WHEN
        Optional<CreditContract> result = adapter.findCreditById(1L);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(contract.getIdAssociado(), result.get().getIdAssociado());
        assertEquals(contract.getCodigoConta(), result.get().getCodigoConta());
    }

    @Test
    void deveRetornarEmptyQuandoNaoEncontrar() {

        // GIVEN
        when(jpaRepository.findById(1L))
                .thenReturn(Optional.empty());

        // WHEN
        Optional<CreditContract> result = adapter.findCreditById(1L);

        // THEN
        assertTrue(result.isEmpty());
    }
}
