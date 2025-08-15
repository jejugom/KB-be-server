package org.bank.statistics.service;

import java.util.List;

import org.bank.statistics.dto.BookingStatsDto;
import org.bank.statistics.mapper.BookingStatsMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingStatsViewService {

	private final BookingStatsMapper bookingStatsMapper;

	/**
	 * 예약 건수 기준 상위 지점 조회
	 * @param limit
	 * @return
	 */
	public List<BookingStatsDto> getTopBookingStats(int limit) {
		return bookingStatsMapper.findTopBookingStats(limit);
	}
}
