<template>
  <div>
    <div class="cryptocurrency-donations">
      <h1>Cryptocurrency Donatons History</h1>
    </div>
    <mdbTbl class="align-middle mb-0 bg-white" hover>
      <thead style="background-color: rgba(0, 0, 0, 0.075)">
        <tr>
          <th>User</th>
          <th>Non-Profit</th>
          <th>Status</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <div class="d-flex align-items-center">
              <div class="ms-3">
                <p class="fw-bold mb-1">John Doe</p>
                <p class="text-muted mb-0">john.doe@gmail.com</p>
              </div>
            </div>
          </td>
          <td>
            <p class="fw-normal mb-1">Non-Profit 1</p>
            <p class="text-muted mb-0">Cause 1</p>
          </td>
          <td>
            <mdbBadge color="success" pill class="d-inline">Complete</mdbBadge>
          </td>
          <td></td>
        </tr>
        <tr>
          <td>
            <div class="d-flex align-items-center">
              <div class="ms-3">
                <p class="fw-bold mb-1">Alex Ray</p>
                <p class="text-muted mb-0">alex.ray@gmail.com</p>
              </div>
            </div>
          </td>
          <td>
            <p class="fw-normal mb-1">Non-Profit 1</p>
            <p class="text-muted mb-0">Cause 2</p>
          </td>
          <td>
            <mdbBadge color="primary" pill class="d-inline"
              >Smart Contract Processing</mdbBadge
            >
          </td>
          <td></td>
        </tr>
        <tr>
          <td>
            <div class="d-flex align-items-center">
              <div class="ms-3">
                <p class="fw-bold mb-1">Kate Hunington</p>
                <p class="text-muted mb-0">kate.hunington@gmail.com</p>
              </div>
            </div>
          </td>
          <td>
            <p class="fw-normal mb-1">Non-Profit 2</p>
            <p class="text-muted mb-0">Cause 3</p>
          </td>
          <td>
            <mdbBadge color="warning" pill class="d-inline"
              >Pending Donation</mdbBadge
            >
          </td>
          <td>
            <mdbBtn color="link" size="sm" rounded class="fw-bold">
              Donate
            </mdbBtn>
          </td>
        </tr>
      </tbody>
    </mdbTbl>
  </div>
</template>

<script>
import { mdbTbl, mdbBtn, mdbBadge } from "mdbvue";

export default {
  components: {
    mdbTbl,
    mdbBtn,
    mdbBadge,
  },
};
</script>
