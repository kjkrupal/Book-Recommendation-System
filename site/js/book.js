$(document).ready(function () {

    var asin = window.location['search'].split("=")[1];
    
    $('#main-search-button').click(function() {
        self.location.href = "search.html?q=" + $('#main-search-bar').val();
    });

    $.getJSON("http://192.168.43.210:8080/books/asin/"+asin, function(json) {
        
        var imgURL = json[0]['imgURL'];
        var link = json[0]['link'];
        var genre = json[0]['genre'];
        var ratings = json[0]['ratings'];
        var title = json[0]['title'];

        $("#main-book-img").attr("src", imgURL);
        $("#main-book-link").text(title);
        $("#main-book-link").attr("href", link);
        $("#main-book-genre").append(genre);
        $("#main-book-genre").append('<div class="mt-4">' + getStars(ratings) + '</div>');

    });

    $.getJSON("http://192.168.43.210:8080/reco/asin/"+asin, function(json) {
        
        if(json.length > 0){
            $('#reco-area').append(
                '<div class="card" style="width: 100%; display: inline-block !important;">' +
                '<div id="myreco" class="card-body">' +
                '<h5 class="card-title">You may also like...</h5>' +
                '</div></div>'
            );
        }
        for(var i = 0; i < json.length; i++){
            var asin = json[i]['asin'];
            var title = json[i]['title'];
            var author = json[i]['author'];
            var genre = json[i]['genre'];
            var avgRating = json[i]['ratings'];
            var link = json[i]['link'];
            var imgURL = json[i]['imgURL'];
            var confidence = json[i]['confidence'];
            var bookpage = "book.html?asin="+asin;

            $('#myreco').append(
                    '<div class="card mr-2 ml-2" style="width: 20%; display: inline-block !important;">' + 
                    '<img src="'+ imgURL +'" class="card-img-top" alt="Card image cap" style ="width: 100%; height: 200px;">' +
                    '<div class="card-body">' +
                      '<p class="card-title reco"><a href="'+ bookpage +'">'+ title +'</a></p>' +
                      '<p class="card-text reco">'+ confidence +'\% match</p>' +
                    '</div>' + 
                '</div>'
            );

        }

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
            stars = stars + '<span class="fa fa-star-o" style="font-size:36px; color:yellow;"></span>'; 
        }
        return stars;   // The function returns the product of p1 and p2
    }

})