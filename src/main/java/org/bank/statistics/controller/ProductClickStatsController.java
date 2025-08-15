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

	/**
	 * 상담 예약하기 버튼 클릭시 기록되는 로그를 바탕으로 집계된 데이터를 전달받고 DB에 저장
	 * @param statsList
	 * @return
	 */
	@PostMapping("/click-stats")
	public ResponseEntity<Void> receiveClickStats(@RequestBody List<ProductClickStatsDto> statsList) {

		Map<String, Long> statsMap = statsList.stream()
			.collect(Collectors.toMap(
				ProductClickStatsDto::getFinPrdtCd,
				ProductClickStatsDto::getClickCount,
				Long::sum
			));

		productClickStatsService.accumulateClickStats(statsMap);
		return ResponseEntity.ok().build();
	}

	/**
	 * 상담 예약 통계 데이터를 저장
	 * @param statsList
	 * @return
	 */
	@PostMapping("/booking-stats")
	public ResponseEntity<Void> receiveBookingStats(@RequestBody List<BookingStatsDto> statsList) {
		Map<Long, Long> statsMap = statsList.stream()
			.collect(Collectors.toMap(BookingStatsDto::getBranchId, BookingStatsDto::getBookingCount, Long::sum));

		bookingStatsService.accumulateBookingStats(statsMap);

		return ResponseEntity.ok().build();
	}
}
