# Import webdriver from selenium
import pytest
from selenium.webdriver.common import keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


@pytest.mark.usefixtures("setup")
class TestPostJob:

    def test_post_job(self):
        self.driver.implicitly_wait(10)
        self.driver.get("https://alchemy.hguy.co/jobs")
        # find job link and click
        self.driver.find_element(By.XPATH, "//li[@id='menu-item-26']/a").click()
        # Sign in if not already
        signinBtn = self.driver.find_element(By.XPATH, "//div[@class='field account-sign-in']/a")
        if signinBtn.is_displayed():
            signinBtn.click()
            self.driver.find_element(By.ID, "user_login").send_keys("root")
            self.driver.find_element(By.ID, "user_pass").send_keys("pa$$w0rd")
            self.driver.find_element(By.ID, "wp-submit").click()
        else:
            print("Already you signed")

        selectJobType = Select(self.driver.find_element(By.ID, "job_type"))
        JobTitle = "Test Job"
        self.driver.find_element(By.ID, "job_title").send_keys(JobTitle)
        self.driver.find_element(By.ID, "job_location").send_keys("India")
        selectJobType.select_by_visible_text("Internship")
        self.driver.find_element(By.XPATH, "//iframe[@id='job_description_ifr']").click()
        self.driver.find_element(By.XPATH, "//iframe[@id='job_description_ifr']").send_keys("Description of job ")
        self.driver.find_element(By.NAME, "submit_job").click()
        submitListBtn = WebDriverWait(self.driver, 20).until(
            EC.visibility_of_element_located((By.ID, "job_preview_submit_button")))
        submitListBtn.click()
        # validate success msg
        successMsg = WebDriverWait(self.driver, 20).until(
            EC.visibility_of_element_located((By.XPATH, "//a[text()='click here']/parent::div")))
        print(successMsg.text)
        # validate success msg
        assert successMsg.text == "Job listed successfully. To view your listing click here."
        self.driver.find_element(By.XPATH, "//li[@id='menu-item-24']/a[text()='Jobs']").click()
        searchText = self.driver.find_element(By.ID, "search_keywords")
        searchText.send_keys(JobTitle, keys.Keys.ENTER)
        firstJob = WebDriverWait(self.driver, 20).until(
            EC.visibility_of_element_located((By.XPATH, "//div[@class='position']/h3[1]"))).text
        print("first job is :" + firstJob)
        assert firstJob == JobTitle
