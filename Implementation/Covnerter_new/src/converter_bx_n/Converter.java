package converter_bx_n;

import java.io.IOException;

import etl.json.JSON2RDF;
import javaswing2.Text2RDFFrame;

public class Converter {
	
	public static void main(String[] args) throws IOException {
		JSON2RDF.convertJSON2RDF();
		Text2RDFFrame text2rdf = new Text2RDFFrame();
		text2rdf.convertText2RDF();
	}

}
