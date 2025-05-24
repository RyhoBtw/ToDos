let i = 0;

function openPopUp() {
	document.getElementById('myModal').showModal();
}

function closePopUp() {
	document.getElementById('myModal').close();
}

// Beim Laden der Seite prüfen, ob Dark Mode aktiviert war
window.addEventListener('DOMContentLoaded', () => {
    if (localStorage.getItem('darkMode') === 'enabled') {
        document.body.classList.add('dark-mode');
    }
});

function toggleDarkMode() {
    const element = document.body;
    element.classList.toggle("dark-mode");

    // Status speichern
    if (element.classList.contains("dark-mode")) {
        localStorage.setItem('darkMode', 'enabled');
    } else {
        localStorage.setItem('darkMode', 'disabled');
    }
}
