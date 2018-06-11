const submitButton = document.querySelector('.submitButtonLogin');
const login = document.querySelector('#lgoin');
const loginSuccess = document.querySelector('#login-successfull');

submitButton.addEventListener('click', function(evt){
    evt.preventDefault();
    const email = document.querySelector('#email');
    const pwd = document.querySelector('#pwd');

    $.ajax({
        method: 'POST',
        url: 'http://localhost:9000/doLogin',
        contentType: 'application/json',

        data: JSON.stringify({
            email: email.value,
            password : pwd.value
        }),

        success: function() {
            login.setAttribute('aria-hidden', 'true');
            loginSuccess.setAttribute('aria-hidden', 'false');
        },




    });

});
