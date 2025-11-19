import requests

# 定义获取验证码的函数
def get_captcha():
    url = "http://localhost:8081/av-stream/login/captcha"
    response = requests.get(url)
    if response.status_code == 200:
        # 打印完整的JSON响应
        print("完整响应:", response.text)
        captcha_data = response.json()
        verify_code_key = captcha_data.get('verifyCodeKey')
        base64_captcha = captcha_data.get('captcha', '')
        return verify_code_key, base64_captcha
    else:
        print("获取验证码失败")
        return None, None

# 主函数
if __name__ == "__main__":
    verify_code_key, base64_captcha = get_captcha()
    print("verifyCodeKey:", verify_code_key)
    print("captcha长度:", len(base64_captcha))