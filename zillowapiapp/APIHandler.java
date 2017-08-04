/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zillowapiapp;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author DanielMedina
 */
class APIHandler {
    private String url = "https://www.zillow.com/webservice/GetSearchResults.htm?";
    private String APIKey = "X1-ZWz1dyb53fdhjf_6jziz";


    ArrayList<Home> searchAddress(String address, String citystatezip) throws IOException, ParserConfigurationException, SAXException, JDOMException {
        //TODO: Encode parameters
        URL callURL = new URL(url+"zws-id="+APIKey+"&address="+encodeString(address)+"&citystatezip="+encodeString(citystatezip));
        HttpURLConnection conn = (HttpURLConnection) callURL.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        System.out.println("Response: " + responseCode+"\n");
        return processResponse(conn);

    }

    private String encodeString(String s){
        String encoded = null;
        try{
            encoded = URLEncoder.encode(s,"UTF-8");
        }catch (Exception err){
            System.out.print(err);
            err.printStackTrace();
         }
        return encoded;
    }

    private ArrayList<Home> processResponse(HttpURLConnection conn) throws IOException, ParserConfigurationException, SAXException, JDOMException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        XMLHandler parser = new XMLHandler();
        return parser.parse(response);

    }
}
