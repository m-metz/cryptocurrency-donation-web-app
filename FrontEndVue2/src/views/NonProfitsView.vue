<template>
  <mdbRow class="non-profit">
    <mdbCol col="12">
      <h1>Donate Cryptocurrency to Non-Profits</h1>
      <form class="d-flex input-group w-auto align-items-center">
        <input
          type="search"
          class="form-control"
          placeholder="Search for non-profits"
          aria-label="Search"
        />
        <mdbBtn outline="primary" class="my-0">Search</mdbBtn>
      </form></mdbCol
    >

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

    <mdbCol v-if="causes" class="content">
      <mdbRow class="row-cols-1 row-cols-md-3 mt-n3">
        <mdbCol v-for="cause of causes.data" :key="cause.id" class="my-3">
          <mdbCard class="h-100">
            <mdbCardImage
              v-if="cause.attributes.logo"
              v-mdb-waves
              :src="cause.attributes.logo"
              top
              :alt="cause.attributes.name + ' logo'"
            />

            <mdbRow v-else class="justify-content-center my-5">
              <mdbCol col="auto">
                <!-- <mdbView
                  class="rounded-lg z-depth-2 row w-300px justify-content-center align-content-center"
                  hover
                > -->
                <mdbIcon
                  icon="hand-holding-heart"
                  size="5x"
                  class="img-fluid card-img-top grey-text"
                  style="cursor: pointer"
                  v-mdb-waves
                />
                <!-- </mdbView> -->
              </mdbCol>
            </mdbRow>
            <mdbCardBody>
              <mdbCardTitle>{{ cause.attributes.name }}</mdbCardTitle>
              <mdbCardText>
                {{ cause.attributes.description }}
              </mdbCardText>
              <RouterLink
                :to="{
                  name: 'non-profit',
                  params: { id: cause.id },
                }"
              >
                <mdbBtn color="primary">View</mdbBtn>
              </RouterLink>
            </mdbCardBody>
          </mdbCard>
        </mdbCol>
      </mdbRow>
    </mdbCol>
  </mdbRow>
</template>

<script>
import benevityApi from "@/apis/benevity-api";

import {
  mdbCol,
  mdbRow,
  mdbCard,
  mdbCardBody,
  mdbCardTitle,
  mdbCardText,
  mdbCardImage,
  mdbBtn,
  mdbIcon,
  mdbWaves,
} from "mdbvue";
export default {
  components: {
    mdbCol,
    mdbRow,
    mdbCard,
    mdbCardBody,
    mdbCardTitle,
    mdbCardText,
    mdbCardImage,
    mdbBtn,
    mdbIcon,
  },

  created() {
    this.fetchData();
  },

  data() {
    return { loading: false, causes: null, error: null };
  },

  methods: {
    fetchData() {
      this.error = this.cause = null;
      this.loading = true;
      benevityApi.searchCauses("*").then(
        (causes) => {
          this.loading = false;
          this.causes = causes;
        },
        (error) => {
          this.loading = false;
          console.error(error);
          this.error = error.toString();
        }
      );
    },
  },

  directives: {
    mdbWaves,
  },
};
</script>
