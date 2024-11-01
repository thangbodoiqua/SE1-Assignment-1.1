package a1_2201040168;

import java.util.*;

public class Query {
    private List<Word> keywords;
    public Query(String searchPhrase){
        this.keywords = new ArrayList<>();
        String[] words = searchPhrase.split(" ");
        for (int i = 0; i < words.length; i++){
            Word checkKw = Word.createWord(words[i]);
            if (checkKw.isKeyword()){
                this.keywords.add(checkKw);
            }
        }
    }
    public List<Word> getKeywords(){
        return this.keywords;
    }
    public List<Match> matchAgainst(Doc d){
        List<Word> content = new ArrayList<>();
        List<Match> matches = new ArrayList<>();
        content.addAll(d.getTitle());
        content.addAll(d.getBody());
        for (Word keyWord : this.keywords){
            if (content.contains(keyWord)){
                int firstIdx = content.indexOf(keyWord);
                int freq = 1;
                int lastIdx = content.lastIndexOf(keyWord);
                if (lastIdx > firstIdx){
                    for (int j = firstIdx+1; j <= lastIdx; j++){
                        if (keyWord.equals(content.get(j))){
                            freq++;
                        }
                    }
                }
                matches.add(new Match(d, keyWord, freq, firstIdx));
            }
        }
        Collections.sort(matches);
        return matches;
    }

    public static void main(String[] args) {
        Engine e = new Engine();
        Word.loadStopWords("stopwords.txt");
        Doc d = new Doc("Object-oriented \"design\": with UML's diagrams\n" +
                "Definition: An object-oriented system's context made up of (interacting) objects.");
        Query q = new Query("the <context> of observer: design");


        //Check Tile and Body of doc
        List<Word> body = d.getBody();
        List<Word> title = d.getTitle();
        String bodyText = "";
        String titleText = "";
        for (Word w : body){
            bodyText += w.toString()+" ";
        }
        for (Word w : title){
            titleText += w.toString()+" ";
        }
        System.out.println(titleText);
        System.out.println(bodyText);

        //Text keywords of Query
        List<Word> keyWords = q.getKeywords();
        System.out.println("Keywords: " + keyWords );

        //Check matches
        List<Match> matches = q.matchAgainst(d);
        for (Match m : matches){
            System.out.println("Matched Words: " + m.getWord() + ", First index: " + m.getFirstIndex() + ", Frequency: " + m.getFreq());
        };
    }

}

