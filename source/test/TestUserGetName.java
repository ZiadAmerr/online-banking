package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class TestUserGetName {

    @Test
    public void checkName(){
        User user = new User("Bruce","b","f");
        assertSame("Bruce", user.getName());
    }
}
