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

            <title>Words Scraping</title>
            <script>
                $(document).ready(function() {
                    var token = sessionStorage.getItem("token");
                    $.ajaxSetup({
                      headers: {
                        'Authorization': 'Bearer ' + data.token
                      }
                    });
                });
            </script>
          </head>

          <body>

            <nav class="navbar navbar-expand-lg navbar-light bg-light text-center">
              <h4 class="navbar-brand" href="book">Enter an URL to scrap</h4>



            </nav>

            <div class="container">
                <form method="get" action="/scraping">
                    <input style="width:30vw" name="url" type="url">
                    <button type="submit">Find Words</button>
               </form>




                  <div style="margin-top: 2vw; display:flex; column-gap:1vw; row-gap:1vw; flex-wrap:wrap; ">
                    <c:forEach var="vocab" items="${vocabs}">
                      <a href="https://dict.laban.vn/find?type=1&query=${vocab}" style="text-decoration:inherit;background:lightblue;font-weight:700;color:white; border-radius:10px;padding:5px;">${vocab}</a>

                    </c:forEach>
                  </div>

              </table>
            </div>

          </body>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

          </html>