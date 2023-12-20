package ro.scoalainformala;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class BnrReader {
    private static final String BNR_URL = "https://www.bnr.ro/nbrfxrates.xml";

    public static List<CurrencyExchange> readCurrencies() {
        System.setProperty("javax.net.ssl.trustStore","./src/main/resources/keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","mypass");
        List<CurrencyExchange> currencies = new ArrayList<>();

        try {

            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setNamespaceAware(false);
            f.setValidating(false);
            DocumentBuilder b = f.newDocumentBuilder();
            URLConnection urlConnection = new URL(BNR_URL).openConnection();
            urlConnection.addRequestProperty("Accept", "application/xml");
            Document doc = b.parse(urlConnection.getInputStream());
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("Cube");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    NodeList rateNodeList = element.getElementsByTagName("Rate");

                    for (int i = 0; i < rateNodeList.getLength(); i++) {
                        Element rateElement = (Element) rateNodeList.item(i);
                        String rate = rateElement.getTextContent();
                        String currency = rateElement.getAttribute("currency");
                        String id = element.getAttribute("currency");
                        currencies.add(i, new CurrencyExchange(currency, Float.parseFloat(rate)));
                       /* System.out.println("Currency: " + currency);
                        System.out.println("Rate: " + Float.parseFloat(rate));
                        System.out.println("------");*/
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return currencies;
    }
}
