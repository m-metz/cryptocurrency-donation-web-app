<template>
  <div id="app">
    <header>
      <mdbNavbar expand="lg" light bg="light">
        <RouterLink class="navbar-brand" :to="{ name: 'non-profits' }">
          Benevity Cryptocurrency Donation App
        </RouterLink>
        <mdbNavbarToggler>
          <mdbNavbarNav>
            <mdbNavItem :to="{ name: 'non-profits' }"> Non-Profits </mdbNavItem>
            <mdbNavItem :to="{ name: 'cryptocurrency-donations' }">
              Cryptocurrency Donations History
            </mdbNavItem>
          </mdbNavbarNav>
        </mdbNavbarToggler>
      </mdbNavbar>
    </header>

    <main>
      <RouterView />
    </main>
  </div>
</template>

<script>
import { mdbNavbar, mdbNavbarToggler, mdbNavbarNav, mdbNavItem } from "mdbvue";

export default {
  components: {
    mdbNavbar,
    mdbNavbarToggler,
    mdbNavbarNav,
    mdbNavItem,
  },
};
</script>
