package my.shop_structure.testing;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;


@RunWith(Suite.class)
@Suite.SuiteClasses({
						BucketManagmentActionTest.class, 
						MainMenuActionTest.class, 
						ProductListActionTest.class
					})
public class RunAllSuite {
}
