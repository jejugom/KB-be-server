package org.bank.booking.service;


import org.bank.booking.domain.BookingVo;
import org.bank.booking.dto.BankBookingRequestDto;
import org.bank.booking.mapper.BookingMapper;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final BookingMapper bookingMapper;

	@Override
	public void addBooking(BankBookingRequestDto dto) {
		BookingVo bookingVo = BookingVo.builder()
			.bookingId(dto.getBookingId())
			.email(dto.getEmail())
			.branchId(dto.getBranchId())
			.finPrdtCode(dto.getFinPrdtCode())
			.date(dto.getDate())
			.time(dto.getTime())
			.docInfo(dto.getDocInfo())
			.build();

		bookingMapper.insertBooking(bookingVo);
	}
}
