<template>
  <header>
    <nav>
      <RouterLink :to="{ name: 'non-profits' }">Non-Profits</RouterLink>
      |
      <RouterLink :to="{ name: 'cryptocurrency-donations' }">Cryptocurrency Donations</RouterLink>
    </nav>
  </header>

  <RouterView />
</template>

<script>
export default {
  name: 'App'
}
</script>

<style></style>
