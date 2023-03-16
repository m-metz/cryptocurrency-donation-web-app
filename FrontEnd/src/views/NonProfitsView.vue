<template>
  <div class="non-profits">
    <h1>Donate Cryptocurrency to Non-Profits</h1>
    <form class="d-flex input-group w-auto">
      <input
        type="search"
        class="form-control"
        placeholder="Search for non-profits"
        aria-label="Search"
      />
      <MDBBtn outline="primary"> Search </MDBBtn>
    </form>
    <MDBRow :cols="['1', 'md-3']" class="g-4">
      <MDBCol>
        <MDBCard>
          <MDBCardImg src="https://mdbootstrap.com/img/new/standard/city/041.webp" top alt="..." />
          <MDBCardBody>
            <MDBCardTitle>Non Profit 1</MDBCardTitle>
            <MDBCardText>
              This is a longer card with supporting text below as a natural lead-in to additional
              content. This content is a little bit longer.
            </MDBCardText>
            <MDBBtn tag="a" href="#!" color="primary">Donate</MDBBtn>
          </MDBCardBody>
        </MDBCard>
      </MDBCol>
      <MDBCol>
        <MDBCard>
          <MDBCardImg src="https://mdbootstrap.com/img/new/standard/city/042.webp" top alt="..." />
          <MDBCardBody>
            <MDBCardTitle>Non Profit 2</MDBCardTitle>
            <MDBCardText>
              This is a longer card with supporting text below as a natural lead-in to additional
              content. This content is a little bit longer.
            </MDBCardText>
            <MDBBtn tag="a" href="#!" color="primary">Donate</MDBBtn>
          </MDBCardBody>
        </MDBCard>
      </MDBCol>
      <MDBCol>
        <MDBCard>
          <MDBCardImg src="https://mdbootstrap.com/img/new/standard/city/043.webp" top alt="..." />
          <MDBCardBody>
            <MDBCardTitle>Non Profit 3</MDBCardTitle>
            <MDBCardText>
              This is a longer card with supporting text below as a natural lead-in to additional
              content.
            </MDBCardText>
            <MDBBtn tag="a" href="#!" color="primary">Donate</MDBBtn>
          </MDBCardBody>
        </MDBCard>
      </MDBCol>
      <MDBCol>
        <MDBCard>
          <MDBCardImg src="https://mdbootstrap.com/img/new/standard/city/044.webp" top alt="..." />
          <MDBCardBody>
            <MDBCardTitle>Non Profit 4</MDBCardTitle>
            <MDBCardText>
              This is a longer card with supporting text below as a natural lead-in to additional
              content. This content is a little bit longer.
            </MDBCardText>
            <MDBBtn tag="a" href="#!" color="primary">Donate</MDBBtn>
          </MDBCardBody>
        </MDBCard>
      </MDBCol>
    </MDBRow>
  </div>
</template>

<script>
import {
  MDBCol,
  MDBRow,
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
  MDBCardImg,
  MDBBtn,
  mdbRipple
} from 'mdb-vue-ui-kit'
export default {
  components: {
    MDBCol,
    MDBRow,
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
    MDBCardText,
    MDBCardImg,
    MDBBtn
  },
  directives: {
    mdbRipple
  }
}
</script>
