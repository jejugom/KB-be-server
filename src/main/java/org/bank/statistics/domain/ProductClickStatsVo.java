package org.bank.statistics.domain;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 클릭 집계 관련 VO(Value Object) 클래스
 */
@ApiModel(value = "클릭 집계 VO", description = "DB의 product_click_stats 테이블과 매핑되는 핵심 객체")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductClickStatsVo {
	private Long id;
	private String finPrdtCd;
	private Long clickCount;
	private Timestamp lastUpdated;
}
