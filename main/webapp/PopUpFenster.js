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
	
	if (window.location.pathname.endsWith("ToDoPlanner.jsp")) {
	        // Nur wenn die Seite "todoplanner.jsp" geladen ist, weiterleiten
	        window.location.href = "todo";
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

function openDropDown(button) {
           const dropdown = button.nextElementSibling;
           const isOpen = dropdown.classList.contains('show');
           
           // Alle anderen Dropdowns schließen
           document.querySelectorAll('.dropdown-menu').forEach(menu => {
               menu.classList.remove('show');
           });
           
           // Aktuelles Dropdown öffnen/schließen
           if (!isOpen) {
               dropdown.classList.add('show');
           }
   }

// ToDo bearbeiten
function editTodo(id) {
	    console.log('ToDo bearbeiten:', id);
	    alert(`ToDo ${id} bearbeiten - Hier würde das Bearbeitungsformular geöffnet`);
	       
	    // Dropdown schließen
	    document.querySelectorAll('.dropdown-menu').forEach(menu => {
	    	menu.classList.remove('show');
		});
}

   // ToDo löschen
function deleteTodo(id) {
	   if (confirm('Möchten Sie dieses ToDo wirklich löschen?')) {
           console.log('ToDo löschen:', id);
           // Hier würdest du die Löschlogik implementieren
           // Zum Beispiel: removeToDoFromDOM(id);
           alert(`ToDo ${id} wurde gelöscht`);
       }
       
       // Dropdown schließen
       document.querySelectorAll('.dropdown-menu').forEach(menu => {
           menu.classList.remove('show');
		});
}

