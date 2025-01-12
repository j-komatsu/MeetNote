
import { defineStore } from 'pinia';

export const useMinutesStore = defineStore('minutes', {
  state: () => ({
    minutesList: [
      { id: 1, title: '定例会議', date: '2025-01-11', participants: ['山田', '田中'], agenda: '議題1, 議題2' },
      { id: 2, title: 'プロジェクト会議', date: '2025-01-12', participants: ['佐藤', '鈴木'], agenda: '議題3, 議題4' },
    ],
  }),
  actions: {
    addMinutes(minutes) {
      this.minutesList.push({ id: this.minutesList.length + 1, ...minutes });
    },
    updateMinutes(id, updatedMinutes) {
      const index = this.minutesList.findIndex((m) => m.id === id);
      if (index !== -1) {
        this.minutesList[index] = { ...this.minutesList[index], ...updatedMinutes };
      }
    },
    deleteMinutes(id) {
      this.minutesList = this.minutesList.filter((m) => m.id !== id);
    },
  },
});
