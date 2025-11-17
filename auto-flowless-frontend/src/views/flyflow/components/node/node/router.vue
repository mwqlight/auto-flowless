<template>
	<div class="node-wrap">
		<div class="node">
			<div class="node-header">
				<span class="node-title">{{ nodeConfig.nodeName }}</span>
				<el-button type="text" size="small" @click="deleteNode">删除</el-button>
			</div>
			<div class="node-content">
				<el-form :model="nodeConfig" label-width="80px">
					<el-form-item label="路由类型">
						<el-select v-model="nodeConfig.routerConfig.type" placeholder="请选择路由类型">
							<el-option label="基于表单" value="form"></el-option>
							<el-option label="基于角色" value="role"></el-option>
							<el-option label="基于规则" value="rule"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="路由配置">
						<el-input type="textarea" v-model="nodeConfig.routerConfig.config" placeholder="路由配置JSON"></el-input>
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
</style>