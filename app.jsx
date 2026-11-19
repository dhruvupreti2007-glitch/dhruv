document.addEventListener("DOMContentLoaded", function () {

    // Registration validation
    const registerForm =
        document.querySelector('form[action="register"]');

    if (registerForm) {
        registerForm.addEventListener("submit", function (event) {

            const name =
                registerForm.querySelector('[name="name"]').value.trim();

            const email =
                registerForm.querySelector('[name="email"]').value.trim();

            const password =
                registerForm.querySelector('[name="password"]').value;

            if (name.length < 2) {
                alert("Please enter your full name.");
                event.preventDefault();
                return;
            }

            const emailPattern =
                /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (!emailPattern.test(email)) {
                alert("Please enter a valid email.");
                event.preventDefault();
                return;
            }

            if (password.length < 6) {
                alert("Password must contain at least 6 characters.");
                event.preventDefault();
                return;
            }
        });
    }

    // Login validation
    const loginForm =
        document.querySelector('form[action="login"]');

    if (loginForm) {
        loginForm.addEventListener("submit", function (event) {

            const email =
                loginForm.querySelector('[name="email"]').value.trim();

            const password =
                loginForm.querySelector('[name="password"]').value;

            if (!email || !password) {
                alert("Please enter email and password.");
                event.preventDefault();
            }
        });
    }

    // Complaint form validation
    const complaintForm =
        document.querySelector('form[action="complaint"]');

    if (complaintForm) {
        complaintForm.addEventListener("submit", function (event) {

            const title =
                complaintForm.querySelector('[name="title"]').value.trim();

            const description =
                complaintForm.querySelector('[name="description"]').value.trim();

            if (title.length < 3) {
                alert("Please enter a valid complaint title.");
                event.preventDefault();
                return;
            }

            if (description.length < 15) {
                alert("Description must contain at least 15 characters.");
                event.preventDefault();
                return;
            }
        });
    }

});