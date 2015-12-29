<!DOCTYPE html>
<%@page import="com.metasocio.model.usermanagement.User"%>
<html lang="en">
<%@ include file="view/header.html"%>
<%@ include file="view/scripts.html"%>



<body id="page-top">
     <%@ page session="false"%>
     <%@ page errorPage="../../exception/error.jsp"%>
   
    <%@ include file="view/navbar-index.jsp"%>
    
    <header style="background-image: url('assets/img/bg-header.jpg');height:100vh">
        <div class="intro-content">
            <img src="assets/img/profile.png" class="img-responsive img-centered" alt="">
            <div class="brand-name">Meta-Socio</div>
            <hr class="colored">
            <div class="brand-name-subtext">
                            <div class="input-group input-group-lg">
                                <span class="input-group-btn">
                                    <a class="btn btn-primary"
						href="https://accounts.google.com/o/oauth2/auth?scope=email&amp;redirect_uri=http://localhost:8080/MetaSocio/OAuth&amp;response_type=code&amp;client_id=
530623119057-nfsj6k1res5fh0gvpu3epncerdrj1594.apps.googleusercontent.com&amp;approval_prompt=force"><span class="fa fa-sign-in"></span>&nbsp;Sign-In with METACUBE-ID</a>
					</span>
                            </div>
                            <div id="mce-responses">
                                <div class="response" id="mce-error-response">${message}</div>
                                <div class="response" id="mce-error-response">${param.message}</div>
                                
                            </div>
            </div>
        </div>
       
        
    </header>
   
   
</body>

</html>