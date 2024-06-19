<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Loading Screen -->
<div id="ju-loading-screen">
    <div class="sk-double-bounce">
        <div class="sk-child sk-double-bounce1"></div>
        <div class="sk-child sk-double-bounce2"></div>
    </div>
</div>

<!-- Start Top Header -->

<!-- Start Fables Navigation -->
<div class="fables-navigation fables-main-background-color py-3 py-lg-0">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-10 col-lg-9 pr-md-0">                       
                <nav class="navbar navbar-expand-md btco-hover-menu py-lg-2">

                    <a class="navbar-brand pl-0" href="home"><img src="assets/custom/images/fables-logo.png" alt="Fables Template" class="fables-logo"></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#fablesNavDropdown" aria-controls="fablesNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="fables-iconmenu-icon text-white font-16"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="fablesNavDropdown"> 

                        <ul class="navbar-nav mx-auto fables-nav">   
                            <li class="nav-item">
                                <a class="nav-link" href="home" id="sub-nav1"  aria-haspopup="true" aria-expanded="false">
                                    Home
                                </a>
                            </li>


                            <li class="nav-item">
                                <a class="nav-link" href="about1.html" id="sub-nav3"  aria-haspopup="true" aria-expanded="false">
                                    About
                                </a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="store_grid_list.html" id="sub-nav4" aria-haspopup="true" aria-expanded="false">
                                    Store
                                </a>                                      
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="contactus1.html" id="sub-nav7" aria-haspopup="true" aria-expanded="false">
                                    Contact Us
                                </a>
                            </li>  
                        </ul> 
                    </div>
                </nav>
            </div>

            <div class="col-12 col-md-2 col-lg-3 pr-md-0">
                <div class="fables-header-icons">
                    <div class="dropdown"> 
                        <a href="#_" class="fables-third-text-color dropdown-toggle right px-3 px-md-2 px-lg-4 fables-second-hover-color top-header-link max-line-height position-relative" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="fables-iconcart-icon font-20"></span>
                            <span class="fables-cart-number fables-second-background-color text-center">3</span>
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <div class="p-3 cart-block">
                                <p class="fables-second-text-color semi-font mb-4 font-17">(2) Items in my cart</p>
                                <div class="row mx-0 mb-3">
                                    <div class="col-4 p-0">
                                        <a href="#"><img src="assets/custom/images/sml1.jpg" alt="" class="w-100"></a>
                                    </div>
                                    <div class="col-8">
                                        <h2><a href="#" class="fables-main-text-color font-13 d-block fables-main-hover-color">LUIS LEATHER DRIVING</a></h2>
                                        <p class="fables-second-text-color font-weight-bold">$ 100.00</p>
                                        <p class="fables-forth-text-color">QTY : 1</p>
                                    </div>
                                </div>
                                <div class="row mx-0 mb-3">
                                    <div class="col-4 p-0">
                                        <a href="#"><img src="assets/custom/images/sml1.jpg" alt="" class="w-100"></a>
                                    </div>
                                    <div class="col-8">
                                        <h2><a href="#" class="fables-main-text-color font-13 d-block fables-main-hover-color">LUIS LEATHER DRIVING</a></h2>
                                        <p class="fables-second-text-color font-weight-bold">$ 100.00</p>
                                        <p class="fables-forth-text-color">QTY : 1</p>
                                    </div>
                                </div>
                                <span class="font-16 semi-font fables-main-text-color">TOTAL</span>
                                <span class="font-14 semi-font fables-second-text-color float-right">$200.00</span>
                                <hr>
                                <div class="text-center">
                                    <a href="#" class="fables-second-background-color fables-btn-rounded  text-center white-color py-2 px-3 font-14 bg-hover-transparent border fables-second-border-color fables-second-hover-color">View my cart</a> 
                                    <a href="#" class="fables-second-text-color border fables-second-border-color fables-btn-rounded text-center white-color p-2 px-4 font-14 fables-second-hover-background-color">Checkout</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--                    <a href="signIn.jsp" class="fables-third-text-color fables-second-hover-color font-13 top-header-link px-3 px-md-2 px-lg-4 max-line-height"><span class="fables-iconuser"></span></a> -->

                    <c:if test="${sessionScope.acc == null}">
                        <div class="dropdown">
                            <a href="#_" class="fables-third-text-color dropdown-toggle right px-3 px-md-2 px-lg-4 fables-second-hover-color top-header-link max-line-height position-relative" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="fables-iconuser">
                            </a>
                            <div class="nav-item dropdown">
                                <ul class="dropdown-menu" aria-labelledby="sub-nav1">
                                    <li><a href="signIn.jsp"  class="dropdown-item"> Sign In </a></li>
                                    <li><a href="register.jsp"  class="dropdown-item"> Sign Up </a></li>
                                </ul>
                            </div>  
                        </div>
                    </c:if>

                    <c:if test="${sessionScope.acc != null}">
                        <div class="dropdown">
                            <a href="#_" class="fables-third-text-color dropdown-toggle right px-3 px-md-2 px-lg-4 fables-second-hover-color top-header-link max-line-height position-relative" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ${sessionScope. acc.getLname()} 
                            </a>
                            <div class="nav-item dropdown">
                                <ul style="list-style-type: none" class="dropdown-menu" aria-labelledby="sub-nav1">
                                    <li><a href="userDetail.jsp"  class="dropdown-item"> Profile </a></li>
                                    <li><a href="changePassword.jsp"  class="dropdown-item"> Change Password </a></li>
                                    <li><a href="logout"  class="dropdown-item"> Sign Out </a></li>
                                </ul>
                            </div>  
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
</div>
</div>     

<!-- /End Header -->