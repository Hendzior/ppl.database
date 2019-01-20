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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlPersonalDataAccess implements PersonDataAccess  {

    private static final Logger logger = LoggerFactory.getLogger(XmlPersonalDataAccess.class);

    File file;

    public XmlPersonalDataAccess(File file) {
        this.file = file;
    }


    @Override
    public List<Person> read() {
        Person person;
        List<Person> personsList = new ArrayList<>();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList listOfpersons = document.getElementsByTagName("person");
            int totalPersons = listOfpersons.getLength();

            for (int s = 0; s < totalPersons; s++) {

                Node firstPersonNode = listOfpersons.item(s);
                Element firstPersonElement = (Element)firstPersonNode;

                person = new Person();
                person.setFirstName(xmlRead("first-name", firstPersonElement));
                person.setLastName(xmlRead("last-name", firstPersonElement));
                person.setGender(xmlRead("gender", firstPersonElement));
                person.setAge(Integer.parseInt(xmlRead("age", firstPersonElement)));
                person.setCity(xmlRead("city", firstPersonElement));
                personsList.add(person);

            }

        }catch (SAXParseException err){
            logger.error("error reading row {}. Message: {}", err.getMessage());
            logger.error("** Parsing error" + ", line "
                    + err.getLineNumber () + ", uri " + err.getSystemId ());



        }catch (SAXException e){
            logger.error("error while reading", e);

        }catch (Throwable t){
            logger.error("error while reading", t);
        }

        return personsList;
    }
    public String xmlRead(String str, Element firstPersonElement){

        NodeList firstNameList = firstPersonElement.getElementsByTagName(str);
        Element firstNameElement = (Element)firstNameList.item(0);
        NodeList textFNList = firstNameElement.getChildNodes();
        return textFNList.item(0).getNodeValue().trim();

    }
    @Override
    public void write(List<Person> list) {

    }
}