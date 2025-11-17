import {FormGroupVO, FormVO} from "./types";

const formGroupConfig: FormGroupVO[] = [

	{
		name: "文本",
		formList: [
			{
				name: "单行文本",
				type: "Input",
				typeName: "单行文本",
				icon: "EditPen",
				placeholder: "请填写",
				required: false,
				printable: true,
				props: {
					value: "",
					regex: "",
					regexDesc: "",
					minLength:undefined,
					maxLength:undefined
				}
			},
			{
				name: "多行文本",
				icon: "More",
				type: "Textarea",
				typeName: "多行文本",
				placeholder: "请填写",
				required: false,
				printable: true,
				props: {

					value: ""

				}
			},
			{
				name: "说明",
				icon: "ChatLineSquare",
				type: "Description",
				typeName: "说明",
				placeholder: "请填写",
				required: false,
				printable: true,
				props: {

					value: ""

				}
			}
		]
	},
	{name: "数值",
		formList: [
			{
				name: "数字",
				type: "Number",
				icon: "Edit",

				typeName: "数字",
				placeholder: "请填写",
				required: false,
				printable: true,
				props: {
					radixNum: 0,
					sum:false


				}

			},
			{
				name: "金额",
				type: "Money",
				icon: "Money",

				typeName: "金额",
				placeholder: "请填写",
				required: false,
				printable: true,
				props: {
					radixNum: 0,
					sum:false //统计总数
				}
			}
		]
	},	{name: "日期-时间",
		formList: [
			{
				name: "日期",
				type: "Date",
				icon: "Calendar",

				typeName: "日期",
				placeholder: "请选择",
				required: false,
				printable: true,
				props: {
					defaultRoot:false
				}
			}
		]
	},
	// AI功能组件
	{name: "AI功能",
		formList: [
			{
				name: "AI聊天机器人",
				type: "AIChat",
				icon: "ChatDotRound",
				typeName: "AI聊天机器人",
				placeholder: "请输入你的问题",
				required: false,
				printable: true,
				props: {
					value: "",
					model: "gpt-3.5-turbo",
					temperature: 0.7
				}
			},
			{
				name: "AI智能客服",
				type: "AIService",
				icon: "Headphone",
				typeName: "AI智能客服",
				placeholder: "请描述你的问题",
				required: false,
				printable: true,
				props: {
					value: "",
					serviceType: "customer_service"
				}
			},
			{
				name: "AI智能推荐",
				type: "AIRecommend",
				icon: "Star",
				typeName: "AI智能推荐",
				placeholder: "请输入推荐条件",
				required: false,
				printable: true,
				props: {
					value: "",
					recommendType: "product"
				}
			},
			{
				name: "AI图片剪裁",
				type: "AIImageCrop",
				icon: "Picture",
				typeName: "AI图片剪裁",
				placeholder: "请上传图片",
				required: false,
				printable: true,
				props: {
					value: "",
					aspectRatio: 1,
					cropSize: "500x500"
				}
			},
			{
				name: "AI视频处理",
				type: "AIVideoProcess",
				icon: "VideoCamera",
				typeName: "AI视频处理",
				placeholder: "请上传视频",
				required: false,
				printable: true,
				props: {
					value: "",
					processType: "transcode",
					targetFormat: "mp4"
				}
			}
		]
	},
	{name: "日期-时间",
		formList: [
			{
				name: "日期时间",
				type: "DateTime",
				icon: "AlarmClock",

				typeName: "日期时间",
				placeholder: "请选择",
				required: false,
				printable: true,
				props: {
					defaultRoot:false,

					radixNum: 0,
					value:''

				}
			}	,	{
				name: "时间",
				type: "Time",
				icon: "Timer",

				typeName: "时间",
				placeholder: "请选择",
				required: false,
				printable: true,
				props: {
					defaultRoot:false,

					radixNum: 0,
					value:''

				}
			}
		]
	},
	{
	name:"选项",
		formList:[

			{
				name: "单选",
				icon: "Select",
				type: "SingleSelect",
				typeName: "单选",
				placeholder: "请选择",
				required: false,
				printable: true,
				props: {
					privateVal:false,//是否是私有的变量值，如果是私有的 则存储到task的本地变量里
					value:[],
					remoteConfig:{
						enable:false,
						url:'http://127.0.0.1:26859/test/getTestSelectOptions',

						header:[

						],
						body:[

						]
					},
					dataFrom:1,//1手动添加2外部
					options: [
						{
							key: "",
							value: "",
						},
					],
				}
			},
			{
				name: "多选",
				type: "MultiSelect",
				typeName: "多选",
				icon: "Finished",
				placeholder: "请选择",
				required: false,
				printable: true,
				props: {
					remoteConfig:{
						enable:false,
						url:'http://127.0.0.1:26859/test/getTestSelectOptions',

						header:[

						],
						body:[

						]
					},
					dataFrom:1,//1手动添加2外部
					value: [],
					options: [
						{
							key: "",
							value: "",
						},
					],
				}
			}

		]
	},
	{
		name:'组织架构',
		formList:[

			{
				name: "单用户",
				type: "SelectUser",
				typeName: "单用户",
				placeholder: "请选择",
				icon: "User",

				required: false,
				printable: true,
				props: {
					defaultRoot:false,
					multi: false,
					self: true,
					value: []
				},
			},
			{
				name: "多用户",
				type: "SelectMultiUser",
				typeName: "多用户",
				placeholder: "请选择",
				icon: "User",

				required: false,
				printable: true,
				props: {
					defaultRoot:false,
					multi: true,
					self: true,
					value: []
				}
			},
			{
				name: "单部门",
				icon: "OfficeBuilding",
				type: "SelectDept",
				typeName: "单部门",
				placeholder: "请选择",
				required: false,
				printable: true,
				props: {
					defaultRoot:false,
					multi: false,
					self: true,
					value: []
				}
			},
			{
				name: "多部门",
				icon: "OfficeBuilding",
				type: "SelectMultiDept",
				typeName: "多部门",
				placeholder: "请选择",
				required: false,
				printable: true,
				props: {
					defaultRoot:false,
					multi: true,
					self: true,
					value: []
				}
			}
		]
	},
	{
	name:"其他",
		formList:[


			{
				name: "图片",
				type: "UploadImage",
				typeName: "图片",
				icon: "Picture",

				placeholder: "请选择",
				required: false,
				printable: true,
				props: {
					value: [], min: 1, max: 10, maxSize: 10
				}
			},


			{
				name: "文件",
				type: "UploadFile",
				typeName: "文件",
				icon: "Upload",
				placeholder: "请选择",
				required: false,
				printable: true,
				props: {
					value: [], min: 1, max: 10, maxSize: 10, suffixArray: []
				}
			}
		]
	}
]

export { formGroupConfig};
