<template>
    <section class="home">
        <div class="text text-center" v-if="!showDetail">All Company</div>
        <div class="table">
            <div class="d-flex flex-row justify-content-between mb-2">
                <h5 class="color-text" v-if="!showDetail">Daftar Company</h5>

                <div>
                    <div class="container mr-3 d-flex">
                        <div>
                            <!------------------------ MODAL BOX --------------------->
                            <div>
                                <b-button id="show-btn" @click="showModal" v-if="!showDetail" class="btn-primary shadow"
                                    style="margin-left: 272px;">Add
                                    Company</b-button>
                                <b-button id="show-btn " v-if="showDetail" @click="showDetail = false"
                                    class="btn-primary mt-3">Back</b-button>
                                <b-modal ref="my-modal" hide-footer title="Add Company">
                                    <div class="d-block">
                                        <div class="form-add">
                                            <!-- Company Name -->
                                            <div class="form-group">
                                                <label for="name_company">Company Name</label>
                                                <input type="text" v-model="companyDatas.comName" class="form-control"
                                                    id="name_company" placeholder="Enter Name Company" />
                                            </div>
                                            <!-- NPWP -->
                                            <div class="form-group">
                                                <label for="npwp">NPWP</label>
                                                <input type="text" v-model="companyDatas.comTaxNum" class="form-control"
                                                    id="npwp" placeholder="Enter NPWP" />
                                            </div>
                                            <!-- Address -->
                                            <div class="form-group">
                                                <label for="address">Address</label>
                                                <input type="text" v-model="companyDatas.address" class="form-control"
                                                    id="address" placeholder="Enter Address" />
                                            </div>
                                            <!-- Email Company -->
                                            <div class="form-group">
                                                <label for="email_company">Email</label>
                                                <input type="email" v-model="companyDatas.mailAddress" class="form-control"
                                                    id="email_company" placeholder="Enter Email Company" />
                                            </div>
                                            <!-- Code Pos -->
                                            <div class="form-group">
                                                <label for="postal_code">Code Pos</label>
                                                <input type="text" class="form-control" v-model="companyDatas.postal"
                                                    id="postal_code" placeholder="Enter Code Pos" />
                                            </div>
                                            <!-- Nama Admin -->
                                            <div class="form-group">
                                                <label for="admin_name">Admin Name</label>
                                                <input type="text" v-model="companyDatas.adminName" class="form-control"
                                                    id="admin_name" placeholder="Enter Email Company" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="dflex justify-content-center">
                                        <b-button variant="primary" block @click="SubmitCompany">Submit</b-button>
                                        <b-button variant="danger" block @click="toggleModal">Cancel</b-button>
                                    </div>
                                </b-modal>
                            </div>
                            <!--------------------- END ADD COMPANY  --------------------------->

                            <!---------------------- START DETAIL COMPANY ---------------------->
                            <div class="flex-column">
                                <div class="detail shadow">
                                    <h5 class="color-text mt-3 mb-3 text-center text-color text-white " v-if="showDetail"
                                        style="font-size: 20px; ">
                                        Detail Company</h5>
                                </div>
                            </div>

                            <div class="form justify-content-center d-flex flex-row bg-white shadow-lg" v-if="showDetail">

                                <form class="form-detail-company flex-row ">
                                    <div class="form-group">
                                        <label for="name_company">Company Name</label>
                                        <input type="text" class="form-control company-detail" id="name_company"
                                            placeholder="Company Name" v-model="companyData.comName">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="email_company">Email Company</label>
                                            <input type="email" class="form-control" id="email_company" placeholder="Email"
                                                v-model="companyData.mailAddress">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="NPWP">NPWP</label>
                                            <input type="text" class="form-control" id="NPWP"
                                                placeholder="Enter NPWP Number" v-model="companyData.comTaxNum">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="Address">Address</label>
                                        <input type="text" class="form-control company-detail" id="Address"
                                            placeholder="Address" v-model="companyData.address">
                                    </div>
                                    <div class="form-group">
                                        <label for="postal_code">Postal Code</label>
                                        <input type="text" class="form-control company-detail" id="postal_code"
                                            placeholder="Postal Code" v-model="companyData.postal">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="admin_name">Admin Name</label>
                                            <input type="text" class="form-control" id="admin_name"
                                                placeholder="Enter Admin Name" v-model="companyData.adminName">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="admin_email">Admin Email</label>
                                            <input type="text" class="form-control" id="admin_email"
                                                placeholder="Enter Admin Email" v-model="companyData.adminEmail">
                                        </div>

                                    </div>

                                    <button type="submit" class="btn btn-primary mt-2">Update</button>
                                </form>
                            </div>
                            <!---------------------- END DETAIL COMPANY ------------------------>

                            <!---------------------- SHOW MODAL STATUS ------------------------>
                            <!-- <div class="show-modal-status">
                                <div v-if="showModalStatus" class="modal d-flex flex-column">
                                    <div class="modal-content d-flex justify-content-center">
                                        <h5 class="text-center mb-2">Status</h5>
                                        <div class="d-flex flex-column button-action col col-md-6 ">
                                            <button class="btn btn-success button-status">Approve</button>
                                            <button class="btn btn-danger mt-4 mb-4 button-status">Reject</button>
                                            <button class="btn btn-warning button-status mb-2 text-white">Deactice</button>
                                        </div>

                                        <div class="back-button ">
                                            <button @click="showModalStatus = false"
                                                class="btn btn-secondary mt-3 ml-3 shadow">Back</button>
                                        </div>
                                    </div>
                                </div>
                            </div> -->

                            <div>
                                <b-modal v-model="showModalStatus">
                                    <b-modal v-model="showModalStatus" hide-footer>
                                        <div>
                                            <h6> Status Confirmation</h6>
                                            <!-- <p>Are you sure you want to perform this action?</p> -->
                                            <b-col>
                                                <div class="initombol">
                                                    <button @click="accept" class="btn btn-success mr-3">Accept</button>
                                                    <button @click="reject" class="btn btn-danger mr-3">Reject</button>
                                                    <button @click="deadactive"
                                                        class="btn btn-primary mr-3">Deadactive</button>
                                                </div>
                                            </b-col>
                                            <!-- <div class="back-button ">
                                            <button @click="showModalStatus = false"
                                                class="btn btn-secondary mt-3 ml-3 shadow">Back</button>
                                        </div> -->
                                        </div>
                                    </b-modal>
                                </b-modal>
                            </div>

                            <!---------------------- END MODAL STATUS ------------------------>
                        </div>
                    </div>
                </div>
                <!-- END MODAL BOX -->
            </div>

            <!------------------ START TABLE ------------------->
            <table class="table " v-show="!showDetail">
                <thead class="text-center">
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Company Name</th>
                        <th scope="col">Email Admin</th>
                        <th scope="col">Status</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody v-if="companyData.length > 0">
                    <tr class=" baris text-center" v-for="(item, index) in paginatedData" :key="index">
                        <th scope="row" class="text-center">{{ item.id }}</th>
                        <td>{{ item.comName }}</td>
                        <td>{{ item.adminEmail }}</td>
                        <td>In Review</td>
                        <td class="text-center">
                            <button type="button" class="btn btn-primary" @click="showDetail = true">Detail</button>
                            <button type="button" class="btn btn-delete text-white "
                                @click="showModalStatus = true">Status</button>
                        </td>
                    </tr>
                </tbody>

                <tbody v-else>
                    <tr class="msg-tr text-center">
                        <td colspan="6" class="msg-null text-center">
                            <h3 class="color-text">Saat ini Tidak Ada Data Company !</h3>
                        </td>
                    </tr>
                </tbody>

            </table>
            <div class="row d-flex justify-content-center next" v-if="!showDetail">
                <button type="button" class="btn btn-success" @click="previousPage"
                    :disabled="currentPage == 1">Previous</button>
                <p class="ml-4 mr-4 font-italic mt-2">{{ currentPage }} / {{ pageCount }}</p>
                <button type="button" class="btn btn-success" @click="nextPage"
                    :disabled="currentPage == pageCount">Next</button>
            </div>
            <!------------------ END TABLE ------------------>
        </div>
    </section>
</template>
  
<script>
import companyService from '@/services/companyService.js';
import adds from '@/services/userService.js';

export default {
    name: "CompanyS",

    // DATA
    data() {
        return {
            itemsPerPage: 7,
            currentPage: 1,

            companyData: {
                comName: null,
                comTaxNum: null,
                address: null,
                mailAddress: null,
                postal: null,
                adminName: null,
                adminEmail: null,
                status: null,
                sender: null
            },
            companyDatas: {

                comName: null,
                comTaxNum: null,
                address: null,
                mailAddress: null,
                postal: null,
                adminName: null,
                adminEmail: null,
                status: '4',
                sender: null
            },
            buatAkun: {
                name: null,
                pass: null,
                email: null,
                roleId: 3,
                statId: 1,
                username: null,
                createdBy: null,
                approved: null
            },
            showDetail: false,
            showModalStatus: false,

            error: {
                "comname": false,
                "comtax": false,
                "address": false,
                "email": false,
                "emailada": false,
                "pass": false,
            }
        }
    },

    methods: {
        previousPage() {
            this.currentPage--;
        },
        // navigasi ke halaman berikutnya
        nextPage() {
            this.currentPage++;
        },

        // SHOW MODAL BOX
        showModal() {
            this.$refs["my-modal"].show();
        },
        showModalDetail() {
            this.$refs["company-modal"].show();
        },
        hideModal() {
            this.$refs["my-modal"].hide();
        },
        toggleModal() {
            for (const property in this.companyDatas) {
                this.companyDatas[property] = null
            }
            this.$refs["my-modal"].toggle("#toggle-btn");
        },
        toggleModalDetail() {
            this.$refs["company-modal"].toggle("#toggle-btn");
        },

        //test doang ya
        accept() {
            // handle accept action
            this.showModalStatus = false
        },
        reject() {
            // handle reject action
            this.showModalStatus = false
        },
        deadactive() {
            // handle review action
            this.showModalStatus = false
        },

        // GET COMPANY
        getCompany() {
            companyService
                .getAll()
                .then((response) => {
                    this.companyData = response.data;
                    console.log(this.companyData);
                })
                .catch((e) => {
                    console.log(e);
                });
        },
        SubmitCompany() {
            let datas = this.buatAkun
            let data = this.companyDatas
            datas.email = data.mailAddress
            datas.pass = "testing"
            datas.createdBy = this.$session.get('email')
            datas.name = data.adminName
            datas.username = data.adminName + 123
            data.adminEmail = this.$session.get('email')
            data.sender = this.$session.get("jwt").data.substr(23, this.$session.get("jwt").data.length)
            console.log(data);
            companyService.upload(data)
                .then((response) => {
                    console.log(response.data);
                    adds.register(datas)
                        .then((response) => {
                            // this.companyData = response.data;
                            console.log(response.data);
                            this.$toast.success('Company Data has been successfully added!', {
                                position: 'top-right',
                                timeout: 2500,
                            });
                            this.toggleModal()

                        })
                        .catch((e) => {
                            console.log(e);
                        });
                })
                .catch((e) => {
                    console.log(e);
                });
        },

        // Post Data Company


    },

    // MOUNTED
    mounted() {
        this.getCompany();

    },

    computed: {
        // hitung jumlah halaman
        pageCount() {
            const itemCount = this.companyData.length;
            const pageCount = Math.ceil(itemCount / this.itemsPerPage);
            return pageCount;
        },
        // ambil data sesuai halaman saat ini
        paginatedData() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.companyData.slice(startIndex, endIndex);
        },
    },
};
</script>
  
<style scoped>
h5 {
    margin-left: 20px;
    color: gray;
}

h6 {
    text-align: center;
    margin-bottom: 20px;
}

.initombol {
    text-align: center;
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

.baris {
    background-color: #e4e9f7;
}

form {
    width: 60vw;
    padding: 40px 40px;
    border-radius: 20px;
}

.form {
    /* left: 200px; */
    margin-left: 150px;
    border-radius: 20px;
    margin-bottom: 5px;
}

.form-detail-company {
    border: 1px solid #695cfe;

}


/* Style untuk konten modal box */
/* SHOW MODAL STYLE */

.modal {
    top: 150px;
    left: 200px;
    position: absolute;
    margin: 50px 50px;
    width: 25vw;
    height: 300px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-radius: 15px;
    background-color: transparent;

}

.modal-content {
    border: 1px solid grey;
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
}

.modal.fade .modal-dialog {
    transition: opacity 0.3s linear;
    opacity: 0;
}

.modal.fade.show .modal-dialog {
    opacity: 1;
}

.button-status {
    width: 250px;
}

.detail {
    margin-left: 200px;
    background-color: #695cfe;
    width: 200px;
    border-radius: 20px;
    font-size: 5px;

}

/* .status {
  margin-top: 17px;
  background-color: transparent;
  border: 2px solid blue;
  color: blue;
  border-radius: 5px;
  font-size: 13px;
  padding: 3px;

} */

.next {
    /* background-color: #695cfe; */
    position: fixed;
    bottom: 0;
    left: 700px;
    margin-bottom: 10px;
}
</style>