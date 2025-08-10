package org.bank.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductClickStatsDto {
	private String finPrdtCd;
	private Long clickCount;
}
