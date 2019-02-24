$(document).ready(function () {

    var asin = window.location['search'].split("=")[1];
    
    $.getJSON("http://192.168.43.210:8080/books/asin/"+asin, function(json) {
        
        var imgURL = json[0]['imgURL'];
        var link = json[0]['link'];
        var author = json[0]['author'];
        var genre = json[0]['genre'];
        var ratings = json[0]['ratings'];
        var title = json[0]['title'];

        $("#main-book-img").attr("src", imgURL);
        $("#main-book-link").text(title);
        $("#main-book-link").attr("href", link);
        $("#main-book-author").append(author);
        $("#main-book-genre").append(genre);
        $("#main-book-genre").append('<div class="mt-4">' + getStars(ratings) + '</div>');

    });

    function getStars(value) {
        
        var fullstars = value - (value % 1);
        var halfstars = value % 1;
        var emptystars = 5 - fullstars;
        var stars = "";

        if(halfstars != 0){
            emptystars = emptystars - 1;
        }
        for(var i = 0; i < fullstars; i++){
            stars = stars + '<span class="fa fa-star" style="font-size:36px; color:yellow;"></span>';
        }

        for(var i = 0; i < halfstars; i++){
            stars = stars + '<span class="fa fa-star-half-o" style="font-size:36px; color:yellow;"></span>';
        }

        for(var i = 0; i < emptystars; i++){
            stars = stars + '<span class="fa fa-star" style="font-size:36px;"></span>'; 
        }
        return stars;   // The function returns the product of p1 and p2
    }

})