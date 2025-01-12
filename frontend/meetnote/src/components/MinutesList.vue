<template>
  <div>
    <h1>議事録一覧</h1>
    <ul>
      <li v-for="minutes in minutesList" :key="minutes.id" class="minutes-item">
        <div class="minutes-info">
          <span class="date">{{ minutes.date }}</span>
          <span class="title">{{ minutes.title }}</span>
        </div>
        <div class="actions">
          <router-link :to="{ name: 'MinutesDetail', params: { id: minutes.id } }">
            <button>編集</button>
          </router-link>
          <!-- <button @click="handleDelete(minutes.id)">削除</button> -->
        </div>
      </li>
    </ul>
    <button @click="createNewMinutes">新規作成</button>
  </div>
</template>

<script>
import apiClient from '@/api/apiClient';

export default {
  data() {
    return {
      minutesList: [
        { id: 1, date: '2025-01-11', title: '定例会議' },
        { id: 2, date: '2025-01-12', title: 'プロジェクト会議' },
      ],
    };
  },
  mounted() {
    this.fetchMinutesList();
  },
  methods: {
    createNewMinutes() {
      const newMinutes = {
        date: new Date().toISOString().split('T')[0],
        title: `新規議事録`,
        content: '',
      };

      apiClient.post('/minutes', newMinutes)
        .then(response => {
          const createdMinutes = response.data;
          this.minutesList.push(createdMinutes); // フロントエンド側のリストも更新
          this.$router.push({ name: 'MinutesDetail', params: { id: createdMinutes.id } });
        })
        .catch(error => {
          console.error('エラー:', error);
          alert('新規議事録の作成に失敗しました。');
        });
    },
    handleDelete(id) {
    apiClient.delete(`/minutes/${id}`)
      .then(() => {
        // フロントエンドのリストを更新
        this.minutesList = this.minutesList.filter(minutes => minutes.id !== id);
        alert('議事録が削除されました！');
      })
      .catch(error => {
        console.error('削除エラー:', error);
        alert('削除に失敗しました。');
      });
  },
    fetchMinutesList() {
      apiClient.get('/minutes')
        .then(response => {
          this.minutesList = response.data; // バックエンドから取得したデータをセット
        })
        .catch(error => {
          console.error('エラー:', error);
          alert('議事録一覧を取得できませんでした。');
        });
    },
  },
};
</script>

<style scoped>
.minutes-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #ffffff;
  padding: 1rem;
  margin-bottom: 1rem;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.minutes-info {
  display: flex;
  align-items: center;
}

.date {
  font-weight: bold;
  color: #6c757d;
  min-width: 120px;
  margin-right: 1rem;
  text-align: left;
}

.title {
  text-align: left;
  flex-grow: 1;
}

.actions {
  display: flex;
  gap: 0.5rem;
}

button {
  padding: 0.4rem 0.8rem;
  font-size: 1rem;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
