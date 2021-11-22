import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.HashMap;
import java.util.Map;

public class TagCounter extends org.xml.sax.helpers.DefaultHandler {
    private HashMap<String, Integer> nodes = new HashMap<String, Integer>();
    @Override
    public void startElement(String uri, String localName, String qName, Attributes att) throws SAXException {
        if (!nodes.containsKey(qName)) {
            nodes.put(qName, 1);
        }
        else {
            nodes.put(qName, nodes.get(qName)+1);
        }
    }

    public void endDocument() throws SAXException {
        for (Map.Entry<String, Integer> node : nodes.entrySet()) {
            System.out.println(node.getKey() + " " + node.getValue());
        }
    }
}
