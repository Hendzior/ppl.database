package main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CsvPersonDataAccess implements PersonDataAccess {

    private static final Logger logger = LoggerFactory.getLogger(CsvPersonDataAccess.class);

    private File file;

    public CsvPersonDataAccess(File file) {
        this.file = file;
    }

    @Override
    public List<Person> read() {
        List<Person> personsList = new ArrayList<>();

        Person person;
        int i = 1;
        logger.info("reading the file: {}", file);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String str;

            while ((str = br.readLine()) != null) {
                i++;
                try {
                    logger.debug("{} row: {}", i, str);
                    String[] values = str.split(",");

                    person = new Person();
                    person.setFirstName(values[0]);
                    person.setLastName(values[1]);
                    person.setGender(values[2]);
                    person.setAge(Integer.parseInt(values[3]));
                    person.setCity(values[4]);
                    personsList.add(person);

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    logger.error("error reading row {}. Message: {}", i, e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.error("error while reading", e);
        }
        logger.info("file {} read succesfully", file);
        return personsList;
    }

    @Override
    public void write (List<Person> list) {
        logger.info("writing the file: {}", file);
        try (FileWriter fw = new FileWriter(file,true))

        {
            fw.append("first name,last name,gender,age,city");
            for(Person person: list) {
                fw.append("\n");
                fw.append(person.getFirstName());
                fw.append(",");
                fw.append(person.getLastName());
                fw.append(",");
                fw.append(person.getGender());
                fw.append(",");
                fw.append(String.valueOf(person.getAge()));
                fw.append(",");
                fw.append(person.getCity());

            }
            fw.flush();
        } catch (IOException e) {

            logger.error("error while writing", e);
        }

        logger.info("file {} write succesfully", file);
    }

    }

