package main.java;

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

    File file;

    public XmlPersonalDataAccess(File file) {
        this.file = file;
    }

    @Override
    public List<Person> read() {
        Person person;
        ArrayList<Person> personsList = new ArrayList<>();

      try {
          DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
          Document document = documentBuilder.parse(file);
          document.getDocumentElement().normalize();

          NodeList listOfpersons = document.getElementsByTagName("person");
          int totalPersons = listOfpersons.getLength();


          for (int s = 0; s < totalPersons; s++) {

              person = new Person();
              Node firstPersonNode = listOfpersons.item(s);
              Element firstPersonElement = (Element)firstPersonNode;

              NodeList firstNameList = firstPersonElement.getElementsByTagName("first-name");
              Element firstNameElement = (Element)firstNameList.item(0);
              NodeList textFNList = firstNameElement.getChildNodes();
              person.setFirstName((textFNList.item(0)).getNodeValue().trim());

              NodeList lastNameList = firstPersonElement.getElementsByTagName("last-name");
              Element lastNameElement = (Element)lastNameList.item(0);
              NodeList textLNList = lastNameElement.getChildNodes();
              person.setLastName((textLNList.item(0)).getNodeValue().trim());

              NodeList genderList = firstPersonElement.getElementsByTagName("gender");
              Element genderElement = (Element)genderList.item(0);
              NodeList textGList = genderElement.getChildNodes();
              person.setGender((textGList.item(0)).getNodeValue().trim());

              NodeList ageList = firstPersonElement.getElementsByTagName("age");
              Element ageElement = (Element)ageList.item(0);
              NodeList textAList = ageElement.getChildNodes();
              person.setAge(Integer.parseInt((textAList.item(0)).getNodeValue().trim()));

              NodeList cityList = firstPersonElement.getElementsByTagName("city");
              Element cityElement = (Element)cityList.item(0);
              NodeList textCList = cityElement.getChildNodes();
              person.setCity((textCList.item(0)).getNodeValue().trim());

              personsList.add(person);


          }

      }catch (SAXParseException err){
          System.out.println ("** Parsing error" + ", line "
                  + err.getLineNumber () + ", uri " + err.getSystemId ());
          System.out.println(" " + err.getMessage ());
      }catch (SAXException e){
          Exception x = e.getException ();
          ((x == null) ? e : x).printStackTrace ();
      }catch (Throwable t){
          t.printStackTrace ();
      }
        System.out.println(personsList);
        return personsList;
    }

    @Override
    public void write(List<Person> list) {

    }
}
