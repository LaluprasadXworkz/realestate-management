<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>Bidding's </title>
      <link rel="icon" href="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" type="icon/png" />
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />

      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
        integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
      <style>
        .navbar-custom {
          background: linear-gradient(271deg, #ff5733, #3498db, #000000);
          padding: 20px 0;
        }

        .navbar-custom .navbar-brand,
        .navbar-custom .navbar-nav .nav-link {
          color: #fff;
        }

        .navbar-custom .navbar-toggler-icon {
          background-color: #fff;
        }

        .col-md-6 {
          flex: 0 0 auto;
          width: 60%;
        }


        .error {
          color: red;
        }
      </style>
    </head>

    <body>
      <nav class="navbar navbar-expand-lg navbar-dark navbar-custom ">
        <div class="container-fluid">
          <img src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
            style="height: 53px; width: 110px ;margin-left: 15vh;" alt="XWorkz Logo">
          <div class=" navbar-collapse justify-content-end grid gap-3 " style="width: 30vh;margin-right: 50px;">

            <a href="toViewProfile?id=${inforef.getRid()}" class="ml-md-3">
            <button type="button" class="btn btn btn-outline-dark">Back</button>
            </a>
          </div>
      </nav>

      <div class="container mt-5">

        <div class="row justify-content-center">

          <div class="col-md-6">

            <div class="center-align border-success shadow p-3 mb-5 bg-body-tertiary rounded">
              <h4 class="text-center text-danger">Bidding's</h4>
              <table class="table table-hover">
                <tr>
                  <th scope="col">Property Type</th>
                  <th scope="col">Bidder Name</th>
                  <th scope="col">Bidding Amount</th>
                  <th scope="col">Activity</th>
                </tr>
                <c:forEach var="bid" items="${bidding}">
                  <tr>
                    <td>${bid.getPropertyType()}</td>
                    <td>${bid.getBidderName()}</td>
                    <td>${bid.getAmount()}</td>
                    <td><a href="sell?id=${bid.getId()}"><button type="button" class="btn btn-outline-success"
                    style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">
                    <i class="fa-solid fa-check"></i></button></a>
                    </td>
                  </tr>
                </c:forEach>
              </table>
            </div>
          </div>
        </div>
      </div>

      <footer class="bg-dark text-white py-4">
        <div class="container">
          <div class="row">
            <div class="col-md-4">
              <h5>Contact Us</h5>
              <p>Email: myemail@gmail.com</p>
              <p>Phone: +91 9874563210</p>
            </div>
            <div class="col-md-4 text-center">
              <p>Designed by</p>
              <img src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
                style="height: 70px; width: 145px" alt="XWorkz Logo" alt="XWorkz Logo" />
            </div>
            <div class="col-md-4 text-end">
              <h5>Follow Us</h5>
              <a href="https://www.facebook.com/" class="me-2"><i class="fab fa-facebook"></i></a>
              <a href="https://www.instagram.com/" class="me-2"><i class="fab fa-instagram"></i></a>
              <a href="https://github.com/" class="me-2"><i class="fab fa-github"></i></a>
              <a href="https://twitter.com/"><i class="fab fa-twitter"></i></a>
            </div>
            <div class="col-md-12 text-center">
              <div>
                <p id="current-time" class="mb-0">Loading...</p>
                <p id="current-date" class="mb-0">Loading...</p>
              </div>
            </div>
          </div>
        </div>
      </footer>

      <script>

        function updateTime() {
          const now = new Date();
          const time = now.toLocaleTimeString();
          const date = now.toLocaleDateString();
          document.getElementById("current-time").textContent = time;
          document.getElementById("current-date").textContent = date;
        }
        updateTime();
        setInterval(updateTime, 1000);
      </script>
      <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
    </body>

    </html>