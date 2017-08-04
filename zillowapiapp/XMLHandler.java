/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zillowapiapp;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;
/**
 *
 * @author DanielMedina
 */
class XMLHandler {
    XMLHandler(){
    }

    //Parse XML
    public ArrayList<Home> parse(StringBuffer in) throws IOException, SAXException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        InputStream inp = new ByteArrayInputStream(in.toString().getBytes(StandardCharsets.UTF_8));
        Document document = saxBuilder.build(inp);
        Element root = document.getRootElement();
        return parseResults(document);
    }

    // Parse Results from XML
    private ArrayList<Home> parseResults(Document doc){
        ArrayList<Home> results = new ArrayList<>();
        Element root = doc.getRootElement().getChild("response");
        try{
            List<Element> elms = root.getChildren("results");
            for(Element elm: elms){
                Home newResult = new Home(elm.getChild("result"));
                results.add(newResult);
                }
            return results;
        }catch (NullPointerException ex) {
            new PopUpMessage("No results were found").show();
            return new ArrayList<Home>();
        }
    }
}
