package org.bank.statistics.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductClickStatsMapper {

	// 상품별 클릭 수 누적 업데이트 (없으면 insert)
	void upsertClickStats(@Param("finPrdtCd") String finPrdtCd, @Param("clickCount") long clickCount);

}
