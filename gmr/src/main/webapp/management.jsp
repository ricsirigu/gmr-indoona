<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

 <%@ page import="com.gmr.indoona.model.User;" %>
  <%
      User u = (User)request.getAttribute("user");
     %>
            <input type="hidden" id="added-contacts" value=<%= u.getAddedContacts() %>>
            <input type="hidden" id="user-id" value=<%= u.getUserId() %>>
       <%
  %>

  <tr>
    <td><img src="/img/gmr-buddy.png" class="gmr-icon"></td>
    <td class="contact-name">GMR Buddy</td>
    <td>
    <button class="follow btn btn-default" data-contact-id="11" type="button">
      <i class="glyphicon glyphicon-plus"></i>
      <span class="follow">Segui</span>
    </button>
  </td>
  </tr>

  <tr>
    <td><img src="/img/gmr-news.png" class="gmr-icon"></td>
    <td class="contact-name">News GMR</td>
    <td >
    <button class="follow btn btn-default" data-contact-id="12" type="button">
      <i class="glyphicon glyphicon-plus"></i>
      <span class="follow">Segui</span>
    </button>
  </td>
  </tr>

  <tr>
    <td><img src="/img/gmr-events.png" class="gmr-icon"></td>
    <td class="contact-name">Eventi GMR</td>
    <td >
    <button class="follow btn btn-default" data-contact-id="13" type="button">
      <i class="glyphicon glyphicon-plus"></i>
      <span class="follow">Segui</span>
    </button>
  </td>
  </tr>

  <tr>
    <td><img src="/img/gmr-community.png" class="gmr-icon"></td>
    <td class="contact-name">Community GMR</td>
    <td >
    <button class="follow btn btn-default" data-contact-id="14" type="button">
      <i class="glyphicon glyphicon-plus"></i>
      <span class="follow">Segui</span>
    </button>
  </td>
  </tr>

  <tr>
    <td><img src="/img/gmr-promo.png" class="gmr-icon"></td>
    <td class="contact-name">Promozioni GMR</td>
    <td >
    <button class="follow btn btn-default" data-contact-id="15" type="button">
      <i class="glyphicon glyphicon-plus"></i>
      <span class="follow">Segui</span>
    </button>
  </td>
  </tr>


   </table>
</form>

</body>

  <SCRIPT LANGUAGE="JavaScript">

    $( document ).ready(function() {      

      var c = $("#added-contacts").val();
      var contactList = JSON.parse(c)
      contactList.contacts.forEach(function(data, i){        
        var buttonSelected = $("button[data-contact-id='" + data.contact_id +"']");
         buttonSelected.addClass("checked");
         $(buttonSelected.children()[0]).removeClass("glyphicon-plus");
         $(buttonSelected.children()[0]).addClass("glyphicon-ok");
         $("span", buttonSelected).text("");
      })


      $(".follow").click(function(){
      
      var $this = $(this);
      // Holds the product ID of the clicked element
     if($this.hasClass("checked")){
        $.post( "https://gmr-indoona.appspot.com/indoona/management",
        {
          contactId: $(this).data("contact-id"),
          userId: $("#user-id").val(),
          action: "remove"
        })
        .done(function(data, status) {
           $this.removeClass("checked");
           $($this.children()[0]).removeClass("glyphicon-ok");
           $($this.children()[0]).addClass("glyphicon-plus");
           $("span", $this).text("Segui");
        });

     }else {

       $.post( "https://gmr-indoona.appspot.com/indoona/management",
        {
          contactId: $(this).data("contact-id"),
          userId: $("#user-id").val(),
          action: "add"
        })
        .done(function(data, status) {
           $this.addClass("checked");
           $($this.children()[0]).removeClass("glyphicon-plus");
           $($this.children()[0]).addClass("glyphicon-ok");
           $("span", $this).text("");
        });
     }

    });

    });
  </SCRIPT>

</html>