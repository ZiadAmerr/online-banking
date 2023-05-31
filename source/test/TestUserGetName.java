package source.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import source.src.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TestName {
    static User user;

    @Test
    void checkName(){
        user = new User("Bruce","b","f");
        assertSame("Bruce", user.getName());
    }
}
