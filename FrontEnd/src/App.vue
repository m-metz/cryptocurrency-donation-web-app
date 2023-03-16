<template>
  <header>
    <nav>
      <RouterLink :to="{ name: 'non-profits' }">Non-Profits</RouterLink>
      |
      <RouterLink :to="{ name: 'cryptocurrency-donations' }">
        Cryptocurrency Donations History
      </RouterLink>
    </nav>
  </header>

  <main>
    <RouterView />
  </main>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style></style>
