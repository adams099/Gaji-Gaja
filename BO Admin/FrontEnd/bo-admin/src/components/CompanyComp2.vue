<template>
    <section class="home">

        <div class="btn-add mt-3 d-flex flex-row text-white">
            <button class="btn back text-white" v-if="showForm" v-on:click="BackButton(1)">
                <i class="fas fa-arrow-left text-white"></i> Back
            </button>
            <button class="btn btn-add-com" @click="showForm = true" v-else>Add
                Company</button>


        </div>

        <!------------------ START TABLE ------------------->
        <table class="table " v-show="!showForm">
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
                <tr class=" baris text-center shadow-lg bg-white" v-for="(item, index) in paginatedData" :key="index">
                    <th scope="row" class="text-center">{{ item.id }}</th>
                    <td>{{ item.comName }}</td>
                    <td>{{ item.mailAddress }}</td>
                    <button type="button" class="status blue" v-if="item.status == 1">In Review</button>
                    <button type="button" class="status green" v-else-if="item.status == 2">Active</button>
                    <button type="button" class="status red" v-else-if="item.status == 3">Rejected</button>
                    <button type="button" class="status salmon" v-else>Deactive</button>
                    <td class="text-center">
                        <button type="button" class="btn btn-primary" @click="showForm = true">Detail</button>
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

        <div class="row d-flex justify-content-center next color-text" v-if="!showForm && companyData.length < 10">
            <button type="button" class="btn btn-success" @click="previousPage"
                :disabled="currentPage == 1">Previous</button>
            <p class="ml-4 mr-4 font-italic mt-2">{{ currentPage }} / {{ pageCount }}</p>
            <button type="button" class="btn btn-success" @click="nextPage"
                :disabled="currentPage == pageCount">Next</button>
        </div>

        <!------------------ END TABLE ------------------>

        <!--------------------- START ADD COMPANY -------------------------->
        <div class="form" v-show="showForm">
            <div class="form-company">
                <h6 class="text-center mb-3 color-text">Form Add Company</h6>
            </div>
            <form class="iseng form-detail-company flex-row bg-white shadow-lg" @submit.prevent="SubmitCompany">
                <div class="form-group">
                    <label for="name_company">Company Name</label>
                    <input type="text" class="form-control company-detail" id="name_company" placeholder="Company Name"
                        required v-model="companyDatas.comName">
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="email_company">Email Company</label>
                        <input type="email" class="form-control" id="email_company" placeholder="Email" required
                            v-model="companyDatas.mailAddress">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="NPWP">NPWP</label>
                        <input type="text" class="form-control" id="NPWP" placeholder="Enter NPWP Number" required
                            v-model="companyDatas.comTaxNum">
                    </div>
                </div>
                <div class="form-group">
                    <label for="Address">Address</label>
                    <input type="text" class="form-control company-detail" id="Address" placeholder="Address" required
                        v-model="companyDatas.address">
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="postal_code">Postal Code</label>
                        <input type="text" class="form-control company-detail" id="postal_code" placeholder="Postal Code"
                            v-model="companyDatas.postal" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="siup">SIUP</label>
                        <input type="text" class="form-control" id="siup" placeholder="Enter SIUP number"
                            v-model="companyDatas.siup" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="admin_name">Admin Name</label>
                        <input type="text" class="form-control" id="admin_name" placeholder="Enter Admin Name"
                            v-model="companyDatas.adminName">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="admin_email">Admin Email</label>
                        <input type="text" class="form-control" id="admin_email" placeholder="Enter Admin Email"
                            v-model="companyDatas.adminEmail">
                    </div>
                </div>

                <button type="submit" class="btn add-company">Add Company</button>
            </form>
            <!--------------------- END ADD COMPANY -------------------------->

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
            companyDatas: {
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
                updateTime: null,
            },
            itemsPerPage: 7,
            currentPage: 1,

            companyData: {
            },

            buatAkun: {
                name: null,
                email: null,
                pass: null,
                roleId: null,
                statId: null,
                companyId: null,
                created: null,
                createdBy: null,
                phone: null,
                apprBy: null,
                update: null
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

            showForm: false,
            showModal: false,
        }
    },
    methods: {
        SubmitCompany() {
            let akun = this.buatAkun
            let data = this.companyDatas

            akun.email = data.adminEmail;
            akun.name = data.adminName;
            akun.status = 4;
            akun.pass = "testing";
            akun.roleId = 2;
            akun.statId = 4;
            akun.createdBy = this.$session.get('email');

            data.status = 1;
            data.createdBy = this.$session.get('email');
            // console.log(data);
            companyService.upload(data)
                .then((response) => {
                    console.log("add Company");
                    console.log(response.status);

                    // add table approved

                    adds.register(akun)
                        .then((response) => {
                            // this.companyData = response.data;
                            console.log("add User");
                            console.log(response.status);
                            this.$toast.success('Company Data has been successfully added!', {
                                position: 'top-right',
                                timeout: 2500,
                            });
                            for (const property in this.companyDatas) {
                                this.companyDatas[property] = null
                            }
                        })
                        .catch(() => {
                            this.$toast.error('Errorzzzzz!', {
                                position: 'top-right',
                                timeout: 2500,
                            });
                        });
                })
                .catch(() => {

                    this.$toast.error('Errorjjjjjj!', {
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
                    // console.log(this.companyData)
                    console.log("get Company");
                })
                .catch((e) => {
                    console.log(e);
                });
        },

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


        nextPage() {
            this.currentPage++;
        },

        BackButton(back) {
            if (back == 1) {
                this.showForm = false
            }
        },

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

    mounted() {
        this.getCompany();
    },


};
</script>
  
<style scoped>
table {
    width: 70vw;
    margin-left: 50px;
    margin-top: 20px;
}



thead {
    background-color: #695cfe;
    color: white;
    text-align: center;
}

form {
    width: 60vw;
    padding: 40px 40px;
    border-radius: 20px;
}

.form {
    padding-bottom: 20px;
}


.iseng {
    margin-left: 100px;
}

.add-company {
    background-color: #695cfe;
    color: white;
    width: 100%;
    border-radius: 10px;
    margin-top: 15px;
}

.btn-add {
    display: flex;
    justify-content: flex-end;
    margin-right: 80px;

}

.btn-add-com {
    background-color: #695cfe;
    color: white;
    border-radius: 10px;

}

.back {
    background-color: #695cfe;
    color: white;
    border-radius: 10px;
    margin-right: 70px;


}

.btn-add-com:hover {
    color: white;
    background-color: #5a4de5;
}

h6 {
    font-size: 25px;
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
</style>