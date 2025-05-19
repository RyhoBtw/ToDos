let i = 0;

function openPopUp() {
	document.getElementById('myModal').showModal();
}

function closePopUp() {
	document.getElementById('myModal').close();
}

function toggleDarkMode() {
	const element = document.body;
	i++;
	element.classList.toggle("dark-mode");

	if(i === 20) {
		let intervalId = setInterval(() => {
			element.classList.toggle("dark-mode");
		}, 50);

		setTimeout(() => {
			clearInterval(intervalId);
		}, 10000);
	}
}