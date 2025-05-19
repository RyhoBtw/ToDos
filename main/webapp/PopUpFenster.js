
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

function toggleDarkMode() {
	var element = document.body;
	element.classList.toggle("dark-mode");
}
