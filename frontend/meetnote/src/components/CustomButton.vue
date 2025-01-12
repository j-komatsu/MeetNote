
<template>
  <button :class="buttonClass" @click="$emit('click')">
    <slot></slot>
  </button>
</template>

<script>
export default {
  name: 'CustomButton', // 複数単語に変更
  props: {
    type: {
      type: String,
      default: 'primary',
    },
  },
  computed: {
    buttonClass() {
      return `btn btn-${this.type}`;
    },
  },
};

</script>

<style scoped>
.btn {
  padding: 0.5rem 1rem;
  font-size: 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.btn-primary {
  background-color: #007bff;
  color: white;
}
.btn-secondary {
  background-color: #6c757d;
  color: white;
}
.btn-danger {
  background-color: #dc3545;
  color: white;
}
</style>
