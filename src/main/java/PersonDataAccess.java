package main.java;

import java.util.List;

public interface PersonDataAccess {

   List<Person> read();

   void write(List<Person> list);

}
