package org.bank.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 예약 지점 통계를 위한 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingStatsDto {

	private Long branchId;
	private Long bookingCount;

	private String branchName;
}
