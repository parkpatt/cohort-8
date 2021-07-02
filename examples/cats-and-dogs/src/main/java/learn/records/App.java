package learn.records;

import com.github.javafaker.Faker;
import learn.records.models.Cat;

import java.util.*;

public class App {
    public static void main(String[] args) {

        Faker faker = new Faker();

        ArrayList<Cat> cats = new ArrayList<>();
        HashMap<String, Cat> catMap = new HashMap<>();
        HashSet<Cat> uniqueCats = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            Cat cat = new Cat();
            cat.setBreed(faker.cat().breed());
            cat.setName(faker.cat().name());
            cat.setRegistry(faker.cat().registry());

            cats.add(cat);
            catMap.put(cat.getName(), cat);
            uniqueCats.add(cat);
        }


        for (String key : catMap.keySet()) {
            System.out.println(catMap.get(key));
        }


        for (Map.Entry entry : catMap.entrySet()) {
            System.out.println(entry.getValue());
        }





        System.out.println(cats.size());
        System.out.println(catMap.size());
        System.out.println(uniqueCats.size());



        //System.out.printf("Cat name: %s, breed: %s", cat.getName(), cat.getBreed());

    }
}
