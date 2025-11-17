<template>
	<div class="node-wrap">
		<div class="node">
			<div class="node-header">
				<span class="node-title">{{ nodeConfig.nodeName }}</span>
				<el-button type="text" size="small" @click="deleteNode">删除</el-button>
			</div>
			<div class="node-content">
				<el-form :model="nodeConfig" label-width="80px">
					<el-form-item label="触发器类型">
						<el-select v-model="nodeConfig.asyncConfig.type" placeholder="请选择触发器类型">
							<el-option label="HTTP" value="http"></el-option>
							<el-option label="MQ" value="mq"></el-option>
							<el-option label="定时" value="timer"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="请求地址">
						<el-input v-model="nodeConfig.asyncConfig.url" placeholder="请求地址"></el-input>
					</el-form-item>
					<el-form-item label="请求参数">
						<el-button type="primary" size="small" @click="addParam">添加参数</el-button>
						<div v-for="(param, index) in nodeConfig.asyncConfig.param" :key="index" class="param-item">
							<el-input v-model="param.key" placeholder="参数名"></el-input>
							<el-input v-model="param.value" placeholder="参数值"></el-input>
							<el-button type="danger" size="small" @click="deleteParam(index)">删除</el-button>
						</div>
					</el-form-item>
				</el-form>
			</div>
		</div>
		<add-node v-model:childNodeP="nodeConfig.childNode" :currentNode="nodeConfig"></add-node>
	</div>
</template>
<script setup>
import {ref, watch} from 'vue'
import AddNode from '../addNode.vue'
import * as util from '../../../utils/objutil'

let props = defineProps({
	nodeConfig: {
		type: Object,
		default: () => ({}) 
	}
})

let emits = defineEmits(['updateData'])

watch(() => props.nodeConfig, (newValue) => {
	emits('updateData', newValue)
}, {deep: true})

const deleteNode = () => {
	props.nodeConfig.id = 'delete'
	emits('updateData', props.nodeConfig)
}

const addParam = () => {
	if (!props.nodeConfig.asyncConfig.param) {
		props.nodeConfig.asyncConfig.param = []
	}
	props.nodeConfig.asyncConfig.param.push({
		key: '',
		value: ''
	})
}

const deleteParam = (index) => {
	props.nodeConfig.asyncConfig.param.splice(index, 1)
}
</script>
<style scoped>
.node-wrap {
	position: relative;
}

.node {
	margin: 10px 0;
	padding: 10px;
	border: 1px solid #e8e8e8;
	border-radius: 4px;
	background-color: #fff;
}

.node-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

.node-title {
	font-weight: bold;
}

.param-item {
	display: flex;
	gap: 10px;
	margin-top: 10px;
}

.param-item .el-input {
	flex: 1;
}
</style>