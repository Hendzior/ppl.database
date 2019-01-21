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

        /* Csv file reader
        PeopleStatisticsCalculator personsList = new PeopleStatisticsCalculator(csvPersonAccess.read());
        */

        String fileNameXml = "scrData.xml";
        File fileXml = new File(fileNameXml);
        PersonDataAccess xmlPersonalDataAccess = new XmlPersonalDataAccess(fileXml);

        //Xml file reader
        PeopleStatisticsCalculator personsList = new PeopleStatisticsCalculator(xmlPersonalDataAccess.read());

        logger.info("Last Names (alphabetical order): {}", personsList.getLnameAlphabetical());
        logger.info("Average age of all people: {}", personsList.getAveragePeopleAge());
        logger.info("Number of females: {}", personsList.getNoOfFemales());
        logger.info("Number of males: {}", personsList.getNoOfMales());
        logger.info("Min and max age for all cities: {}", personsList.getMinAndMaxAgeForCities());
        logger.info("List of all people: {}", xmlPersonalDataAccess.read());
    //  logger.info("List of all people: {}", csvPersonAccess.read());

        // Csv file writer
        csvPersonAccess.write(xmlPersonalDataAccess.read());

        //Xml file writer
        // xmlPersonalDataAccess.write(csvPersonAccess.read());


    }

}
