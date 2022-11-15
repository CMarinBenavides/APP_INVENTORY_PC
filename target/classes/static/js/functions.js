// $.getScript("sweetalert2.all.min.js");
// // //funcion para revisar que solo se pueda seleccionar un tipo de vehiculo a la vez
// function selects(){
//     $("#moto").on('change',function(ev){
//         ev.preventDefault();
//         $("#moto").prop("checked",true);
//         $("#carro").prop("checked",false);
//     });
//     $("#carro").on('change',function(ev){
//         ev.preventDefault();
//         $("#carro").prop("checked",true);
//         $("#moto").prop("checked",false);
//     });
// }
// //funcion para verificar si lo que se ingresa de placa es valido
// function checkplaca(placa) {
//     key = placa.keyCode || placa.which;
//     tecla = String.fromCharCode(key).toString();
//     valido = "ABCDEFGHIJKLMNOPQRSTWUVXYZabcdefghijklmnopqrstwuvxyz1234567890";

//     especiales = [8,13];
//     tecla_especial = false

//     for (var i in especiales) {
//         if(key == especiales[i]){
//             tecla_especial = true;
//             break;
//         }
//     }

//     if(valido.indexOf(tecla) == -1 && !tecla_especial)
//     {
//         Swal.fire({
//             title: "ADVERTENCIA",
//             text: "Solo se admiten numeros y letras",
//             icon: "warning",
//             showConfirmButton: false,
//             timer: 1000,
//             toast: true,
//             position: "top-end"});
//         return false;
//     }
// }

// function reloadC(){
//     $("#swal2-checkbox").on('change',function(ev){
//         ev.preventDefault();
//         $("#swal2-checkbox").prop("checked",false);

//     });
// }

function mostrar(){
    var tipo = document.getElementById("password");

    if(tipo.type == "password"){
        tipo.type = "text";
    } else {
        tipo.type = "password";
    }
}


