package javaswing2;

import java.util.ArrayList;

public class Processing {
  String inputText = " ";
  
  String rdf = "";
  
  public String getRdf() {
    return this.rdf;
  }
  
  public void setRdf(String rdf) {
    this.rdf = rdf;
  }
  
  ArrayList<String> textList = new ArrayList<String>();
  
  ArrayList<String> sentence = new ArrayList<String>();
  
  public ArrayList<String> getSentence() {
    return this.sentence;
  }
  
  public void setSentence(ArrayList<String> sentence) {
    this.sentence = sentence;
  }
  
  ArrayList<String> heading = new ArrayList<String>();
  
  String rdfTop = "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:kmm=\"http://www.name.com/\">\n<rdf:Description rdf:about=\"http://www.name.com/doc#number\">\n";
  
  String rdfBottom = "</rdf:Description></rdf:RDF>";
  
  String paraTop = "<kmm:section rdf:parseType=\"Resource\">\n";
  
  String paraBottom = "</kmm:section>\n";
  
  String sentenceTop = "<kmm:sen rdf:parseType=\"Resource\">\n";
  
  String sentenceBottom = "</kmm:sen>\n";
  
  String textTop = "<kmm:text>";
  
  String textBottom = "</kmm:text>\n";
  
  String idTop = "<kmm:id>";
  
  String idBottom = "</kmm:id>\n";
  
  String headTop = "<kmm:head>";
  
  String headBottom = "</kmm:head>\n";
  
  int idSentence = 0, dvar = 0;
  
  public void Preprocessing() {
    this.rdf += this.rdfTop;
    String[] textArray = this.inputText.split("\r\n|\r|\n");
    for (int i = 0; i < textArray.length; i++) {
      textArray[i] = textArray[i].trim();
      if (!textArray[i].endsWith(".")) {
        this.rdf += this.headTop + textArray[i] + this.headBottom;
        this.heading.add(textArray[i]);
        this.textList.add(textArray[i]);
      } else {
        loadRDF(textArray[i], i);
      } 
    } 
    this.rdf += this.rdfBottom;
  }
  
  public void loadRDF(String input, int var) {
    this.rdf += this.paraTop;
    this.rdf += addSentence(input, var);
    this.rdf += this.paraBottom;
  }
  
  public String addSentence(String input, int var) {
    String rdfSentence = "";
    String id = "#" + Integer.toString(var) + "-";
    ArrayList<String> array = new ArrayList<String>();
    ArrayList<String> array2 = new ArrayList<String>();
    String[] dataArray = null;
    dataArray = input.split("[.]");
    int i;
    for (i = 0; i < dataArray.length; i++) {
      dataArray[i] = dataArray[i].trim();
      dataArray[i] = dataArray[i] + ".";
      char c = dataArray[i].charAt(0);
      boolean bool = Character.isLowerCase(c);
      if (!bool) {
        array.add(dataArray[i]);
      } else {
        String s = array.get(array.size() - 1);
        array.remove(array.size() - 1);
        array.add(s + dataArray[i] + ".");
      } 
    } 
    for (i = 0; i < array.size(); i++) {
      String s = replace(array.get(i), "..", ". ");
      if (!s.startsWith(".")) {
        this.textList.add(s);
        this.sentence.add(s);
        array2.add(s);
      } 
    } 
    array.clear();
    for (i = 0; i < array2.size(); i++) {
      rdfSentence = rdfSentence + this.sentenceTop;
      rdfSentence = rdfSentence + this.idTop + "#" + this.idSentence++ + this.idBottom;
      rdfSentence = rdfSentence + this.textTop + (String)array2.get(i) + this.textBottom;
      rdfSentence = rdfSentence + this.sentenceBottom;
    } 
    array2.clear();
    return rdfSentence;
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
  
  public ArrayList<String> getTextList() {
    return this.textList;
  }
  
  public ArrayList<String> getHeading() {
    return this.heading;
  }
  
  public void setTextList(ArrayList<String> textList) {
    this.textList = textList;
  }
  
  public String getInputText() {
    return this.inputText;
  }
  
  public void setInputText(String inputText) {
    this.inputText = inputText;
  }
}
