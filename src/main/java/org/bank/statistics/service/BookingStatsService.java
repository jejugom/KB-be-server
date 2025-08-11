package org.bank.statistics.service;

import java.util.List;
import java.util.Map;

import org.bank.statistics.dto.BookingStatsDto;

public interface BookingStatsService {

	void accumulateBookingStats(Map<Long, Long> statsMap);

	List<BookingStatsDto> getTopBookingStats(int limit);
}
