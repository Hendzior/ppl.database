package main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;


public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        logger.info("Starting App");

        String fileName = "scrData.csv";
        File file = new File(fileName);
        PersonDataAccess csvPersonAccess = new CsvPersonDataAccess(file);

        // Csv file reader
        List<Person> personsList = csvPersonAccess.read();

        String fileNameXml = "scrData.xml";
        File fileXml = new File(fileNameXml);
        PersonDataAccess xmlPersonalDataAccess = new XmlPersonalDataAccess(fileXml);

        //Xml file reader
        //List<Person> personsList = xmlPersonalDataAccess.read();

        PeopleStatisticsCalculator peopleStatisticsCalculator = new PeopleStatisticsCalculator(personsList);

        logger.info("Last Names (alphabetical order): {}", peopleStatisticsCalculator.getLnameAlphabetical());
        logger.info("Average age of all people: {}", peopleStatisticsCalculator.getAveragePeopleAge());
        logger.info("Number of females: {}", peopleStatisticsCalculator.getNoOfFemales());
        logger.info("Number of males: {}", peopleStatisticsCalculator.getNoOfMales());
        logger.info("Min and max age for all cities: {}", peopleStatisticsCalculator.getMinAndMaxAgeForCities());
        logger.info("List of all people: {}", personsList);

        // Csv file writer
        // csvPersonAccess.write((List<Person>) personsList);

        //Xml file writer
        xmlPersonalDataAccess.write(personsList);


    }

}
