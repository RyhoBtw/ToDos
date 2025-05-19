
function openPopUp(){
	document.getElementById('myModal').showModal()
	
}

function closePopUp(){
	document.getElementById('myModal').close()
}

function quickAddTask() {
	const input = document.getElementById("quickAddInput");
	const taskText = input.value.trim();

	if (taskText === "") return;

	const li = document.createElement("li");
	li.textContent = taskText;
	document.getElementById("activeTasksList").appendChild(li);

	input.value = "";
}

/*function toggleDarkMode() {
	var element = document.body;
	element.classList.toggle("dark-mode");
	button.textContent = "Light Mode";
}*/
	
document.addEventListener("DOMContentLoaded", function () {
  const button = document.getElementById("darkModeToggle");
  const body = document.body;

  // Start im Light Mode
  body.classList.add("light-mode");

  button.addEventListener("click", function () {
    if (body.classList.contains("light-mode")) {
      body.classList.remove("light-mode");
      body.classList.add("dark-mode");
      button.textContent = "Light Mode";
    } else {
      body.classList.remove("dark-mode");
      body.classList.add("light-mode");
      button.textContent = "Dark Mode";
    }
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const dateInput = document.getElementById("5");
  
  const today = new Date();
  const yyyy = today.getFullYear();
  const mm = String(today.getMonth() + 1).padStart(2, '0'); // Monate: 0-11
  const dd = String(today.getDate()).padStart(2, '0');

  const formattedDate = `${yyyy}-${mm}-${dd}`;
  dateInput.value = formattedDate;
});

