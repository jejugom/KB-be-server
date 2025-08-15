package org.bank.statistics.service;

import java.util.List;
import java.util.Map;

import org.bank.statistics.dto.BookingStatsDto;
import org.bank.statistics.mapper.BookingStatsMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingStatsServiceImpl implements BookingStatsService {

	private final BookingStatsMapper bookingStatsMapper;

	/**
	 * 지점별 예약 집계 데이터 누적 저장하느느 메서드
	 * @param statsMap (key:branchId/ value:bookingCount(예약건수))
	 */
	@Override
	public void accumulateBookingStats(Map<Long, Long> statsMap) {
		for (Map.Entry<Long, Long> entry : statsMap.entrySet()) {
			Long branchId = entry.getKey();
			Long bookingCount = entry.getValue();

			// 해당 지점의 기존 통계 데이터를 조회
			BookingStatsDto existing = bookingStatsMapper.findByBranchId(branchId);
			
			if (existing == null) { // 없으면 insert
				BookingStatsDto dto = new BookingStatsDto();
				dto.setBranchId(branchId);
				dto.setBookingCount(bookingCount);
				bookingStatsMapper.insertBookingStats(dto);
			} else { // 있으면 update
				existing.setBookingCount(bookingCount);
				bookingStatsMapper.updateBookingStats(existing);
			}
		}
	}

	/**
	 * 예약 건수가 많은 지점 순으로 상위 N개의 지점 조회
	 * @param limit
	 * @return
	 */
	@Override
	public List<BookingStatsDto> getTopBookingStats(int limit) {
		return bookingStatsMapper.findTopBookingStats(limit);
	}
}
