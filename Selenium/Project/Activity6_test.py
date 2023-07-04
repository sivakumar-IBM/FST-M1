# Import webdriver from selenium
import pytest
from selenium.webdriver.common import keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


@pytest.mark.usefixtures("setup")
class TestJobListing:

    def test_listing_job(self):
        self.driver.implicitly_wait(10)
        self.driver.get("https://alchemy.hguy.co/jobs")
        # find job link and click
        self.driver.find_element(By.XPATH, "//li[@id='menu-item-24']/a").click()
        pageTitle = self.driver.title
        print("New page title is :" + pageTitle)
        assert pageTitle == "Jobs – Alchemy Jobs"
        searchText = self.driver.find_element(By.ID, "search_keywords")
        searchText.send_keys("Banking", keys.Keys.ENTER)
        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((By.XPATH, "//span[contains(text(),'Search completed')]")))
        listofjobs = self.driver.find_elements(By.XPATH, "//div[@class='position']/h3")
        print(f'No of jobs found is {len(listofjobs)}')
        for element in listofjobs:
            if "Banking" in element.text:
                element.click()
        # wait for apply button and click
        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((By.XPATH, "//input[@class='application_button button']"))).click()
        # //wait for email to appear and get the email text
        email = WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((By.XPATH, "//a[@class='job_application_email']"))).text;
        print("Mail your details to : " + email)
