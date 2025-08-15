package org.bank.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상담 예약하기 클릭 통계 관련 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductClickStatsDto {
	private String finPrdtCd;
	private Long clickCount;
}
