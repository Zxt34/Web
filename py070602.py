from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
import time

driver = webdriver.Chrome()

driver.maximize_window()

driver.get("https://www.baidu.com/")
driver.find_element_by_id("kw").send_keys("横道世之介")
su = driver.find_element_by_id("su")
# 双击：
# ActionChains(driver).double_click(su).perform()

# 右击：
ActionChains(driver).context_click(su).perform()

time.sleep(5)
driver.quit()