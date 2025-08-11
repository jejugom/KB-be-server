package org.bank.statistics.service;

import java.util.Map;

public interface BookingStatsService {

	void accumulateBookingStats(Map<Long, Long> statsMap);
}
