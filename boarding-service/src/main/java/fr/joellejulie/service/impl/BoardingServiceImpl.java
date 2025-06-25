package fr.joellejulie.service.impl;


import fr.joellejulie.dto.BoardingDto;
import fr.joellejulie.entity.Boarding;
import fr.joellejulie.repository.BoardingRepository;
import fr.joellejulie.service.BoardingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardingServiceImpl implements BoardingService {

    private final BoardingRepository boardingRepository;

    @Override
    public Boarding performBoarding(BoardingDto boardingDto) {
        Boarding boarding = Boarding.builder()
                .id(boardingDto.getId())
                .boardingTime(boardingDto.getBoardingTime())
                .gateNumber(boardingDto.getGateNumber())
                .seatNumber(boardingDto.getSeatNumber())
                .build();

        return boardingRepository.save(boarding);
    }

    @Override
    public List<Boarding> getAllBoardings() {
        return boardingRepository.findAll();
    }

}
