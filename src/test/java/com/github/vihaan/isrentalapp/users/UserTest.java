package com.github.vihaan.isrentalapp.users;


import com.github.vihaan.isrentalapp.rentals.ComputerRental;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.Mockito.mock;


/**
 * Parasoft Jtest UTA: Test class for User
 *
 * @see User
 * @author abcd-
 */
public class UserTest
{
    /**
     * Parasoft Jtest UTA: Test for equals(Object)
     *
     * @author abcd-
     * @see User#equals(Object)
     */
    @Test
    public void testEquals() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            Object o = null; // UTA: provided value
            boolean result = underTest.equals(o);

        });
    }

    /**
     * Parasoft Jtest UTA: Test for equals(Object)
     *
     * @author abcd-
     * @see User#equals(Object)
     */
    @Test
    public void testEquals2() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            User o = mock(User.class);
            boolean result = underTest.equals(o);

        });
    }

    /**
     * Parasoft Jtest UTA: Test for getComputerRentals()
     *
     * @author abcd-
     * @see User#getComputerRentals()
     */
    @Test
    public void testGetComputerRentals() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            List<ComputerRental> result = underTest.getComputerRentals();

        });
    }

    /**
     * Parasoft Jtest UTA: Test for getName()
     *
     * @author abcd-
     * @see User#getName()
     */
    @Test
    public void testGetName() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            String result = underTest.getName();

        });
    }

    /**
     * Parasoft Jtest UTA: Test for getPassword()
     *
     * @author abcd-
     * @see User#getPassword()
     */
    @Test
    public void testGetPassword() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            String result = underTest.getPassword();

        });
    }

    /**
     * Parasoft Jtest UTA: Test for getRoles()
     *
     * @author abcd-
     * @see User#getRoles()
     */
    @Test
    public void testGetRoles() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            Set<UserRole> result = underTest.getRoles();

        });
    }

    /**
     * Parasoft Jtest UTA: Test for getSurname()
     *
     * @author abcd-
     * @see User#getSurname()
     */
    @Test
    public void testGetSurname() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            String result = underTest.getSurname();

        });
    }

    /**
     * Parasoft Jtest UTA: Test for getUsername()
     *
     * @author abcd-
     * @see User#getUsername()
     */
    @Test
    public void testGetUsername() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            String result = underTest.getUsername();

        });
    }

    /**
     * Parasoft Jtest UTA: Test for hashCode()
     *
     * @author abcd-
     * @see User#hashCode()
     */
    @Test
    public void testHashCode() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            int result = underTest.hashCode();

        });
    }

    /**
     * Parasoft Jtest UTA: Test for setComputerRentals(List)
     *
     * @author abcd-
     * @see User#setComputerRentals(List)
     */
    @Test
    public void testSetComputerRentals() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            List<ComputerRental> computerRentals = new ArrayList<ComputerRental>(); // UTA: default value
            ComputerRental item = mock(ComputerRental.class);
            computerRentals.add(item);
            underTest.setComputerRentals(computerRentals);

        });
    }

    /**
     * Parasoft Jtest UTA: Test for setName(String)
     *
     * @author abcd-
     * @see User#setName(String)
     */
    @Test
    public void testSetName() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            String name = "name"; // UTA: default value
            underTest.setName(name);

        });
    }

    /**
     * Parasoft Jtest UTA: Test for setPassword(String)
     *
     * @author abcd-
     * @see User#setPassword(String)
     */
    @Test
    public void testSetPassword() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            String password = "password"; // UTA: default value
            underTest.setPassword(password);

        });
    }

    /**
     * Parasoft Jtest UTA: Test for setRoles(Set)
     *
     * @author abcd-
     * @see User#setRoles(Set)
     */
    @Test
    public void testSetRoles() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            Set<UserRole> roles = new HashSet<UserRole>(); // UTA: default value
            UserRole item = UserRole.ADMINISTRATOR; // UTA: default value
            roles.add(item);
            underTest.setRoles(roles);

        });
    }

    /**
     * Parasoft Jtest UTA: Test for setSurname(String)
     *
     * @author abcd-
     * @see User#setSurname(String)
     */
    @Test
    public void testSetSurname() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            String surname = "surname"; // UTA: default value
            underTest.setSurname(surname);

        });
    }

    /**
     * Parasoft Jtest UTA: Test for setUsername(String)
     *
     * @author abcd-
     * @see User#setUsername(String)
     */
    @Test
    public void testSetUsername() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            User underTest = new User();

            // When
            String username = "username"; // UTA: default value
            underTest.setUsername(username);

        });
    }

    /**
     * Parasoft Jtest UTA: Test for toString()
     *
     * @author abcd-
     * @see User#toString()
     */
    @Test
    public void testToString() throws Throwable {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            // Given
            String username = "username"; // UTA: default value
            String password = "password"; // UTA: default value
            String name = "name"; // UTA: default value
            String surname = "surname"; // UTA: default value
            Set<UserRole> roles = new HashSet<UserRole>(); // UTA: default value
            UserRole item = UserRole.ADMINISTRATOR; // UTA: default value
            roles.add(item);
            List<ComputerRental> computerRentals = new ArrayList<ComputerRental>(); // UTA: default value
            ComputerRental item2 = mock(ComputerRental.class);
            computerRentals.add(item2);
            User underTest = new User(username, password, name, surname, roles, computerRentals);

            // When
            String result = underTest.toString();

        });
    }
}