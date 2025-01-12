
<template>
  <div id="app">
    <header>
      <h1>MeetNote</h1>
      <nav>
        <router-link to="/">議事録一覧</router-link>
      </nav>
    </header>
    <main>
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style scoped>
header {
  background-color: #343a40;
  color: white;
  padding: 1rem;
  text-align: center;
}
nav a {
  color: white;
  margin: 0 1rem;
  text-decoration: none;
}
main {
  padding: 1rem;
}
</style>
