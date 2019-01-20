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
        PeopleStatisticsCalculator personsList = new PeopleStatisticsCalculator(csvPersonAccess.read());

        logger.info("Last Names (alphabetical order): {}", personsList.getLnameAlphabetical());
        logger.info("Average age of all people: {}", personsList.getAveragePeopleAge());

        logger.info("Number of females: {}", personsList.getNoOfFemales());
        logger.info("Number of males: {}", personsList.getNoOfMales());
        //logger.info(String.valueOf(personsList.findMinAndMaxAgeForCities()));
        logger.info("Min and max age for all cities: {}", personsList.getMinAndMaxAgeForCities());


        String fileNameXml = "scrData.xml";
        File fileXml = new File(fileNameXml);
        PersonDataAccess xmlPersonalDataAccess = new XmlPersonalDataAccess(fileXml);

        logger.info("List of all people: {}", xmlPersonalDataAccess.read());



    }

}
