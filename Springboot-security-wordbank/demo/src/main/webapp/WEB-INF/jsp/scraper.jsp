<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

          <html>

          <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <link rel="stylesheet" href="<c:url value="/resources/css/scraper.css"/>">
            <title>Words Scraping</title>
            <script type="text/javascript" src="<c:url value="/resources/js/scraper.js" />"></script>
            <script>

                 function showPopup(elem) {
                     // Get the closest .popup element to the current element
                     var popup = elem.querySelector('.popup');
                      popup.style.width = elem.clientWidth + 'px';
                       popup.style.height = elem.clientHeight + 'px';
                     // Show the popup element
                     popup.classList.add('visible');
                 }
                 function banWord(button) {


                      var vocab = $(button).data('vocab');
                      console.log(vocab);
                      $(button).parent().parent().hide();
                      $.ajax({
                        url:'/noNeed',
                        method: 'POST',
                        data: { vocab: vocab },
                        success: function(response) {
                            console.log(response);
                             var bankCount=$('#bank-count');
                              var bankProgress=$('#bank-progress');
                              bankCount.html(response.bankCount);
                            bankProgress.html(response.bankProgress);

                        }
                      });
                  }
                  function addWord(button) {


                        var vocab = $(button).data('vocab');
                        console.log(vocab);
                        $(button).parent().parent().hide();
                        $.ajax({
                          url:'/addToBank',
                          method: 'POST',
                          data: { vocab: vocab },
                          success: function(response) {
                              console.log(response);

                              var bankCount=$('#bank-count');
                              var bankProgress=$('#bank-progress');
                              bankCount.html(response.bankCount);
                              bankProgress.html(response.bankProgress);

                          }
                        });
                    }
                  function hidePopup(elem) {
                     // Get the closest .popup element to the current element
                     var popup = elem.querySelector('.popup');
                      popup.style.width = elem.clientWidth + 'px';
                       popup.style.height = elem.clientHeight + 'px';
                     // Hide the popup element
                     popup.classList.remove('visible');
                 }

            </script>

          </head>

          <body>



            <div style="width:100%">
                <div class="header">
                <form method="get" action="/scraping">
                    <div class="box">
                      <div class="input-box">
                        <div class="label">Enter an URL to mine words!</div>
                        <input style="width:30vw" name="url" type="url" required>
                      </div>

                        <button class="keyboard__key" type="submit" data-key="c">
                            <span class="keyboard__key-lines">
                                <span class="keyboard__key-line"><ion-icon name="funnel"></ion-icon></span>
                            </span>
                        </button>

                    </div>
                </form>
                    <div class="user-info">
                        <sec:authorize access="isAuthenticated()">

                              <sec:authorize access="hasRole('ADMIN')">
                                    <div class="info">
                                        <div class="label"><span style="font-size:small;max-width:3vw;overflow:hidden;">${user.email}</span></div>
                                        <div>

                                        <span class="label">Bank: <span id="bank-count">${bankCount}</span> words</span>
                                        </div>
                                        <div>

                                        <span class="label">Progress:<span id="bank-progress">${sessionScope.progress}</span>%</span>
                                        </div>
                                    </div>
                                </sec:authorize>
                          </sec:authorize>


                                <a href="/reviewForm" class="keyboard__key">Review</a>


                    </div>


                </div>



                  <div style="padding:20px; display:flex; column-gap:10px; row-gap:10px; flex-wrap:wrap;">
                    <c:forEach var="vocab" items="${vocabs}">
                      <div onmouseenter="showPopup(this)" onmouseleave="hidePopup(this)">
                          <div class="vocab">${vocab}</div>
                            <div class="popup">
                                <button id="ban-button" onclick="banWord(this)" data-vocab="${vocab}" style="background:none;color:white;"><ion-icon name="ban"></ion-icon></button>
                                <button id="add-button" onclick="addWord(this)" data-vocab="${vocab}" style="background:none;color:white;"><ion-icon name="arrow-down-circle"></ion-icon></button>
                                <a class="action" href="https://dict.laban.vn/find?type=1&query=${vocab}" style="text-decoration:inherit;color:white;" target="_blank"><ion-icon name="search-circle"></ion-icon></a>
                            </div>
                      </div>
                    </c:forEach>
                  </div>


            </div>

          </body>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
          <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
          <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

          </html>