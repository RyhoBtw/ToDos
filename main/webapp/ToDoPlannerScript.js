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
function editTodo(button) {
            const card = button.closest('.todo-card');
            const title = card.querySelector('.todo-title').textContent;
            const description = card.querySelector('.todo-description').textContent;
            
            alert(`Bearbeiten: ${title}\n\nBeschreibung: ${description}\n\n(Hier würde normalerweise ein Bearbeitungsformular geöffnet)`);
            
            // Menü schließen
            button.closest('.dropdown-menu').classList.remove('show');
}



