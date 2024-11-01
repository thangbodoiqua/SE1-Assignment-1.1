package a1_2201040168;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Word {
    public static Set<String> stopWords;
    private String rawText, textPart, prefix, suffix;
    private boolean isValid;

    private Word(String rawText, String prefix, String textPart, String suffix, boolean isValid){
        this.rawText = rawText;
        this.prefix = prefix;
        this.textPart = textPart;
        this.suffix = suffix;
        this.isValid = isValid;
    }
    public static Word createWord(String rawText){
        Pattern pattern = Pattern.compile("^([(\"«<>,-]*)([a-zA-Z-']+)([-:,<>»\".st)]*)$");
        Matcher matcher = pattern.matcher(rawText);
      if (matcher.matches()) {
            String prefix = matcher.group(1);
            String textPart = matcher.group(2);
            String suffix = matcher.group(3);
            if (textPart.endsWith("'s") || textPart.endsWith("'d")) {
                if (textPart.length() == 2){
                    return new Word(rawText,"" , rawText, "", false);
                }
                suffix = textPart.substring(textPart.length() - 2) + suffix;
                textPart = textPart.substring(0, textPart.length() - 2);
            }
            return new Word(rawText, prefix, textPart, suffix, true);
        }
        return new Word(rawText,"" , rawText, "", false);
    }

    boolean isKeyword() {
            return
                    this.isValid &&
                !stopWords.contains(this.textPart.toLowerCase())
                && !this.textPart.isEmpty()
                && this.textPart.matches("[a-zA-Z'-]+");
    }
    public String getPrefix(){
        return this.prefix;
    }
    public String getSuffix(){
        return this.suffix;
    }
    public String getText(){
        return this.textPart;
    }
    public String toString(){return this.rawText;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word w = (Word) o;
        return this.textPart.equalsIgnoreCase(w.textPart);
    }


    public static boolean loadStopWords(String fileName){
        stopWords = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                stopWords.add(line.trim().toLowerCase());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
