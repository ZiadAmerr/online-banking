package source.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import source.src.User;

import static org.junit.jupiter.api.Assertions.assertSame;

  class TestUsername {
    static User user;
    private static final String USERNAME = "batman";
    private static final String PASSWORD = "pswd";

    @BeforeAll
    static void setUp() {
        user =  new User("Bruce", USERNAME, PASSWORD);
    }

    @AfterEach
    void breakUp(){
        user.logout();
    }

    @Test
    void checkUsername(){
        assertSame(USERNAME, user.getUsername());
    }
}
