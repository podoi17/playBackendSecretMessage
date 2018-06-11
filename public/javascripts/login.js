const submitButton = document.querySelector('.submitButtonLogin');
const login = document.querySelector('#login');
const loginSuccess = document.querySelector('#login-successfull');
const failParagraph = document.querySelector('#failParagraph');
const secretMessageLabel = document.querySelector('.secret-message-label');
const checkMessageButton = document.querySelector('.checkMessageButton');
const messageStatusLabel = document.querySelector('.secret-message-status');
const reloadPage = document.querySelector('.reload-page');



reloadPage.addEventListener('click', function (evt) {
    evt.preventDefault();
    console.log(sessionStorage)
    if(sessionStorage !== null) {
        login.setAttribute('aria-hidden', 'true');
        loginSuccess.setAttribute('aria-hidden', 'false');
    }
})



submitButton.addEventListener('click', function(evt){
    evt.preventDefault();
    console.log('submitbutton gedrückt');
    const email = document.querySelector('#email');
    const pwd = document.querySelector('#pwd');

    $.ajax({
        method: 'POST',
        url: '/doLogin',
        contentType: 'application/json',

        data: JSON.stringify({
            email : email.value,
            password : pwd.value
        }),

        success: function(data) {
            login.setAttribute('aria-hidden', 'true');
            loginSuccess.setAttribute('aria-hidden', 'false');
            secretMessageLabel.innerHTML = data;
            sessionStorage.setItem('connected', data);

            checkMessageButton.addEventListener('click', function (evt) {
                evt.preventDefault();

                $.ajax({
                    method : 'POST',
                    url : '/checkSecretMessage',
                    contentType: 'application/json',

                    data: JSON.stringify({
                        message: secretMessageLabel.textContent,
                    }),

                    success: function(data) {
                        console.log(sessionStorage.length);
                        secretMessageLabel.innerHTML = data;
                        messageStatusLabel.innerHTML = 'erfolg';

                    },

                    error: function() {
                        //messageStatusLabel.innerHTML = 'fehler'
                        location.reload();
                        console.log('error');
                    }
                })

            })



        },

        error: function () {
            failParagraph.hidden = false;
        }
    });

});