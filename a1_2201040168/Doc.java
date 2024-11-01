package a1_2201040168;

import java.util.*;

public class Doc {
    private List<Word> title, body;
    public Doc(String content){
        Scanner sc = new Scanner(content);
        this.title = new ArrayList<>();
        this.body = new ArrayList<>();
        String[] titleText = sc.nextLine().split(" ");
        String[] bodyText = sc.nextLine().split(" ");
        for (int i = 0; i < titleText.length; i++){
            this.title.add(Word.createWord(titleText[i]));
        }

        for (int i = 0; i < bodyText.length; i++){
            this.body.add(Word.createWord(bodyText[i]));
        }
    }
    public List<Word> getTitle(){
        return title;
    }
    public List<Word> getBody(){
        return body;
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doc doc = (Doc) o;
        if (this.title.size() != ((Doc) o).title.size() || this.body.size() != ((Doc) o).body.size())
            return false;
        for (int i = 0; i < this.title.size(); i++){
            if (!this.title.get(i).equals(((Doc) o).title.get(i))){
                return false;
            }
        }
        for (int i = 0; i < this.body.size(); i++){
            if (!this.body.get(i).equals(((Doc) o).body.get(i))){
                return false;
            }
        }
        return true;

    }

}
