<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>Update Register Form</title>
      <link rel="icon" href="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" type="icon/png" />
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />

      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
        integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
      <style>
        .custom-navbar {
          background-image: linear-gradient(270deg, #ff5733, #3498db, #000000);
        }

        .errors, .error {
          color: red;
        }
      </style>
    </head>

    <body>
      <nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
        <div class="container">
          <a class="navbar-brand" href="#">
            <img src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
              style="height: 50px; width: 105px; margin-left: 15px;" alt="XWorkz Logo" />
          </a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link text-white ml-md-3" href="toViewProfile?id=${inforef.getRid()}" tabindex="-1" aria-disabled="true">Back</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div class="container mt-4">
        <label for="exampleFormControlInput1" class="form-label">Update Registration From</label>
        <form id="myForm" onsubmit="return registerUser()" action="updateInfoById" method="post"
          class="form-control border-success shadow p-3 mb-5 bg-body-tertiary rounded">
          <div class="row mb-3">
            <div class="col-md-6">
              <label for="firstName" class="form-label">First Name <span class="errors">*</span></label>
              <input type="text" class="form-control" id="firstName" name="firstName"
                value="${inforef.getFirstName()}" />
              <span class="error" id="fNameError"></span>
            </div>
            <div class="col-md-6">
              <label for="lastName" class="form-label">Last Name <span class="errors">*</span></label>
              <input type="text" class="form-control" id="lastName" name="lastName" value="${inforef.getLastName()}" />
              <span class="error" id="lNameError"></span>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-md-6">
              <label for="email" class="form-label">Email <span class="errors">*</span></label>
              <input type="email" class="form-control" id="email" name="email" value="${inforef.getEmail()}" />
              <span class="error" id="emailError"></span>
            </div>
            <div class="col-md-6">
              <label for="alternativeEmail" class="form-label">Alternative Email <span class="errors">*</span></label>
              <input type="email" class="form-control" id="alternativeEmail" name="alternativeEmail"
                value="${inforef.getAlternativeEmail()}" />
              <span class="error" id="altEmailError"></span>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-md-6">
              <label for="contactNumber" class="form-label">Contact Number <span class="errors">*</span></label>
              <input type="tel" class="form-control" id="contactNumber" name="contactNumber"
                value="${inforef.getContactNumber()}" />
              <span class="error" id="cNumbError"></span>
            </div>
            <div class="col-md-6">
              <label for="alternativeContact" class="form-label">Alternative Contact Number <span
                  class="errors">*</span></label>
              <input type="tel" class="form-control" id="alternativeContact" name="alternativeContact"
                value="${inforef.getAlternativeContact()}" />
              <span class="error" id="altCNumbError"></span>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-md-6">
              <label for="currentAddress" class="form-label">Current Address<span class="errors">*</span></label>
              <input type="text" class="form-control" id="currentAddress" name="currentAddress"
                value="${inforef.getCurrentAddress()}"></input>
              <span class="error" id="curAddresError"></span>
            </div>
            <div class="col-md-6">
              <label for="permanentAddress" class="form-label">Permanent Address<span class="errors">*</span></label>
              <input type="text" class="form-control" id="permanentAddress" name="permanentAddress"
                value="${inforef.getPermanentAddress()}"></input>
              <span class="error" id="perAddresError"></span>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-md-6">
              <label for="panCardNumber" class="form-label">PAN Card Number<span class="errors">*</span></label>
              <input type="text" class="form-control" id="panCardNumber" name="panCardNumber"
                value="${inforef.getPanCardNumber()}" />
              <span class="error" id="panError"></span>
            </div>
            <div class="col-md-6">
              <label for="aadharNumber" class="form-label">Aadhar Number<span class="errors">*</span></label>
              <input type="text" class="form-control" id="aadharNumber" name="aadharNumber"
                value="${inforef.getAadharNumber()}" />
              <span class="error" id="aadhNumbError"></span>
            </div>
          </div>
          <div class="row justify-content-center">
            <div class="col-auto">
              <input type="submit" class="btn btn-outline-danger" value="Update" />
            </div>
            <div class="col-auto">
              <input type="reset" class="btn btn-outline-dark" value="Clear" onclick="clearForm()" />
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
        function registerUser() {
          let firstName = document.getElementById("firstName").value;
          let lastName = document.getElementById("lastName").value;
          let email = document.getElementById("email").value;
          let alternativeEmail =
            document.getElementById("alternativeEmail").value;
          let contactNumber = document.getElementById("contactNumber").value;
          let alternativeContact =
            document.getElementById("alternativeContact").value;
          let currentAddress = document.getElementById("currentAddress").value;
          let permanentAddress =
            document.getElementById("permanentAddress").value;
          let panCardNumber = document.getElementById("panCardNumber").value;
          let aadharNumber = document.getElementById("aadharNumber").value;

          let isValid = true;
          if (firstName === "" || firstName.length < 4) {
            document.getElementById("fNameError").innerText =
              "Given name must be at least 4 characters";
            isValid = false;
          } else {
            document.getElementById("fNameError").innerText = "";
          }

          if (lastName === "" || lastName.length < 1) {
            document.getElementById("lNameError").innerText =
              "Last name must be at least 1 characters";
            isValid = false;
          } else {
            document.getElementById("lNameError").innerText = "";
          }

          if (email === "" || !validateEmail(email)) {
            document.getElementById("emailError").innerText = "Invalid email address";
            isValid = false;
          } else {
            document.getElementById("emailError").innerText = "";
          }

          if (alternativeEmail === "" || !validateEmail(alternativeEmail)) {
            document.getElementById("altEmailError").innerText = "Invalid alternative email address";
            isValid = false;
          } else if (email === alternativeEmail) {
            document.getElementById("altEmailError").innerText = "Alternative email cannot be the same as primary email";
            isValid = false;
          } else {
            document.getElementById("altEmailError").innerText = "";
          }

          if (contactNumber === "" || !validatePhoneNumber(contactNumber)) {
            document.getElementById("cNumbError").innerText = "Invalid contact number";
            isValid = false;
          } else {
            document.getElementById("cNumbError").innerText = "";
          }

          if (
            alternativeContact === "" ||
            !validatePhoneNumber(alternativeContact)
          ) {
            document.getElementById("altCNumbError").innerText = "Invalid alternative contact number";
            isValid = false;
          } else if (contactNumber === alternativeContact) {
            document.getElementById("altCNumbError").innerText = "Alternative Phone Number cannot be the same as primary Contact Number";
            isValid = false;
          } else {
            document.getElementById("altCNumbError").innerText = "";
          }

          if (currentAddress === "") {
            document.getElementById("curAddresError").innerText = "Current address cannot be empty";
            isValid = false;
          } else {
            document.getElementById("curAddresError").innerText = "";
          }

          if (permanentAddress === "") {
            document.getElementById("perAddresError").innerText = "Permanent address cannot be empty ";
            isValid = false;
          } else {
            document.getElementById("perAddresError").innerText = "";
          }

          if (panCardNumber === "" || !validatePAN(panCardNumber)) {
            document.getElementById("panError").innerText = "Invalid PAN card number";
            isValid = false;
          } else {
            document.getElementById("panError").innerText = "";
          }

          if (aadharNumber === "" || !validateAadhar(aadharNumber)) {
            document.getElementById("aadhNumbError").innerText = "Invalid Aadhar number";
            isValid = false;
          } else {
            document.getElementById("aadhNumbError").innerText = "";
          }

          return isValid;
        }
        function clearForm() {
          document.getElementById("myForm").reset();
          document.querySelectorAll(".error").forEach(function (el) {
            el.innerText = "";
          });
        }
        function validateEmail(email) {
          var re = /\S+@\S+\.\S+/;
          return re.test(email);
        }

        function validatePhoneNumber(phoneNumber) {
          var re = /^\d{10}$/;
          return re.test(phoneNumber);
        }

        function validatePAN(pan) {
          var re = /[A-Z]{5}[0-9]{4}[A-Z]{1}/;
          return re.test(pan);
        }

        function validateAadhar(aadhar) {
          var re = /^\d{12}$/;
          return re.test(aadhar);
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
      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.8/umd/popper.min.js"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

    </html>