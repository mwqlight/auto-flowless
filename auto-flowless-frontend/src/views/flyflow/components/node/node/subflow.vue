<template>
	<div class="node-wrap">
		<div class="node">
			<div class="node-header">
				<span class="node-title">{{ nodeConfig.nodeName }}</span>
				<el-button type="text" size="small" @click="deleteNode">删除</el-button>
			</div>
			<div class="node-content">
				<el-form :model="nodeConfig" label-width="80px">
					<el-form-item label="子流程">
						<el-select v-model="nodeConfig.subflowConfig.flowId" placeholder="请选择子流程">
							<el-option label="流程1" value="flow1"></el-option>
							<el-option label="流程2" value="flow2"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="参数映射">
						<el-button type="primary" size="small" @click="addParamMap">添加映射</el-button>
						<div v-for="(param, index) in nodeConfig.subflowConfig.paramMap" :key="index" class="param-item">
							<el-input v-model="param.from" placeholder="源参数"></el-input>
							<el-input v-model="param.to" placeholder="目标参数"></el-input>
							<el-button type="danger" size="small" @click="deleteParamMap(index)">删除</el-button>
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

const addParamMap = () => {
	if (!props.nodeConfig.subflowConfig.paramMap) {
		props.nodeConfig.subflowConfig.paramMap = []
	}
	props.nodeConfig.subflowConfig.paramMap.push({
		from: '',
		to: ''
	})
}

const deleteParamMap = (index) => {
	props.nodeConfig.subflowConfig.paramMap.splice(index, 1)
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