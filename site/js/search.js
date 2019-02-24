$(document).ready(function () {

    $('#main-search-button').click(function() {
        window.location.assign("search.html?q=" + $('#main-search-bar').val());
    });
    var title = window.location['search'].split("=")[1];

    

    $.getJSON("http://192.168.43.210:8080/books/title/"+title, function(json) {
    
        for(var i = 0; i < json.length; i++){
            var asin = json[i]['asin'];
            var title = json[i]['title'];
            var author = json[i]['author'];
            var genre = json[i]['genre'];
            var avgRating = json[i]['ratings'];
            var link = json[i]['link'];
            var imgURL = json[i]['imgURL'];
            var bookpage = "book.html?asin="+asin;

            $('#book-area').append(
                '<div class="mt-3"></div>' +
                '<div class="card" style="width: 100%;">' +
                    '<div class="row">' +
                    '<div class="col-md-4">' +
                        '<img class="card-img-top mt-4 ml-4 mb-4" src="'+ imgURL +'" alt="Card image cap" style ="width: 150px; height: 200px;">' +
                    '</div>' +
                    '<div class="col-md-8">' +
                        '<div class="card-body">' +
                            '<h5 class="card-title"><a href="'+ bookpage +'">'+ title +'</a></h5>' +
                            '<p class="card-text">By '+ author +'</p>' +
                            '<p class="card-text">Genre: '+ genre +'</p>' +
                            getStars(avgRating)+
                        '</div>'+
                    '</div>' +
              '</div>'+
              '</div>'+
              '<div class="mt-3"></div>'
            );

        }

    });

    function getStars(value) {
        
        var fullstars = value - (value % 1);
        var halfstars = value % 1;
        var emptystars = 5 - fullstars;
        
        if(halfstars != 0){
            emptystars = emptystars - 1;
        }

        var stars = "";

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

});