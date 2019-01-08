import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(App.class);




		logger.debug("processing App");

		File file = new File("scrData.csv");

		String str;

		ArrayList<Person> persony = new ArrayList<>();

		HashMap<String, MinMaxAge> ageMap = new HashMap<>();

		BufferedReader br = null;
		MinMaxAge cityPer = null;
		Person person = null;
		int i = 1;
		try {
			logger.info("reading the file");
			br = new BufferedReader(new FileReader(file));
			br.readLine();

			while ((str = br.readLine()) != null) {

				i++;
				try {

					String[] values = str.split(",");

					person = new Person();
					person.setFirstName(values[0]);
					person.setLastName(values[1]);
					person.setGender(values[2]);
					person.setAge(Integer.parseInt(values[3]));
					person.setCity(values[4]);

					persony.add(person);
					cityPer = ageMap.get(person.getCity());

				} catch (NumberFormatException e) {
					logger.error("error reading file. Message:  " +e.getMessage());
					System.out.println("Data input error at line: " +i);
					continue;

				} catch (ArrayIndexOutOfBoundsException e) {
					logger.error("error reading file. Message:  " +e.getMessage());
					System.out.println("Data input error at line: " +i);
					continue;
				}

				if (cityPer == null) {

					cityPer = new MinMaxAge();
					cityPer.setMax(person.getAge());
					cityPer.setMin(person.getAge());

					ageMap.put(person.getCity(), cityPer);

				} else if (cityPer.getMax() < person.getAge()) {

					cityPer.setMax(person.getAge());

				}

				else if (cityPer.getMin() > person.getAge()) {
					cityPer.setMin(person.getAge());

				}

			}

		} catch (IOException e) {
			System.out.println("Unable to read file");

		} finally {
			logger.info("file" + file.toString() +"read succesfull");
			try {

				br.close();


			} catch (IOException e) {
				logger.error("error reading file. Message:  " +e.getMessage());
				System.out.println(e);

			}

		}

		List<Integer> age = new ArrayList<>();
		List<String> lname = new ArrayList<>();
		List<String> gen = new ArrayList<>();

		int sum = 0;

		int male = 0;
		int female = 0;

		for (Person per : persony) {

			age.add(per.getAge());
			lname.add(per.getLastName());
			gen.add(per.getGender());
			sum += per.getAge();
			if (per.getGender().equals("male")) {
				male++;
			}

			if (per.getGender().equals("female")) {
				female++;
			}

		}

		Collections.sort(lname);
		System.out.println("Last Names (alphabetical order): " + lname);
		System.out.println("Average age of all people: " + sum / age.size());
		System.out.println("Number of males: " + male);
		System.out.println("Number of females: " + female);

		System.out.println(ageMap);

	}

}
