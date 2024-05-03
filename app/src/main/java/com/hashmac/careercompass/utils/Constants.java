package com.hashmac.careercompass.utils;

import com.hashmac.careercompass.beans.user.User;

public class Constants {

    public static String finalWords = "Based on this comprehensive profile and the user's responses, could you predict three career paths that best match their profile? Please consider their interests, skills, strengths, aspirations, preferred work environment, and long-term career goals.";

    public static String exampleJSON = "Remember Your response should be in JSON format like following example  " +
            "{\n" +
            "  \"career_paths\": [\n" +
            "    {\n" +
            "      \"career_path_name\": \"Android App Developer\",\n" +
            "      \"matching_skills\": [\n" +
            "        \"Java\",\n" +
            "        \"Kotlin\",\n" +
            "        \"MVVM\",\n" +
            "        \"Dependency Injections\"\n" +
            "      ],\n" +
            "      \"matching_interests\": [\n" +
            "        \"Mobile app development\"\n" +
            "      ],\n" +
            "      \"matching_strengths\": [\n" +
            "        \"3 years of experience with Android app development\"\n" +
            "      ],\n" +
            "      \"matching_aspirations\": [\n" +
            "        \"Long-term career goals include becoming a lead Android app developer\"\n" +
            "      ],\n" +
            "      \"matching_work_environment\": \"Collaborative and fast-paced environment where innovation is encouraged\",\n" +
            "      \"fit_percentage\": 90\n" +
            "    },\n" +
            "    {\n" +
            "      \"career_path_name\": \"Software Engineer\",\n" +
            "      \"matching_skills\": [\n" +
            "        \"Java\",\n" +
            "        \"Kotlin\",\n" +
            "        \"Data Structures\",\n" +
            "        \"OOP\"\n" +
            "      ],\n" +
            "      \"matching_interests\": [\n" +
            "        \"Problem-solving\",\n" +
            "        \"Building scalable software solutions\"\n" +
            "      ],\n" +
            "      \"matching_strengths\": [\n" +
            "        \"Strong academic record (CGPA: 3.77)\"\n" +
            "      ],\n" +
            "      \"matching_aspirations\": [\n" +
            "        \"Long-term career goals include becoming a senior software engineer or architect\"\n" +
            "      ],\n" +
            "      \"matching_work_environment\": \"Challenging and intellectually stimulating environment where continuous learning is valued\",\n" +
            "      \"fit_percentage\": 80\n" +
            "    },\n" +
            "    {\n" +
            "      \"career_path_name\": \"Data Scientist\",\n" +
            "      \"matching_skills\": [\n" +
            "        \"Data Structures\",\n" +
            "        \"Problem-solving\"\n" +
            "      ],\n" +
            "      \"matching_interests\": [\n" +
            "        \"Analyzing data to identify trends and patterns\"\n" +
            "      ],\n" +
            "      \"matching_strengths\": [\n" +
            "        \"Strong analytical skills\"\n" +
            "      ],\n" +
            "      \"matching_aspirations\": [\n" +
            "        \"Long-term career goals include becoming a lead data scientist\"\n" +
            "      ],\n" +
            "      \"matching_work_environment\": \"Data-driven environment where collaboration with cross-functional teams is essential\",\n" +
            "      \"fit_percentage\": 70\n" +
            "    }\n" +
            "  ]\n" +
            "}";

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

    public static String getDetailsPrompt(String title) {
        return "Provide us comprehensive information and guidance on this career path, we'll need to gather some additional details. Please find the requested information below:\n" +
                "\n" +
                "Career Title Selected: "+ title +"\n" +
                "\n" +
                "Market Salary:\n" +
                "\n" +
                "Could you provide information about the average salary range for this career in your location or desired location? Additionally, any insights on salary growth trends or factors influencing salary variations would be helpful.\n" +
                "Job Growth Ratio:\n" +
                "\n" +
                "What is the projected job growth rate or demand for professionals in this field? Any data or statistics on industry trends, job availability, and future prospects would be beneficial.\n" +
                "Skills Mastery:\n" +
                "\n" +
                "What are the key skills and competencies required to excel in this career? Please provide details on essential technical skills, soft skills, and industry-specific knowledge.\n" +
                "How can individuals master these skills effectively? Are there specific courses, certifications, training programs, or resources recommended for skill development?\n" +
                "Additionally, please include information on the time required to master these skills, associated costs (if any), and any other relevant details.\n" +
                "Career Advancement Opportunities:\n" +
                "\n" +
                "What are the typical career advancement paths or opportunities available in this field? Please outline potential career trajectories, advancement criteria, and milestones.\n" +
                "Work Environment and Lifestyle:\n" +
                "\n" +
                "Could you describe the typical work environment and lifestyle associated with this career? Any insights on work hours, work-life balance, remote work options, or travel requirements would be valuable.\n" +
                "Challenges and Considerations:\n" +
                "\n" +
                "What are some common challenges or considerations individuals may face when pursuing this career? Please include factors such as competition, industry dynamics, technological advancements, and market trends.\n" +
                "Success Stories and Advice:\n" +
                "\n" +
                "Do you have any success stories, testimonials, or insights from professionals currently working in this field? Additionally, any advice or tips for individuals aspiring to succeed in this career would be appreciated.\n" +
                "Additional Resources:\n" +
                "\n" +
                "Are there any additional resources, websites, books, or communities recommended for further exploration and learning about this career?\n" +
                "Please provide as much detail as possible to ensure we can offer comprehensive guidance and support on your chosen career path. Your input will help us tailor our recommendations and advice to your specific needs and goals.";
    }
}
