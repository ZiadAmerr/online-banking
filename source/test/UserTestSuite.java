package source.test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
     TestUserLogin.class,
     TestUserCreateAccount.class,
     TestUserUseAccount.class,
        TestUserBuy.class,
        TestUserLogout.class,
        TestUserTransfer.class,
       TestUserViewTransc.class,
      TestUserGetNot.class,
      TestName.class,
     TestUserLoggedIn.class,
     TestUsername.class
})

public class UserTestSuite {

}
