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
          <p class="text-center my-4">
            <mdbBtn size="lg" color="primary" @click.native="modal = true"
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
          <mdbBtn size="lg" color="primary" @click.native="modal = true"
            >Donate</mdbBtn
          >
        </mdbCol>
      </mdbRow>
      <mdbModal size="xl" :show="modal" @close="modal = false">
        <form>
          <mdbModalHeader class="text-center">
            <mdbModalTitle tag="h4" bold class="w-100">Sign up</mdbModalTitle>
          </mdbModalHeader>
          <mdbModalBody class="mx-3 grey-text">
            <mdbInput
              name="first-name"
              label="First name"
              icon="user"
              class="mb-4"
            />
            <mdbInput
              name="last-name"
              label="Last name"
              icon="user"
              iconClass="fa-blank"
              class="mb-4"
            ></mdbInput>
            <mdbInput
              name="email"
              label="Email"
              icon="envelope"
              type="email"
              class="mb-4"
            />
          </mdbModalBody>
          <mdbModalFooter center>
            <mdbBtn outline="primary" @click.native="modal = false"
              >Close</mdbBtn
            >
            <mdbBtn color="primary">Save changes</mdbBtn>
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