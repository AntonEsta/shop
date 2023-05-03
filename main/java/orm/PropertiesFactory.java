package orm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import lombok.NonNull;

public class PropertiesFactory {

    private PropertiesFactory() {}

    public static Properties createProperties(@NonNull String source) {
        Path path = Path.of(source);
        if (!Files.exists(path)) {
        	return null;
        }
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList;
        try {
            InputSource inSource = new InputSource(Files.newBufferedReader(path));
            nodeList = (NodeList) xPath.compile("//config").evaluate(inSource, XPathConstants.NODESET);
        } catch (XPathExpressionException | IOException e) {
            throw new RuntimeException(e);
        }
        Properties properties = new Properties() {
            final HashMap<String, String> properties = new HashMap<>();
            @Override
            public String getProperty(String name) {
                return properties.get(name);
            }

            @Override
            public void setProperty(String name, String value) {
                properties.put(name, value);
            }

        };

        if (nodeList != null) {
            NodeList list = nodeList.item(0).getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                properties.setProperty(list.item(i).getNodeName(), list.item(i).getTextContent());
            }
        }

        return properties;
    }

}
