<template>
    <section class="home">
        <div class="text text-center">All Company</div>
        <div class="table">
            <div class="d-flex flex-row justify-content-between mb-2">
                <h5>Daftar Company</h5>
                <!-- <div><button class="btn add-user">Add User</button></div> -->
                <div>
                    <div class="container mr-3">
                        <div>
                            <div>
                                <b-button id="show-btn" @click="showModal" class="btn-primary">Add</b-button>

                                <b-modal ref="my-modal" hide-footer title="Add Company">
                                    <div class="d-block">
                                        <div class="form">
                                            <!-- Company Name -->
                                            <div class="form-group">
                                                <label for="name_company">Name Company</label>
                                                <input type="text" class="form-control" id="name_company"
                                                    placeholder="Enter Name Company" />
                                            </div>
                                            <!-- NPWP -->
                                            <div class="form-group">
                                                <label for="npwp">NPWP</label>
                                                <input type="text" class="form-control" id="npwp"
                                                    placeholder="Enter NPWP" />
                                            </div>
                                            <!-- Address -->
                                            <div class="form-group">
                                                <label for="address">Address</label>
                                                <input type="text" class="form-control" id="address"
                                                    placeholder="Enter Address" />
                                            </div>
                                            <!-- Email Company -->
                                            <div class="form-group">
                                                <label for="email_company">Email</label>
                                                <input type="email" class="form-control" id="email_company"
                                                    placeholder="Enter Email Company" />
                                            </div>
                                            <!-- Code Pos -->
                                            <div class="form-group">
                                                <label for="postal_code">Code Pos</label>
                                                <input type="email" class="form-control" id="postal_code"
                                                    placeholder="Enter Code Pos" />
                                            </div>
                                            <!-- Nama Admin -->
                                            <div class="form-group">
                                                <label for="admin_name">Admin Name</label>
                                                <input type="text" class="form-control" id="admin_name"
                                                    placeholder="Enter Email Company" />
                                            </div>

                                            <!-- DROPDOWN -->
                                            <!-- <label for="username">Role User</label>
                        <select class="form-select mb-3" aria-label="Default select example">
                          <option selected class="option"> Role</option>
                          <option value="1">Admin</option>
                          <option value="2">Approval</option>
                        </select> -->
                                        </div>
                                    </div>

                                    <div class="dflex justify-content-center">
                                        <b-button variant="primary" block @click="toggleModal">Submit</b-button>
                                        <b-button variant="danger" block @click="toggleModal">Cancel</b-button>
                                    </div>
                                </b-modal>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- MODAL BUTTON -->
            </div>

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
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>
                            <button type="button" class="btn btn-primary">Detail</button>
                            <button type="button" class="btn btn-delete">Remove</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </section>
</template>
  
<script>
export default {
    name: "CompanyS",

    methods: {
        showModal() {
            this.$refs["my-modal"].show();
        },
        hideModal() {
            this.$refs["my-modal"].hide();
        },
        toggleModal() {
            // We pass the ID of the button that we want to return focus to
            // when the modal has hidden
            this.$refs["my-modal"].toggle("#toggle-btn");
        },
    },
};
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
    padding-left: 20px;
    padding-right: 20px;
}

.btn-danger {
    /* margin-left: 10px; */
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

.add-user {
    background-color: #695cfe;
    color: whitesmoke;
}

.show-botton {
    background-color: #695cfe;
}

.show-botton:hover {
    background-color: #695cfe;
}

.form-select {
    width: 100%;
    height: 40px;
    border: 1px solid rgb(200, 200, 200);
    border-radius: 5px;
}

.btn-primary {
    background-color: #695cfe;
}

.table {
    padding-left: 20px;
    padding-right: 20px;
}

.btn-delete {
    margin-left: 10px;
    background-color: rgb(195, 10, 10);
    color: white;
}
</style>