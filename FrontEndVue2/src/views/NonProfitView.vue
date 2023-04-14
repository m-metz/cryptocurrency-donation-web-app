<template>
  <mdbRow class="non-profit">
    <mdbCol v-if="loading" class="loading">
      <div class="d-flex justify-content-center align-items-center mt-5">
        <div
          class="spinner-border text-primary"
          style="width: 3rem; height: 3rem"
          role="status"
        >
          <span class="sr-only">Loading...</span>
        </div>
      </div>
    </mdbCol>

    <mdbCol v-if="error" class="error">{{ error }}</mdbCol>

    <mdbCol v-if="cause" class="content">
      <mdbRow
        v-if="cause.data.attributes.logos.length"
        class="justify-content-center my-5"
      >
        <mdbCol col="auto">
          <mdbView
            class="rounded-lg z-depth-2 row w-300px justify-content-center align-content-center"
            hover
          >
            <img
              class="col-auto"
              :src="cause.data.attributes.logos[0].url"
              alt="Sample image"
            />
            <mdbMask overlay="white-slight" waves />
          </mdbView>
        </mdbCol>
      </mdbRow>
      <mdbRow class="mb-3">
        <mdbCol>
          <h2 class="h1 font-weight-normal text-center my-4">
            {{ cause.data.attributes.name }}
          </h2>
          <p v-if="metaMaskInstalled" class="text-center my-4">
            <mdbBtn size="lg" color="primary" @click="modal = true"
              >Donate</mdbBtn
            >
          </p>
          <div v-if="!metaMaskInstalled" class="text-center my-4">
            <p>
              {{ metaMaskInstallMessage }}
            </p>
            <p>
              <CdwuMetaMaskOnboardConnectButton
                size="lg"
              ></CdwuMetaMaskOnboardConnectButton>
            </p>
          </div>
          <h3 class="font-weight-normal mb-3 text-primary">Who we are?</h3>
          <p>
            {{ cause.data.attributes.caption }}
          </p>
        </mdbCol>
      </mdbRow>
      <mdbRow class="mb-3">
        <mdbCol>
          <h3 class="font-weight-normal mb-3 text-primary">Description</h3>
          <p>
            {{ cause.data.attributes.description }}
          </p>
        </mdbCol>
      </mdbRow>
      <mdbRow class="mb-4 justify-content-center">
        <mdbCol v-if="metaMaskInstalled" col="auto">
          <mdbBtn size="lg" color="primary" @click="modal = true"
            >Donate</mdbBtn
          >
        </mdbCol>
        <mdbCol v-if="!metaMaskInstalled" col="auto" class="text-center">
          <p>
            {{ metaMaskInstallMessage }}
          </p>
          <p>
            <CdwuMetaMaskOnboardConnectButton
              size="lg"
            ></CdwuMetaMaskOnboardConnectButton>
          </p>
        </mdbCol>
      </mdbRow>
      <!--
        We want show to always be true and then just hide the modal. This is
        so we don't lose the form data if the user accidentally clicks outside
        the modal.
      -->
      <mdbModal
        size="xl"
        :show="true"
        class="d-none"
        :class="displayModalClass"
        @close="modal = false"
      >
        <mdbModalHeader class="text-center">
          <mdbModalTitle tag="h4" bold class="w-100"
            >Make a Donation</mdbModalTitle
          >
        </mdbModalHeader>
        <mdbAlert
          color="success"
          v-if="txResponse"
          @closeAlert="txResponse = null"
          leaveAnimation="fadeOut"
        >
          <strong>Cryptocurrency Donation Registered.</strong>
          You sent {{ txResponseFormatEtherValue }} to {{ txResponse.to }}. You
          can monitor this transaction on
          <a :href="etherScanUrl">{{ etherScanDomain }}</a>
        </mdbAlert>
        <mdbAlert
          color="warning"
          v-if="cryptocurrencyDonationError"
          @closeAlert="cryptocurrencyDonationError = null"
          leaveAnimation="fadeOut"
          dismiss
        >
          <strong>Cryptocurrency Donation could not be registered.</strong>
          {{ cryptocurrencyDonationError }}.
        </mdbAlert>
        <form id="cwa-donation-form" @submit.prevent="submitForm">
          <mdbModalBody
            :class="displayModalPageClass(1)"
            class="d-none mx-3 grey-text"
          >
            <mdbInput
              name="givenNames"
              label="Given names"
              icon="user"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="100"
              :value="
                getPropIfExist(user, '@_firstname') +
                getPropIfExist(user, '@_initials')
              "
            />
            <mdbInput
              name="lastName"
              label="Last name"
              icon="blank"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="100"
              :value="getPropIfExist(user, '@_lastname')"
            ></mdbInput>
            <mdbInput
              name="email"
              label="Email"
              icon="envelope"
              iconClass="fa-fw"
              type="email"
              class="mb-4"
              required
              maxlength="30"
              :value="getPropIfExist(user, '@_email')"
            />
            <mdbInput
              name="address1"
              autocomplete="address-line1"
              label="Street Address"
              icon="map-marker-alt"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="100"
              :value="getPropIfExist(user, '@_address-street')"
            ></mdbInput>
            <mdbInput
              name="address2"
              autocomplete="address-line2"
              label="Apt, Suite, Unit, Additional Address Inion (optional)"
              icon="blank"
              iconClass="fa-fw"
              class="mb-4"
              maxlength="100"
            ></mdbInput>
            <mdbInput
              name="city"
              label="City"
              icon="blank"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="85"
              :value="getPropIfExist(user, '@_address-city')"
            ></mdbInput>
            <mdbInput
              name="stateProvinceRegion"
              autocomplete="address-level1"
              label="State, Province, Region"
              icon="blank"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="60"
              :value="getPropIfExist(user, '@_address-state')"
            ></mdbInput>
            <mdbInput
              name="country"
              label="Country"
              icon="blank"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="10"
              :value="getPropIfExist(user, '@_address-country')"
            ></mdbInput>
            <mdbInput
              name="zipPostalCode"
              autocomplete="postal-code"
              label="Zip, Postal Code"
              icon="blank"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="7"
              :value="getPropIfExist(user, '@_address-postcode')"
            ></mdbInput>
          </mdbModalBody>
          <mdbModalBody
            :class="displayModalPageClass(2)"
            class="d-none mx-3 grey-text"
          >
            <mdbInput
              name="initialCryptoAmount"
              autocomplete="do-not-autofill"
              label="ETH Amount"
              icon="ethereum"
              iconClass="fa-fw"
              fab
              type="number"
              class="mb-4"
              required
              :min="0.001"
              :step="0.001"
            />
          </mdbModalBody>
          <mdbModalFooter center>
            <mdbBtn
              v-if="modalPage > 1"
              outline="primary"
              @click="valdiateBeforeNavigatingForm({ forward: false })"
              >Previous</mdbBtn
            >
            <mdbBtn
              v-if="modalPage < 2"
              color="primary"
              @click="valdiateBeforeNavigatingForm({ forward: true })"
              >Next</mdbBtn
            >
            <mdbBtn
              v-if="modalPage === 2 && metaMaskInstalled"
              color="primary"
              type="submit"
              :disabled="txResponse !== null || transactionSubmitting"
              >Submit</mdbBtn
            >
          </mdbModalFooter>
        </form>
      </mdbModal>
    </mdbCol>
  </mdbRow>
</template>

<script>
import { ethers } from "ethers";
import MetaMaskOnboarding from "@metamask/onboarding";
import benevityApi from "@/apis/benevity-api";
import CdwuMetaMaskOnboardConnectButton from "@/components/CdwuMetaMaskOnboardConnectButton.vue";
import cryptocurrencyDonationWebAppApi from "@/apis/cryptocurrency-donation-web-app-api";
import {
  mdbAlert,
  mdbCol,
  mdbRow,
  mdbBtn,
  mdbInput,
  mdbMask,
  mdbModal,
  mdbModalHeader,
  mdbModalTitle,
  mdbModalBody,
  mdbModalFooter,
  mdbView,
  mdbWaves,
} from "mdbvue";

/**
 * Configure the expected ethereum network.
 *
 * See network names here
 * https://docs.ethers.org/v5/api/providers/api-providers/#EtherscanProvider.
 */
const ETHEREUM_NETWORK = "sepolia";

export default {
  components: {
    CdwuMetaMaskOnboardConnectButton,
    mdbAlert,
    mdbCol,
    mdbRow,
    mdbInput,
    mdbMask,
    mdbModal,
    mdbModalHeader,
    mdbModalTitle,
    mdbModalBody,
    mdbModalFooter,
    mdbView,
    mdbBtn,
  },

  directives: {
    mdbWaves,
  },

  data() {
    return {
      loading: false,
      cause: null,
      error: null,

      user: null,

      modal: false,
      modalPage: 1,

      txResponse: null,
      cryptocurrencyDonationError: null,

      /*
      This property doesn't change dynamically because just a true or false is
      returned once. That being said, nothing needs to watch this method because
      MetaMask needs a page refresh after installation (handled by Onboarding).
      */
      metaMaskInstalled: MetaMaskOnboarding.isMetaMaskInstalled(),
      metaMaskInstallMessage: "Metamask is required to donate cryptocurrency.",
      transactionSubmitting: false,
    };
  },

  watch: {
    // watch the params of the route to fetch the data again
    "$route.params": {
      handler: "fetchData",
      // the callback will be called immediately after the start of the observation
      immediate: true,
    },
  },

  computed: {
    displayModalClass() {
      if (this.modal) {
        return "d-block";
      } else {
        return "";
      }
    },
    etherScanUrl() {
      let networkSubDomain = "";
      if (ETHEREUM_NETWORK !== "homestead") {
        networkSubDomain = ETHEREUM_NETWORK + ".";
      }
      return (
        "https://" +
        networkSubDomain +
        "etherscan.io/tx/" +
        this.txResponse?.hash
      );
    },

    etherScanDomain() {
      let networkSubDomain = "";
      if (ETHEREUM_NETWORK !== "homestead") {
        networkSubDomain = ETHEREUM_NETWORK + ".";
      }
      return networkSubDomain + "etherscan.io";
    },

    txResponseFormatEtherValue() {
      return ethers.formatEther(this.txResponse.value);
    },
  },

  methods: {
    displayModalPageClass(page) {
      if (this.modalPage === page) {
        return "d-block";
      } else {
        return "";
      }
    },

    fetchData() {
      this.error = this.cause = null;
      this.loading = true;
      benevityApi.causes(this.$route.params.id).then(
        (cause) => {
          this.loading = false;
          this.cause = cause;
        },
        (error) => {
          this.loading = false;
          console.error(error);
          this.error = error;
        }
      );

      if (this.$route.query.user) {
        benevityApi
          .adapterGeneralGetUserProfile(this.$route.query.user)
          .then((getUserProfile) => {
            this.user = getUserProfile.response.content.user;
          });
      }
    },

    getPropIfExist(object, prop) {
      if (typeof object === "object" && object !== null) {
        if (prop in object) {
          return object[prop];
        }
      }
      return "";
    },

    submitForm(event) {
      if (!this.txResponse && this.metaMaskInstalled) {
        this.transactionSubmitting = true;

        const formData = new FormData(event.target);
        const formDataObject = Object.fromEntries(formData.entries());

        // parseInt(ETHAmountValue) converts to a Wei int (10**18)
        const weiAmount = ethers.parseEther(formDataObject.initialCryptoAmount);

        const transactionRequest = {
          to: cryptocurrencyDonationWebAppApi.toCryptoAddress,
          value: weiAmount.toString(),
        };

        /*
        TODO I get a new provider every time because if MetaMask changes networks, the old provider
        doesn't seem to pick up this change.

        Maybe look into this in the future, as it may be spam creating objects every submit.
         */
        const ethereumProvider = new ethers.BrowserProvider(
          window.ethereum,
          ETHEREUM_NETWORK
        );

        ethereumProvider
          .getSigner()
          .then((signer) => {
            if (signer.provider._network.name !== ETHEREUM_NETWORK) {
              throw Error(
                `Your selected network ${signer.provider._network.name} does not match ${ETHEREUM_NETWORK}`
              );
            }
            return signer.sendTransaction(transactionRequest);
          })
          .then((txResponse) => {
            cryptocurrencyDonationWebAppApi.cryptocurrencyDonationCreateDonation(
              {
                nonProfitId: this.$route.params.id,
                txResponse: txResponse,
                formData: formData,
              }
            );

            /*
             Don't return the success response from the API because it is now
             blank (an asynchronous operation runs, so no JSON is returned).
             */
            return txResponse;
          })
          .then(
            (txResponse) => {
              this.txResponse = txResponse;
              this.transactionSubmitting = false;
            },
            (error) => {
              this.transactionSubmitting = false;
              console.error(error);
              this.cryptocurrencyDonationError = error;
            }
          );
      }
    },

    valdiateBeforeNavigatingForm({ forward = true }) {
      let allowNavigation = false;

      const inputs = document
        .querySelectorAll("#cwa-donation-form .modal-body")
        [this.modalPage - 1].querySelectorAll(":scope input");

      for (const input of inputs) {
        if (!input.reportValidity()) {
          allowNavigation = false;
          break;
        } else {
          allowNavigation = true;
        }
      }

      if (forward === true && allowNavigation === true) {
        this.modalPage++;
      } else if (forward === false) {
        this.modalPage--;
      }
    },
  },
};
</script>
