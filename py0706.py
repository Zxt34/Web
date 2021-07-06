from selenium import webdriver
import time

driver = webdriver.Chrome()

driver.get("https://www.baidu.com/")

# id
# driver.find_element_by_id("kw").send_keys("光与夜之恋")
# driver.find_element_by_id("su").click()
# name
# driver.find_element_by_name("wd").send_keys("张新成")
# driver.find_element_by_id("su").click()
# link
# driver.find_element_by_link_text("新闻").click()
# partial
# driver.find_element_by_partial_link_text("hao").click()
# tag
# xpath
# driver.find_element_by_xpath("//*[@id='kw']").send_keys("萧逸")
# driver.find_element_by_xpath("//*[@name='wd']").send_keys("萧逸")
# driver.find_element_by_xpath("//*[@id='su']").click()
# css
driver.find_element_by_css_selector("#kw").send_keys("查理苏")
driver.find_element_by_css_selector("#su").click()

time.sleep(8)
driver.quit()