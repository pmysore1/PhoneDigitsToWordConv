/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

    //Stops the submit request
    $("#myAjaxRequestForm").submit(function(e){
           e.preventDefault();
    });
    
    $("#myButton").attr("disabled",true);
    $('#phonenumber').on('input', function() {       
       if($(this).val().length == 7 || $(this).val().length == 10) {           
           $("#myButton").attr("disabled",false);
       } else {
            $("#myButton").attr("disabled",true);
       }
    });
      $("#phonenumber").keydown(function (e) {
          // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
             // Allow: Ctrl/cmd+A
            (e.keyCode == 65 && (e.ctrlKey === true || e.metaKey === true)) ||
             // Allow: Ctrl/cmd+C
            (e.keyCode == 67 && (e.ctrlKey === true || e.metaKey === true)) ||
             // Allow: Ctrl/cmd+X
            (e.keyCode == 88 && (e.ctrlKey === true || e.metaKey === true)) ||
             // Allow: home, end, left, right
            (e.keyCode >= 35 && e.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
      });

    //checks for the button click event
    $("#myButton").click(function(e){
           
            //get the form data and then serialize that
            dataString = $("#myAjaxRequestForm").serialize();
           
            //get the form data using another method 
            var countryCode = $("input#phonenumber").val(); 
            dataString = "phonenumber=" + countryCode;
           
            //make the AJAX request, dataType is set to json
            //meaning we are expecting JSON data in response from the server
            $.ajax({
                type: "POST",
                url: "PhoneNumberGenServlet",
                data: dataString,
                dataType: "json",               
                //if received a response from the server
                success: function(data, textStatus, jqXHR) {
                //success: function(data) {
                    //our country code was correct so we have some information to display
                    // if(data.success){
                       //  $("#ajaxResponse").html("");
                         $("#ajaxResponse").html("<b>Number of alpha numeric combination:</b> " + data.data.length + "<br/><br/>");
                         //$("#ajaxResponse").append("<b>Country Code:</b> " + data.phone_list + "<br/>");
                        
                          $('#table_id').DataTable( {
                                data: data.data,
                                destroy: true,
                                columns: [                                   
                                    { data: "phoneNumber" }
                                ]
                            } );

                },
               
                //If there was no resonse from the server
                error: function(jqXHR, textStatus, errorThrown){
                //error: function(jqXHR, textStatus){
                     console.log("Something really bad happened " + textStatus);
                      $("#ajaxResponse").html(jqXHR.responseText);
                },
               
                //capture the request before it was sent to server
                beforeSend: function(jqXHR, settings){
                    //adding some Dummy data to the request
                    settings.data += "&dummyData=whatever";
                    //disable the button until we get the response
                    $('#myButton').attr("disabled", true);
                    $("#ajaxResponse").html("<b>Number of alpha numeric combination: Retrieving...<br/>");
                },
               
                //this is called after the response or error functions are finsihed
                //so that we can take some action
                complete: function(jqXHR, textStatus){
                    //enable the button 
                    $('#myButton').attr("disabled", false);
                }
     
            });        
    });
    
   



});
