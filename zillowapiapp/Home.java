/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zillowapiapp;
import org.jdom2.*;
/**
 *
 * @author DanielMedina
 */
class Home {
        private Element root;

    Home(Element homeXML){
        root = homeXML;
    }

    public String getAddress(){
        Element addressElm = root.getChild("address");
        String address = addressElm.getChildText("street")+"\n"+addressElm.getChildText("zipcode")+"\n"+addressElm.getChildText("city")+", "+addressElm.getChildText("state");
//        System.out.println("Address: \n" + address);
        return address;
    }

    public String getEstimate(){
        Element estElm = root.getChild("zestimate");
        String est = estElm.getChildText("amount");
//        System.out.println("Estimate: " + est);
        return est;
    }
}
