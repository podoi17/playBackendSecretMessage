const button = document.querySelector('.submitButtonRegister');
const successRegi = document.querySelector('#successfull-register');
const failRegi = document.querySelector('#fail-register');

button.addEventListener('click', function(evt){
    evt.preventDefault();
    const email = document.querySelector('#email');
    const username = document.querySelector('#username');
    const pwd = document.querySelector('#pwd');

    $.ajax({
        method: 'POST',
        url: '/save',
        contentType: 'application/json',

        data: JSON.stringify({
            email : email.value,
            username : username.value,
            password : pwd.value
        }),

        success: function() {
            register.setAttribute('aria-hidden', 'true');
            successRegi.setAttribute('aria-hidden', 'false');
        },

        error: function() {
            register.setAttribute('aria-hidden', 'true');
            failRegi.setAttribute('aria-hidden', 'false');
        }



    });

});