<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Property Sold</title>
    <link
      rel="icon"
      href="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
      type="icon/png"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
      integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <style>
      .custom-navbar {
        background-image: linear-gradient(270deg, #ff5733, #3498db, #000000);
      }
      .col-md-6 {
        flex: 0 0 auto;
        width: 45%;
      }

      .error {
        color: red;
      }
    </style>
  </head>

  <body>
    <nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
      <div class="container">
        <a class="navbar-brand" href="#">
          <img
            src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
            style="height: 50px; width: 105px; margin-left: 15px"
            alt="XWorkz Logo"
          />
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div
          class="collapse navbar-collapse justify-content-end"
          id="navbarNav"
        >
          <ul class="navbar-nav">
            <li class="nav-item">
              <a
                class="nav-link text-white ml-md-3"
                href="toViewProfile?id=${inforef.getRid()}"
                tabindex="-1"
                aria-disabled="true"
                >Back</a
              >
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
          <!-- Adjusted column width for medium and large screens -->
          <div
            class="center-align border-success shadow p-3 mb-5 bg-body-tertiary rounded"
          >
            <h4 class="text-center text-danger">Property Sold</h4>
            <div class="table-responsive">
              <!-- Ensures table is scrollable on smaller screens -->
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th scope="col">Property Type</th>
                    <th scope="col">Sold To</th>
                    <th scope="col">Price</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="sold" items="${details}">
                    <tr>
                      <td>${sold.getPropertyType()}</td>
                      <td>${sold.getSoldTo()}</td>
                      <td>${sold.getPrice()}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
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
            <img
              src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
              style="height: 70px; width: 145px"
              alt="XWorkz Logo"
              alt="XWorkz Logo"
            />
          </div>
          <div class="col-md-4 text-end">
            <h5>Follow Us</h5>
            <a href="https://www.facebook.com/" class="me-2"
              ><i class="fab fa-facebook"></i
            ></a>
            <a href="https://www.instagram.com/" class="me-2"
              ><i class="fab fa-instagram"></i
            ></a>
            <a href="https://github.com/" class="me-2"
              ><i class="fab fa-github"></i
            ></a>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.8/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
