package com.bychkou.collection_box_manager.validator;

import com.bychkou.collection_box_manager.dto.FundraisingEventRequestDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class FundraisingEventValidator {

	private static final Set<String> ALLOWABLE_CURRENCIES = Set.of("USD", "EUR", "PLN");

	public void validate(FundraisingEventRequestDto fundraisingEventRequestDto) {
		if (fundraisingEventRequestDto.getName() == null) {
			throw new IllegalArgumentException("Name is required");
		}

		if (fundraisingEventRequestDto.getName().isBlank()) {
			throw new IllegalArgumentException("Name cannot be blank");
		}

		if (fundraisingEventRequestDto.getCurrency() == null) {
			throw new IllegalArgumentException("Currency is required");
		}

		String currency = fundraisingEventRequestDto.getCurrency().toUpperCase();
		if (!ALLOWABLE_CURRENCIES.contains(currency)) {
			throw new IllegalArgumentException("Unsupported currency " + currency);
		}
	}
}