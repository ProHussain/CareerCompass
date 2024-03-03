package com.hashmac.careercompass.beans.user;

public class User {
    private Auth auth;
    private Education education;
    private Experience experience;

    public User() {
    }

    public User(Auth auth, Education education, Experience experience) {
        this.auth = auth;
        this.education = education;
        this.experience = experience;
    }

    public Auth getAuth() {
        return auth;
    }

    public Education getEducation() {
        return education;
    }

    public Experience getExperience() {
        return experience;
    }

}
