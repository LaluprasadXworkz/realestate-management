<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Property Details</title>
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

      .center-align {
        text-align: center;
      }

      .errors,
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
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
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
      <form
        id="myForm"
        onsubmit="return validateForm()"
        action="creatingPropertyDto"
        method="post"
        enctype="multipart/form-data"
        class="form-control border-success shadow p-3 mb-5 bg-body-tertiary rounded"
      >
        <div class="row justify-content-center">
          <h3 class="form-label text-center">Property Details</h3>

          <div class="col-sm-12 col-md-6 mb-3">
            <label for="pType" class="form-label"
              >Property Type <span class="errors">*</span></label
            >
            <select name="propertyType" id="pType" class="form-control">
              <option value="" selected="selected">Select property type</option>
              <option value="Residential">Residential</option>
              <option value="Commercial">Commercial</option>
              <option value="Raw land">Raw land</option>
              <option value="Industrial">Industrial</option>
              <option value="Special purpose">Special purpose</option>
            </select>
            <span class="error" id="pTypeError"></span>
          </div>

          <div class="col-sm-12 col-md-6 mb-3">
            <label for="sFeet" class="form-label"
              >Square Feet <span class="errors">*</span></label
            >
            <input
              type="text"
              class="form-control"
              id="sFeet"
              name="squareFeet"
            />
            <span class="error" id="sFeetError"></span>
          </div>
          <div class="col-sm-12 col-md-6 mb-3">
            <label for="cost" class="form-label"
              >Price <span class="errors">*</span></label
            >
            <input type="text" class="form-control" id="cost" name="price" />
            <span class="error" id="costError"></span>
          </div>
          <div class="col-sm-12 col-md-6 mb-3">
            <label for="loc" class="form-label"
              >Location <span class="errors">*</span></label
            >
            <textarea class="form-control" id="loc" name="location"></textarea>
            <span class="error" id="locError"></span>
          </div>
          <div class="col-sm-12 col-md-6 mb-3">
            <label for="pinCode" class="form-label"
              >Pin code <span class="errors">*</span></label
            >
            <input
              type="number"
              class="form-control"
              id="pinCode"
              name="pinCode"
            />
            <span class="error" id="pinCodeError"></span>
          </div>
          <div class="col-sm-12 col-md-6 mb-3">
            <label for="propertyImage" class="form-label"
              >Add Property Images</label
            >
            <input
              type="file"
              class="form-control"
              id="propertyImage"
              name="multipartFile"
              accept="image/*"
            />
          </div>
          <div class="col-12 text-center">
            <input
              type="submit"
              class="btn btn-outline-danger"
              value="Submit"
            />
            <input
              type="reset"
              class="btn btn-outline-dark"
              value="Clear"
              onclick="clearForm()"
            />
          </div>
        </div>
      </form>
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
      function validateForm() {
        var pType = document.getElementById("pType").value;
        var sFeet = document.getElementById("sFeet").value;
        var cost = document.getElementById("cost").value;
        var loc = document.getElementById("loc").value;
        var pinCode = document.getElementById("pinCode").value;
        var isValid = true;

        if (pType.trim() === "") {
          document.getElementById("pTypeError").innerText =
            "Please enter property type.";
          isValid = false;
        } else {
          document.getElementById("pTypeError").innerText = "";
        }

        if (sFeet.trim() === "") {
          document.getElementById("sFeetError").innerText =
            "Please enter square feet.";
          isValid = false;
        } else {
          document.getElementById("sFeetError").innerText = "";
        }

        if (cost.trim() === "") {
          document.getElementById("costError").innerText =
            "Please enter price.";
          isValid = false;
        } else {
          document.getElementById("costError").innerText = "";
        }

        if (loc.trim() === "") {
          document.getElementById("locError").innerText =
            "Please enter location.";
          isValid = false;
        } else {
          document.getElementById("locError").innerText = "";
        }

        if (pinCode.trim() === "") {
          document.getElementById("pinCodeError").innerText =
            "Please enter pin code.";
          isValid = false;
        } else {
          document.getElementById("pinCodeError").innerText = "";
        }
        return isValid;
      }

      function clearForm() {
        document.getElementById("myForm").reset();
        document.querySelectorAll(".error").forEach(function (el) {
          el.innerText = "";
        });
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
