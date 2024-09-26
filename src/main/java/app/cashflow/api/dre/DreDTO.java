package app.cashflow.api.dre;

import java.time.LocalDateTime;

public record DreDTO(LocalDateTime startDate, LocalDateTime endDate) {
}
