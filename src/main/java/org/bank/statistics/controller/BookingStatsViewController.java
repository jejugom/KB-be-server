package org.bank.statistics.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.bank.statistics.dto.BookingStatsDto;
import org.bank.statistics.service.BookingStatsViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stats")
public class BookingStatsViewController {

	private final BookingStatsViewService bookingStatsViewService;

	/**
	 * 가장 많이 예약된 지점 5곳 조회하는 메서드
	 * @param limit
	 * @param model
	 * @return
	 */
	@GetMapping("/top-branch")
	public String viewTopBookings(@RequestParam(defaultValue = "5") int limit, Model model) {
		// limit 값에 맞춰 조회
		List<BookingStatsDto> topList = bookingStatsViewService.getTopBookingStats(limit);

		// 현재 날짜 기준 전월 구하기
		LocalDate now = LocalDate.now().minusMonths(1);
		String lastMonth = now.format(DateTimeFormatter.ofPattern("yyyy년 M월"));

		model.addAttribute("topStats", topList);
		model.addAttribute("limit", limit);
		model.addAttribute("lastMonth", lastMonth);

		return "bookingStatistics";
	}
}
