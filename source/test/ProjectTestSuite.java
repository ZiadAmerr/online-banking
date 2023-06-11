package source.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import source.src.User;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountTest.class,
        UserTestSuite.class,
        BillTest.class,
        ItemTest.class,
        BuyableTest.class,
        NotificationTest.class,
        TransactionTest.class,

})
public class ProjectTestSuite { }