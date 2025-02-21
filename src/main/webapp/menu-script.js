// menuScripts.js

document.addEventListener("DOMContentLoaded", function() {
        var dropdownSubmenus = document.querySelectorAll('.dropdown-submenu');

        dropdownSubmenus.forEach(function(submenu) {
            submenu.addEventListener('mouseenter', function() {
                var subMenuDropdown = submenu.querySelector('.dropdown-menu');
                if (subMenuDropdown) {
                    subMenuDropdown.classList.add('show');
                }
            });

            submenu.addEventListener('mouseleave', function() {
                var subMenuDropdown = submenu.querySelector('.dropdown-menu');
                if (subMenuDropdown) {
                    subMenuDropdown.classList.remove('show');
                }
            });
        });
    });