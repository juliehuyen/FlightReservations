package fr.joellejulie.service;

import fr.joellejulie.dto.BoardingDto;
import fr.joellejulie.entity.Boarding;

import java.util.List;

public interface BoardingService {
    Boarding performBoarding(BoardingDto boardingDto);

    List<Boarding> getAllBoardings();

}
