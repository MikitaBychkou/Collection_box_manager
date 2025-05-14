package com.bychkou.collection_box_manager.validator;


import com.bychkou.collection_box_manager.dto.AddMoneyRequestDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class AddMoneyValidator {

	private static final Set<String> ALLOWABLE_CURRENCIES = Set.of("USD", "EUR", "PLN");

	public void validate(AddMoneyRequestDto addMoneyRequestDto) {
		if (addMoneyRequestDto.getAmount() == null) {
			throw new IllegalArgumentException("Amount is required");
		}

		if (addMoneyRequestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Amount must be greater than zero");
		}

		if (addMoneyRequestDto.getCurrency() == null) {
			throw new IllegalArgumentException("Currency is required");
		}

		String currency = addMoneyRequestDto.getCurrency().toUpperCase();
		if (!ALLOWABLE_CURRENCIES.contains(currency)) {
			throw new IllegalArgumentException("Unsupported currency " + currency);
		}
	}
}
