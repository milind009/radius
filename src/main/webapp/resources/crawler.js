$(document).ready(function(){
  $("#submit_btn").click(function(){
      var data = $("#url_input").val();
      console.log(data);
      $.ajax({
          type : "GET",
          url : "/url?url="+data,
          data: "",
          dataType: 'text',
          success: function (data) {
              var response = JSON.parse(data);
              $("#para").append("<br>Total issues : " + response["totalIssues"]);
              $("#para").append("<br>Issues less than 24 hours : " + response["issuesLessThan24hrs"]);
              $("#para").append("<br>Issues between 24 hours and 7 days :" + response["issuesLessThan7Days"]);
              $("#para").append("<br>Issues more than 7 days :" + response["issuesMoreThan7Days"]);
          },

         error : function(xhr){
              console.log("error encountered " + xhr.status);
         }
      })
  });
});