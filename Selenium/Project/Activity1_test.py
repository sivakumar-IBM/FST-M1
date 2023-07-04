import pytest


@pytest.mark.usefixtures("setup")
class TestPageTitle:

    def test_title_page(self):
        driver = self.driver
        driver.get("https://alchemy.hguy.co/jobs")
        assert driver.title == "Alchemy Jobs – Job Board Application"
