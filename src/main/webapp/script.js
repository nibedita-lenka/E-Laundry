function updatePrice(){
    console.log("heyy");
    var shirtPrice = document.getElementById("shirtPrice").innerText;
    var pantPrice = document.getElementById("pantPrice").innerText;
    var tShirtPrice = document.getElementById("tShirtPrice").innerText;
    var winterPrice = document.getElementById("winterPrice").innerText;
    var blanketPrice = document.getElementById("blanketPrice").innerText;
    var otherPrice = document.getElementById("otherPrice").innerText; 

    var shirtCount = document.getElementById("shirt").value;
    var pantCount = document.getElementById("pant").value;
    var tShirtCount = document.getElementById("tshirt").value;
    var winterCount = document.getElementById("winter").value;
    var blanketCount = document.getElementById("blanket").value;
    var otherCount = document.getElementById("other").value;


    var totalShirtPrice = shirtPrice * shirtCount;
    var totalPantPrice = pantPrice * pantCount;
    var totalTShirtPrice = tShirtPrice * tShirtCount;
    var totalWinterPrice = winterPrice * winterCount;
    var totalBlanketPrice = blanketPrice * blanketCount;
    var totalOtherPrice = otherPrice * otherCount;

    total = totalShirtPrice + totalPantPrice + totalTShirtPrice + totalWinterPrice + totalBlanketPrice + totalOtherPrice;
    document.getElementById("totalPrice").value = total;
}