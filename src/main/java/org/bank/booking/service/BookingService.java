package org.bank.booking.service;

import org.bank.booking.dto.BankBookingRequestDto;

/**
 * 예약 관련 비즈니스 로직을 처리하는 서비스 인터페이스
 */
public interface BookingService {

	/**
	 * client be-server에서 받은 예약 정보 저장
	 * @param requestDto
	 */
	void addBooking(BankBookingRequestDto requestDto);
}
