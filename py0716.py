from selenium import webdriver
import unittest
import time
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException

#继承
class Baidu1(unittest.TestCase):
    # 定义全局变量，类的实例
    def setUp(self):
        print("setup")
        self.driver = webdriver.Chrome();
        self.url = "https://www.baidu.com/"
        self.driver.maximize_window()

    def tearDown(self):
        print("teardown")
        self.driver.quit()

    def test_hao(self):
        print("hao123")
        driver = self.driver
        driver.get(self.url)
        time.sleep(3)
        driver.find_element_by_link_text("hao123").click()
        time.sleep(5)

    def test_baidusearch(self):
        print("search")
        driver = self.driver
        driver.get(self.url)
        time.sleep(3)
        driver.find_element_by_id("kw").send_keys("时空中的绘旅人")
        driver.find_element_by_id("su").click()
        time.sleep(5)

    if __name__ == "__main__":
        unittest.main(verbosity=1)

