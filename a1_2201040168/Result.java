package a1_2201040168;

import java.util.*;

public class Result implements Comparable<Result>{
    private Doc d;
    private List<Match> matches;
    public Result(Doc d, List<Match> matches){
        this.d = d;
        this.matches = matches;
    }
    public List<Match> getMatches(){
        return this.matches;
    }
    public int getTotalFrequency(){
        int totalFreq = 0;
        for (Match match : this.matches){
            totalFreq += match.getFreq();
        }
        return totalFreq;
    }
    public double getAverageFirstIndex(){
        double averageFirstIndex =  0.0;
        if (this.matches.isEmpty())
            return averageFirstIndex;
        for (Match match : this.matches){
            averageFirstIndex += match.getFirstIndex();
        }
        return averageFirstIndex / (double) this.matches.size();
    }

    @Override
    public int compareTo(Result o) {
        int sizeComparison = Integer.compare(o.matches.size(), this.matches.size());
        if (sizeComparison != 0) {
            return sizeComparison;
        }

        int freqComparison = Integer.compare(o.getTotalFrequency(), this.getTotalFrequency());
        if (freqComparison != 0) {
            return freqComparison;
        }

        return Double.compare(this.getAverageFirstIndex(), o.getAverageFirstIndex());
    }

    public Doc getDoc() {
        return d;
    }
    public String htmlHighlight(){
        StringBuilder title = new StringBuilder();
        StringBuilder body = new StringBuilder();
        List<Word> matchedWords = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++){
            matchedWords.add(matches.get(i).getWord());
        }
        for (Word w : d.getTitle()){
            if (matchedWords.contains(w)){
                title.append(w.getPrefix() + "<u>" + w.getText() + "</u>" + w.getSuffix()+" ");
            }
            else {
                title.append(w.toString()+ " ");
            }
        }
        for (Word w : d.getBody()){
            if (matchedWords.contains(w)){
                body.append(w.getPrefix() + "<b>" + w.getText() + "</b>" + w.getSuffix() +" ");
            }
            else {
                body.append(w.toString()+" ");
            }
        }
        return "<h3>" + title.toString().trim() +"</h3>" + "<p>" + body.toString().trim() + "</p>";

    }
}
