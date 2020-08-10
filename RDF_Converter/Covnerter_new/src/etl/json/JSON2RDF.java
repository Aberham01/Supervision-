/*
 * Copyright 2019 Martynas JuseviÄ?ius <martynas@atomgraph.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package etl.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.jena.riot.system.StreamRDF;
import org.apache.jena.riot.system.StreamRDFLib;
import picocli.CommandLine;

//@CommandLine.Command(name = "json2rdf")
public class JSON2RDF {

	private final InputStream jsonIn;
	private final OutputStream rdfOut;

	URI baseURI;
	Charset inputCharset = StandardCharsets.UTF_8;
	Charset outputCharset = StandardCharsets.UTF_8;
//    @CommandLine.Parameters(paramLabel = "base", index = "0", description = "Base URI of the RDF output data\nExample: https://localhost/")
//    private String baseURI = "https://localhost/";
//    
//    @CommandLine.Option(names = { "--input-charset" }, description = "Input charset (default: ${DEFAULT-VALUE})")
//    private Charset inputCharset = StandardCharsets.UTF_8;
//
//    @CommandLine.Option(names = { "--output-charset" }, description = "Output charset (default: ${DEFAULT-VALUE})")
//    private Charset outputCharset = StandardCharsets.UTF_8;

	public static void convertJSON2RDF() throws IOException {

		File in_file = new File("data/computers.json");
		InputStream in = new FileInputStream(in_file);
		File out_file = new File("data/JSON2RDF_Converted.nt");
		OutputStream os = new FileOutputStream(out_file);

		JSON2RDF json2rdf = new JSON2RDF(in, os);

		try {

				json2rdf.convert();
				System.out.println("Done");
		} catch (Exception ex) { // command line arguments could not be parsed
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public JSON2RDF(InputStream csvIn, OutputStream rdfOut) {
		this.jsonIn = csvIn;
		this.rdfOut = rdfOut;
		try {
			baseURI = new URI("https://computers.org/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void convert() throws IOException {
		if (jsonIn.available() == 0)
			throw new IllegalStateException("JSON input not provided");

		try (Reader reader = new BufferedReader(new InputStreamReader(jsonIn, inputCharset))) {
			StreamRDF rdfStream = StreamRDFLib
					.writer(new BufferedWriter(new OutputStreamWriter(rdfOut, outputCharset)));
			new JsonStreamRDFWriter(reader, rdfStream, baseURI.toString()).convert();
		}
	}

}
