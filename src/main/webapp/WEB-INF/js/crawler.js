$(document).ready(function(){
  $("#submit_btn").click(function(){
      var data = $("#url_input").val();
      console.log(data);
      $.ajax({
          url : "http:localhost:8080/url?url="+data,
          type : "GET",
          data: "",
          dataType: 'application/json; charset=utf-8',
          success: function (data) {
              $("#para").append("<br>Total issues : " + data["totalIssues"]);
              $("#para").append("<br>Issues less than 24 hours : " + data["issuesLessThan24hrs"]);
              $("#para").append("<br>Issues between 24 hours and 7 days :" + data["issuesLessThan7Days"]);
              $("#para").append("<br>Issues more than 7 days :" + data["issuesMoreThan7Days"]);
          }
      })
  });
});