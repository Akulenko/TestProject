package webdriver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Created by user on 13.10.2016.
 */
public class RunConfigurator {
    public String GetValue(String tag) {
        String tagValue = "";
        try {
            //      File configFile=new File("../../resources/run.xml");
            FileInputStream file = new FileInputStream(new File("src\\test\\resources\\Run.xml"));
        //    FileInputStream file = new FileInputStream(new File("Run.xml"));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document doc = builder.parse(file);

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath() ;
            Element element = (Element) xpath.evaluate("//*[@id='"+tag+"']", doc, XPathConstants.NODE);

            tagValue=element.getTextContent();
          //  String expression = "/"+tag;

      //      tagValue = xPath.compile(expression).evaluate(xmlDocument);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return tagValue;
    }


}
