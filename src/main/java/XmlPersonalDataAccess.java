package main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlPersonalDataAccess implements PersonDataAccess {

    private static final Logger logger = LoggerFactory.getLogger(XmlPersonalDataAccess.class);

    File file;

    public XmlPersonalDataAccess(File file) {
        this.file = file;
    }


    @Override
    public List<Person> read() {
        Person person;
        List<Person> personsList = new ArrayList<>();
        logger.info("reading the file: {}", file);
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList listOfpersons = document.getElementsByTagName("person");
            int totalPersons = listOfpersons.getLength();

            for (int s = 0; s < totalPersons; s++) {


                Node PersonNode = listOfpersons.item(s);
                Element personElement = (Element) PersonNode;

                person = new Person();
                person.setFirstName(readXmlTagValue("first-name", personElement));
                person.setLastName(readXmlTagValue("last-name", personElement));
                person.setGender(readXmlTagValue("gender", personElement));
                person.setAge(Integer.parseInt(readXmlTagValue("age", personElement)));
                person.setCity(readXmlTagValue("city", personElement));
                personsList.add(person);
                logger.debug("{} row: {}", s, person);

            }

            logger.info("file {} read succesfully", file);
        } catch (SAXParseException err) {
            logger.error("error reading row {}. Message: {}", err.getMessage());
            logger.error("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());


        } catch (SAXException e) {
            logger.error("error while reading", e);
        } catch (Throwable t) {
            logger.error("error while reading", t);
        }

        return personsList;
    }

    public String readXmlTagValue(String str, Element firstPersonElement) {

        NodeList firstNameList = firstPersonElement.getElementsByTagName(str);
        Element firstNameElement = (Element) firstNameList.item(0);
        NodeList textFNList = firstNameElement.getChildNodes();

        return textFNList.item(0).getNodeValue().trim();

    }

    @Override
    public void write(List<Person> list) {

        logger.info("writing the file: {}", file);
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("personList");
            document.appendChild(rootElement);

            for (Person pers : list) {
                Element person = document.createElement("person");
                rootElement.appendChild(person);

                person.appendChild(writeXmlTagValue("first-name", pers.getFirstName(), document));
                person.appendChild(writeXmlTagValue("last-name", pers.getLastName(), document));
                person.appendChild(writeXmlTagValue("gender", pers.getGender(), document));
                person.appendChild(writeXmlTagValue("age", String.valueOf(pers.getAge()), document));
                person.appendChild(writeXmlTagValue("city", pers.getCity(), document));

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(String.valueOf(file)));
            transformer.transform(source, result);

            logger.info("file {} write succesfully", file);

        } catch (ParserConfigurationException pce) {
            logger.error("error writing file {}. " + file + "Message: {}", pce.getMessage());

        } catch (TransformerException tfe) {
            logger.error("error writing file {}. " + file + "Message: {}", tfe.getMessage());

        }



    }

    public Element writeXmlTagValue(String name, String value, Document document) {

        Element fName = document.createElement(name);
        fName.appendChild(document.createTextNode(value));

        return fName;

    }

}