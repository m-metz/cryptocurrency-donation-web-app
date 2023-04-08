<template>
  <div class="non-profits">
    <h1>Donate Cryptocurrency to Non-Profits</h1>
    <form class="d-flex input-group w-auto align-items-center my-3">
      <input
        type="search"
        class="form-control"
        placeholder="Search for non-profits"
        aria-label="Search"
      />
      <mdbBtn outline="primary" class="my-0">Search</mdbBtn>
    </form>
    <mdbRow class="row-cols-1 row-cols-md-3 mx-0 my-n3">
      <mdbCol class="p-3">
        <mdbCard class="h-100">
          <mdbCardImage
            v-mdb-waves
            src="https://mdbootstrap.com/img/new/standard/city/041.webp"
            top
            alt="..."
          />
          <mdbCardBody>
            <mdbCardTitle>Non Profit 1</mdbCardTitle>
            <mdbCardText>
              This is a longer card with supporting text below as a natural
              lead-in to additional content. This content is a little bit
              longer.
            </mdbCardText>
            <mdbBtn tag="a" href="#!" color="primary">Donate</mdbBtn>
          </mdbCardBody>
        </mdbCard>
      </mdbCol>
      <mdbCol class="p-3">
        <mdbCard class="h-100">
          <mdbCardImage
            v-mdb-waves
            src="https://mdbootstrap.com/img/new/standard/city/042.webp"
            top
            alt="..."
          />
          <mdbCardBody>
            <mdbCardTitle>Non Profit 2</mdbCardTitle>
            <mdbCardText>
              This is a longer card with supporting text below as a natural
              lead-in to additional content. This content is a little bit
              longer.
            </mdbCardText>
            <mdbBtn tag="a" href="#!" color="primary">Donate</mdbBtn>
          </mdbCardBody>
        </mdbCard>
      </mdbCol>
      <mdbCol class="p-3">
        <mdbCard class="h-100">
          <mdbCardImage
            v-mdb-waves
            src="https://mdbootstrap.com/img/new/standard/city/043.webp"
            top
            alt="..."
          />
          <mdbCardBody>
            <mdbCardTitle>Non Profit 3</mdbCardTitle>
            <mdbCardText>
              This is a longer card with supporting text below as a natural
              lead-in to additional content.
            </mdbCardText>
            <mdbBtn tag="a" href="#!" color="primary">Donate</mdbBtn>
          </mdbCardBody>
        </mdbCard>
      </mdbCol>
      <mdbCol class="p-3">
        <mdbCard class="h-100">
          <mdbCardImage
            v-mdb-waves
            src="https://mdbootstrap.com/img/new/standard/city/044.webp"
            top
            alt="..."
          />
          <mdbCardBody>
            <mdbCardTitle>Non Profit 4</mdbCardTitle>
            <mdbCardText>
              This is a longer card with supporting text below as a natural
              lead-in to additional content. This content is a little bit
              longer.
            </mdbCardText>
            <mdbBtn tag="a" href="#!" color="primary">Donate</mdbBtn>
          </mdbCardBody>
        </mdbCard>
      </mdbCol>
    </mdbRow>
  </div>
</template>

<script>
import {
  mdbCol,
  mdbRow,
  mdbCard,
  mdbCardBody,
  mdbCardTitle,
  mdbCardText,
  mdbCardImage,
  mdbBtn,
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
  },
  directives: {
    mdbWaves,
  },
};
</script>
