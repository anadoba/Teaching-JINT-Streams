package service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UserServiceTest {

    private PrintStream originalSystemOut;
    private final ByteArrayOutputStream testOutContent = new ByteArrayOutputStream();
    private UserService service;

    @Before
    public void initObjects() {
        UserFactory factory = new UserFactory();
        service = new UserService(factory.getExampleUsers());
        originalSystemOut = System.out;
        System.setOut(new PrintStream(testOutContent));
    }

    @After
    public void restoreDefaults() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void findUsersWithAddressesCountMoreThan() {
        assertFalse(service.findUsersWithAddressesCountMoreThan(1).isEmpty());
    }

    @Test
    public void findUserWithLongestUsername() {
        assertNotNull(service.findUserWithLongestUsername());
    }

    @Test
    public void getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18() {
        assertFalse(service.getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18().isEmpty());
    }

    @Test
    public void getSortedPermissionsOfUsersWithNameStartingWith() {
        assertFalse(service.getSortedPermissionsOfUsersWithNameStartingWith("Ad").isEmpty());
    }

    @Test
    public void getUsersAverageAge() {
        assertTrue(service.getUsersAverageAge() > 0);
    }

    @Test
    public void printCapitalizedPermissionNamesOfUsersWithSurnameEndingWith() {
        service.printCapitalizedPermissionNamesOfUsersWithSurnameEndingWith("ak");
        assertTrue(testOutContent.toString().length() > 0);
    }

    @Test
    public void groupUsersByRole() {
        assertFalse(service.groupUsersByRole().isEmpty());
    }

    @Test
    public void partitionUserByUnderAndOver18() {
        assertFalse(service.partitionUserByUnderAndOver18().isEmpty());
    }

}
