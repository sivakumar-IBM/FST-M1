# Import webdriver from selenium
import pytest
from selenium.webdriver.common import keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


@pytest.mark.usefixtures("setup")
class TestBackEndSite:

    def test_backend_site(self):
        self.driver.implicitly_wait(10)
        self.driver.get("https://alchemy.hguy.co/jobs/wp-admin")
        # Enter Username
        self.driver.find_element(By.ID, "user_login").send_keys("root")
        # Pwd
        self.driver.find_element(By.ID, "user_pass").send_keys("pa$$w0rd")
        # click login
        self.driver.find_element(By.ID, "wp-submit").click()
        # verify Dashboard is available to confirm login success
        assert self.driver.find_element(By.XPATH,
                                        "//div[@class='wp-menu-name' and text()='Dashboard']").is_displayed() == True
