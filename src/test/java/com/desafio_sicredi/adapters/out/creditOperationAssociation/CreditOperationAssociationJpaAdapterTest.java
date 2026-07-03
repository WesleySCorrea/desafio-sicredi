package com.desafio_sicredi.adapters.out.creditOperationAssociation;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.desafio_sicredi.domain.model.CreditOperationAssociation;
import com.desafio_sicredi.adapters.out.persistence.creditOperationAssociation.CreditOperationAssociationEntity;
import com.desafio_sicredi.adapters.out.persistence.creditOperationAssociation.CreditOperationAssociationJpaAdapter;
import com.desafio_sicredi.adapters.out.persistence.creditOperationAssociation.CreditOperationAssociationJpaRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreditOperationAssociationJpaAdapterTest {

    @Mock
    private CreditOperationAssociationJpaRepository jpaRepository;

    @InjectMocks
    private CreditOperationAssociationJpaAdapter adapter;

    @Test
    void deveSalvarEntityViaAdapter() {

        // GIVEN
        CreditOperationAssociation domain =
                new CreditOperationAssociation(100L, 200L);

        // WHEN
        adapter.save(domain);

        // THEN
        ArgumentCaptor<CreditOperationAssociationEntity> captor =
                ArgumentCaptor.forClass(CreditOperationAssociationEntity.class);

        verify(jpaRepository).save(captor.capture());

        CreditOperationAssociationEntity entity = captor.getValue();
    }
}
