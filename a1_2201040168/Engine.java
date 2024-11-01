package a1_2201040168;

import java.io.*;
import java.util.*;


public class Engine {
    private Doc[] docs;
    public int loadDocs(String dirname){
        File folder = new File(dirname);
        if (!folder.exists() || !folder.isDirectory()) {
            return 0;
        }
        File[] files = folder.listFiles();
        if (files == null) {
            return 0;
        }
        this.docs = new Doc[files.length];
        for (int i = 0; i < files.length; i++) {
            try {
                Scanner sc = new Scanner(files[i]);
                StringBuilder content = new StringBuilder();
                while (sc.hasNextLine()){
                    content.append(sc.nextLine()).append("\n");
                }
                this.docs[i] = new Doc(content.toString());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return this.docs.length;
    }
    public Doc[] getDocs(){
        return this.docs;
    }
    public List<Result> search(Query q){
        List<Result> results = new ArrayList<>();
        for (Doc d : this.docs){
            Result result = new Result(d, q.matchAgainst(d));
            if (!result.getMatches().isEmpty()){
                results.add(result);
            }
        }
        Collections.sort(results);
        return results;
    }
    public String htmlResult(List<Result> results){
        String htmlResult = new String();
        for (Result result : results){
            htmlResult += result.htmlHighlight();
        }
        return htmlResult;
    }

}
