//package converter_bx;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.StringWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Arrays;
//
//import org.apache.commons.io.IOUtils;
//
//import com.squareup.okhttp.MediaType;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.RequestBody;
//import com.squareup.okhttp.Response;
//import com.squareup.okhttp.ResponseBody;
//
//public class Text2RDF {
//	   public static final String POST_URL = "http://wit.istc.cnr.it/stlab-tools/fred/";
//
//	    public static final String POST_DATA = "{\"text\": \"semantic web\"}";
//    public static void main(String[] args) throws IOException {
//
////    	Runtime runtime = Runtime.getRuntime();
////    	System.out.println("dfksl");
////    	try {
////    		String cmd = "curl -X GET \"https://wit.istc.cnr.it/stlab-tools/fred?text=Miles Davis was an american jazz musician&prefix=http://computer.org/&namespace=ab&wsd=&wfd=true&wfdProfile=&tense=&roles=&textannotation=&semanticSubgraph=\"";
////    		System.out.println(cmd);
////    	    Process process = runtime.exec(cmd);
////    	    System.out.println("23");
////    	    int resultCode = process.waitFor();
////    	    System.out.println("54");
////    	    if (resultCode == 0) {
////    	        // all is good
////    	    	System.out.println("working...");
////    	    }
////    	} catch (Exception cause) {
////    	    // process cause
////    		System.out.println("problem");
////    		cause.printStackTrace();
////    	}
//        String[] details = {};
//        System.out.println(Arrays.toString(details));
//
//        URL line_api_url = new URL(POST_URL);
//        String payload = POST_DATA;
//
//        HttpURLConnection linec = (HttpURLConnection) line_api_url
//                .openConnection();
//        linec.setDoInput(true);
//        linec.setDoOutput(true);
//        linec.setRequestMethod("POST");
//        linec.setRequestProperty("Content-Type", "application/json");
//        linec.setRequestProperty("Authorization", "");
//        
//        OutputStreamWriter writer = new OutputStreamWriter(
//                linec.getOutputStream(), "UTF-8");
//        writer.write(payload);
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                linec.getInputStream()));
//        String inputLine;
//
//        while ((inputLine = in.readLine()) != null)
//            System.out.println(inputLine);
//        in.close();
//    }
//    
//}