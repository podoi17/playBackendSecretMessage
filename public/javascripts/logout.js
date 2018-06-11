const logoutLink =  document.querySelector('.logout-link');
const logoutButton = document.querySelector('.logout-button');

logoutLink.addEventListener('click', function (evt) {
    evt.preventDefault();

    $.ajax({
        method: 'GET',
        url: '/logout',
        contentType: 'application/json',

        success: function() {
            location.assign('/login');
        }
    })
})


logoutButton.addEventListener('click', function (evt) {
    evt.preventDefault();

    $.ajax({
        method: 'GET',
        url: '/logout',
        contentType: 'application/json',

        success: function() {
            location.href('/login');
        }
    })
})