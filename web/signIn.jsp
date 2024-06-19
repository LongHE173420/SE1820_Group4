<%-- 
    Document   : signIn
    Created on : 23 thg 5, 2024, 14:23:43
    Author     : Quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Fables">
        <meta name="author" content="Enterprise Development">
        <link rel="shortcut icon" href="assets/custom/images/shortcut.png">

        <title> Signin</title>

        <!-- animate.css-->  
        <link href="assets/vendor/animate.css-master/animate.min.css" rel="stylesheet">
        <!-- Load Screen -->
        <link href="assets/vendor/loadscreen/css/spinkit.css" rel="stylesheet">
        <!-- GOOGLE FONT -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
        <!-- Font Awesome 5 -->
        <link href="assets/vendor/fontawesome/css/fontawesome-all.min.css" rel="stylesheet">
        <!-- Fables Icons -->
        <link href="assets/custom/css/fables-icons.css" rel="stylesheet"> 
        <!-- Bootstrap CSS -->
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="assets/vendor/bootstrap/css/bootstrap-4-navbar.css" rel="stylesheet">
        <!-- FANCY BOX -->
        <link href="assets/vendor/fancybox-master/jquery.fancybox.min.css" rel="stylesheet">
        <!-- OWL CAROUSEL  -->
        <link href="assets/vendor/owlcarousel/owl.carousel.min.css" rel="stylesheet">
        <link href="assets/vendor/owlcarousel/owl.theme.default.min.css" rel="stylesheet">
        <!-- Timeline -->
        <link rel="stylesheet" href="assets/vendor/timeline/timeline.css"> 
        <!-- FABLES CUSTOM CSS FILE -->
        <link href="assets/custom/css/custom.css" rel="stylesheet">
        <!-- FABLES CUSTOM CSS RESPONSIVE FILE -->
        <link href="assets/custom/css/custom-responsive.css" rel="stylesheet"> 
        <!--        add new icon-->
        <script src="https://kit.fontawesome.com/d9088230f9.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <jsp:include page="header.jsp"/>

        <div class="fables-header fables-after-overlay">
            <div class="container"> 
                <h2 class="fables-page-title fables-second-border-color">Sign In</h2>
            </div>
        </div>  

        <!-- Start Breadcrumbs -->
        <div class="fables-light-background-color">
            <div class="container"> 
                <nav aria-label="breadcrumb">
                    <ol class="fables-breadcrumb breadcrumb px-0 py-3">
                        <li class="breadcrumb-item"><a href="home" class="fables-second-text-color">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Sign In</li>
                    </ol>
                </nav> 
            </div>
        </div>
        <!-- /End Breadcrumbs -->

        <!-- Start page content -->   
        <div class="container">
            <div class="row my-4 my-lg-5">
                <div class="col-12 col-md-8 offset-md-2 col-lg-6 offset-lg-3 text-center">
                    <p class="font-20 semi-font fables-main-text-color mt-4 mb-4 mb-lg-5">Sign In</p>
                    <form action="login" method="get">

                        <p class="text-danger">${mess}</p>                      

                        <div class="form-group"> 
                            <div class="input-icon">
                                <span class="fables-iconemail fables-input-icon mt-2 font-13"></span>
                                <input  name="user" type="email" class="form-control rounded-0 py-3 pl-5 font-13 sign-register-input"  placeholder="Email" value="${user}" required> 
                            </div>

                        </div>
                        <div class="form-group"> 
                            <div class="input-icon">
                                <span class="fables-iconpassword fables-input-icon font-19 mt-1"></span>
                                <input id="password" name="password" id="passwordInput" type="password" class="form-control rounded-0 py-3 pl-5 font-13 sign-register-input" placeholder="Password" required>
                                <span id="togglePassword"><img style="width: 35%;" src="assets/custom/images/eye-close.png" alt="eye" id="eyeID"></span>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-block rounded-0 white-color fables-main-hover-background-color fables-second-background-color font-16 semi-font py-3">Sign in</button>
                    </form>

                    <div class="fadeIn first">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/SE1820_Group4/loginGG&response_type=code&client_id=82568343053-8aatbq2dj2gst27tk6mb2gb544bmgf7l.apps.googleusercontent.com&approval_prompt=force"><i class="fa-brands fa-google fa-3x3" style="color: #d31212;"></i></a>
                    </div>

                    <a href="forGotPassword.jsp" class="fables-forth-text-color font-16 fables-second-hover-color underline mt-3 mb-4 m-lg-5 d-inline-block">Forgot Password</a>
                    <a href="changePassword.jsp" class="fables-forth-text-color font-16 fables-second-hover-color underline mt-3 mb-4 m-lg-5 d-inline-block">Change Password</a>
                    <p class="fables-forth-text-color">Dont have an account ?  <a href="register.jsp" class="font-16 semi-font fables-second-text-color underline fables-main-hover-color ml-2">Register</a></p>
                </div>
            </div>

        </div>

        <!-- /End page content -->

        <jsp:include page="footer.jsp"/>


        <script src="assets/vendor/jquery/jquery-3.3.1.min.js"></script>
        <script src="assets/vendor/loadscreen/js/ju-loading-screen.js"></script>
        <script src="assets/vendor/jquery-circle-progress/circle-progress.min.js"></script>
        <script src="assets/vendor/popper/popper.min.js"></script>
        <script src="assets/vendor/WOW-master/dist/wow.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap-4-navbar.js"></script>
        <script src="assets/vendor/owlcarousel/owl.carousel.min.js"></script> 
        <script src="assets/vendor/timeline/jquery.timelify.js"></script>
        <script src="assets/custom/js/custom.js"></script>  
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>


    </body>
</html>
