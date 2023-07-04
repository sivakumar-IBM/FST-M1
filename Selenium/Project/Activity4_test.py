# Import webdriver from selenium
import pytest
from selenium.webdriver.common.by import By


@pytest.mark.usefixtures("setup")
class TestSecondHeadingTitle:

    def test_heading_title(self):
        driver = self.driver
        driver.get("https://alchemy.hguy.co/jobs")
        # Perform testing and assertions
        secondHeadingTitle = driver.find_element(By.XPATH, "//h2").text
        print("Title of header image is:" + secondHeadingTitle)
        assert secondHeadingTitle == "Quia quis non"
