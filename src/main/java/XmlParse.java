import lombok.Getter;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class XmlParse {

    private static final String CURRENCY = "currency";
    private static final String RATE = "rate";
    private static final String CUBE_NODE = "//Cube/Cube/Cube";

    private final List<CurrencyRate> currRateList = new ArrayList<>();

    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;

    {
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = builder.parse("currencyRates.xml");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression expression = null;
        try {
            expression = xPath.compile(CUBE_NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        NodeList nodeList = null;
        try {
            assert expression != null;
            nodeList = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < (nodeList != null ? nodeList.getLength() : 0); i++) {
            Node node = nodeList.item(i);
            NamedNodeMap nodeMap = node.getAttributes();
            if (nodeMap.getLength() > 0) {
                Node currencyNodeMap = nodeMap.getNamedItem(CURRENCY);
                if (currencyNodeMap != null) {
                    String currencyTxt = currencyNodeMap.getNodeValue();
                    String rateTxt = nodeMap.getNamedItem(RATE).getNodeValue();
                    currRateList.add(new CurrencyRate(currencyTxt, rateTxt));
                }
            }
        }

    }

    public void getListOfCurrencyShortcut() {
        for (CurrencyRate currencyRate : currRateList) {
            System.out.print(currencyRate.getCurrency() + " ");
        }
    }
}
