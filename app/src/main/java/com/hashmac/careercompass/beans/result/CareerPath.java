package com.hashmac.careercompass.beans.result;

import java.util.List;

public class CareerPath {
    private String career_path_name;
    private List<String> matching_skills;
    private List<String> matching_interests;
    private List<String> matching_strengths;
    private List<String> matching_aspirations;
    private String matching_work_environment;
    private int fit_percentage;

    public String getCareer_path_name() {
        return career_path_name;
    }

    public void setCareer_path_name(String career_path_name) {
        this.career_path_name = career_path_name;
    }

    public List<String> getMatching_skills() {
        return matching_skills;
    }

    public void setMatching_skills(List<String> matching_skills) {
        this.matching_skills = matching_skills;
    }

    public List<String> getMatching_interests() {
        return matching_interests;
    }

    public void setMatching_interests(List<String> matching_interests) {
        this.matching_interests = matching_interests;
    }

    public List<String> getMatching_strengths() {
        return matching_strengths;
    }

    public void setMatching_strengths(List<String> matching_strengths) {
        this.matching_strengths = matching_strengths;
    }

    public List<String> getMatching_aspirations() {
        return matching_aspirations;
    }

    public void setMatching_aspirations(List<String> matching_aspirations) {
        this.matching_aspirations = matching_aspirations;
    }

    public String getMatching_work_environment() {
        return matching_work_environment;
    }

    public void setMatching_work_environment(String matching_work_environment) {
        this.matching_work_environment = matching_work_environment;
    }

    public int getFit_percentage() {
        return fit_percentage;
    }

    public void setFit_percentage(int fit_percentage) {
        this.fit_percentage = fit_percentage;
    }
}
