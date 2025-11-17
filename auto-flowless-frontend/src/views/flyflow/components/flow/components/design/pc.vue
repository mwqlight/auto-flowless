<script setup lang="ts">

import {Close} from "@element-plus/icons-vue";
import {computed, ref} from "vue";
import * as util from "../../../../utils/objutil";
import {useFlowStore} from "../../../../stores/flow";
import getFormName from "../../../../utils/getFormWidget";
import draggable from "vuedraggable";


const getFormWidget = (name: string) => {
	//写的时候，组件的起名一定要与dragList中的element名字一模一样，不然会映射不上
	return getFormName[name];
};




let flowStore = useFlowStore();
let targetList = computed({
	get: () => {
		let value = step2List.value;
		if (value?.length == 0) {
			return [{
				type: 'Empty',
				name: ''
			}]
		}
		return value;
	},
	set: (v) => {

		let value = v.filter(res => res.type != 'Empty');
		flowStore.setStep2Form(util.deepCopy(value));

	}
})

const deleteForm = (id) => {


	flowStore.setStep2Form(step2List.value.filter(res => res.id !== id));

	if (currentFormCom.value && currentFormCom.value?.id === id) {
		currentFormCom.value = undefined
	}

};

var emit=defineEmits(['setCurrentForm']);

var step2Object = computed(() => {
	var obj = {}

	step2List.value.forEach(res => obj[res.id] = res)

	return obj;
});
var step2List = computed(() => {
	let step2 = flowStore.step2Form;
	return step2;
});

//定义当前打开的表单
const currentForm = ref();
//判断是否选中当前表单显示边框
var isCurrentForm = (fid) => {
	if (!currentFormCom.value) {
		return false;
	}
	if (currentFormCom.value.id === fid) {
		return true;
	}



	return false;
}
const showCurrentPageConfigPanel = (id) => {
	currentFormCom.value = step2List.value.filter(res => res.id === id)[0];

};
const showPanel = (form) => {

	currentFormCom.value = form;
}

// 根据行分组表单元素
const groupedFormElements = computed(() => {
	const groups = [];
	
	// 先将所有元素按行号排序
	const sortedElements = [...step2List.value].sort((a, b) => {
		const rowA = a.layout?.row || 1;
		const rowB = b.layout?.row || 1;
		if (rowA !== rowB) {
			return rowA - rowB;
		}
		// 同一行的元素按列号排序
		const colA = a.layout?.col || 1;
		const colB = b.layout?.col || 1;
		return colA - colB;
	});
	
	// 按行分组
	let currentRow = null;
	let currentRowNumber = null;
	
	sortedElements.forEach(element => {
		const rowNumber = element.layout?.row || 1;
		if (currentRowNumber !== rowNumber) {
			currentRowNumber = rowNumber;
			currentRow = [];
			groups.push(currentRow);
		}
		currentRow.push(element);
	});
	
	return groups;
});

// 获取元素在列表中的索引
const getElementIndex = (id) => {
	return step2List.value.findIndex(item => item.id === id);
}

var currentFormCom=computed({
	get(){
		return currentForm.value
	},
	set(v){
		currentForm.value=v;
		emit('setCurrentForm',v)
	}
})



</script>

<template>
	<div
			class="drag-content"

	>
		<div class="drag-content-inner">

			<el-form :label-position="'top'"    label-width="100px">
				<!-- 根据行分组渲染表单元素 -->
				<template v-for="(row, rowIndex) in groupedFormElements" :key="'row-' + rowIndex">
					<el-row :gutter="20" style="margin-bottom: 10px;">
						<template v-for="element in row" :key="element.id">
							<el-col
								:span="element.layout?.span || 24"
								:offset="element.layout?.offset || 0"
							>
								<div
									class="okcomponent border line  " effect="dark"
									:class="{'active-component':isCurrentForm(element.id)}"
									@click.stop="showCurrentPageConfigPanel(element.id)"
								>

									<el-icon v-if="element.type!='Empty'" class="deleteIcon"
											 @click.stop="deleteForm(element.id)">
										<Close/>
									</el-icon>

									<el-form-item :label="step2Object[element.id]?.name"
												:style="{marginBottom:(element.type==='Empty'?'0px':'18px')}"
												:required="step2Object[element.id]?.required">

										<component style="width: 100%"
												@showPanel="showPanel"
												:index="getElementIndex(element.id)"
												:is="getFormWidget(element.type)"
												:id="element.id"
												:from="1"
												v-model:form="step2Object[element.id]"
										></component>
									</el-form-item>

								</div>
							</el-col>
						</template>
					</el-row>
				</template>

				<!-- 如果没有任何表单元素，显示空提示 -->
				<div v-if="groupedFormElements.length === 0" style="text-align: center; padding: 100px 0;">
					暂无表单元素
				</div>
			</el-form>
		</div>
	</div>
</template>

<style scoped lang="less">
.leftItem {
	padding-left: 0px;
}

.zj {
	display: inline-block;
	width: 140px;
	margin: 5px;
}

@f22_width: 400px;

@center_width: 360px;
.drag-content {
	min-height: 640px;

	padding: 30px 10px;
	background-color: white;
}

.drag-content-inner {
	background-color: var(--el-bg-color-page);
	border-radius: var(--el-border-radius-base);

	padding: 5px;
}

.f11 {
	width: calc(100% - @f22_width);

}

.f22 {
	width: @f22_width;

}

.okcomponent {
	padding: 5px;
	border-radius: 0px;
	margin-bottom: 10px;
	background-color: white;
	border: 1px solid white;
	position: relative;

}

.active-component {
	border: 1px solid var(--el-color-primary);
}

.deleteIcon {
	position: absolute;
	right: 20px;
	z-index: 20;
}

.deleteIcon:hover {
	cursor: pointer;
	color: palevioletred;
}
</style>
