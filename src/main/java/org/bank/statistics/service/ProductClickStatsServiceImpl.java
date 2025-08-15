package org.bank.statistics.service;

import java.util.Map;

import org.bank.statistics.mapper.ProductClickStatsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductClickStatsServiceImpl implements ProductClickStatsService {
	private final ProductClickStatsMapper mapper;

	/**
	 * 상품 클릭 통계를 DB에 저장하는 메서드
	 * @param clickStats
	 */
	@Override
	@Transactional
	public void accumulateClickStats(Map<String, Long> clickStats) {
		clickStats.forEach((finPrdtCd, count) -> {
			mapper.upsertClickStats(finPrdtCd, count);
		});
	}
}
