<template>
    <section class="home">
        <div class="btn-add mt-2 d-flex flex-row text-white justify-content-end">
            <button class="btn back text-white" v-if="showDetail" @click="BackButton(1)">
                <i class="fas fa-arrow-left text-white"></i> Back
            </button>
        </div>
        <div class="table">
            <div class="d-flex flex-row justify-content-between mb-2">
                <h5 class="color-text" v-if="!showDetail">Daftar Company</h5>

                <div>
                    <div class="container mr-3 d-flex">
                        <div>
                            <!---------------------- START DETAIL COMPANY ---------------------->
                            <div class="flex-column">
                                <div class="detail shadow">
                                    <h5 class="color-text mt-3 mb-3 text-center text-color text-white " v-if="showDetail"
                                        style="font-size: 20px; ">
                                        Detail Company</h5>

                                </div>
                            </div>

                            <div class="form justify-content-center d-flex flex-row bg-white shadow-lg" v-if="showDetail">

                                <form class="form-detail-company flex-row" @submit.prevent="updateCompanyFunc">
                                    <div class="form-group">
                                        <label for="name_company">Company Name</label>
                                        <input type="text" class="form-control company-detail" id="name_company"
                                            placeholder="Company Name" required v-model="updateCompany.comName">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="email_company">Email Company</label>
                                            <input type="email" class="form-control" id="email_company" placeholder="Email"
                                                required v-model="updateCompany.mailAddress">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="NPWP">NPWP</label>
                                            <input type="text" class="form-control" id="NPWP"
                                                placeholder="Enter NPWP Number" required v-model="updateCompany.comTaxNum">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="Address">Address</label>
                                        <input type="text" class="form-control company-detail" id="Address"
                                            placeholder="Address" required v-model="updateCompany.address">
                                    </div>
                                    <div class="form-group">
                                        <label for="postal_code">Postal Code</label>
                                        <input type="text" class="form-control company-detail" id="postal_code"
                                            placeholder="Postal Code" required v-model="updateCompany.postal">
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="admin_name">Admin Name</label>
                                            <input type="text" class="form-control" id="admin_name"
                                                placeholder="Enter Admin Name" disabled v-model="updateCompany.adminName">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="admin_email">Admin Email</label>
                                            <input type="text" class="form-control" id="admin_email"
                                                placeholder="Enter Admin Email" disabled v-model="updateCompany.adminEmail">
                                        </div>

                                    </div>

                                    <button type="submit" class="btn btn-primary mt-2">Update</button>
                                </form>
                            </div>
                            <!---------------------- END DETAIL COMPANY ------------------------>

                            <!---------------------- SHOW MODAL STATUS ------------------------>
                            <div>
                                <b-modal v-model="showModalStatus">
                                    <b-modal v-model="showModalStatus" hide-footer>
                                        <div>
                                            <h6> Status Confirmation</h6>
                                            <b-col>
                                                <div class="initombol">
                                                    <button @click="accept" class="btn btn-success mr-3">Accept</button>
                                                    <button @click="reject" class="btn btn-danger mr-3">Reject</button>
                                                    <button @click="deadactive"
                                                        class="btn btn-primary mr-3">Deadactive</button>
                                                </div>
                                            </b-col>

                                        </div>
                                    </b-modal>
                                </b-modal>
                            </div>
                            <!---------------------- END MODAL STATUS ------------------------>
                        </div>
                    </div>
                </div>
            </div>


            <!------------------ START TABLE ------------------->
            <table class="table " v-show="!showDetail">
                <thead class="text-center">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Company Name</th>
                        <th scope="col">Mail Address</th>
                        <th scope="col">Status</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody v-if="companyData.length > 0">
                    <tr class=" baris text-center shadow-lg " v-for="(item, index) in paginatedData" :key="index">
                        <th scope="row" class="text-center">{{ item.id }}</th>
                        <td>{{ item.comName }}</td>
                        <td>{{ item.mailAddress }}</td>
                        <button type="button" class="status blue" v-if="item.status == 1">In Review</button>
                        <button type="button" class="status green" v-else-if="item.status == 2">Active</button>
                        <button type="button" class="status red" v-else-if="item.status == 3">Rejected</button>
                        <button type="button" class="status salmon" v-else>Deactive</button>
                        <td class="text-center">
                            <button type="button" class="btn btn-primary" @click="showDetails(item)">Detail</button>
                            <button type="button" class="btn btn-delete text-white "
                                @click="showModalStatuss(item)">Status</button>
                        </td>
                    </tr>
                </tbody>

                <tbody v-else>
                    <tr class="msg-tr text-center">
                        <td colspan="6" class="msg-null text-center">
                            <h3 class="color-text">Saat ini Tidak Ada Data Terkini !</h3>
                        </td>
                    </tr>
                </tbody>

            </table>
            <div class="row d-flex justify-content-center next color-text" v-if="!showDetail">
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

export default {
    name: "CompanyS",

    // DATA
    data() {
        return {
            itemsPerPage: 7,
            currentPage: 1,

            companyData: {
            },

            updateCompany: {
                id: null,
                comName: null,
                comTaxNum: null,
                siup: null,
                address: null,
                mailAddress: null,
                postal: null,
                adminName: null,
                adminEmail: null,
                status: null,
                createdBy: null,
                createdTime: null,
                apprBy: null,
                updateTime: null
            },

            showDetail: false,
            showModalStatus: false,
        }
    },

    methods: {
        updateCompanyFunc() {
            let data = this.updateCompany;

            // console.log(data);
            companyService.upload(data)
                .then((response) => {
                    // console.log(response.data);
                    console.log(response.status);
                    this.$toast.success('Company Data has been successfully Update!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    this.getCompany();
                    this.showDetail = !this.showDetail;
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });

        },

        previousPage() {
            this.currentPage--;
        },

        showDetails(test) {
            this.showDetail = !this.showDetail
            // console.log(test);
            for (const property in this.updateCompany) {
                this.updateCompany[property] = test[property];
            }
        },

        showModalStatuss(test) {
            this.showModalStatus = !this.showModalStatus
            // console.log(test);
            for (const property in this.updateCompany) {
                this.updateCompany[property] = test[property];
            }
        },

        // navigasi ke halaman berikutnya
        nextPage() {
            this.currentPage++;
        },
        BackButton(back) {
            if (back == 1) {
                this.showDetail = false
            }
        },

        //test doang ya
        accept() {
            // handle accept action
            let data = this.updateCompany;
            data.status = 2;
            data.apprBy = this.$session.get('email');
            companyService.upload(data)
                .then((response) => {
                    this.$toast.success('Company status has been successfully Update!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    // console.log(response.data);
                    console.log(response.status);
                    this.showModalStatus = false;
                    this.getCompany();
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });
            this.showModalStatus = false
        },
        reject() {
            // handle reject action
            let data = this.updateCompany;
            data.status = 3;
            data.apprBy = this.$session.get('email');
            companyService.upload(data)
                .then((response) => {
                    this.$toast.success('Company status has been successfully Rejected!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    console.log(response.status);
                    this.showModalStatus = false;
                    this.getCompany();
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });
        },
        deadactive() {
            // handle review action
            let data = this.updateCompany;
            data.status = 4;
            data.apprBy = this.$session.get('email');
            companyService.upload(data)
                .then((response) => {
                    this.$toast.success('Company status has been successfully Deactive!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    console.log(response.status);
                    this.showModalStatus = false;
                    this.getCompany();
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });
        },

        // GET COMPANY
        getCompany() {
            companyService
                .getAll()
                .then((response) => {
                    this.companyData = response.data;
                    // console.log(this.companyData);
                    console.log("get Company");
                })
                .catch((e) => {
                    console.log(e);
                });
        },
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
    background-color: white;
}

form {
    width: 60vw;
    padding: 40px 40px;
    border-radius: 20px;
}

.form {
    /* left: 200px; */
    margin-left: 100px;
    border-radius: 20px;
    margin-bottom: 5px;
}

.detail {
    margin-left: 100px;
    background-color: #695cfe;
    width: 200px;
    border-radius: 20px;
    font-size: 5px;

}

.next {
    /* background-color: #695cfe; */
    position: fixed;
    bottom: 0;
    left: 700px;
    margin-bottom: 10px;
}

.status {
    margin-top: 17px;
    background-color: transparent;
    border-radius: 5px;
    font-size: 13px;
    padding: 3px;

}

.blue {
    border: 2px solid blue;
    color: blue;
}

.salmon {
    border: 2px solid salmon;
    color: salmon;
}

.red {
    border: 2px solid red;
    color: red;
}

.green {
    border: 2px solid green;
    color: green;
}

.row {
    position: fixed;

}

.back {
    background-color: #695cfe;
    margin-right: 120px;
    border-radius: 10px;
}
</style>