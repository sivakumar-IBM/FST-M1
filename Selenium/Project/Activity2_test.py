# Import webdriver from selenium
import pytest
from selenium.webdriver.common.by import By


@pytest.mark.usefixtures("setup")
class TestHeadingTitle:

    def test_heading_title(self):
        driver = self.driver
        driver.get("https://alchemy.hguy.co/jobs")
    # Perform testing and assertions
        pageHeadingTitle = driver.find_element(By.XPATH,"//h1[@class='entry-title']").text
        print("Title of heading page is:" + pageHeadingTitle)
        assert pageHeadingTitle == "Welcome to Alchemy Jobs"