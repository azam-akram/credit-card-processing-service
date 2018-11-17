package com.credit.card.processor.service;

import com.credit.card.processor.model.entity.CreditCardEntity;
import com.credit.card.processor.model.input.CreditCardInput;
import com.credit.card.processor.model.output.CreditCardOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.credit.card.processor.repository.CreditCardRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository repository) {
        this.creditCardRepository = repository;
    }

    @Transactional
    public CreditCardOutput saveNewCreditCard(final CreditCardInput creditCardInput) {
        CreditCardEntity entity = CreditCardEntity.builder()
                .name(creditCardInput.getName())
                .cardNumber(creditCardInput.getCardNumber())
                .accountLimit(creditCardInput.getAccountLimit())
                .build();

        return EntityToOutputConverter.convertCreditCardsEntityToOutput(creditCardRepository.save(entity));
    }

    public List<CreditCardOutput> getAllCardRecords() {
        return EntityToOutputConverter.convertCreditCardsEntitiesToOutput(creditCardRepository.findAll());
    }
}
