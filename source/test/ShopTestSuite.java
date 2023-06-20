package source.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestShopBill.class,
        TestShopItems.class,
        TestShopGeneral.class
})
public class ShopTestSuite { }