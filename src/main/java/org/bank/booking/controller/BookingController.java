package org.bank.booking.controller;

import org.bank.booking.dto.BankBookingRequestDto;
import org.bank.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService bookingService;

	/**
	 * 사용자 서버에서 전달해준 예약 내역 은행 서버에 저장
	 * @param requestDto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Void> addBooking(@RequestBody BankBookingRequestDto requestDto) {
		bookingService.addBooking(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
