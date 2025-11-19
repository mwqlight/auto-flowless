import requests
import base64
from PIL import Image
import io

# 获取验证码
def get_captcha():
    url = 'http://localhost:8081/av-stream/login/captcha'
    response = requests.get(url)
    result = response.json()
    if result['ok']:
        data = result['data']
        return data['verifyCodeKey'], data['verifyCodeBase64']
    else:
        print('获取验证码失败:', result['msg'])
        return None, None

# 显示验证码
def show_captcha(base64_data):
    # 去掉base64前缀
    base64_data = base64_data.replace('data:image/png;base64,', '')
    # 解码base64数据
    image_data = base64.b64decode(base64_data)
    # 创建图片对象
    image = Image.open(io.BytesIO(image_data))
    # 显示图片
    image.show()

# 登录
def login(verify_code_key, verify_code):
    url = 'http://localhost:8081/av-stream/login/login'
    data = {
        'username': 'admin',
        'password': '123456',
        'verifyCodeKey': verify_code_key,
        'verifyCode': verify_code
    }
    response = requests.post(url, json=data)
    return response.json()

# 主函数
def main():
    # 获取验证码
    verify_code_key, verify_code_base64 = get_captcha()
    if verify_code_key and verify_code_base64:
        # 显示验证码
        show_captcha(verify_code_base64)
        # 手动输入验证码
        verify_code = input('请输入验证码: ')
        # 登录
        result = login(verify_code_key, verify_code)
        print('登录结果:', result)
    else:
        print('获取验证码失败')

if __name__ == '__main__':
    main()
