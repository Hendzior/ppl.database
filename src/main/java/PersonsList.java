package main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PersonsList {

    private static final Logger logger = LoggerFactory.getLogger(App.class);
    List<Person> personList;

    public PersonsList(List<Person> personList) {
        this.personList = personList;
    }

    public void printLnameAlphabetical() {

        ArrayList<String> lNameList = new ArrayList<>();

        for (Person person : personList) {

            lNameList.add(person.getLastName());
        }

        Collections.sort(lNameList);
        logger.info("Last Names (alphabetical order): {}", lNameList);
    }

    public void averagePeopleAge() {
        int avgAge;
        int sumAge = 0;
        for (Person person : personList) {
            sumAge += person.getAge();
        }

        avgAge = sumAge / personList.size();
        logger.info("Average age of all people: {}", avgAge);
    }

    public void printNoOfMalesFemales() {
        int noMales = 0;
        int noFemales = 0;
        for (Person person : personList) {

            if (person.getGender().equalsIgnoreCase("male")) {
                noMales++;

            } else {
                noFemales++;
            }

        }
        logger.info("Number of males: {}", noMales);
        logger.info("Number of females: {}", noFemales);

    }

    public HashMap<String, MinMaxAge> findMinAndMaxAgeForCities() {
        HashMap<String, MinMaxAge> minMaxAgeForCities = new HashMap<>();
        MinMaxAge minMaxAge;

        for (Person person : personList) {

            minMaxAge = minMaxAgeForCities.get(person.getCity());

            if (minMaxAge == null) {

                minMaxAge = new MinMaxAge();
                minMaxAge.setMax(person.getAge());
                minMaxAge.setMin(person.getAge());
                minMaxAgeForCities.put(person.getCity(), minMaxAge);

            } else if (minMaxAge.getMax() < person.getAge()) {
                minMaxAge.setMax(person.getAge());
            } else if (minMaxAge.getMin() > person.getAge()) {
                minMaxAge.setMin(person.getAge());

            }
        }
        logger.info(String.valueOf(minMaxAgeForCities));
        return minMaxAgeForCities;
    }
}
