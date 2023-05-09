<template>
  <section class="home">
    <div class="text text-center">Dashboard</div>
    <div class="table">
      <h5>Daftar User</h5>
      <table class="table">
        <thead class="text-center">
          <tr>
            <th scope="col">No</th>
            <th scope="col">Nama</th>
            <th scope="col">Email</th>
            <th scope="col">Handle</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row" class="text-center">1</th>
            <td></td>
            <td>Otto</td>
            <td>
              <button type="button" class="btn btn-primary">Detail</button>
              <button type="button" class="btn btn-danger">Remove</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>

<script>
</script>

<style scoped>
h5 {
  margin-left: 20px;
  color: gray;
}

thead {
  background-color: #695cfe;
  color: aliceblue;
}

.table {
  margin-left: 20px;
  margin-right: 20px;
}

.btn-danger {
  margin-left: 10px;
}

table {
  border-collapse: separate !important;
  border-spacing: 0 !important;
}

table tr th,
table tr td {
  border-right: 1px solid #dee2e6 !important;
  border-bottom: 1px solid #dee2e6 !important;
}

table tr th:first-child,
table tr td:first-child {
  border-left: 1px solid #dee2e6 !important;
}

table tr th {
  border-top: 1px solid #dee2e6 !important;
}

/* top-left border-radius */
table tr:first-child th:first-child {
  border-top-left-radius: 0.25rem !important;
}

/* top-right border-radius */
table tr:first-child th:last-child {
  border-top-right-radius: 0.25rem !important;
}

/* bottom-left border-radius */
table tr:last-child td:first-child {
  border-bottom-left-radius: 0.25rem !important;
}

/* bottom-right border-radius */
table tr:last-child td:last-child {
  border-bottom-right-radius: 0.25rem !important;
}
</style>