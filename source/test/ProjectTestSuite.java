package source.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import source.src.User;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTestSuite.class,
        BillTest.class,
        ItemTest.class,
        BuyableTest.class,
        NotificationTest.class,
        TransactionTest.class,
        AccountTest.class,
})
public class ProjectTestSuite { }