<template>
  <header>
    <MDBNavbar expand="lg" light bg="light" container>
      <RouterLink class="navbar-brand" :to="{ name: 'non-profits' }">
        Benevity Cryptocurrency Donation App
      </RouterLink>
      <MDBNavbarToggler target="#navbarNav"></MDBNavbarToggler>
      <MDBNavbarNav collapse="navbarNav">
        <MDBNavbarItem :to="{ name: 'non-profits' }">Non-Profits</MDBNavbarItem>
        <MDBNavbarItem :to="{ name: 'cryptocurrency-donations' }">
          Cryptocurrency Donations History
        </MDBNavbarItem>
      </MDBNavbarNav>
    </MDBNavbar>
  </header>

  <main>
    <RouterView />
  </main>
</template>

<script>
import { MDBNavbar, MDBNavbarToggler, MDBNavbarNav, MDBNavbarItem } from 'mdb-vue-ui-kit'

import { ref } from 'vue'

export default {
  components: {
    MDBNavbar,
    MDBNavbarToggler,
    MDBNavbarNav,
    MDBNavbarItem
  },
  setup() {
    const collapse1 = ref(false)
    const dropdown1 = ref(false)
    return {
      collapse1,
      dropdown1
    }
  }
}
</script>

<style></style>
