import { createRouter, createWebHistory } from 'vue-router';
import MinutesList from '@/components/MinutesList.vue';
import MinutesDetail from '@/components/MinutesDetail.vue';

const routes = [
  {
    path: '/',
    name: 'MinutesList',
    component: MinutesList,
  },
  {
    path: '/detail/:id',
    name: 'MinutesDetail',
    component: MinutesDetail,
    props: true,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
