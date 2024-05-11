<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>Profile</title>
      <link rel="icon" href="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" type="icon/png" />
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
        integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
      <style>
        .custom-navbar {
          background-image: linear-gradient(270deg, #ff5733, #3498db, #000000);
        }

        @media (max-width: 768px) {

          .grid {
            display: block;
          }
        }
      </style>
    </head>

    <body>

      <nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
        <div class="container">
          <a class="navbar-brand" href="#">
            <img src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
              style="height: 53px; width: 110px; margin-left: 15px" alt="XWorkz Logo" />
          </a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link text-white" href="viewProfileToBidding?id=${inforef.getRid()}">Bidding's</a>
              </li>
              <li class="nav-item">
                <a class="nav-link text-white" href="viewProfileToSell?id=${inforef.getRid()}">Sell</a>
              </li>

              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button"
                  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                  style="width: 15vh;margin-right: -15px; background-color: black; padding: 8px 0px -8px 0px;">${inforef.getFirstName()}</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="view?id=${inforef.getRid()}"><i class="fa-solid fa-user"></i> View
                    Profile</a>
                  <a class="dropdown-item" href="sold?id=${inforef.getRid()}"><i class="fa-solid fa-building"></i>
                    Property Sold</a>
                  <a class="dropdown-item" href="buy?id=${inforef.getRid()}"><i
                      class="fa-solid fa-building-circle-check"></i> Property
                    Buy</a>
                  <a class="dropdown-item" href="update?id=${inforef.getRid()}"><i
                      class="fa-regular fa-pen-to-square"></i> Update Profile</a>
                  <a class="dropdown-item" href="delete?id=${inforef.getRid()}"><i class="fa-solid fa-delete-left"></i>
                    Delete Account</a>
                  <a class="dropdown-item" href="home"><i class="fa-solid fa-right-from-bracket"></i> Log out</a>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div>
        <marquee width="100%" direction="right">
          <p>${message}</p>
        </marquee>
        <h4 class="text-center">Property's for Sales</h4>
        <hr>
        <br>


        <div class="container">
          <div style="display: flex; flex-wrap: wrap; margin-left: 5%;" class="grid gap-3">
            <c:forEach var="info" items="${infoList}" varStatus="loop">
              <div class="card p-2 g-col-6" style="width: 18rem;">
                <img src="display?imagepath=${info.getPropertyImage()}" class="card-img-top" alt="...">
                <div class="card-body">
                  <h6 class="card-title">Property Type: ${info.getPropertyType()}</h6>
                  <p class="card-text">
                    Owner Name: ${info.getOwnerName()},
                    Square Feet: ${info.getSquareFeet()},
                    Price: ${info.getPrice()},
                    Location: ${info.getLocation()},
                    Pin code: ${info.getPinCode()}
                  </p>
                  <a href="bid?id=${info.getId()}" class="btn btn-outline-warning btn-sm">Bid</a>
                </div>
              </div>
              <c:if test="${loop.index % 5 == 4 && !loop.last}">
                <div class="w-100"></div>
              </c:if>
            </c:forEach>
          </div>
        </div>
        <br>
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
                style="height: 85px; width: 160px" alt="XWorkz Logo" />
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

      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.8/umd/popper.min.js"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

    </html>