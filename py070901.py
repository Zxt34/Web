from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time

driver = webdriver.Chrome()
driver.maximize_window()
driver.get("http://127.0.0.1:86/zentao/user-login-L3plbnRhby8=.html")
driver.find_element_by_id("account").clear()
driver.find_element_by_name("password").clear()
time.sleep(2)
# 键盘事件：
driver.find_element_by_id("account").send_keys("admin")
driver.find_element_by_id("account").send_keys(Keys.TAB)
time.sleep(2)
driver.find_element_by_name("password").send_keys("200034Tt")
time.sleep(2)
driver.find_element_by_name("password").send_keys(Keys.CONTROL,'a')
time.sleep(2)
driver.find_element_by_name("password").send_keys(Keys.CONTROL,'x')
time.sleep(2)
driver.find_element_by_name("password").send_keys("200034Tt")
time.sleep(2)
# driver.find_element_by_id("submit").click()
driver.find_element_by_name("password").send_keys(Keys.ENTER)
time.sleep(5)
driver.quit()