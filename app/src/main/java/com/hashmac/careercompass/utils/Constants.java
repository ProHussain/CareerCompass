package com.hashmac.careercompass.utils;

import com.hashmac.careercompass.beans.user.User;

public class Constants {

    public static String finalWords = "Based on this comprehensive profile and the user's responses, could you predict three career paths that best match their profile? Please consider their interests, skills, strengths, aspirations, preferred work environment, and long-term career goals.";


    public static String getStartPrompt(User user) {
        return "Given the user's provided information and responses to the following questions:\n" +
                "\n" +
                "Personal Information:\n" +
                "\n" +
                "Name: "+ user.getAuth().getName() +"\n" +
                "Email: "+ user.getAuth().getEmail() +"\n" +
                "Phone: "+ user.getAuth().getPhone() +"\n" +
                "Educational Background:\n" +
                "\n" +
                "Degree Level: "+ user.getEducation().getDegree() +"\n" +
                "Major Subject: "+ user.getEducation().getSubject() +"\n" +
                "Passing Year: "+ user.getEducation().getPassingYear() +"\n" +
                "College Name: "+ user.getEducation().getInstitute() +"\n" +
                "CGPA: "+ user.getEducation().getCgpa() +"\n" +
                "Favorite Subjects: "+ user.getEducation().getFavSubjects() +"\n" +
                "Short Bio: "+ user.getExperience().getDescription() +"\n" +
                "\n" +
                "Additional Questions:\n";
    }
}
