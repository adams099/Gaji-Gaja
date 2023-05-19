<template>
    <section class="home">

        <div class="btn-add mt-3 d-flex flex-row text-white">
            <button class="btn back" v-if="showForm">
                <i class="fas fa-arrow-left"></i> Back
            </button>
            <button class="btn btn-add-com" @click="showForm = true" v-else>Add
                Company</button>
        </div>

        <!-- TABLE COMPANY -->
        <table class="table table-bordered" v-show="!showForm">
            <thead>
                <tr>
                    <th scope="col" style="width: 70px;">ID</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">Handle</th>
                </tr>
            </thead>
            <tbody>
                <tr class="text-center">
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td style="width: 100px;">
                        <div class="button-action d-flex flex-row">
                            <button class="btn btn-primary">Detail</button>
                            <button class="btn btn-primary ml-2 mr-2">Detail</button>
                            <button class="btn btn-primary">Detail</button>
                        </div>
                    </td>
                </tr>

            </tbody>
        </table>

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

export default {
    name: "CompanyS",

    // DATA
    data() {
        return {
            companyDatas: {
                comName: null,
                mailAddress: null,
                comTaxNum: null,
                address: null,
                siup: null,
                postal: null,
                adminName: null,
                adminEmail: null,
                status: null,
                createdBy: null,
                createdTime: null,
                apprBy: null,
                updateTime: null
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

            showForm: false,
        }
    },


};
</script>
  
<style scoped>
table {
    width: 70vw;
    margin-left: 50px;
    margin-top: 20px;
}

tbody {
    background-color: white;
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
</style>