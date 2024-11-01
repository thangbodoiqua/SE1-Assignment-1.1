package a1_2201040168;

public class Match implements Comparable<Match>{
    private Doc d;
    private Word w;
    private int freq, firstIndex;
    public Match(Doc d, Word w, int freq, int firstIndex){
        this.d = d;
        this.w= w;
        this.freq = freq;
        this.firstIndex = firstIndex;
    }
    public int getFreq(){
        return this.freq;
    }
    public int getFirstIndex(){
        return this.firstIndex;
    }

    public Word getWord() {
        return this.w;
    }

    @Override
    public int compareTo(Match o) {
        return this.firstIndex - o.firstIndex;
    }

}
