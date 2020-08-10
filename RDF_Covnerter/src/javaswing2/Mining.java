package javaswing2;

import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Mining {
  private String textContent;
  
  private String inputText;
  
  private String formatText;
  
  ArrayList<String> textList = new ArrayList<String>();
  
  ArrayList<String> heading = new ArrayList<String>();
  
  ArrayList<String> keyword = new ArrayList<String>();
  
  ArrayList<String> phrase = new ArrayList<String>();
  
  ArrayList<String> iphrase = new ArrayList<String>();
  
  ArrayList<String> term = new ArrayList<String>();
  
  ArrayList<String> cardinal = new ArrayList<String>();
  
  ArrayList<String> sentence = new ArrayList<String>();
  
  private String rdf;
  
  private String taggedRDF;
  
  private String phraseList = "";
  
  private String termList = "";
  
  public String getFormatText() {
    System.out.println(this.formatText);
    return this.formatText;
  }
  
  public void setFormatText() {
    String word = null;
    String tempText = this.inputText;
    if (this.nkeyword.getLength() != 0)
      for (int i = 0; i < this.nkeyword.getLength(); i++) {
        word = this.nkeyword.item(i).getTextContent().toString();
        tempText = tempText.replaceAll(word, "<b>" + word + "</b>");
      }  
    if (this.nterm.getLength() != 0)
      for (int i = 0; i < this.nterm.getLength(); i++) {
        word = this.nterm.item(i).getTextContent().toString();
        tempText = tempText.replaceAll(word, "<u>" + word + "</u>");
      }  
    this.formatText = "<html>" + tempText + "</html>";
  }
  
  public void locateWord(String word) {}
  
  public String getTermList() {
    return this.termList;
  }
  
  public String getPhraseList() {
    return this.phraseList;
  }
  
  public void processTextContent() {
    Processing p = new Processing();
    p.setInputText(this.textContent);
    p.Preprocessing();
    this.rdf = p.getRdf();
    this.sentence = p.getSentence();
    Tagging tag = new Tagging();
    tag.POSTagging(this.sentence, this.rdf);
    this.taggedRDF = tag.getTaggedRDF();
    System.out.println("taggedRDF");
    System.out.println(this.taggedRDF);
    getBOPList();
    this.textContent = this.taggedRDF;
  }
  
  private NodeList nheading = null, nphrase = null, niphrase = null, nterm = null, nkeyword = null, nnumber = null;
  
  public void getBOPList() {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      InputSource is = new InputSource();
      is.setCharacterStream(new StringReader(this.taggedRDF));
      Document doc = db.parse(is);
      this.nheading = doc.getElementsByTagName("kmm:head");
      this.nphrase = doc.getElementsByTagName("kmm:phrase");
      this.niphrase = doc.getElementsByTagName("kmm:iphrase");
      this.nterm = doc.getElementsByTagName("kmm:term");
      this.nkeyword = doc.getElementsByTagName("kmm:keyword");
      this.nnumber = doc.getElementsByTagName("kmm:number");
    } catch (Exception e) {
      e.printStackTrace();
    } 
    setTermList();
    setPhraseList();
    setFormatText();
  }
  
  public void setTermList() {
    this.termList = "## HEADINGS ##\n";
    if (this.nheading.getLength() != 0) {
      for (int i = 0; i < this.nheading.getLength(); i++)
        this.termList += this.nheading.item(i).getTextContent() + "\n"; 
    } else {
      this.termList += "null\n";
    } 
    this.termList += "\n## KEYWORDS ##\n";
    if (this.nkeyword.getLength() != 0) {
      for (int i = 0; i < this.nkeyword.getLength(); i++)
        this.termList += this.nkeyword.item(i).getTextContent() + "\n"; 
    } else {
      this.termList += "null\n";
    } 
    this.termList += "\n## TERMS ##\n";
    if (this.nterm.getLength() != 0) {
      for (int i = 0; i < this.nterm.getLength(); i++)
        this.termList += this.nterm.item(i).getTextContent() + "\n"; 
    } else {
      this.termList += "null\n";
    } 
    this.termList += "\n## CARDINAL ##\n";
    if (this.nnumber.getLength() != 0) {
      for (int i = 0; i < this.nnumber.getLength(); i++)
        this.termList += this.nnumber.item(i).getTextContent() + "\n"; 
    } else {
      this.termList += "null\n";
    } 
  }
  
  public void setPhraseList() {
    this.phraseList = "## IMPROTANT PHRASES ##\n";
    if (this.niphrase.getLength() != 0) {
      for (int i = 0; i < this.niphrase.getLength(); i++)
        this.phraseList += this.niphrase.item(i).getTextContent() + "\n"; 
    } else {
      this.phraseList += "null\n";
    } 
    this.phraseList += "\n## GENERAL PHRASES ##\n";
    if (this.nphrase.getLength() != 0) {
      for (int i = 0; i < this.nphrase.getLength(); i++)
        this.phraseList += this.nphrase.item(i).getTextContent() + "\n"; 
    } else {
      this.phraseList += "null\n";
    } 
  }
  
  public String getTextContent() {
    return this.textContent;
  }
  
  public void setTextContent(String textContent) {
    this.inputText = textContent;
    this.textContent = textContent;
  }
}
