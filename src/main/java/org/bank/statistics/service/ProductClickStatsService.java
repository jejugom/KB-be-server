package org.bank.statistics.service;

import java.util.Map;

public interface ProductClickStatsService {
	void accumulateClickStats(Map<String, Long> clickStats);
}
