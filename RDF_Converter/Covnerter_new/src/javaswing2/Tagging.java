package javaswing2;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreePrint;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Tagging {
  String taggedRDF = "";
  
  ArrayList<String> arrayTag = new ArrayList<String>();
  
  ArrayList<String> arrayPenn = new ArrayList<String>();
  
  ArrayList<String> arrayDependencies = new ArrayList<String>();
  
  String entityTop = "";
  
  String entityBottom = "";
  
  String keywordTop = "<kmm:keyword>";
  
  String keywordBottom = "</kmm:keyword>";
  
  String numberTop = "<kmm:number>";
  
  String numberBottom = "</kmm:number>";
  
  String termTop = "<kmm:term>";
  
  String termBottom = "</kmm:term>";
  
  String sTop = "<kmm:s>";
  
  String sBottom = "</kmm:s>";
  
  String vTop = "<kmm:v>";
  
  String vBottom = "</kmm:v>";
  
  String oTop = "<kmm:o>";
  
  String oBottom = "</kmm:o>";
  
  String svoTop = "<kmm:svo rdf:parseType=\"Resource\">";
  
  String svoBottom = "</kmm:svo>";
  
  String phraseTop = "<kmm:phrase>";
  
  String phraseBottom = "</kmm:phrase>";
  
  String iphraseTop = "<kmm:iphrase>";
  
  String iphraseBottom = "</kmm:iphrase>";
  
  String[] commonTerms = new String[] { 
      "a", "an", "the", "all", "another", "any", "anybody", "anyone", "anything", "both", 
      "each", "each other", "eachother", "either", "everybody", "everyone", "everything", "few", "he", "her", 
      "hers", "herself", "him", "himself", "his", "it", "its", "itself", "little", "many", 
      "me", "mine", "more", "most", "much", "my", "myself", "neither", "no one", "nobody", 
      "none", "nothing", "one", "one another", "other", "others", "our", "ours", "ourselves", "several", 
      "she", "some", "somebody", "someone", "something", "that", "their", "theirs", "them", "themselves", 
      "these", "they", "this", "those", "us", "we", "what", "whatever", "which", "whichever", 
      "who", "whoever", "whom", "whomever", "whose", "you", "your", "yours", "yourself", "yourselves" };
  
  public void POSTagging(ArrayList<String> input, String rdf) {
    this.taggedRDF = rdf;
    TreePrint tp2 = new TreePrint("penn");
    String filePenn = "tree.txt";
    //LexicalizedParser lp = new LexicalizedParser("E:\\#user-developer\\workspace\\converter_bx\\libraries\\text2RDF\\text2rdf\\englishPCFG.ser.gz");
    LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
   // lp.setOptionFlags(new String[]{"-maxLength", "80", "-retainTmpSubcategories"});
    for (int i = 0; i < input.size(); i++) {
      Tree parse2 = lp.parse(input.get(i));
      //Tree parse2 = lp.getBestParse();
      StringWriter stw2 = new StringWriter();
      tp2.printTree(parse2, new PrintWriter(stw2));
      this.arrayPenn.add(stw2.toString());
    } 
    keywordSelection();
    Writer writer = null;   
    this.taggedRDF = replace(this.taggedRDF, "&", "and");
    String taggedFile = "data/taggedRDF.nt"; // output file name
    try {
      File outputFile = new File(taggedFile);
      writer = new BufferedWriter(new FileWriter(outputFile));
      //writer.write(this.taggedRDF);
      
      final Model model = ModelFactory.createDefaultModel();
      model.read(new ByteArrayInputStream(this.taggedRDF.getBytes()), null);
      model.write(writer, "N-TRIPLE");       
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (writer != null)
          writer.close(); 
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public void keywordSelection() {
    ArrayList<String> sentence = new ArrayList<String>();
    ArrayList<String> senRelation = new ArrayList<String>();
    for (int i = 0; i < this.arrayPenn.size(); i++) {
      String[] tempArray = ((String)this.arrayPenn.get(i)).split("\r\n|\r|\n");
      for (int j = 0; j < tempArray.length; j++) {
        senRelation.add(tempArray[j]);
        tempArray[j] = tempArray[j].trim();
        sentence.add(tempArray[j]);
      } 
      addKeyword(sentence, i);
      addPhrase(senRelation, i);
      addStructure(senRelation, i);
      sentence.clear();
      senRelation.clear();
    } 
  }
  
  public void addPhrase(ArrayList<String> senRelation, int var) {
    int lhb = 0;
    String phrase = "";
    int j = 0;
    for (int i = 0; i < senRelation.size(); i++) {
      if (((String)senRelation.get(i)).trim().startsWith("(VP")) {
        lhb = 1;
        if (!phrase.equals("") && phrase.endsWith(")") && (
          phrase.indexOf("(NP") >= 0 || phrase.indexOf("(PP") >= 0))
          if (phrase.indexOf("(NNP") > 0 || phrase.indexOf("(CD") > 0) {
            phrase = getPhrase(phrase);
            tagIPhrase(phrase, var);
          } else {
            phrase = getPhrase(phrase);
            tagPhrase(phrase, var);
          }  
        phrase = "";
      } 
      if (lhb == 1)
        phrase = phrase + ((String)senRelation.get(i)).trim(); 
    } 
    if (!phrase.equals("") && phrase.endsWith(")") && (
      phrase.indexOf("(NP") >= 0 || phrase.indexOf("(PP") >= 0))
      if (phrase.indexOf("(NNP") > 0 || phrase.indexOf("(CD") > 0) {
        phrase = getPhrase(phrase);
        tagIPhrase(phrase, var);
      } else {
        phrase = getPhrase(phrase);
        tagPhrase(phrase, var);
      }  
  }
  
  public void tagPhrase(String word, int var) {
    String search = "<kmm:id>#" + var + "</kmm:id>";
    int indexStart = this.taggedRDF.indexOf(search);
    int indexEnd = indexStart + search.length();
    word = word.trim();
    String wordTag = this.phraseTop + word + this.phraseBottom;
    this.taggedRDF = this.taggedRDF.substring(0, indexEnd) + wordTag + this.taggedRDF.substring(indexEnd, this.taggedRDF.length());
  }
  
  public void tagIPhrase(String word, int var) {
    String search = "<kmm:id>#" + var + "</kmm:id>";
    int indexStart = this.taggedRDF.indexOf(search);
    int indexEnd = indexStart + search.length();
    word = word.trim();
    String wordTag = this.iphraseTop + word + this.iphraseBottom;
    this.taggedRDF = this.taggedRDF.substring(0, indexEnd) + wordTag + this.taggedRDF.substring(indexEnd, this.taggedRDF.length());
  }
  
  public String getPhrase(String phrase) {
    String[] tempArray = null;
    tempArray = phrase.split("[ (]");
    phrase = "";
    for (int i = 0; i < tempArray.length; i++) {
      if (tempArray[i].endsWith(")")) {
        tempArray[i] = replace(tempArray[i], ")", "");
        phrase = phrase + tempArray[i] + " ";
      } 
    } 
    phrase = replace(phrase, " ,", ",");
    phrase = replace(phrase, " ;", ";");
    phrase = replace(phrase, " .", ".");
    phrase = replace(phrase, " :", ":");
    return phrase;
  }
  
  public void addStructure(ArrayList<String> senRelation, int var) {
    String temp = "", tempString = "";
    String head = "", verb = "", obj = "";
    String svo = "";
    String[] tempArray = null;
    int flip = 0, typeVerb = 0, count = 0;
    for (int i = 0; i < senRelation.size(); i++) {
      temp = replace(senRelation.get(i), " ", "~");
      if (temp.indexOf("(NP") == 4) {
        flip = 1;
      } else if (temp.indexOf("(VP") == 4) {
        flip = 2;
      } else if (temp.indexOf("(") == 4) {
        flip = 3;
      } 
      if (flip == 1) {
        tempArray = temp.split("[~(]");
        for (int m = 0; m < tempArray.length; m++) {
          if (tempArray[m].endsWith(")"))
            head = head + replace(tempArray[m], ")", "") + " "; 
        } 
      } 
    } 
    head = head.trim();
    head = replace(head, " 's", "'s");
    head = replace(head, " -LRB- ", this.sBottom + this.sTop);
    head = replace(head, " -RRB-", "");
    head = this.sTop + head + this.sBottom;
    head = head.trim();
    int j = 0;
    int vp = 0, np = 0, cc = 0;
    int k;
    for (k = 0; k < senRelation.size(); k++) {
      if (((String)senRelation.get(k)).indexOf("(VP") == 4)
        flip = 2; 
      if (flip == 2 && (
        (String)senRelation.get(k)).indexOf("(") == 6) {
        tempArray[j] = ((String)senRelation.get(k)).trim();
        if (tempArray[j].startsWith("(NP") || tempArray[j].startsWith("(PP")) {
          np++;
        } else if (tempArray[j].startsWith("(VP")) {
          vp++;
        } else if (tempArray[j].startsWith("(CC")) {
          cc++;
        } 
        j++;
      } 
    } 
    if (vp == 1 && np == 0) {
      typeVerb = 3;
      count = 2;
    } 
    if (typeVerb == 3)
      for (k = 0; k < senRelation.size(); k++) {
        if (((String)senRelation.get(k)).indexOf("(") == 8) {
          tempArray[j] = ((String)senRelation.get(k)).trim();
          if (tempArray[j].startsWith("(NP") || tempArray[j].startsWith("(PP")) {
            np++;
          } else if (tempArray[j].startsWith("(VP")) {
            vp++;
          } else if (tempArray[j].startsWith("(CC")) {
            cc++;
          } 
          j++;
        } 
      }  
    if (vp >= 2 && np >= 0) {
      typeVerb = 2;
    } else if (vp == 1 && np == 0) {
      typeVerb = 3;
    } else {
      typeVerb = 1;
    } 
    flip = 0;
    int flip2 = 0;
    if (typeVerb == 1) {
      for (int m = 0; m < senRelation.size(); m++) {
        if (((String)senRelation.get(m)).indexOf("(VP") == 4)
          flip = 2; 
        if (flip == 2) {
          if (((String)senRelation.get(m)).indexOf("(VP") > 0 && flip2 != 1)
            flip2 = 0; 
          if (((String)senRelation.get(m)).indexOf("(NP") > 0)
            flip2 = 1; 
          if (flip2 == 0) {
            verb = verb + getWord(senRelation.get(m));
          } else {
            obj = obj + getWord(senRelation.get(m));
          } 
        } 
      } 
      svo = head + this.vTop + verb + this.vBottom + this.oTop + obj + this.oBottom;
    } else if (typeVerb == 2) {
      for (int m = 0; m < senRelation.size(); m++) {
        if (((String)senRelation.get(m)).indexOf("(VP") == 4)
          flip = 2; 
        if (flip == 2) {
          if (((String)senRelation.get(m)).indexOf("(VP") > 0)
            flip2 = 0; 
          if (((String)senRelation.get(m)).indexOf("(NP") > 0)
            flip2 = 1; 
          if (((String)senRelation.get(m)).indexOf("(CC") == 6 + count) {
            flip2 = 3;
            verb = verb + "<>";
            obj = obj + "<>";
          } 
          if (flip2 == 0) {
            verb = verb + getWord(senRelation.get(m));
          } else if (flip2 == 1) {
            obj = obj + getWord(senRelation.get(m));
          } 
        } 
      } 
      verb = replace(verb, "<>", this.vBottom + this.vTop);
      obj = replace(obj, "<>", this.oBottom + this.oTop);
      svo = head + this.vTop + verb + this.vBottom + this.oTop + obj + this.oBottom;
    } 
    addSVO(svo, var);
  }
  
  public void addSVO(String word, int var) {
    String search = "<kmm:id>#" + var + "</kmm:id>";
    int indexStart = this.taggedRDF.indexOf(search);
    int indexEnd = indexStart + search.length();
    word = word.trim();
    String wordTag = this.svoTop + word + this.svoBottom;
    this.taggedRDF = this.taggedRDF.substring(0, indexEnd) + wordTag + this.taggedRDF.substring(indexEnd, this.taggedRDF.length());
  }
  
  public String getWord(String tagString) {
    String word = "";
    String[] tempArray = tagString.split("[( ]");
    for (int j = 0; j < tempArray.length; j++) {
      if (tempArray[j].endsWith(")"))
        word = word + replace(tempArray[j], ")", "").trim() + " "; 
    } 
    word = replace(word, " ,", ",");
    word = replace(word, " ;", ";");
    return word;
  }
  
  public void addKeyword(ArrayList<String> sentence, int var) {
    String word = "";
    for (int i = 0; i < sentence.size(); i++) {
      word = "";
      if (((String)sentence.get(i)).startsWith("(NP")) {
        String string = sentence.get(i);
        if (((String)sentence.get(i + 1)).startsWith("(CC") && ((String)sentence.get(i + 2)).startsWith("(NNP")) {
          string = (String)sentence.get(i) + replace(sentence.get(i + 1), "CC ", "NNP ") + (String)sentence.get(i + 2);
          string = replace(string, "&", "and");
        } else if (((String)sentence.get(i + 1)).startsWith("(NNP")) {
          string = (String)sentence.get(i) + (String)sentence.get(i + 1);
        } 
        if (string.indexOf("NNP") > 0) {
          word = getWord(string);
          if (word.indexOf(",") > 0) {
            String[] tempArray = word.split(",");
            for (int j = 0; j < tempArray.length; j++) {
              tempArray[j] = tempArray[j].trim();
              if (!tempArray[j].equals(""))
                addKeyword(tempArray[j], var); 
            } 
          } else {
            addKeyword(word, var);
          } 
        } else {
          string = sentence.get(i);
          if (((String)sentence.get(i + 1)).startsWith("(CC") && ((String)sentence.get(i + 2)).startsWith("(NN")) {
            string = (String)sentence.get(i) + replace(sentence.get(i + 1), "CC ", "NN ") + (String)sentence.get(i + 2);
            string = replace(string, "&", "and");
          } 
          if (string.indexOf("(CD") >= 0) {
            String[] tempArray = string.split("[ ()]");
            for (int j = 0; j < tempArray.length; j++) {
              tempArray[j] = replace(tempArray[j], "NP", "");
              tempArray[j] = replace(tempArray[j], "CD", "");
              tempArray[j] = replace(tempArray[j], "NNS", "");
              tempArray[j] = replace(tempArray[j], "NN", "");
              tempArray[j] = replace(tempArray[j], "JJ", "");
              if (!tempArray[j].equals(""))
                word = word + tempArray[j] + " "; 
            } 
            addNumber(word, var);
          } else if (string.endsWith(")")) {
            String[] tempArray = string.split("[( ]");
            for (int j = 0; j < tempArray.length; j++) {
              if (tempArray[j].endsWith(")")) {
                tempArray[j] = replace(tempArray[j], ")", "");
                word = word + tempArray[j] + " ";
              } 
            } 
            if (word.indexOf(",") > 0) {
              tempArray = word.split(",");
              for (int k = 0; k < tempArray.length; k++) {
                tempArray[k] = tempArray[k].trim();
                if (!tempArray[k].equals(""))
                  addTerm(tempArray[k], var); 
              } 
            } else {
              addTerm(word, var);
            } 
          } 
        } 
      } 
    } 
  }
  
  public void addTerm(String word, int var) {
    String search = "<kmm:id>#" + var + "</kmm:id>";
    int indexStart = this.taggedRDF.indexOf(search);
    int indexEnd = indexStart + search.length();
    word = word.trim();
    word = word.substring(0, 1).toLowerCase() + word.substring(1);
    for (int j = 0; j < this.commonTerms.length; j++) {
      if (word.contains(" ")) {
        if (word.startsWith(this.commonTerms[j] + " ")) {
          word = word.substring(this.commonTerms[j].length(), word.length());
          word.trim();
        } 
        if (word.endsWith(" " + this.commonTerms[j])) {
          word = word.substring(0, word.length() - this.commonTerms[j].length());
          word.trim();
        } 
      } else if (word.equals(this.commonTerms[j])) {
        word = "";
      } 
    } 
    System.out.println(word);
    if (!word.equals("")) {
      String wordTag = this.termTop + word.trim() + this.termBottom;
      this.taggedRDF = this.taggedRDF.substring(0, indexEnd) + wordTag + this.taggedRDF.substring(indexEnd, this.taggedRDF.length());
    } 
  }
  
  public void addNumber(String word, int var) {
    String search = "<kmm:id>#" + var + "</kmm:id>";
    int indexStart = this.taggedRDF.indexOf(search);
    int indexEnd = indexStart + search.length();
    word = word.trim();
    String wordTag = this.numberTop + word + this.numberBottom;
    this.taggedRDF = this.taggedRDF.substring(0, indexEnd) + wordTag + this.taggedRDF.substring(indexEnd, this.taggedRDF.length());
  }
  
  public void addKeyword(String word, int var) {
    String search = "<kmm:id>#" + var + "</kmm:id>";
    int indexStart = this.taggedRDF.indexOf(search);
    int indexEnd = indexStart + search.length();
    word = word.trim();
    String[] wordSplit = word.split(" ");
    String updatedWord = "";
    int i;
    for (i = 0; i < wordSplit.length; i++) {
      for (int j = 0; j < this.commonTerms.length; j++) {
        if (wordSplit[i].toLowerCase().trim().equals(this.commonTerms[j]))
          wordSplit[i] = ""; 
      } 
    } 
    for (i = 0; i < wordSplit.length; i++)
      updatedWord = updatedWord + wordSplit[i] + " "; 
    String wordTag = this.keywordTop + updatedWord.trim() + this.keywordBottom;
    this.taggedRDF = this.taggedRDF.substring(0, indexEnd) + wordTag + this.taggedRDF.substring(indexEnd, this.taggedRDF.length());
  }
  
  public static String replace(String str, String pattern, String replace) {
    int s = 0;
    int e = 0;
    StringBuffer result = new StringBuffer();
    while ((e = str.indexOf(pattern, s)) >= 0) {
      result.append(str.substring(s, e));
      result.append(replace);
      s = e + pattern.length();
    } 
    result.append(str.substring(s));
    return result.toString();
  }
  
  public ArrayList<String> getArrayDependencies() {
    return this.arrayDependencies;
  }
  
  public void setArrayDependencies(ArrayList<String> arrayDependencies) {
    this.arrayDependencies = arrayDependencies;
  }
  
  public ArrayList<String> getArrayPenn() {
    return this.arrayPenn;
  }
  
  public void setArrayPenn(ArrayList<String> arrayPenn) {
    this.arrayPenn = arrayPenn;
  }
 
  public ArrayList<String> getArrayTag() {
    return this.arrayTag;
  }
  
  public void setArrayTag(ArrayList<String> arrayTag) {
    this.arrayTag = arrayTag;
  }
  
  public String getTaggedRDF() {
    return this.taggedRDF;
  }
  
  public void setTaggedRDF(String taggedRDF) {
    this.taggedRDF = taggedRDF;
  }
}
