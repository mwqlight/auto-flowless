<template>
	<div>
		<el-container>
		<el-aside width="280px">
			<el-scrollbar :min-size="1" :height="util.getPageSize().pageHeight-120">
				<div effect="dark" style="background-color: white;margin-top: 0px;padding-top: 20px">
					<h4 style="text-align: center">组件库</h4>
					
					<!-- AI绘制表单按钮 -->
					<div style="padding: 10px; text-align: center;">
						<el-button type="primary" :icon="ChatDotRound" @click="showAIDrawFormDialog">
							AI绘制表单
						</el-button>
					</div>
						<template v-for="(item,index) in oriFormList">
							<h5 style="padding-left: 20px">{{ item.name }}</h5>
							<draggable
									disabled
									v-model="item.formList"
									ghost-class="ghost" :force-fallback="true"
									item-key="index"
									:sort="false"
									@end="dragEnd"
									@start="drag=true"
									class="leftItem"
									:animation="300"
									:group="{ name: 'dragFormList', pull: 'clone', put: false }"

									:clone='cloneFunc'
							>

								<template #item="{ element }">
									<div class="zj">
										<el-button size="large" style="width: 100%" :icon="$icon[element.icon]"
										>{{ element.name }}
										</el-button>
									</div>
								</template>
							</draggable>
						</template>


					</div>

				</el-scrollbar>

			</el-aside>
			<el-main style="padding: 0px;">
				<div style="display: flex;flex-direction: row">

					<div class="f11">

						<pc  @setCurrentForm="setCurrentFormFunc"></pc>

					</div>
					<div class="f22" v-if="currentForm">
						<el-card class="box-card">
							<template #header>
								<div class="card-header">
									{{ currentForm?.typeName }}
								</div>
							</template>

									<el-form label-width="120px" label-position="top">
										<el-form-item   label="表单ID">
											<el-input disabled v-model="currentForm.id" maxlength="50">
												<template #append>
													<el-button @click="copyFormId(currentForm.id)" :icon="DocumentCopy" />
												</template>
											</el-input>
										</el-form-item>
										<el-form-item required label="标题">
											<el-input v-model="currentForm.name" maxlength="10"/>
										</el-form-item>

										<el-form-item label="提示" :required="currentForm.type==='Description'">
	<el-input v-model="currentForm.placeholder" maxlength="50"/>
</el-form-item>

<!-- 布局配置 -->
<el-form-item label="布局设置">
	<el-row gutter="20">
		<el-col :span="8">
			<el-form-item label="所在行" size="small">
				<el-input-number v-model="currentForm.layout.row" :min="1" :max="10" style="width: 100%" />
			</el-form-item>
		</el-col>
		<el-col :span="8">
			<el-form-item label="所在列" size="small">
				<el-input-number v-model="currentForm.layout.col" :min="1" :max="24" style="width: 100%" />
			</el-form-item>
		</el-col>
		<el-col :span="8">
			<el-form-item label="占据列数" size="small">
				<el-select v-model="currentForm.layout.span" style="width: 100%">
					<el-option label="24 (1行1列)" value="24" />
					<el-option label="12 (1行2列)" value="12" />
					<el-option label="8 (1行3列)" value="8" />
					<el-option label="6 (1行4列)" value="6" />
					<el-option label="4 (1行6列)" value="4" />
					<el-option label="3 (1行8列)" value="3" />
					<el-option label="2 (1行12列)" value="2" />
				</el-select>
			</el-form-item>
		</el-col>
	</el-row>
</el-form-item>



										<component
												:is="getFormConfigWidget(currentForm.type)"
												:id="currentForm.id"
												ref="currentFormConfigRef"
										></component>
										<el-form-item label="其他">
											<el-checkbox v-model="currentForm.required" label="必填"/>

										</el-form-item>
									</el-form>

						</el-card>


					</div>

				</div>

			</el-main>
		</el-container>
	</div>
	
	<!-- AI绘制表单对话框 -->
	<el-dialog v-model="aiDrawFormDialogVisible" title="AI绘制表单" width="600px">
		<el-form label-width="80px">
			<el-form-item label="表单描述">
				<el-input
					type="textarea"
					v-model="aiFormDescription"
					placeholder="请输入表单需求描述，例如：‘创建一个员工信息表单，包含姓名、部门、职位、邮箱、电话等字段’"
					rows="4"
				></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="aiDrawFormDialogVisible = false">取消</el-button>
				<el-button type="primary" @click="aiDrawForm">生成表单</el-button>
			</span>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup>
import {ref, computed, watch} from "vue";

import {formValidateDict} from '../../utils/formValidate'

import {Monitor, Iphone, DocumentCopy} from "@element-plus/icons-vue";
import {useFlowStore} from "../../stores/flow";
//要注意导入
import getFormConfigName from "../../utils/getFormConfigWidget";
import draggable from "vuedraggable";
import {formGroupVO} from "../../api/form/types";
import {formGroupConfig} from "../../api/form/data";
// 引入AI生成表单API
import {aiGenerateForm} from '../../api/form/index'
import * as util from "../../utils/objutil";

import Pc from "./components/design/pc.vue";

const activeConfigTab = ref('base');

const drag = ref(false);

const currentFormConfigRef = ref();
let flowStore = useFlowStore();

// AI绘制表单相关
const aiDrawFormDialogVisible = ref(false);
const aiFormDescription = ref('');

// 显示AI绘制表单对话框
const showAIDrawFormDialog = () => {
	aiDrawFormDialogVisible.value = true;
};

// AI绘制表单
const aiDrawForm = () => {
	// 调用AI生成表单API
	aiGenerateForm({ description: aiFormDescription.value }).then(response => {
		const generatedFormElements = response.data;
		
		// 将生成的表单元素添加到表单中
		const currentFormList = [...step2FormList.value];
		generatedFormElements.forEach(element => {
			element.id = util.getRandomId();
			currentFormList.push(element);
		});
		
		flowStore.setStep2Form(currentFormList);
	}).catch(error => {
		console.error('AI生成表单失败:', error);
	});
	
	// 关闭对话框
	aiDrawFormDialogVisible.value = false;
	// 清空输入
	aiFormDescription.value = '';
}


var step2List = computed(() => {
	let step2 = flowStore.step2;
	return step2;
});
var step2FormList = computed(() => {

	if (!flowStore) {
		return undefined;
	}
	let step2 = flowStore.step2Form;
	return step2;
});
//监听表单变化 渲染手机端和pc端
watch(() => step2FormList?.value, (v) => {
	flowStore.setStep2Pc(v);
	var arr = [];
	for (var item of v) {
	 {
			arr.push(item);
		}
	}
	flowStore.setStep2(arr);
}, {deep: true})



//定义当前打开的表单
const currentForm = ref();

function setCurrentFormFunc(v) {
	currentForm.value = v;
}





const cloneFunc = (el) => {
	el.id = util.getRandomId();
	return el;
}


const getFormConfigWidget = (name: string) => {
	//写的时候，组件的起名一定要与dragList中的element名字一模一样，不然会映射不上
	return getFormConfigName[name];
};


const dragEnd = (a) => {
	drag.value = false;
	oriFormList.value = util.deepCopy(oriFormList.value)

}
//复制表单idid
function copyFormId(id){
	util.copyToBoard(id)
}

let oriFormList = ref<FormGroupVO[]>(formGroupConfig);


const validate = (f) => {

	var err = []

	let formList = step2List.value;
	if (formList.length == 0) {
		err.push("表单不能为空")
	}

	var cIndex = 0;
	var indexObj = {};
	for (var form of formList) {
		indexObj[form.id] = cIndex
		cIndex++;
	}
	for (var form of formList) {


		let formValidateDictElement = formValidateDict[form.type];
		if (formValidateDictElement) {
			let result = formValidateDictElement(form);

			if (!result.valid) {
				err.push(result.msg)
			}
		}

		//计算每个表单的顺序


		//检查动态表单配置

		{
			let dynamicFormConfig = form.dynamicFormConfig;
			if (!dynamicFormConfig) {
				dynamicFormConfig = [];
			}
			for (var it of dynamicFormConfig) {
				let list = it.list;
				let conditionList = it?.condition?.conditionList;
				if (conditionList?.length > 0) {
					//判断条件里的顺序
					for (var c of conditionList) {
						console.log(c)
						for (var t of c.conditionList) {
							let key = t.key;
							let indexObjElement = indexObj[key];
							if (indexObjElement) {
								if (indexObjElement >= indexObj[form.id]) {
									err.push("请检查表单[" + form.name + "]的动态表单条件中引用的条件顺序不能在当前表单之下")

								}
							}
						}
					}

					//有条件
					if (list?.length < 1) {
						err.push("请设置表单[" + form.name + "]的动态表单值")

					} else {
						for (var l of list) {
							if (util.isBlank(l.value) || util.isBlank(l.contentConfig)) {
								err.push("请完善表单[" + form.name + "]的动态表单值")
							}
						}
						let arr = list.map(r => r.contentConfig);
						if (util.distinct(arr).length != arr.length) {
							err.push("表单[" + form.name + "]的动态表单不能重复配置")
						}
					}
				}
			}

		}


	}

	//表单唯一名字集合
	let uniqueFormNameList = Array.from(new Set(formList.map(res => res.name)))


	if (uniqueFormNameList.length < formList.length) {

		for(var formName of uniqueFormNameList){
			let length = formList.filter(res=>res.name===formName).length;
			if(length>1){
				err.push("表单名称【"+formName+"】不能重复");
			}
		}

	}

	f(err.length == 0, err);
};
defineExpose({validate});
</script>
<style scoped lang="less">
.leftItem {
	padding-left: 0px;
}

.zj {
	display: inline-block;
	width: 120px;
	margin: 5px 10px 5px 10px;
}

@f22_width: 500px;

@center_width: 360px;


.f11 {
	width: calc(100% - @f22_width);

}

.f22 {
	width: @f22_width;

}


</style>
