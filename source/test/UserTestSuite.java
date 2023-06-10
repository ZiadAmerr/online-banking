package source.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestUserBuy.class,
        TestUserCreateAccount.class,
        TestUserGetName.class,
        TestUserGetNotifications.class,
        TestUserGetUsername.class,
        TestUserLoggedIn.class,
        TestUserLogin.class,
        TestUserLogout.class,
        TestUserTransfer.class,
        TestUserUseAccount.class,
        TestUserViewTransc.class,
})
public class UserTestSuite { }