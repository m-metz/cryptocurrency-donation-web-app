<template>
  <mdbRow class="non-profit">
    <mdbCol v-if="loading" class="loading"></mdbCol>

    <mdbCol v-if="error" class="error">{{ error }}</mdbCol>

    <mdbCol v-if="cause" class="content">
      <mdbRow
        v-if="cause.data.attributes.logos"
        class="justify-content-center my-5"
      >
        <mdbCol col="auto">
          <mdbView
            class="rounded-lg z-depth-2 row w-300px justify-content-center align-content-center"
            hover
          >
            <img
              class="col-auto"
              :src="cause.data.attributes.logos[2].url"
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
          <p class="text-center my-4">
            <mdbBtn size="lg" color="primary" @click="modal = true"
              >Donate</mdbBtn
            >
          </p>
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
        <mdbCol col="auto">
          <mdbBtn size="lg" color="primary" @click="modal = true"
            >Donate</mdbBtn
          >
        </mdbCol>
      </mdbRow>
      <!--
        We want show to always be true and then just hide the modal. This is
        so we don't lose the form data if the user accidentally clicks outside
        the modal.
      -->
      <mdbModal
        size="xl"
        show="true"
        :class="displayModalClass"
        @close="modal = false"
      >
        <form>
          <mdbModalHeader class="text-center">
            <mdbModalTitle tag="h4" bold class="w-100"
              >Make a Donation</mdbModalTitle
            >
          </mdbModalHeader>
          <mdbModalBody class="mx-3 grey-text">
            <mdbInput
              name="given-names"
              label="Given names"
              icon="user"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="100"
            />
            <mdbInput
              name="last-name"
              label="Last name"
              icon="blank"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="100"
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
            />
            <mdbInput
              name="address-line1"
              label="Street Address"
              icon="map-marker-alt"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="100"
            ></mdbInput>
            <mdbInput
              name="address-line2"
              label="Apt, Suite, Unit, Additional Address Information (optional)"
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
            ></mdbInput>
            <mdbInput
              name="state-province-region"
              label="State, Province, Region"
              icon="blank"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="60"
            ></mdbInput>
            <mdbInput
              name="country"
              label="Country"
              icon="blank"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="10"
            ></mdbInput>
            <mdbInput
              name="zip-postal-code"
              label="Zip, Postal Code"
              icon="blank"
              iconClass="fa-fw"
              class="mb-4"
              required
              maxlength="7"
            ></mdbInput>
            <mdbInput
              name="from-crypto-address"
              autocomplete="from-crypto-address"
              label="Your Ethereum Account Address"
              icon="ethereum"
              iconClass="fa-fw"
              fab
              class="mb-4"
              required
              maxlength="50"
            />
            <mdbInput
              name="initial-crypto-amount"
              autocomplete="do-not-autofill"
              label="ETH Amount"
              icon="ethereum"
              iconClass="fa-fw"
              fab
              type="number"
              class="mb-4"
              required
              min="1"
              step="0.001"
            />
          </mdbModalBody>
          <mdbModalFooter center>
            <mdbBtn outline="primary" @click="modal = false">Close</mdbBtn>
            <mdbBtn color="primary" type="submit">Save changes</mdbBtn>
          </mdbModalFooter>
        </form>
      </mdbModal>
    </mdbCol>
  </mdbRow>
</template>

<script>
import benevityApi from "@/apis/benevity-api";
import {
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

export default {
  components: {
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

      modal: false,
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
        return "";
      } else {
        return "d-none";
      }
    },
  },

  methods: {
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
          this.error = error.toString();
        }
      );
    },
  },
};
</script>
