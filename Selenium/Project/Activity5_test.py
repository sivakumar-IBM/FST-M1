# Import webdriver from selenium
import pytest
from selenium.webdriver.common.by import By


@pytest.mark.usefixtures("setup")
class TestSecondHeadingTitle:

    def test_heading_title(self):
        driver = self.driver
        driver.implicitly_wait(10)
        driver.get("https://alchemy.hguy.co/jobs")
        # find job link and click
        driver.find_element(By.XPATH, "//li[@id='menu-item-24']/a").click()
        pageTitle = driver.title
        print("New page title is :" + pageTitle)
        assert pageTitle == "Jobs – Alchemy Jobs"