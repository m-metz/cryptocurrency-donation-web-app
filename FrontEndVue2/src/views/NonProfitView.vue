<template>
  <div class="non-profit">
    <h1>Donate Cryptocurrency to Non-Profits</h1>
    <mdbRow class="row-cols-1 row-cols-md-3 mt-n3">
      <mdbCol class="my-3">
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
