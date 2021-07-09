from selenium import webdriver
import time

driver = webdriver.Chrome()

# 自定义设置页面宽高：
# driver.set_window_size(480,800)
# 窗口最大化：
driver.maximize_window()

driver.get("https://www.baidu.com/")
# driver.find_element_by_id("kw").send_keys("东京奥运会")
# driver.find_element_by_id("su").click()
# time.sleep(5)
# driver.find_element_by_id("kw").clear()
# time.sleep(2)

driver.find_element_by_id("kw").send_keys("福原爱")
# submit提交：
driver.find_element_by_id("su").submit()
time.sleep(5)

# text获取文本：
# text = driver.find_element_by_id("bottom_layer").text
# print(text)

# 智能等待：
# driver.implicitly_wait(10)
# driver.find_element_by_link_text("福原爱 - 百度百科").click()
# time.sleep(8)

# 打印title和url：
# title = driver.title
# url = driver.current_url
# print(title)
# print(url)

# 浏览器的前进后退：
# driver.back()
# time.sleep(5)
# driver.forward()

# 控制浏览器滚动条：
# js = "var q = document.documentElement.scrollTop = 10000"
# driver.execute_script(js)
# time.sleep(3)
# js = "var q = document.documentElement.scrollTop = 0"
# driver.execute_script(js)
# time.sleep(3)

# 键盘事件：
driver.quit()