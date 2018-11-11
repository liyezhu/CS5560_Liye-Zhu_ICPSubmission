package openie;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.util.Quadruple;
import java.util.Collection;
import java.util.Iterator;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mayanka on 27-Jun-16.
 */
public class CoreNLP {
    public static String returnTriplets(String sentence) {

        Document doc = new Document(sentence);
        String triplets="";
        int i=0;
        for (Sentence sent : doc.sentences()) {  // Will iterate over two sentences
            i++;
            //triplets+=sent+",";
            Iterator<Quadruple<String, String, String, Double>> openIETripletList=sent.openie().iterator();
            int noOfTriplets=0;
            if(openIETripletList.hasNext()) {
                //triplets+= l.toString();
                Quadruple<String, String, String, Double> singleTriplet= openIETripletList.next();
                String subject=singleTriplet.first.replaceAll(" ","").replaceAll("-","").replaceAll("/","");
                String predicate=singleTriplet.second.replaceAll(" ","").replaceAll("-","").replaceAll("/","");
                String object=singleTriplet.third.replaceAll(" ","").replaceAll("-","").replaceAll("/","");
                // subject;object;predicate;\n
                //triplets+=subject+","+predicate+","+object+",Obj\n";
                //triplets+=predicate+",Subject,Object,Func\n";
                triplets+=subject+","+predicate+","+object+"|";


                noOfTriplets++;
            }
            //System.out.println(triplets);
            //triplets+=","+noOfTriplets+"\n";

        }
        /**
         *  Triplet Results
         *  sentence, subject;predicate;object\n subject;predicate;object \n , No. Of Triplets
         */

        return triplets;
    }
    public static String returnLemma(String sentence) {

        Document doc = new Document(sentence);
        String lemma="";
        for (Sentence sent : doc.sentences()) {  // Will iterate over two sentences
            List<String> l=sent.lemmas();
            for (int i = 0; i < l.size() ; i++) {
                lemma+= l.get(i) +" ";
            }
            System.out.println(lemma);
        }

        return lemma;
    }

}
