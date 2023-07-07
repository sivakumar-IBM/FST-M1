# Import webdriver from selenium
import pytest
from selenium.webdriver.common import keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


@pytest.mark.usefixtures("setup")
class TestPostJobUsingBackEnd:

    def test_posting_job_backend(self):
        self.driver.implicitly_wait(10)
        self.driver.get("https://alchemy.hguy.co/jobs/wp-admin")
        # Enter Username
        self.driver.find_element(By.ID, "user_login").send_keys("root");
        # Pwd
        self.driver.find_element(By.ID, "user_pass").send_keys("pa$$w0rd");
        # click login
        self.driver.find_element(By.ID, "wp-submit").click();
        # verify Dashboard is available to confirm login success
        assert self.driver.find_element(By.XPATH,
                                        "//div[@class='wp-menu-name' and text()='Dashboard']").is_displayed() == True
        # click Joblisting
        self.driver.find_element(By.XPATH, "//div[@class='wp-menu-name' and text()='Job Listings']").click()
        # click Add New
        WebDriverWait(self.driver, 20).until(
            EC.visibility_of_element_located(
                (By.XPATH, "//a[@class='page-title-action' and text()='Add New']"))).click()
        # Enter Job title
        self.driver.find_element(By.ID, "post-title-0").send_keys("Test Job")
        # Location
        self.driver.find_element(By.ID, "_job_location").send_keys("India")
        # Click publish button
        self.driver.find_element(By.XPATH, "//button[contains(@class,'publish')]").click()
        self.driver.find_element(By.XPATH,
                                 "//button[@class='components-button editor-post-publish-button editor-post-publish-button__button is-primary' and text()='Publish']").click()
        WebDriverWait(self.driver, 20).until(
            EC.visibility_of_element_located(
                (By.XPATH, "//div[@class='editor-post-publish-panel__header-published' and text()='Published']")))
        # Go to home page
        self.driver.find_element(By.XPATH,
                                 "//a[@class='components-button edit-post-fullscreen-mode-close has-icon']").click()
        # click Joblisting
        self.driver.find_element(By.XPATH, "//div[@class='wp-menu-name' and text()='Job Listings']").click()
        # Assert the job posted
        assert self.driver.find_element(By.XPATH, "//div[@class='job_position']/a[1]").text == "Test Job"