<template>
    <section class="home">

        <div class="btn-add mt-5">
            <button class="btn btn-success">Add
                Company</button>
        </div>

        <table class="table table-bordered">
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

    methods: {
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

.btn-success {
    background-color: #695cfe;
}
</style>