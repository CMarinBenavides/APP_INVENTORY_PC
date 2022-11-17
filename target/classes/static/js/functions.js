$.getScript("sweetalert2.all.min.js");
$.getScript("@{/sweetalert2.all.min.js}");
$.getScript("jquery-3.5.1.min.js");
$.getScript("@{/jquery-3.5.1.min.js}");
//funcion para verificar si lo que se ingresa de placa es valido
function checkSerial() {
  serial = document.getElementById("serial").value;
    if (serial.length > 4) {
        //verificar si los tres primeros caracteres son numeros y el ultimo es una letra
        if (serial.substring(0, 3).match(/^[0-9]+$/) && serial.substring(3, 5).match(/^[a-zA-Z]+$/)) {
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'El serial no es valido',
                toast: true,
                position: 'top-start',
                showConfirmButton: false,
                timer: 1000,
            });
        return false;
        }
    return true;
    }
    return false;
}
function mostrar(){
    var tipo = document.getElementById("password");

    if(tipo.type == "password"){
        tipo.type = "text";
    } else {
        tipo.type = "password";
    }
}



