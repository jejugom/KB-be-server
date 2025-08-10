package org.bank.statistics.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bank.statistics.dto.ProductClickStatsDto;
import org.bank.statistics.service.ProductClickStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Api(tags = "상품 예약 클릭 로그 집계 API", description = "상품 예약 클릭 로그 집계 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/click-stats")
public class ProductClickStatsController {

	private final ProductClickStatsService productClickStatsService;

	@PostMapping("")
	public ResponseEntity<Void> receiveClickStats(@RequestBody List<ProductClickStatsDto> statsList) {

		// Map으로 변환 (finPrdtCd -> clickCount)
		Map<String, Long> statsMap = statsList.stream()
			.collect(Collectors.toMap(
				ProductClickStatsDto::getFinPrdtCd,
				ProductClickStatsDto::getClickCount,
				Long::sum
			));

		productClickStatsService.accumulateClickStats(statsMap);
		return ResponseEntity.ok().build();
	}
}
