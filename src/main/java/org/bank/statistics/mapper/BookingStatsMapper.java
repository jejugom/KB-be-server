package org.bank.statistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bank.statistics.dto.BookingStatsDto;

@Mapper
public interface BookingStatsMapper {

	BookingStatsDto findByBranchId(Long branchId);

	void insertBookingStats(BookingStatsDto dto);

	void updateBookingStats(BookingStatsDto dto);

	List<BookingStatsDto> findTopBookingStats(int limit);
}
