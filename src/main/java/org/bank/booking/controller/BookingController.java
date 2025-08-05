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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = "예약 API", description = "예약 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService bookingService;

	@ApiOperation(value = "신규 예약 생성", notes = "새로운 상담 예약을 생성합니다.")
	@ApiResponses({
		@ApiResponse(code = 201, message = "예약 성공"),
		@ApiResponse(code = 400, message = "잘못된 요청 데이터 (유효하지 않은 날짜 등)"),
		@ApiResponse(code = 409, message = "이미 예약된 시간이거나 중복된 예약")
	})
	@PostMapping
	public ResponseEntity<Void> addBooking(@RequestBody BankBookingRequestDto requestDto) {
		bookingService.addBooking(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/test")
	public String welcome() { return "welcome"; }
}
