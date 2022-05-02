package by.issoft.tools.sort;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;

public class XmlParser {

    private final Map<String, SortValues> sortMap = new LinkedHashMap<>();

    public Map<String, SortValues> getSortMap() {
        return sortMap;
    }

    public void xmlReadAndParse(String Path){
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(Path);

            Node root = document.getDocumentElement();

            NodeList nodelist = root.getChildNodes();
            IntStream.range(0, nodelist.getLength()).mapToObj(nodelist::item).filter(node -> node.getNodeType() != Node.TEXT_NODE).map(Node::getChildNodes)
                    .forEach(nodeProps -> IntStream.range(0, nodeProps.getLength()).mapToObj(nodeProps::item).filter(bookProp -> bookProp.getNodeType() != Node.TEXT_NODE)
                    .forEach(bookProp -> {
                        // put key/value into sortmap
                        sortMap.put(bookProp.getNodeName(), SortValues.valueOf(bookProp.getChildNodes().item(0).getTextContent().toUpperCase(Locale.ROOT)));
                    }));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
