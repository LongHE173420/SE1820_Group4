<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Fables">
        <meta name="author" content="Enterprise Development">
        <link rel="shortcut icon" href="assets/custom/images/shortcut.png">

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

        <style>
            .pagination {
                display: flex;
                justify-content: center;
                list-style: none;
                padding: 20px;
            }
            .pagination li {
                margin: 0 5px;
            }
            .pagination li a {
                text-decoration: none;
                color: #5a5a5a;
                background-color: #f2f2f2;
                padding: 8px 16px;
                border-radius: 5px;
                transition: all 0.3s;
            }
            .pagination li a:hover,
            .pagination li.active a {
                background-color: #007bff;
                color: white;
            }
            .news-card {
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                transition: 0.3s;
                border-radius: 5px;
                overflow: hidden;
                margin-bottom: 24px;
                display: flex;
                align-items: center;
            }
            .news-card img {
                width: 200px;
                height: 200px;
                object-fit: cover;
                border-top-left-radius: 5px;
                border-bottom-left-radius: 5px;
            }
            .news-card-body {
                padding: 15px;
                flex: 1;
            }
            .news-card-title {
                font-size: 20px;
                margin-bottom: 15px;
            }
            .news-card-text {
                font-size: 16px;
                color: #666;
            }
            .navbar-nav {
                display: flex;
                justify-content: space-around;
                padding: 0;
                list-style: none;
                background-color: #f8f9fa;
                padding: 10px 0;
                margin: 0;
            }
            .navbar-nav li {
                list-style: none;
                margin: 0 10px;
            }
            .navbar-nav li a {
                text-decoration: none;
                color: #007bff;
                padding: 10px 20px;
                display: block;
                transition: background-color 0.3s;
            }
            .navbar-nav li a:hover,
            .navbar-nav li.active a {
                background-color: #007bff;
                color: white;
                border-radius: 5px;
            }
            .navbar {
                margin: 0;
                padding: 0;
                background-color: #343a40 !important;
            }
            .navbar-nav {
                background-color: transparent !important;
            }
        </style>
    </head>
    <body>

        <jsp:include page="header.jsp"/>

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
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <ul class="navbar-nav">
                        <li><a href="newsUser?groupName=all">All</a></li>
                            <c:forEach var="g" items="${requestScope.groups}">
                                <c:set var="sg" value="${requestScope.selectGroup}"/>
                                <c:if test="${sg.getName()==g.getName()}">
                                <li class="active"><a href="newsUser?groupName=${g.getName().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}">${g.getName()}</a></li>
                                </c:if>
                                <c:if test="${sg.getName()!=g.getName()}">
                                <li><a href="newsUser?groupName=${g.getName().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}">${g.getName()}</a></li>
                                </c:if>
                            </c:forEach>
                    </ul>
                </div>
                <div class="col-md-10">
                    <div class="single-product-area">
                        <div class="zigzag-bottom"></div>            
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">                      
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
                                                <div class="col-xs-12">
                                                    <div class="news-card">
                                                        <img src="${ns.getImage()}" alt="${ns.getTitle()}"
                                                             onerror="this.onerror=null; 
                                                             this.src='https://lh4.ggpht.com/-PtwFBckvv78/V3aBB39xD9I/AAAAAAAAHFA/EXKKalIB8IkvyJjUzGrDVQCzLMs5Alx9QCLcB/s1600/anh-blogspot-khong-hien-thi.png';">
                                                        <div class="news-card-body">
                                                            <h3 class="news-card-title">
                                                                <a href="newsUserDetail?title=${ns.getTitle().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}" title="${ns.getTitle()}">${ns.getTitle()}</a>
                                                            </h3>
                                                            <p class="news-card-text">${ns.getHeading()}</p>
                                                            <a href="newsUserDetail?title=${ns.getTitle().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}" class="btn btn-primary">Read More</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>                                   
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="product-pagination text-center">
                                        <nav>
                                            <ul class="pagination">
                                                <c:if test="${requestScope.page > 1}">
                                                    <li>
                                                        <a href="newsUser?groupName=${requestScope.pageGroup}&page=${requestScope.page - 1}" aria-label="Previous">
                                                            &laquo;
                                                        </a>
                                                    </li>
                                                </c:if>                                        
                                                <c:forEach begin="1" end="${requestScope.count}" var="i">
                                                    <li class="${requestScope.page == i ? 'active' : ''}">
                                                        <a href="newsUser?groupName=${requestScope.pageGroup}&page=${i}">${i}</a>
                                                    </li>                                         
                                                </c:forEach>                                            
                                                <c:if test="${requestScope.page < requestScope.count}">
                                                    <li>
                                                        <a href="newsUser?groupName=${requestScope.pageGroup}&page=${requestScope.page + 1}" aria-label="Next">
                                                            &raquo;
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
            </div>
        </div>


        <script type="text/javascript">
            function sendRequest(value) {
                var url = "newsUser";
                var xhttp = new XMLHttpRequest();
                xhttp.open("POST", url, true);
                xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhttp.send("viewNewsId=" + value);
            }
        </script>

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
        <jsp:include page="footer.jsp"/>

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
        </script>>
    </body>
</html>
