package app.cashflow.api.dre;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/dre")
@RequiredArgsConstructor
public class DreController {
    private final DreService dreService;

    @PostMapping
    public Dre create(@RequestBody DreDTO dreDTO) {
        return dreService.create(dreDTO.startDate(), dreDTO.endDate());
    }
}
