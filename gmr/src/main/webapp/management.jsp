<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" integrity="sha256-MfvZlkHCEqatNoGiOXveE8FIwMzZg4W85qfrfIFBfYc= sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
  <link href="/css/management.css" rel="stylesheet" >
  <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>

  <title>Management GMR</title>
</head>
<body>

<form NAME="followForm" action="/management" METHOD="POST">

  <table class="table table-responsive">
  <img src="https://lh3.googleusercontent.com/-UNNKtV1KTIE/AAAAAAAAAAI/AAAAAAAAERk/t_VWFoLnu6s/s120-c/photo.jpg" alt="GuideMeRight logo" class="img-responsive logo center-block" >


  <tr>
    <td><img src="/img/gmr-buddy.png" class="gmr-icon"></td>
    <td>GMR Buddy</td>
    <td>
    <button class="follow btn btn-default" data-contact-id="gmr-buddy" type="button">
      <i class="glyphicon glyphicon-plus"></i>
      <span>Segui</span>
    </button>
  </td>
  </tr>

  <tr>
    <td><img src="/img/gmr-news.png" class="gmr-icon"></td>
    <td >News GMR</td>
    <td >
    <button class="follow btn btn-default" data-contact-id="news" type="button">
      <i class="glyphicon glyphicon-plus"></i>
      <span>Segui</span>
    </button>
  </td>
  </tr>

  <tr>
    <td><img src="/img/gmr-events.png" class="gmr-icon"></td>
    <td >Eventi</td>
    <td >
    <button class="follow btn btn-default" data-contact-id="events" type="button">
      <i class="glyphicon glyphicon-plus"></i>
      <span>Segui</span>
    </button>
  </td>
  </tr>

  <tr>
    <td><img src="/img/gmr-community.png" class="gmr-icon"></td>
    <td >Community GMR</td>
    <td >
    <button class="follow btn btn-default" data-contact-id="community" type="button">
      <i class="glyphicon glyphicon-plus"></i>
      <span>Segui</span>
    </button>
  </td>
  </tr>

  <tr>
    <td><img src="/img/gmr-promo.png" class="gmr-icon"></td>
    <td >Promozioni GMR</td>
    <td >
    <button class="follow btn btn-default" data-contact-id="promo" type="button">
      <i class="glyphicon glyphicon-plus"></i>
      <span>Segui</span>
    </button>
  </td>
  </tr>


  <INPUT TYPE="HIDDEN" NAME="sender" id="user-id" value=<%= request.getParameter("user") %>>

  </table>
</form>

</body>

  <SCRIPT LANGUAGE="JavaScript">

    $( document ).ready(function() {      

      $(".follow").click(function(){
      // Holds the product ID of the clicked element
     if($(this).hasClass("checked")){
        $.post( "https://gmr-indoona.appspot.com/indoona/management",
        {
          contactId: $(this).data("contact-id"),
          userId: $("#user-id").val(),
          action: "remove"
        }, 
        function( data ) {
           $(this).removeClass("checked");
           $($(this).children()[0]).removeClass("glyphicon-ok");
           $($(this).children()[0]).addClass("glyphicon-plus");
           $("span", this).text("Segui");
        });

     }else {

       $.post( "https://gmr-indoona.appspot.com/indoona/management",
        {
          contactId: $(this).data("contact-id"),
          userId: $("#user-id").val(),
          action: "add"
        }, 
        function( data ) {
           $(this).addClass("checked");
           $($(this).children()[0]).removeClass("glyphicon-plus");
           $($(this).children()[0]).addClass("glyphicon-ok");
           $("span", this).text("");
        });
     }

    });

    });
  </SCRIPT>

</html>