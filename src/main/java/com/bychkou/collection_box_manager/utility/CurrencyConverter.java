package com.bychkou.collection_box_manager.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CurrencyConverter {

	private static final Map<String, BigDecimal> defaultRates = new HashMap<>();

	static {
		defaultRates.put("USD_EUR", BigDecimal.valueOf(0.92));
		defaultRates.put("EUR_USD", BigDecimal.valueOf(1.09));
		defaultRates.put("USD_PLN", BigDecimal.valueOf(4.10));
		defaultRates.put("PLN_USD", BigDecimal.valueOf(0.24));
		defaultRates.put("EUR_PLN", BigDecimal.valueOf(4.45));
		defaultRates.put("PLN_EUR", BigDecimal.valueOf(0.22));
	}

	public BigDecimal convert(String from, String to, BigDecimal amount) {
		if (from.equals(to)) {
			return amount;
		}

		try {
			String url= String.format("https://api.frankfurter.app/latest?amount=%s&from=%s&to=%s",
					amount, from, to);

			RestTemplate restTemplate= new RestTemplate();
			Map<String, Object> response= restTemplate.getForObject(url, Map.class);

			if (response != null) {
				if (response.containsKey("rates")) {

					Map<String, Object> rates= (Map<String, Object>) response.get("rates");
					if (rates.containsKey(to)) {
						double convertedAmount = ((Number) rates.get(to)).doubleValue();

						 return BigDecimal.valueOf(convertedAmount);
					} else {
						log.warn("Currency rates map does not have target currency: " + to);
					}
				} else {

					log.warn("Response does not have the appropriate rate");
				}
			} else {
				log.warn("Currency API response is null");
			}
		} catch (Exception e) {
			log.warn("API is not working: " + e.getMessage());
		}

		BigDecimal rate= defaultRates.getOrDefault(from + "->" + to, BigDecimal.ONE);
		return amount.multiply(rate);
	}
}
