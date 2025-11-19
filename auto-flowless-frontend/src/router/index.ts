import {createRouter, createWebHashHistory, onBeforeRouteLeave, RouteRecordRaw} from "vue-router";
import {isMobile} from "@/views/flyflow/utils/appversion";


export const Layout = () => import("@/layout/index.vue");

// 静态路由
export const constantRoutes: RouteRecordRaw[] = [
	{
		path: "/redirect",
		component: Layout,
		meta: {hidden: true},
		children: [
			{
				path: "/redirect/:path(.*)",
				component: () => import("@/views/redirect/index.vue"),
			},
		],
	},


	{
		path: "/login",
		component: () => import("@/views/login/index.vue"),
		meta: {hidden: true},
	},


	{
		path: "/redirectlogin/:p/:r/:pname/:pval",
		component: () => import("@/views/login/index.vue"),
		meta: {hidden: true},
	},


	{
		path: "/aplogin",
		component: () => import("@/views/login/ap.vue"),
		meta: {hidden: true},
	},


	{
		path: "/",
		component: Layout,
		redirect: "/dashboard",
		children: [
			{
				path: "dashboard",
				component: () => import("@/views/dashboard/index.vue"),
				name: "Dashboard",
				meta: {title: "dashboard", icon: "homepage", affix: true},
			},
			{
				path: "401",
				component: () => import("@/views/error-page/401.vue"),
				meta: {hidden: true},
			},


			// {
			//   path: "flow/data",
			//   component: () => import("@/views/flyflow/views/flow/flowdata.vue"),
			//   meta: { hidden: true },
			// },
			{
				path: "404",
				component: () => import("@/views/error-page/404.vue"),
				meta: {hidden: true},
			},
		],
	},

	// 外部链接
	/*{
		  path: '/external-link',
		  component: Layout,
		  children: [
			  {
				  path: 'https://www.cnblogs.com/haoxianrui/',
				  meta: { title: '外部链接', icon: 'link' }
			  }
		  ]
	  }*/
	// 算法实验室路由
	{
		path: '/alg-lab',
		component: Layout,
		redirect: '/alg-lab/index',
		name: 'AlgLab',
		meta: {title: '算法实验室', icon: 'algorithm'},
		children: [
			{
				path: 'index',
				component: () => import('@/views/alg-lab/index.vue'),
				name: 'AlgLabIndex',
				meta: {title: '算法列表'}
			},
			{
				path: 'detail/:algId',
				component: () => import('@/views/alg-lab/detail.vue'),
				name: 'AlgLabDetail',
				meta: {title: '算法详情', hidden: true}
			}
		]
	}
];

/**
 * 创建路由
 */
const router = createRouter({
	history: createWebHashHistory(),
	routes: constantRoutes as RouteRecordRaw[],
	// 刷新时，滚动条位置还原
	scrollBehavior: () => ({left: 0, top: 0}),
});


router.beforeEach((to, from, next) => {
	// 在导航前执行操作，例如身份验证检查

	let href = window.location.href;

	//判断是来自微信 或者 钉钉认证跳转过来的 替换体制
	if (href.indexOf("code") >= 0 && href.indexOf("state") >= 0) {
		let idx1 = href.indexOf("code");
		let idx2 = href.indexOf("state");
		let idx3 = href.indexOf("/redirectlogin");

		let s = href.substring(idx1 + 5, idx2 - 1);


		if(idx3>=0){
			let href1 = href.substring(0, idx1 - 1) + href.substring(idx2 + 6, href.length) + '?token=' + s;
			window.location.href = href1
		}else{
			let s = href.substring(idx1 + 5, idx2 - 1);

			let href1 = href.substring(0, idx1 - 2)+'/#/login?token='+s;
			window.location.href= href1
		}




	} else {
		next(); // 继续导航
	}
})

/**
 * 重置路由
 */
export function resetRouter() {
	router.replace({path: "/login"});
}

export default router;
