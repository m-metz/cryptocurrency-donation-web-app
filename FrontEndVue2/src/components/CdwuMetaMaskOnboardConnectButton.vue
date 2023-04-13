<template>
  <mdbBtn
    :disabled="buttonDisabled"
    @click="onClick"
    outline="amber"
    :class="btnClasses"
  >
    <img
      src="@/assets/metamask-icon.svg"
      alt="MetaMask icon"
      class="pr-3 pb-1"
      :style="iconStyles"
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
      onAccountsChangedEnabled: false,
    };
  },

  methods: {
    handleNewAccounts(newAccounts) {
      this.metaMaskAccounts = newAccounts;
    },
    onClick() {
      if (MetaMaskOnboarding.isMetaMaskInstalled()) {
        window.ethereum
          .request({ method: "eth_requestAccounts" })
          .then(this.handleNewAccounts, (error) =>
            console.error("MetaMask Error:", error)
          );
      } else {
        /*
        Onboarding will register with the MetaMask Extension or app on page
        reload after installation so that the extension knows what site to go
        to after creating a wallet.
        */
        onboarding.startOnboarding();
      }
    },
  },

  computed: {
    buttonText() {
      if (MetaMaskOnboarding.isMetaMaskInstalled()) {
        if (this.metaMaskAccounts.length > 0) {
          /*
          Looking through their code, this doesn't seem required to call because
          the object automatically removes their metamask:reload event
          forwarding iframe after MetaMask is installed and the page reloads.

          On reload the forwarder is reinjected into the page when session
          storage REGISTRATION_IN_PROGRESS is true. When the event is received
          again, it does a wallet_registerOnboarding request to MetaMask which
          registers with MetaMask plugin/app what website to go to after
          creating a wallet. After this method is called automaticcaly.

          Although unecessary, it is recommended in their React example, so I'll
          keep it. It doesn't seem to hurt calling it times as they check
          REGISTRATION_IN_PROGRESS is null or false. 
          */
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
      if (MetaMaskOnboarding.isMetaMaskInstalled()) {
        if (this.metaMaskAccounts.length > 0) {
          return true;
        }
      }
      return false;
    },

    iconStyles() {
      return {
        height: this.iconHeight,
      };
    },

    btnClasses() {
      return ["btn-metamask", this.size && "btn-" + this.size];
    },
  },

  props: {
    iconHeight: {
      type: String,
      default: "2.3rem",
    },
    size: {
      type: String,
    },
  },

  created() {
    /*
    Don't need to track MetaMaskOnboarding.isMetaMaskInstalled() as a data
    property with a watcher. This is because this method just checks
    properties on the global ethereum object and if you uninstall MetaMask while
    on a web page you have an unattached ethereum object that gives back forever
    pending promises without error. ethereum will just act as an empty sink
    essentially. You would have to reload after uninstalling and reinstalling
    because there is no way to detect MetaMask uninstalling currently (as far
    as I know on 2023-04-13).

    Also when you install MetaMask, the page auto-refreshes on some event (when
    onboarding), so we don't need reactivity for installation status.
    */
    if (MetaMaskOnboarding.isMetaMaskInstalled()) {
      console.log("Adding accountsChanged listener");
      window.ethereum.on("accountsChanged", this.handleNewAccounts);
    }
  },

  destroyed() {
    console.log(MetaMaskOnboarding.isMetaMaskInstalled());
    if (MetaMaskOnboarding.isMetaMaskInstalled()) {
      console.log("Removing accountsChanged listener");
      window.ethereum.removeListener("accountsChanged", this.handleNewAccounts);
    }
  },
};
</script>

<style>
.btn-metamask {
  padding-bottom: 0.5rem;
  padding-top: 0.5rem;
}
</style>
