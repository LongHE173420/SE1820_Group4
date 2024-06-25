<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>News</title>

        <!-- Google Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="css/responsive.css">

        <!--        
                <link rel="stylesheet" href="css/styles.css">
                <link rel="stylesheet" href="css/toastr.min.css">
                <link rel="stylesheet" href="css/popup.css">
                <link rel="stylesheet" href="css/myCss.css">
        -->
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <title> Home </title>

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
        <!-- portfolio filter gallery -->
        <link href="assets/vendor/portfolio-filter-gallery/portfolio-filter-gallery.css" rel="stylesheet">
        <!-- Video Background -->
        <link href="assets/vendor/video-background/video-background.css" rel="stylesheet"> 
        <!-- FANCY BOX -->
        <link href="assets/vendor/fancybox-master/jquery.fancybox.min.css" rel="stylesheet"> 
        <!-- RANGE SLIDER -->
        <link href="assets/vendor/range-slider/range-slider.css" rel="stylesheet">
        <!-- OWL CAROUSEL  --> 
        <link href="assets/vendor/owlcarousel/owl.carousel.min.css" rel="stylesheet">
        <link href="assets/vendor/owlcarousel/owl.theme.default.min.css" rel="stylesheet">
        <!-- FABLES CUSTOM CSS FILE -->
        <link href="assets/custom/css/custom.css" rel="stylesheet">
        <!-- FABLES CUSTOM CSS RESPONSIVE FILE -->
        <link href="assets/custom/css/custom-responsive.css" rel="stylesheet">
    </head>
    <body>

        <div id="header-area">
            <%@include file="header.jsp"%>
        </div> <!-- End header area -->

        <c:set var="pr" value="Product" />
        <c:set var="ac" value="News" />
        <c:set var="n" value="1" />

        <div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>News</h2>
                </div>
            </div>
        </div>
    </div>
    <div class="mainmenu-area" style="background: #2C3E4F; padding: 5px 0; overflow: auto;"> <!-- Giảm padding -->
        <div class="container">
            <div class="row">
                <div class="navbar-collapse collapse" style="display: flex; justify-content: center;">
                    <ul class="nav navbar-nav" style="display: flex; flex-direction: row; list-style: none; padding: 0; margin: 0; align-items: center; width: 100%;">
                        <li style="flex-grow: 1; text-align: center; padding: 5px 10px;">
                            <a href="newsUser?groupName=all" style="text-decoration: none; color: white; font-size: 14px;">All</a>
                        </li>
                        <c:forEach var="g" items="${requestScope.groups}">
                            <c:set var="sg" value="${requestScope.selectGroup}"/>
                            <c:if test="${sg.getName() == g.getName()}">
                                <li class="active" style="flex-grow: 1; text-align: center; padding: 5px 10px;"> <!-- Giảm padding và có thể thay đổi font-size nếu cần -->
                                    <a href="newsUser?groupName=${g.getName().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}" style="text-decoration: none; color: white; font-size: 14px;">${g.getName()}</a> <!-- Giảm font-size -->
                                </li>
                            </c:if>
                            <c:if test="${sg.getName() != g.getName()}">
                                <li style="flex-grow: 1; text-align: center; padding: 5px 10px;"> <!-- Giảm padding và có thể thay đổi font-size nếu cần -->
                                    <a href="newsUser?groupName=${g.getName().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}" style="text-decoration: none; color: white; font-size: 14px;">${g.getName()}</a> <!-- Giảm font-size -->
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>





        <div class="single-product-area">
            <div class="zigzag-bottom"></div>            
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-sm-6">                      
                        <c:set var="sg" value="${requestScope.selectGroup}"/>
                        <c:if test="${sg.getId()!=null}">
                            <h2 class="sidebar-title">${sg.getName()}</h2>
                        </c:if>
                        <c:if test="${sg.getId()==null}">
                            <h2 class="sidebar-title">Recent Posts</h2>
                        </c:if>
                        <div>
                            <div>
                                <c:forEach var="ns" items="${requestScope.news}">
                                    <div class="row" style="margin-bottom: 24px;">
                                        <div class="col-xs-6">
                                            <div class="news_image">
                                                <a style="display: inline-block" href="newsUserDetail?title=${ns.getTitle().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}" title="${ns.getTitle()}" onclick="sendRequest(${ns.getId()})"> 
                                                    <img style="width: 100%; height: 100%; object-fit: cover;"
                                                         src="${ns.getImage()}"
                                                         onerror="this.onerror=null; 
                                                         this.src='https://lh4.ggpht.com/-PtwFBckvv78/V3aBB39xD9I/AAAAAAAAHFA/EXKKalIB8IkvyJjUzGrDVQCzLMs5Alx9QCLcB/s1600/anh-blogspot-khong-hien-thi.png';">                                
                                                </a>
                                            </div>
                                        </div>
                                        <div class="col-xs-6" style="height: 100%;">
                                            <div style="height: 100%;">
                                                <div>
                                                    <h3 class="title-news">
                                                        <a href="newsUserDetail?title=${ns.getTitle().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}"  title="${ns.title}"  onclick="sendRequest(${ns.getId()})">${ns.getTitle()}</a>                                                        
                                                        <!--                                                        <form id="myForm" method="post" action="newsDetail">
                                                                                                                    <input type="hidden" name="nid" id="dataField" value="${ns.getId()}">
                                                                                                                    <button type="submit">
                                                                                                                        <a href="newsDetail" onclick="sendDataToServlet(${ns.getId()})">${ns.getTitle()}</a>
                                                                                                                    </button>
                                                                                                                </form>                                                        -->
                                                    </h3>
                                                    <p class="post-truncate">${ns.getHeading()}</p>
                                                </div>                                                
                                            </div>
                                        </div>
                                    </div>   
                                </c:forEach>                                   
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6">
                        <div class="single-sidebar">


                        </div>
                        <div class="single-sidebar">
                            <h2 class="sidebar-title">Latest news</h2>
                            <ul>
                                <c:forEach end='4' var="ns" items="${requestScope.defendNews}">
                                    <li><a href="newsUserDetail?title=${ns.getTitle().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}" onclick="sendRequest(${ns.getId()})">${ns.getTitle()}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-8 col-sm-6">
                            <div class="product-pagination text-center">
                                <nav>
                                    <ul class="pagination">
                                        <c:if test="${requestScope.page > 1}">
                                            <li>
                                                <a href="newsUser?groupName=${requestScope.pageGroup}&page=${requestScope.page - 1}" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        </c:if>                                        
                                        <c:forEach begin="1" end="${requestScope.count}" var="i">
                                            <li class="page-item ${requestScope.page == i ? 'active' : ''}"><a href="newsUser?groupName=${requestScope.pageGroup}&page=${i}">${i}</a></li>                                         
                                            </c:forEach>                                            
                                            <c:if test="${requestScope.page < requestScope.count}">
                                            <li>
                                                <a href="newsUser?groupName=${requestScope.pageGroup}&page=${requestScope.page + 1}" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>                        
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div id ="footer-area">
            <%@include file="footer.jsp"%>
        </div>
        <script type="text/javascript">
            function sendRequest(value) {
                var url = "news";
                var xhttp = new XMLHttpRequest();
                xhttp.open("POST", url, true);
                xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhttp.send("viewNewsId=" + value);
            }
        </script>

        <!--Start of Tawk.to Script-->
        <script type="text/javascript">
            var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
            (function () {
                var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
                s1.async = true;
                s1.src = 'https://embed.tawk.to/6496f437cc26a871b0247198/1h3mqju94';
                s1.charset = 'UTF-8';
                s1.setAttribute('crossorigin', '*');
                s0.parentNode.insertBefore(s1, s0);
            })();
        </script>
        <!--End of Tawk.to Script-->

        <!-- Latest jQuery form server -->
        <script src="https://code.jquery.com/jquery.min.js"></script>

        <!-- Bootstrap JS form CDN -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        <!-- jQuery sticky menu -->
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.sticky.js"></script>

        <!-- jQuery easing -->
        <script src="js/jquery.easing.1.3.min.js"></script>

        <!-- Main Script -->
        <script src="js/main.js"></script>
        <script src="assets/vendor/jquery/jquery-3.3.1.min.js"></script>
        <script src="assets/vendor/timeline/jquery.timelify.js"></script>
        <script src="assets/vendor/loadscreen/js/ju-loading-screen.js"></script>
        <script src="assets/vendor/jquery-circle-progress/circle-progress.min.js"></script>
        <script src="assets/vendor/popper/popper.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap-4-navbar.js"></script>
        <script src="assets/vendor/owlcarousel/owl.carousel.min.js"></script> 
        <script src="assets/vendor/fancybox-master/jquery.fancybox.min.js"></script>
        <script src="assets/vendor/video-background/jquery.mb.YTPlayer.js"></script>
        <script src="assets/vendor/WOW-master/dist/wow.min.js"></script>
        <script src="assets/custom/js/custom.js"></script>  
        <script>
        </script>
    </body>
</html>
