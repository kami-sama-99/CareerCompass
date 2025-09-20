package com.careercompass.user.model;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationPreferencesDto {
    private List<String> preferredStates;
    private List<String> preferredCities;
    private List<String> extraCurricularPreferences;
    private List<String> personalPreferences;
    private BudgetRangeDto budgetRange;
    private boolean scholarshipInterest;
    private String campusTypePreference;
}
