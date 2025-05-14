package com.bychkou.collection_box_manager.service;

import com.bychkou.collection_box_manager.dto.AddMoneyRequestDto;
import com.bychkou.collection_box_manager.dto.CollectionBoxResponseDto;
import com.bychkou.collection_box_manager.mapper.CollectionBoxMapper;
import com.bychkou.collection_box_manager.model.CollectionBox;
import com.bychkou.collection_box_manager.model.CollectionBoxTransaction;
import com.bychkou.collection_box_manager.model.FundraisingEvent;
import com.bychkou.collection_box_manager.repository.CollectionBoxRepository;
import com.bychkou.collection_box_manager.repository.CollectionBoxTransactionRepository;
import com.bychkou.collection_box_manager.repository.FundraisingEventRepository;
import com.bychkou.collection_box_manager.utility.CurrencyConverter;
import com.bychkou.collection_box_manager.validator.AddMoneyValidator;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CollectionBoxServiceImpl implements CollectionBoxService {

	private final CollectionBoxRepository boxRepository;
	private final FundraisingEventRepository eventRepository;
	private final CollectionBoxTransactionRepository transactionRepository;
	private final CurrencyConverter currencyConverter;
	private final AddMoneyValidator addMoneyValidator;

	@Override
	@Transactional
	public CollectionBoxResponseDto registerNewBox() {
		CollectionBox box = CollectionBox.builder()
				.assigned(false)
				.empty(true)
				.build();
		return CollectionBoxMapper.entityToDto(boxRepository.save(box));
	}

	@Override
	@Transactional(readOnly = true)
	public List<CollectionBoxResponseDto> getAllBoxes() {
		return boxRepository.findAll().stream()
				.map(CollectionBoxMapper::entityToDto)
				.toList();
	}

	@Override
	@Transactional
	public void unregisterBox(Long boxId) {
		CollectionBox box = boxRepository.findById(boxId)
				.orElseThrow(() -> new NoSuchElementException("Box not found"));
		box.setFundraisingEvent(null);
		box.setAssigned(false);
		box.setEmpty(true);
		transactionRepository.deleteAll(box.getTransactions());
		box.getTransactions().clear();
		boxRepository.delete(box);
	}

	@Override
	@Transactional
	public void assignBox(Long boxId, Long eventId) {
		CollectionBox box= boxRepository.findById(boxId)
				.orElseThrow(() -> new NoSuchElementException("Box not found"));

		if (!box.isEmpty()) {
			throw new IllegalArgumentException("Box must be empty before assignment");
		}

		FundraisingEvent event = eventRepository.findById(eventId)
				.orElseThrow(() -> new NoSuchElementException("Event not found"));

		box.setFundraisingEvent(event);
		box.setAssigned(true);
		boxRepository.save(box);
	}

	@Override
	@Transactional
	public void addMoney(Long boxId, AddMoneyRequestDto addMoneyRequestDto) {
		addMoneyValidator.validate(addMoneyRequestDto);

		CollectionBox box= boxRepository.findById(boxId)
				.orElseThrow(() -> new NoSuchElementException("Box not found"));

		CollectionBoxTransaction transaction = CollectionBoxTransaction.builder()
				.collectionBox(box)
				.currency(addMoneyRequestDto.getCurrency())
				.amount(addMoneyRequestDto.getAmount())
				.build();

		transactionRepository.save(transaction);

		box.setEmpty(false);
		boxRepository.save(box);
	}

	@Override
	@Transactional
	public void emptyBox(Long boxId) {
		CollectionBox box= boxRepository.findById(boxId)
				.orElseThrow(() -> new NoSuchElementException("Box not found"));

		if (!box.isAssigned()) {
			throw new IllegalArgumentException("Box is not assigned");
		}

		FundraisingEvent event = box.getFundraisingEvent();

		List<CollectionBoxTransaction> transactions= box.getTransactions();

		for (CollectionBoxTransaction t : transactions) {

			BigDecimal converted = currencyConverter.convert(
					t.getCurrency(),
					event.getCurrency(),
					t.getAmount()
			);

			event.setAccountBalance(event.getAccountBalance().add(converted));
		}

		transactionRepository.deleteAll(transactions);
		box.getTransactions().clear();

		box.setEmpty(true);
		boxRepository.save(box);
	}
}