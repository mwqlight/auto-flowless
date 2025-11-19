import requests
import base64
from PIL import Image
from io import BytesIO

# 定义获取验证码的函数
def get_captcha():
    url = "http://localhost:8081/av-stream/login/captcha"
    response = requests.get(url)
    if response.status_code == 200:
        captcha_data = response.json()
        data = captcha_data.get('data', {})
        verify_code_key = data.get('verifyCodeKey')
        verify_code_base64 = data.get('verifyCodeBase64', '')
        # 解析base64编码的图片
        image_data = base64.b64decode(verify_code_base64.split(',')[1])  # 移除数据前缀
        image = Image.open(BytesIO(image_data))
        image.show()
        return verify_code_key
    else:
        print("获取验证码失败")
        return None

# 定义登录函数
def login(username, password, verify_code_key, verify_code):
    url = "http://localhost:8081/av-stream/login/login"
    data = {
        "username": username,
        "password": password,
        "verifyCodeKey": verify_code_key,
        "verifyCode": verify_code
    }
    response = requests.post(url, json=data)
    if response.status_code == 200:
        return response.json()
    else:
        return {"ok": False, "msg": "登录请求失败"}

# 主函数
if __name__ == "__main__":
    username = "zms"
    password = "123456"
    verify_code_key = get_captcha()
    if verify_code_key:
        verify_code = input("请输入验证码: ")
        result = login(username, password, verify_code_key, verify_code)
        print("登录结果:", result)