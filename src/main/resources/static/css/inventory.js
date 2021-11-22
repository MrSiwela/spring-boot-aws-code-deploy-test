// Get the modal
var modal = document.getElementById("myModal");

// Edit modal
var editModal = document.getElementById("editModal");

var deleteModal = document.getElementById("deleteModal")

// Get the button that opens the modal
var btn = document.getElementById("modalBtn");

var editBtn = document.getElementById("edit-btn");

var deleteBtn = document.getElementById("delete-btn");


// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
var spanList = document.querySelectorAll(".close");

// get the form to reset form values on modal close
var form  = document.querySelector("form");

// When the user clicks on the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on the button, open the modal
editBtn.onclick = function() {
  editModal.style.display = "block";
}

deleteBtn.onclick = function() {
  deleteModal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
  form.reset();
}

// When the user clicks on <span> (x) For  edit, close the modal
spanList[1].onclick = function() {
  editModal.style.display = "none";
  form.reset();
}

spanList[2].onclick = function() {
  deleteModal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal || event.target == editModal || event.target == deleteModal) {
    modal.style.display = "none";
    editModal.style.display = "none";
    deleteModal.style.display = "none";
  }
}