package com.careercompass.user.model;

import lombok.Data;

@Data
public class BudgetRangeDto {
    private Integer min;
    private Integer max;
    private String currency;
}
