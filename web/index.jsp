<%-- 
    Document   : index
    Created on : 22-Feb-2014, 6:12:29 PM
    Author     : PradeepSamuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JalPrika</title>
        <link href="http://getbootstrap.com/examples/jumbotron-narrow/jumbotron-narrow.css" rel="stylesheet">
        <link href="http://<%= request.getServerName()%>:8080/css/bootstrap.min.css" rel="stylesheet">
        <script src="http://<%= request.getServerName()%>:8080/js/bootstrap.js"></script>
        <script src="http://<%= request.getServerName()%>:8080/js/jquery/jquery.js"></script>
        <style>
            .mainLogo {
                display: block;
                margin-left: auto;
                margin-right: auto 
            }
            .mainTitle{
                text-shadow: 0px 2px 3px #666;
            }
        </style>
        <script>

        </script>
    </head>
    <body>
        <img src="http://<%= request.getServerName()%>:8080/img/logo.png" class="mainLogo">
        <div class="jumbotron">
            <span class="help-block">We just give you a insight into your chances of getting a job through your resume, Tell us your dream company, We will sprinkle you with some JalPrika.</span>
            <form action="UploadServlet" method="post" target="subFrame"
                  enctype="multipart/form-data">
                <div class="form-group">
                    <label for="inputEmail">Email address</label>
                    <input type="email" class="form-control" id="inputEmail" placeholder="Enter email" style="width:200px; margin:0 auto; margin-left: auto" name="email">
                </div>
                <div class="form-group">
                    <label for="inputName">Full Name</label>
                    <input type="text" class="form-control" id="inputName" placeholder="Enter full name" style="width:200px; margin:0 auto; margin-left: auto" name="fullname">
                </div>
                <div class="form-group">
                    <p class="mainTitle">Upload your Resume</p>

                    <input type="file" name="file" id="file" style="width:200px; margin:0 auto; margin-left: auto" />
                    <br />
                </div>
                <div class="form-group">
                    <p class="mainTitle">Select Company</p>
                    <select id="company" name = "company" class="form-control" style="width:200px; margin:0 auto; margin-left: auto">
                        <option>Google</option>
                        <option>CGI</option>
                        <option>Cisco</option>
                        <option>MathWorks</option>
                        <option>Sega</option>
                    </select>
                    <br />
                </div>
                <input type="submit" value="Sprinkle!" class="btn btn-primary"/>
            </form>
            <div id ="output" style="width:50%; position: absolute;left: 30%; margin-top: 70px;"></div>
        </div>
        <iframe name = "subFrame" id="subFrame" style="display: none; border: 0px;" src = ""></iframe>
        <script type="text/javascript">
            function doAlert(x)
            {
                var html = "<table class = 'table' style='text-align:center; padding: 10px;border :1px;'><tr><th style='text-align : center;'>Company</th><th style='text-align : center;'>Propability</th></tr><tr>"
                 if(x.hasOwnProperty('UI')){
                     html += "<td>Google</td><td>"+x.UI+"</td></tr>"
                 }
                 if(x.hasOwnProperty('Database')){
                     html += "<td>CGI</td><td>"+x.Database+"</td></tr>"
                 }
                 if(x.hasOwnProperty('Network')){
                     html += "<td>Cisco</td><td>"+x.Network+"</td></tr>"
                 }
                 if(x.hasOwnProperty('Embedded')){
                     html += "<td>Sega</td><td>"+x.Embedded+"</td></tr>"
                 }
                 if(x.hasOwnProperty('ImageProcessing')){
                     html += "<td>MathWorks</td><td>"+x.ImageProcessing+"</td></tr>"
                 }
                 html+="</table>"
                 jQuery("#output").html(html);
            }
            function formatAlert()
            {
                alert("We accept only files in PDF and TXT formats.");
            }
        </script>
    </body>
</html>
