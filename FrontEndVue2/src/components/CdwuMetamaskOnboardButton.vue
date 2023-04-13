<template>
  <mdbBtn
    style="background-color: rgb(245, 132, 31)"
    :disabled="buttonDisabled"
    @click="onClick"
    outline="amber"
    size="lg"
    class="py-2"
  >
    <img
      src="@/assets/metamask-icon.svg"
      alt="MetaMask icon"
      class="pr-3 pb-1"
      style="height: 2.3rem"
    />
    {{ buttonText }}
  </mdbBtn>
</template>

<script>
import MetaMaskOnboarding from "@metamask/onboarding";
import mdbBtn from "mdbvue/lib/components/mdbBtn";

const ONBOARD_TEXT = "Click here to install MetaMask!";
const CONNECT_TEXT = "Connect MetaMask";
const CONNECTED_TEXT = "Connected";

const onboarding = new MetaMaskOnboarding();

export default {
  components: {
    mdbBtn,
  },

  data() {
    return {
      metaMaskAccounts: [],
      metaMaskInstalled: MetaMaskOnboarding.isMetaMaskInstalled(),
      onAccountsChangedEnabled: false,
    };
  },

  methods: {
    handleNewAccounts(newAccounts) {
      this.metaMaskAccounts = newAccounts;
    },
    onClick() {
      if (this.metaMaskInstalled) {
        window.ethereum
          .request({ method: "eth_requestAccounts" })
          .then(this.handleNewAccounts, (error) => console.log(error));
      } else {
        onboarding.startOnboarding();
      }
    },
  },

  computed: {
    buttonText() {
      if (this.metaMaskInstalled) {
        if (this.metaMaskAccounts.length > 0) {
          onboarding.stopOnboarding();
          return CONNECTED_TEXT;
        } else {
          return CONNECT_TEXT;
        }
      } else {
        return ONBOARD_TEXT;
      }
    },
    buttonDisabled() {
      if (this.metaMaskInstalled) {
        if (this.metaMaskAccounts.length > 0) {
          return true;
        }
      }
      return false;
    },
  },

  watch: {
    metaMaskInstalled: {
      handler(newMetamaskInstalled) {
        if (newMetamaskInstalled) {
          /*
          I don't think this handler should get called more than once when
          metaMaskInstalled changes, but just in case, we should never register
          the same event listener twice.
          */
          if (!this.onAccountsChangedEnabled) {
            console.log("Adding accountsChanged listener");
            window.ethereum.on("accountsChanged", this.handleNewAccounts);
          }
        }
      },
      /*
      The callback will be called immediately after the start of the
      observation.
      */
      immediate: true,
    },

    destroyed() {
      if (this.metaMaskInstalled) {
        console.log("Removing accountsChanged listener");
        window.ethereum.removeListener(
          "accountsChanged",
          this.handleNewAccounts
        );
      }
    },
  },
};
</script>

<style>
.btn-outline-primary:hover,
.btn-outline-primary:focus,
.btn-outline-primary:active,
.btn-outline-primary:active:focus,
.btn-outline-primary.active {
  color: #4285f4 !important;
  background-color: transparent !important;
  border-color: #4285f4 !important;
}
</style>
