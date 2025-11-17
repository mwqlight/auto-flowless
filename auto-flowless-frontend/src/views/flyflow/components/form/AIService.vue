<script setup lang="ts">
import {ref} from "vue";

const props = defineProps({
  id: {
    type: String,
    default: ""
  },
  form: {
    type: Object,
    default: () => ({})
  }
});

const emit = defineEmits(['update:form']);

const messages = ref([
  { text: '您好！欢迎使用智能客服，有什么可以帮您的吗？', sender: 'bot' }
]);

const inputMessage = ref('');

const sendMessage = () => {
  if (!inputMessage.value.trim()) return;
  
  // 添加用户消息
  messages.value.push({ text: inputMessage.value, sender: 'user' });
  
  // 模拟客服回复
  setTimeout(() => {
    messages.value.push({ 
      text: `感谢您的咨询，您的问题是："${inputMessage.value}"，我们将尽快为您处理。`, 
      sender: 'bot' 
    });
  }, 1500);
  
  // 更新表单值
  const updatedForm = {...props.form};
  updatedForm.props.value = inputMessage.value;
  emit('update:form', updatedForm);
  
  inputMessage.value = '';
};
</script>

<template>
  <div class="ai-service-component">
    <div class="chat-messages">
      <div 
        v-for="(message, index) in messages" 
        :key="index"
        class="message"
        :class="message.sender === 'bot' ? 'bot-message' : 'user-message'"
      >
        {{ message.text }}
      </div>
    </div>
    <div class="chat-input-area">
      <el-input
        v-model="inputMessage"
        placeholder="描述您的问题..."
        @keyup.enter="sendMessage"
      />
      <el-button type="primary" @click="sendMessage" style="margin-left: 10px;">
        提交
      </el-button>
    </div>
  </div>
</template>

<style scoped lang="less">
.ai-service-component {
  width: 100%;
  height: 300px;
  border: 1px solid #e5e7eb;
  border-radius: 8px;
  overflow: hidden;
}

.chat-messages {
  height: 220px;
  padding: 10px;
  overflow-y: auto;
  background-color: #f9fafb;
}

.message {
  max-width: 80%;
  margin-bottom: 10px;
  padding: 8px 12px;
  border-radius: 20px;
  line-height: 1.4;
}

.bot-message {
  background-color: #ffffff;
  border: 1px solid #e5e7eb;
  align-self: flex-start;
}

.user-message {
  background-color: #67c23a;
  color: white;
  align-self: flex-end;
  margin-left: auto;
}

.chat-input-area {
  display: flex;
  padding: 10px;
  background-color: #ffffff;
  border-top: 1px solid #e5e7eb;
}
</style>