package main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        logger.info("Starting App");

        String fileName = "scrData.csv";
        File file = new File(fileName);

        PersonDataAccess csvPersonAccess = new CsvPersonDataAccess(file);
        PersonsList personsList = new PersonsList(csvPersonAccess.read());

        personsList.averagePeopleAge();
        personsList.printNoOfMalesFemales();
        personsList.printLnameAlphabetical();
        personsList.findMinAndMaxAgeForCities();

        String fileNameXml = "scrData.xml";
        File fileXml = new File(fileNameXml);
        PersonDataAccess xmlPersonalDataAccess = new XmlPersonalDataAccess(fileXml);

        xmlPersonalDataAccess.read();


    }

}
