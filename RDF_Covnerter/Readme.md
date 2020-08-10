Tool for Converting JSON and Text to RDF triples 


        [x] JSON2RDF
JSON2RDF method receives data as JSON format and converts to rdf representation
as N-Triples serialization. I used a pre existing project and
you can take a look here “http://www.bobdc.com/blog/json2rdf/”,


        [x] Text2RDF
It receives textual data as input, then tokenizes it to extract the keywords and
phrases to represent it as rdf triples. The output is N-Triplesformat.
I have used an existing project https://code.google.com/archive/p/text2rdf/ and decompiled 
the jar (executable file) and then modified the code to integrate
with my project. For text mining “stanford-corenlp4.1.0-” is used to process the text
and prepare it for rdf based representation. 


[x] To run the application, just clone the repo and include the following libraries 
https://drive.google.com/drive/folders/1YZje620NNt8XlV-t7qeyFryEpXh9Y8P4?usp=sharing