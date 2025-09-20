package com.careercompass.file.model;

import lombok.Data;

import java.util.List;

@Data
public class ParsedResume {
    private String name;
    private List<String> education;
    private List<String> projects;
    private List<String> experience;
    private List<String> skills;
    private List<String> socials;
    private List<String> achievements;
}
