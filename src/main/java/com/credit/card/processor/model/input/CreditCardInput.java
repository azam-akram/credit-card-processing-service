package com.credit.card.processor.model.input;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardInput {
    @NotBlank
    private String name;

    @NotBlank
    private String cardNumber;

    @NotNull
    private Integer accountLimit;
}
