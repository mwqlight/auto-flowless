import { RouteRecordRaw } from 'vue-router'
import Layout from '@/layout/index.vue'

const crmRoutes: RouteRecordRaw[] = [
  {
    path: '/crm',
    component: Layout,
    redirect: '/crm/customer',
    name: 'CRM',
    meta: {
      title: 'CRM管理',
      icon: 'crm'
    },
    children: [
      {
        path: 'customer',
        component: () => import('@/views/crm/customer/CustomerList.vue'),
        name: 'CustomerList',
        meta: {
          title: '客户管理',
          icon: 'customer'
        }
      },
      {
        path: 'clue',
        component: () => import('@/views/crm/clue/ClueList.vue'),
        name: 'ClueList',
        meta: {
          title: '线索管理',
          icon: 'clue'
        }
      },
      {
        path: 'opportunity',
        component: () => import('@/views/crm/opportunity/OpportunityList.vue'),
        name: 'OpportunityList',
        meta: {
          title: '商机管理',
          icon: 'opportunity'
        }
      }
    ]
  }
]

export default crmRoutes