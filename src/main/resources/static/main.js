
let txtDate = document.getElementById("DateExpiry");
let txtCVV = document.getElementById("cvv");
let txtPAN = document.getElementById("pan");
let btn = document.getElementById("btnSend");
let txtAlert = document.getElementById("alertValidateText");



txtNombre.addEventListener("blur", function (event) {
    event.preventDefault();
    txtDate.value=txtDate.value.trim();
});
    txtCVV.addEventListener("blur", function (event) {
        event.preventDefault();
        txtCVV.value = txtCVV.value.trim();
    });

    txtPAN.addEventListener("blur", function (event) {
        event.preventDefault();
        txtPAN.value = txtPAN.value.trim();
    });



btnContacto.addEventListener("click", function (event) {
    event.preventDefault();

   
    if (txtDate.value==null||txtCVV.value==null ||txtPAN.value==null ) {
        let htmlAlert = `<ul class = listaDeErrores> Please answer all the following fields.</ul>`;
        Swal.fire({
            icon: 'error',
            title: 'WAIT...',
            html: htmlAlert,
        });

        htmlAlert = "";
        

        
    } else {
        Swal.fire("Credit card send")
        
    }

});

