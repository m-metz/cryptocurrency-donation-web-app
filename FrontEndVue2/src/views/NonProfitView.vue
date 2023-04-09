<template>
  <div class="non-profit">
    <div v-if="loading" class="loading"></div>

    <div v-if="error" class="error">{{ error }}</div>

    <div v-if="cause" class="content">
      <mdbRow
        v-if="cause.data.attributes.logos"
        class="justify-content-center my-5"
      >
        <mdbCol lg="auto">
          <mdbView class="rounded-lg z-depth-2 mb-lg-0 mb-4" hover>
            <img
              class="img-fluid"
              :src="cause.data.attributes.logos[2].url"
              alt="Sample image"
            />
            <mdbMask overlay="white-slight" waves />
          </mdbView>
        </mdbCol>
      </mdbRow>
      <h2 class="h1-responsive font-weight-normal text-center my-4">
        {{ cause.data.attributes.name }}
      </h2>
      <p class="text-center my-4">
        <mdbBtn size="lg" color="primary">Donate</mdbBtn>
      </p>
      <p class="w-responsive mx-auto mb-5">
        {{ cause.data.attributes.caption }}
      </p>
      <mdbRow
        v-if="cause.data.attributes.logos"
        class="mb-3 align-items-center"
      >
        <mdbCol lg="auto">
          <mdbView class="rounded-lg z-depth-2 mb-lg-0 mb-4" hover>
            <img
              class="img-fluid"
              :src="cause.data.attributes.logos[2].url"
              alt="Sample image"
            />
            <mdbMask overlay="white-slight" waves />
          </mdbView>
        </mdbCol>
        <mdbCol>
          <h3 class="font-weight-normal mb-3 p-0 text-primary">Description</h3>
          <p>
            {{ cause.data.attributes.description }}
          </p>
        </mdbCol>
      </mdbRow>
      <mdbRow
        v-if="cause.data.attributes.logos"
        class="mb-4 justify-content-center"
      >
        <mdbCol col="auto">
          <mdbBtn size="lg" color="primary">Donate</mdbBtn>
        </mdbCol>
      </mdbRow>
      <mdbRow v-else class="mb-5">
        <mdbCol>
          <section class="w-responsive mx-auto mb-5">
            <h3 class="font-weight-bold mb-3 p-0 text-primary">
              <strong>Description</strong>
            </h3>
            <p>
              {{ cause.data.attributes.description }}
            </p>
            <p class="text-center my-4">
              <mdbBtn size="lg" color="primary">Donate</mdbBtn>
            </p>
          </section>
        </mdbCol>
      </mdbRow>
    </div>
  </div>
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
          this.error = error.toString();
        }
      );
    },
  },
};
</script>
