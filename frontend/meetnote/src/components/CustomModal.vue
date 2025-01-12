<template>
  <div v-if="show" class="modal-backdrop">
    <div class="modal">
      <h3>{{ title }}</h3>
      <p>{{ message }}</p>
      <button @click="closeModal">閉じる</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CustomModal', // 複数単語の名前
  props: {
    show: {
      type: Boolean,
      required: true,
    },
    title: {
      type: String,
      default: '通知',
    },
    message: {
      type: String,
      required: true,
    },
  },
  methods: {
    closeModal() {
      this.$emit('close');
    },
  },
};
</script>

<style scoped>
.modal-backdrop {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal {
  background: white;
  padding: 1.5rem;
  border-radius: 8px;
  width: 300px;
  max-width: 90%;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  text-align: center;
}

button {
  margin-top: 1rem;
  padding: 0.5rem 1rem;
  border: none;
  background-color: #007bff;
  color: white;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}

button:hover {
  background-color: #0056b3;
}
</style>
