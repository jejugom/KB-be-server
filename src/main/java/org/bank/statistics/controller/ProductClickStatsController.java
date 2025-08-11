package org.bank.statistics.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bank.statistics.dto.BookingStatsDto;
import org.bank.statistics.dto.ProductClickStatsDto;
import org.bank.statistics.service.BookingStatsService;
import org.bank.statistics.service.ProductClickStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductClickStatsController {

	private final ProductClickStatsService productClickStatsService;
	private final BookingStatsService bookingStatsService;

	@PostMapping("/click-stats")
	public ResponseEntity<Void> receiveClickStats(@RequestBody List<ProductClickStatsDto> statsList) {

		// Map으로 변환 (finPrdtCd -> clickCount)
		Map<String, Long> statsMap = statsList.stream()
			.collect(Collectors.toMap(
				ProductClickStatsDto::getFinPrdtCd,
				ProductClickStatsDto::getClickCount,
				Long::sum
			));

		productClickStatsService.accumulateClickStats(statsMap);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/booking-stats")
	public ResponseEntity<Void> receiveBookingStats(@RequestBody List<BookingStatsDto> statsList) {
		Map<Long, Long> statsMap = statsList.stream()
			.collect(Collectors.toMap(BookingStatsDto::getBranchId, BookingStatsDto::getBookingCount, Long::sum));

		bookingStatsService.accumulateBookingStats(statsMap);

		return ResponseEntity.ok().build();
	}
}
