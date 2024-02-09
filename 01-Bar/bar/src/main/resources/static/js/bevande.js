console.log("CIAO MONDO DA JAVASCRIPT")



function onSelectionChange(){
    var inputNome = document.getElementById("inputNomeBevanda");
    var inputPrezzo = document.getElementById("inputPrezzoBevanda");
    var inputId = document.getElementById("inputIdBevanda");

    var selectBevanda = document.getElementById("bevandaSelection");
    var selectedOption = document.getElementById(selectBevanda.value);

    inputId.value = selectedOption.getAttribute("id-bevanda");
    inputNome.value = selectedOption.getAttribute("nome-bevanda");
    inputPrezzo.value = selectedOption.getAttribute("prezzo-bevanda");
}