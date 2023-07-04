# Import webdriver from selenium
import pytest
from selenium.webdriver.common.by import By


@pytest.mark.usefixtures("setup")
class TestHeaderImageURL:

    def test_header_imageurl(self):
        driver = self.driver
        driver.get("https://alchemy.hguy.co/jobs")
        # Perform testing and assertions
        headerImageURL = driver.find_element(By.XPATH,
                                             "//img[@class='attachment-large size-large wp-post-image']").get_attribute(
            "src")
        print("Title of header image is:" + headerImageURL)
