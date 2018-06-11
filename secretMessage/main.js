const register = document.querySelector('#register');
const successRegi = document.querySelector('#successfull-register');
const failRegi = document.querySelector('#fail-register');


const button = document.querySelector('.submitButton');
button.addEventListener('click', function(evt){
    evt.preventDefault();
    const email = document.querySelector('#email');
    const username = document.querySelector('#username');
    const pwd = document.querySelector('#pwd');
    register.setAttribute('aria-hidden', 'true');
    successRegi.setAttribute('aria-hidden', 'false');


    $.ajax({
        method: 'POST',
        url: '/save',
        contentType: 'application/json',

        data: JSON.stringify({
            email: email.value,
            username : username.value,
            password : pwd.value
        }),

        success: function(){
            window.location.pathname = '/test'
        }

    });

});
