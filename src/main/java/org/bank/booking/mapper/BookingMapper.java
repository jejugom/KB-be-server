package org.bank.booking.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bank.booking.domain.BookingVo;

@Mapper
public interface BookingMapper {

	/**
	 * 예약 정보 DB에 삽입
	 * @param booking 예약 VO
	 */
	void insertBooking(BookingVo booking);

}
