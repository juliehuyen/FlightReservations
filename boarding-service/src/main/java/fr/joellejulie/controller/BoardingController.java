package fr.joellejulie.controller;

import fr.joellejulie.dto.BoardingDto;
import fr.joellejulie.entity.Boarding;
import fr.joellejulie.service.BoardingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/boardings")
@AllArgsConstructor
public class BoardingController {

    private final BoardingService boardingService;

    @PostMapping
    public ResponseEntity<BoardingDto> performBoarding(@RequestBody BoardingDto req) {
        Boarding saved = boardingService.performBoarding(req);
        return ResponseEntity.ok(BoardingDto.mapToDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<BoardingDto>> getAllBoardings() {
        List<Boarding> list = boardingService.getAllBoardings();
        List<BoardingDto> dtos = list.stream()
                .map(BoardingDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
