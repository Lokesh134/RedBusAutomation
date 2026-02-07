package tests;

import Base.BaseTest;
import pages.RedBusHomePage;
import pages.RedBusSearchResultsPage;

public class RedBusTest extends BaseTest {

    public static void main(String[] args) {

        RedBusTest test = new RedBusTest();
        test.setup();

        RedBusHomePage homePage = new RedBusHomePage(test.driver, test.wait);
        homePage.selectSourceAndDestination(
                test.prop.getProperty("source"),
                test.prop.getProperty("destination"));
        homePage.clickSearch();

        RedBusSearchResultsPage resultPage =
                new RedBusSearchResultsPage(test.driver, test.wait);
        resultPage.applyFilters();
        resultPage.loadAllBuses();
        resultPage.printBusNames();

        test.tearDown();
    }
}
