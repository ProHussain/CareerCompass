package com.hashmac.careercompass.beans.user;

public class Education {
    private String degree;
    private String subject;
    private String passingYear;
    private String cgpa;
    private String favSubjects;
    private String institute;

    public Education() {
    }

    public Education(String degree, String subject, String passingYear, String cgpa, String favSubjects, String institute) {
        this.degree = degree;
        this.subject = subject;
        this.passingYear = passingYear;
        this.cgpa = cgpa;
        this.favSubjects = favSubjects;
        this.institute = institute;
    }

    public String getDegree() {
        return degree;
    }

    public String getSubject() {
        return subject;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public String getCgpa() {
        return cgpa;
    }

    public String getFavSubjects() {
        return favSubjects;
    }

    public String getInstitute() {
        return institute;
    }
}