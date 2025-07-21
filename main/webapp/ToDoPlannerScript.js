function openPopUp() {
	document.getElementById('myModal').showModal();
}

function closePopUp(id) {
	document.getElementById(id).close();
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
	
document.addEventListener("DOMContentLoaded", function () {

  const dateInput = document.getElementById("5");
  const today = new Date();
  const yyyy = today.getFullYear();
  const mm = String(today.getMonth() + 1).padStart(2, '0'); // Monate: 0-11
  const dd = String(today.getDate()).padStart(2, '0');

  const formattedDate = `${yyyy}-${mm}-${dd}`;

  dateInput.value = formattedDate;

});

// Toggelt 3 Punkte Menü
function toggleMenu(button) {
            const menu = button.nextElementSibling;
            const isOpen = menu.classList.contains('show');
            
            // Alle anderen Menüs schließen
            document.querySelectorAll('.dropdown-menu').forEach(m => {
                m.classList.remove('show');
            });
            
            // Aktuelles Menü togglen
            if (!isOpen) {
                menu.classList.add('show');
            }
}

// Schließt 3 Punkte Menü mit Klick außerhalb
document.addEventListener('click', function(event) {
            if (!event.target.closest('.menu-container')) {
                document.querySelectorAll('.dropdown-menu').forEach(menu => {
                    menu.classList.remove('show');
                });
            }
        });
		
// ToDo bearbeiten
function editTodo(id, title, priority, category, enddate) {

	
	document.getElementById("modalId").value = id;
	document.getElementById("1C").value = title;
	document.getElementById("3C").value = priority;
	document.getElementById("4C").value = category;
	document.getElementById("5C").value = enddate;
	
	document.getElementById('myModalChange').showModal();
}



