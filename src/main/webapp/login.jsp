<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login Page</title>
    <link
      rel="icon"
      href="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
      type="image/png"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
    />

    <style>
      .custom-navbar {
        background-image: linear-gradient(270deg, #ff5733, #3498db, #000000);
      }
      .col-md-6 {
        width: content;
      }
      .center-align {
        text-align: center;
      }
      @media (min-width: 768px) {
        .custom-col-md-6 {
          width: 30%;
        }
      }
      .mt-5 {
        margin-top: 3rem !important;
        height: 49vh;
      }

      .error {
        color: red;
      }

      body {
        display: flex;
        flex-direction: column;
        min-height: 100vh;
      }

      footer {
        margin-top: auto;
        background-color: #333;
        color: #fff;
        padding: 20px;
        text-align: center;
      }
    </style>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
      <div class="container">
        <a class="navbar-brand" href="#">
          <img
            src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
            style="height: 53px; width: 110px; margin-left: 15px"
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
              <a href="homeToRegister" class="nav-link text-white">Register</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="home">Home</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6 col-sm-10 col-12 custom-col-md-6">
          <div
            class="center-align border border-black shadow-lg p-3 mb-5 bg-body-tertiary rounded"
          >
            <h2>Login</h2>
            <form action="generateOTP" method="post" id="generatedOTPForm">
              <div class="mb-3">
                <label for="emailID" class="form-label">Email address</label>
                <input
                  type="email"
                  name="email"
                  class="form-control"
                  id="emailID"
                  onchange="getEmailForLogin()"
                  placeholder="Enter Registered Email"
                />
                <span class="error" id="emailerror">${msg}</span>
              </div>
              <button
                type="submit"
                class="btn btn-primary mb-3"
                id="submitButton"
                disabled
              >
                Generate OTP
              </button>
            </form>
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
          <div class="col-md-12 text-center mt-3">
            <p id="current-time" class="mb-0">Loading...</p>
            <p id="current-date" class="mb-0">Loading...</p>
          </div>
        </div>
      </div>
    </footer>
    <script>
      function getEmailForLogin() {
        let email = document.getElementById("emailID").value;
        const submitButton = document.getElementById("submitButton");
        const request = new XMLHttpRequest();
        request.open(
          "GET",
          "http://localhost:8080/realestate-management/getEmailForLogin/" +
            email
        );
        request.send();
        request.onload = function () {
          let returnValue = this.responseText;
          if (returnValue) {
            console.log(returnValue);
            submitButton.disabled = false;
            document.getElementById("emailerror").innerHTML = returnValue;
          } else {
            console.log(returnValue);
            submitButton.disabled = true;
            document.getElementById("emailerror").innerHTML = returnValue;
          }
        };
      }

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

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
</html>
