package com.edu.ssafy.search.util;

public class WordShapeSimilarityAnalyzer {
    public String s1;
    public String s2;
    public final String[] chosungs;
    public final String[] jungsungs;
    public final String[] jongsungs;
    public final String[] jaums;
    public final String[] moums;

    public WordShapeSimilarityAnalyzer() {
        this.chosungs = new String[]{"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ" , "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
        this.jungsungs = new String[]{"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
        this.jongsungs = new String[]{"", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
        this.jaums = new String[]{"ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄸ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅃ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
        this.moums = new String[]{"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
    }

    public WordShapeSimilarityAnalyzer setBase(String s1, String s2) {
        String refinedS1 = s1.trim().replaceAll(" ", "");
        String refinedS2 = s2.trim().replaceAll(" ", "");

        if(refinedS1.length() <= refinedS2.length()) {
            this.s1 = refinedS1;
            this.s2 = refinedS2;
        } else {
            this.s1 = refinedS2;
            this.s2 = refinedS1;
        }

        return this;
    }

    public double analyze() {
        double[][] editDistance = new double[s1.length() + 1][s2.length() + 1];

        for(int row=1;row<s1.length() + 1;row++) {
            editDistance[row][0] = row;
        }

        for(int col=1;col<s2.length() + 1;col++) {
            editDistance[0][col] = col;
        }

        for(int row = 1; row < s1.length() + 1; row++) {
            for (int col = 1; col < s2.length() + 1; col++) {
                // Skip
                if(s1.charAt(row-1) == s2.charAt(col-1)) {
                    editDistance[row][col] = editDistance[row-1][col-1];
                }
                // Modify
                else {
                    editDistance[row][col] = cutting(editDistance[row-1][col-1] + customModifyCost(s1.charAt(row-1), s2.charAt(col-1)));
                }
                // Insert
                if(editDistance[row][col-1] + 1 < editDistance[row][col]) {
                    editDistance[row][col] = editDistance[row][col-1] + 1;
                }
                // Delete
                if(editDistance[row - 1][col] + 1 < editDistance[row][col]) {
                    editDistance[row][col] = editDistance[row - 1][col] + 1;
                }
            }
        }

        return cutting(editDistance[s1.length()][s2.length()] / s2.length());
    }

    private double customModifyCost(char s1, char s2) {
        int s1Base = s1 - 44032;
        char s1Chosung = (char)(s1Base / 28 / 21);
        char s1Jungsung = (char)(s1Base / 28 % 21);
        char s1Jongsung = (char)(s1Base % 28);

        int s2Base = s2 - 44032;
        char s2Chosung = (char)(s2Base / 28 / 21);
        char s2Jungsung = (char)(s2Base / 28 % 21);
        char s2Jongsung = (char)(s2Base % 28);

        double cost = 0;
        if(s1Chosung != s2Chosung) cost += 0.4;
        if(s1Jungsung != s2Jungsung) cost += 0.3;
        if(s1Jongsung != s2Jongsung) cost += 0.3;

        return cutting(cost);
    }

    private double cutting(double value) {
        return Double.parseDouble(String.format("%.2f", value));
    }
}
