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

	@Override
	public void accumulateBookingStats(Map<Long, Long> statsMap) {
		for (Map.Entry<Long, Long> entry : statsMap.entrySet()) {
			Long branchId = entry.getKey();
			Long bookingCount = entry.getValue();

			log.info("Processing branchId={}, bookingCount={}", branchId, bookingCount);

			BookingStatsDto existing = bookingStatsMapper.findByBranchId(branchId);
			if (existing == null) {
				log.info("Inserting new booking stats for branchId={}", branchId);
				BookingStatsDto dto = new BookingStatsDto();
				dto.setBranchId(branchId);
				dto.setBookingCount(bookingCount);
				bookingStatsMapper.insertBookingStats(dto);
			} else {
				log.info("Updating booking stats for branchId={} from {} to {}",
					branchId, existing.getBookingCount(), bookingCount);
				existing.setBookingCount(bookingCount);
				bookingStatsMapper.updateBookingStats(existing);
			}
		}
	}

	@Override
	public List<BookingStatsDto> getTopBookingStats(int limit) {
		log.info("Fetching top {} booking stats", limit);
		return bookingStatsMapper.findTopBookingStats(limit);
	}
}
