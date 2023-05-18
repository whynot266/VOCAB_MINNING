$(document).ready(function(){
     $('#add-button').click(function() {
        var button = $(this);
        var vocab = button.data('vocab');
        console.log(vocab);
        $.ajax({
          url:'/addToBank',
          method: 'POST',
          data: { vocab: vocab },
          success: function(data) {
              console.log(data);
              var bankCount=$('#bank-count');
              bankCount.html(data);

          }
        });
     });
    $('#ban-button').click(function() {


    });
});