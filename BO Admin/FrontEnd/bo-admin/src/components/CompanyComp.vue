<template>
    <section class="home">
        <div class="text text-center">Company Form</div>
        <div class="table">
            <!--------------------- START ADD COMPANY -------------------------->
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
    },
};
</script>
  
<style scoped>
.table {
    padding-left: 20px;
    padding-right: 20px;
}

.table {
    padding-left: 20px;
    padding-right: 20px;
}

form {
    width: 60vw;
    padding: 40px 40px;
    border-radius: 20px;
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
</style>