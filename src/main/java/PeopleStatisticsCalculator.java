package main.java;

import java.util.*;

public class PeopleStatisticsCalculator {


    List<Person> personList;

    public PeopleStatisticsCalculator(List<Person> personList) {
        this.personList = personList;
    }

    public List<String> getLnameAlphabetical() {

       List<String> lNameList = new ArrayList<>();

        for (Person person : personList) {

            lNameList.add(person.getLastName());
        }

        Collections.sort(lNameList);

        return lNameList;
    }

    public int getAveragePeopleAge() {
        int avgAge;
        int sumAge = 0;
        for (Person person : personList) {
            sumAge += person.getAge();
        }

        avgAge = sumAge / personList.size();

        return avgAge;
    }

    public int getNoOfMales() {

        int noMales = 0;
        for (Person person : personList) {

            if (person.getGender().equalsIgnoreCase("male")) {
                noMales++;

            }
        }

        return noMales;
    }
    public int getNoOfFemales() {

        int noFemales = 0;
        for (Person person : personList) {

            if (person.getGender().equalsIgnoreCase("female")) {
                noFemales++;
            }
        }

            return noFemales;
        }

    public Map<String, MinMaxAge> getMinAndMaxAgeForCities() {
        Map<String, MinMaxAge> minMaxAgeForCities = new HashMap<>();
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

        return minMaxAgeForCities;
    }
}
