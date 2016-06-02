package service;

import entities.Permission;
import entities.Person;
import entities.Role;
import entities.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class UserService implements UserServiceInterface {

    private List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public List<User> findUsersWithAddressesCountMoreThan(int numberOfAddresses) {
        return users.stream()
                .filter(user -> user.getPersonDetails().getAddresses().size() > numberOfAddresses)
                .collect(Collectors.toList());
    }

    public Person findOldestPerson() {
        return users.stream()
                .max((user1, user2) -> Integer.compare(user1.getPersonDetails().getAge(), user2.getPersonDetails().getAge()))
                .map(User::getPersonDetails)
                .get();
    }

    public User findUserWithLongestUsername() {
        return users.stream()
                .max((user1, user2) -> Integer.compare(user1.getName().length(), user2.getName().length()))
                .get();
    }

    public String getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18() {
        return users.stream()
                .filter(user -> user.getPersonDetails().getAge() > 18)
                .map(user -> user.getPersonDetails().getName() + ' ' + user.getPersonDetails().getSurname())
                .collect(Collectors.joining(","));
    }

    public List<String> getSortedPermissionsOfUsersWithNameStartingWith(String prefix) {
        return users.stream()
                .filter(user -> user.getPersonDetails().getName().startsWith(prefix))
                .map(user -> user.getPersonDetails().getRole().getPermissions())
                .flatMap(Collection::stream)
                .map(Permission::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    public double getUsersAverageAge() {
        return users.stream()
                .map(user -> user.getPersonDetails().getAge())
                .collect(Collectors.averagingInt(age -> age));
    }

    public void printCapitalizedPermissionNamesOfUsersWithSurnameEndingWith(String suffix) {
        users.stream()
                .filter(user -> user.getPersonDetails().getSurname().endsWith(suffix))
                .map(user -> user.getPersonDetails().getRole().getPermissions())
                .flatMap(Collection::stream)
                .map(Permission::getName)
                .map(permName -> Character.toUpperCase(permName.charAt(0)) + permName.substring(1))
                .forEach(System.out::println);
    }

    public Map<Role, List<User>> groupUsersByRole() {
        return users.stream()
                .collect(Collectors.groupingBy(user -> user.getPersonDetails().getRole()));
    }

    public Map<Boolean, List<User>> partitionUserByUnderAndOver18() {
        return users.stream()
                .collect(Collectors.partitioningBy(user -> user.getPersonDetails().getAge() >= 18));
    }
}
