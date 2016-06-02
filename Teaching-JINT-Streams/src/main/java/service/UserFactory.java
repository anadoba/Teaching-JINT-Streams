package service;

import entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserFactory {

    public List<User> getExampleUsers() {

        final List<Address> addresses = new ArrayList<Address>() {{
            add(new Address("Jasna", 5, 6, "Wegorzewo", "11-300", "Poland"));
            add(new Address("Portowa", 11, 86, "Gdynia", "82-300", "Poland"));
            add(new Address("Lawendowa", 1, 3, "Gdansk", "80-100", "Poland"));
            add(new Address("Lozy", 9, 3, "Gdansk", "80-820", "Poland"));
            add(new Address("Marynarki wojennej", 9, 22, "Olsztyn", "23-820", "Poland"));
        }};

        Permission read = new Permission("read");
        Permission write = new Permission("write");
        Permission list = new Permission("list");
        Permission details = new Permission("details");

        final Role admin = new Role("admin", Arrays.asList(read, write, list, details));
        final Role user = new Role("user", Arrays.asList(list));
        final Role customer = new Role("customer", Arrays.asList(read, list));
        final Role employee = new Role("employee", Arrays.asList(read, write, list));

        final List<Person> people = new ArrayList<Person>() {{
            add(new Person("Adam", "Siadam", Arrays.asList("684658312"), Arrays.asList(addresses.get(0)), admin, 23));
            add(new Person("Jurek", "Ogurek", Arrays.asList("675823666"), Arrays.asList(addresses.get(1), addresses.get(2)), user, 32));
            add(new Person("Giedymin", "Litwin", Arrays.asList("504867222", "594475999"), Arrays.asList(addresses.get(3)), customer, 38));
            add(new Person("Andrzej", "Rajczak", Arrays.asList("702745857"), Arrays.asList(addresses.get(4)), user, 21));
            add(new Person("Tomek", "Ranek", Arrays.asList("811453076"), Arrays.asList(addresses.get(2)), employee, 25));
            add(new Person("Magdalena", "Ratajczak", Arrays.asList("603576923"), Arrays.asList(addresses.get(4), addresses.get(0)), employee, 25));
        }};

        List<User> users = new ArrayList<User>() {{
            add(new User("adam", "hasloraz", people.get(0)));
            add(new User("jurek", "haslodwa", people.get(1)));
            add(new User("giedymin", "haslotrzy", people.get(2)));
            add(new User("andrzej", "turbohaslo", people.get(3)));
            add(new User("tomus", "turbo34haslo", people.get(4)));
            add(new User("madzia", "bezhasla", people.get(5)));
        }};

        return users;
    }
}
