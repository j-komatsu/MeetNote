<template>
  <div>
    <h1>議事録詳細</h1>
    <form @submit.prevent="updateMinutes">
      <div>
        <label for="title">タイトル:</label>
        <input id="title" v-model="minutes.title" required />
      </div>
      <div>
        <label for="date">日付:</label>
        <input id="date" type="date" v-model="minutes.date" required />
      </div>
      <div>
        <label for="content">内容:</label>
        <textarea id="content" v-model="minutes.content" required></textarea>
      </div>
      <button type="submit">保存</button>
      <button type="button" @click="handleDelete">削除</button>
      <button type="button" @click="goBack">一覧に戻る</button>
    </form>

    <!-- 保存完了モーダル -->
    <CustomModal
      v-if="isSaveModalVisible"
      :show="isSaveModalVisible"
      title="保存完了"
      message="議事録が保存されました！"
      @close="goBack" 
    />

    <!-- 削除完了モーダル -->
    <CustomModal
      v-if="isDeleteModalVisible"
      :show="isDeleteModalVisible"
      title="削除完了"
      message="議事録が削除されました！"
      @close="goBack" 
    />
  </div>
</template>

<script>
import apiClient from '@/api/apiClient';
import CustomModal from '@/components/CustomModal.vue';

export default {
  components: {
    CustomModal,
  },
  props: ['id'],
  data() {
    return {
      minutes: {
        title: '',
        date: '',
        content: '',
      },
      isSaveModalVisible: false, // 保存モーダル
      isDeleteModalVisible: false, // 削除モーダル
    };
  },
  created() {
    this.fetchMinutesDetail();
  },
  methods: {
    fetchMinutesDetail() {
      apiClient.get(`/minutes/${this.id}`)
        .then(response => {
          this.minutes = response.data;
        })
        .catch(error => {
          console.error('エラー:', error);
        });
    },
    updateMinutes() {
      apiClient.put(`/minutes/${this.id}`, this.minutes)
        .then(() => {
          this.isSaveModalVisible = true; // 保存モーダルを表示
        })
        .catch(error => {
          console.error('エラー:', error);
        });
    },
    handleDelete() {
      apiClient.delete(`/minutes/${this.id}`)
        .then(() => {
          this.isDeleteModalVisible = true; // 削除モーダルを表示
        })
        .catch(error => {
          console.error('エラー:', error);
          alert('削除に失敗しました。');
        });
    },
    goBack() {
      this.$router.push('/');
    },
  },
};
</script>
