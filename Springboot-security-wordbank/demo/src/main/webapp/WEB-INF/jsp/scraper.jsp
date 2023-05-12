<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

          <html>

          <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
            <link rel="stylesheet" href="<c:url value="/resources/css/scraper.css"/>">
            <title>Words Scraping</title>
            <script>
                $document.ready.function({
                    $
                });
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
                                <span class="keyboard__key-line">MINE!</span>
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

                                        <span class="label">Bank: ${bankCount} words</span>
                                        </div>
                                        <div>

                                        <span class="label">Progress:%</span>
                                        </div>
                                    </div>
                                </sec:authorize>
                          </sec:authorize>
                        <button class="keyboard__key" type="submit" data-key="c">
                            <span class="keyboard__key-lines">
                                <span class="keyboard__key-line">Review</span>
                            </span>
                        </button>
                    </div>


                </div>



                  <div style="padding:20px; display:flex; column-gap:10px; row-gap:10px; flex-wrap:wrap;">
                    <c:forEach var="vocab" items="${vocabs}">

                      <a href="https://dict.laban.vn/find?type=1&query=${vocab}" target="_blank" class="vocab">${vocab}</a>
                        <div class="popup">
                            <a href="/noneed"
                        </div>
                    </c:forEach>
                  </div>


            </div>

          </body>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

          </html>