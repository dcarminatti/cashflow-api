package app.cashflow.api.dre;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Dre {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double grossRevenue;
    private double expenses;
    private double variableCosts;
    private double fixedCosts;
    private double netRevenue;
    private double financialRevenues;
    private double financialExpenses;
    private double taxes;
    private double netProfit;
}
