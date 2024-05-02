package com.typotest.codecoverage.service.utils;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static Double getGrowthRate(Double averageScore,
                                       Double lastMonthScore) {
        if (lastMonthScore == null || lastMonthScore == 0 || averageScore == null) {
            return null;
        }
        Double growthRate =
                ((averageScore - lastMonthScore) * 100) / lastMonthScore;
        return Math.abs(growthRate);
    }

    public static Double getGrowthValue(Double averageScore,
                                        Double lastMonthScore) {
        if (lastMonthScore == null || lastMonthScore == 0 || averageScore == null) {
            return null;
        }
        return Math.abs(decimalUptoTwoDigit(averageScore) - decimalUptoTwoDigit(lastMonthScore));
    }

    public static Double decimalUptoTwoDigit(Double number) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return Double.parseDouble(numberFormat.format(number));
    }

    public static String getMood(Double overallEngagementScore) {
        if (overallEngagementScore >= 0 && overallEngagementScore < 4) {
            return MOOD.VERY_SAD.getMood();
        } else if (overallEngagementScore >= 4 && overallEngagementScore < 5) {
            return MOOD.SAD.getMood();
        } else if (overallEngagementScore >= 5 && overallEngagementScore < 7) {
            return MOOD.NEUTRAL.getMood();
        } else if (overallEngagementScore >= 7 && overallEngagementScore < 9) {
            return MOOD.HAPPY.getMood();
        } else {
            return MOOD.VERY_HAPPY.getMood();
        }
    }

    public static Double getEngagementPercentage(String mood, Double overAllEngagementScore,
                                                 Integer verySadCount, Integer sadCount, Integer neutralCount, Integer happyCount,
                                                 Integer veryHappyCount, Integer maxTeamSize) {
        if (MOOD.VERY_SAD.getMood().equals(mood)) {
            return (verySadCount + sadCount) * 100.0 / maxTeamSize;
        } else if (MOOD.SAD.getMood().equals(mood)) {
            return (verySadCount + sadCount) * 100.0 / maxTeamSize;
        } else if (MOOD.NEUTRAL.getMood().equals(mood)) {
            return (neutralCount + sadCount) * 100.0 / maxTeamSize;
        } else if (MOOD.HAPPY.getMood().equals(mood)) {
            return (veryHappyCount + happyCount) * 100.0 / maxTeamSize;
        } else {
            return (veryHappyCount + happyCount) * 100.0 / maxTeamSize;
        }
    }

    public static String getCommentsForEngagementScore(Double engagementScore) {
        if (engagementScore >= 9) {
            return "Awesome! Let's keep up the good work.";
        } else if (engagementScore >= 7 && engagementScore < 9) {
            return "Good! We are going the right way.";
        } else if (engagementScore >= 5 && engagementScore < 7) {
            return "Average! Let's work to make this better.";
        } else {
            return "Bad! This needs immediate attention.";
        }
    }

    public static String getCommentsForResponseRate(Double responseRate) {
        if (responseRate >= 80) {
            return "Great! Most People are sharing their thoughts.";
        } else if (responseRate >= 50 && responseRate < 80) {
            return "Good! People are responding well but we can do better.";
        } else if (responseRate >= 30 && responseRate < 50) {
            return "Low! This needs immediate attention.";
        } else {
            return "Poor! Let's find out why people are not responding.";
        }
    }

    public static Double getScore(Integer promotersCount, Integer detractorsCount,
                                  Integer categorySize) {
        Double score = null;
        if (categorySize == null || categorySize == 0 || promotersCount == null) {
            return score;
        }
        if (promotersCount > detractorsCount) {
            score = (5.0) + (((promotersCount - detractorsCount) * 1.0 / categorySize) * 5.0);
        } else if (detractorsCount > promotersCount) {
            score = (5.0) - ((detractorsCount - promotersCount) * 1.0 / categorySize * 5.0);
        } else {
            score = 5.0;
        }
        return score;
    }

    public static void deleteFile(File file) {
        file.delete();
    }

    enum MOOD {
        VERY_SAD("Very Sad"),
        SAD("Sad"),
        NEUTRAL("Neutral"),
        HAPPY("Happy"),
        VERY_HAPPY("Very Happy");

        String mood;

        MOOD(String mood) {
            this.mood = mood;
        }

        public String getMood() {
            return mood;
        }
    }

    public static File writeToFile(InputStream inputStream, String filePath) {
        File targetFile = new File(filePath);
        try {
            Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return targetFile;
    }

    public static List<String> roleList() {
        return Arrays.asList("Manager", "Manager of Managers", "Individual Contributor");
    }

    public static Map<String, String> roleMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Manager", "LineManager");
        map.put("Manager of Managers", "SuperManager");
        map.put("Individual Contributor", "IC");
        return map;
    }

    public static List<String> notifyTeammatesList() {
        return Arrays.asList("Send", "No", "Help");
    }

    public static List<String> sendCheckInList() {
        return Arrays.asList("Send first check-in", "Set up Kudos", "Set up Watercooler",
                "Go to App Home");
    }

    public static String getCommaSeparatedString(List<String> stringList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            builder.append(stringList.get(i));
            if (i < stringList.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public static String getStringWithSpaceAndAtTheRate(List<String> stringList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            builder.append("@").append(stringList.get(i));
            if (i < stringList.size() - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public static String geSpaceSeparatedString(List<String> stringList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            builder.append(stringList.get(i));
            if (i < stringList.size() - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public static String getCommaSeparatedStringFromIntegerList(List<Integer> integerList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < integerList.size(); i++) {
            builder.append(integerList.get(i));
            if (i < integerList.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public static List<Integer> mergeList(List<Integer> list1, List<Integer> list2) {
        list1.addAll(list2);
        return list1;
    }
}
