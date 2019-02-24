$(document).ready(function () {

    $('#main-search-button').click(function() {
        window.location.href = "search.html?q=" + $('#main-search-bar').val();
    });

});