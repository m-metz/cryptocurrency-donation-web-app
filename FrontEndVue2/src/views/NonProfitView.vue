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
          <mdbView class="rounded-lg z-depth-2" hover>
            <img
              class="img-fluid"
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
            <mdbBtn size="lg" color="primary">Donate</mdbBtn>
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
          <mdbBtn size="lg" color="primary">Donate</mdbBtn>
        </mdbCol>
      </mdbRow>
    </mdbCol>
  </mdbRow>
</template>

<script>
import benevityApi from "@/apis/benevity-api";
import { mdbCol, mdbRow, mdbBtn, mdbMask, mdbView, mdbWaves } from "mdbvue";

export default {
  components: {
    mdbCol,
    mdbRow,
    mdbMask,
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
