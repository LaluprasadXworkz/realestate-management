<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register Form</title>
    <link rel="icon" href="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" type="icon/png" />
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
        .error {
              color: red;
        }

      </style>
  </head>

  <body>
    <nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
        <div class="container-fluid">
          <img src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
                              style="height: 53px; width: 110px ;margin-left: 15vh;" alt="XWorkz Logo">
          <div class="collapse navbar-collapse justify-content-end" id="navbarNav" style="margin-right: 50px;">
            <a href="home" class="ml-md-3">
              <button type="button" class="btn btn btn-outline-dark">Home</button>
            </a>
          </div>
        </div>
      </nav>
    <div class="container mt-4">
    <label for="exampleFormControlInput1" class="form-label">Registration From</label>
      <form
        id="myForm"
        onsubmit="return registerUser()"
        action="creatingRealEstate"
        method="post"
        class="form-control border-success shadow p-3 mb-5 bg-body-tertiary rounded"
      >
        <div class="row mb-3">
          <div class="col-md-6">
            <label for="firstName" class="form-label"
              >First Name <span class="error">*</span></label
            >
            <input
              type="text"
              class="form-control"
              id="firstName"
              name="firstName"
            />
            <span class="error" id="fNameError"></span>
          </div>
          <div class="col-md-6">
            <label for="lastName" class="form-label"
              >Last Name <span class="error">*</span></label
            >
            <input
              type="text"
              class="form-control"
              id="lastName"
              name="lastName"
            />
            <span class="error" id="lNameError"></span>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-md-6">
            <label for="email" class="form-label"
              >Email <span class="error">*</span></label
            >
            <input
              type="email"
              class="form-control"
              id="email"
              name="email"
              onchange="emailcheck()"
            />
            <span class="error" id="emailError"></span>
          </div>
          <div class="col-md-6">
            <label for="alternativeEmail" class="form-label"
              >Alternative Email <span class="error">*</span></label
            >
            <input
              type="email"
              class="form-control"
              id="alternativeEmail"
              name="alternativeEmail"
            />
            <span class="error" id="altEmailError"></span>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-md-6">
            <label for="contactNumber" class="form-label"
              >Contact Number <span class="error">*</span></label
            >
            <input
              type="tel"
              class="form-control"
              id="contactNumber"
              name="contactNumber"
              onchange="contactNumberCheck()"
            />
            <span class="error" id="cNumbError"></span>
          </div>
          <div class="col-md-6">
            <label for="alternativeContact" class="form-label"
              >Alternative Contact Number <span class="error">*</span></label
            >
            <input
              type="tel"
              class="form-control"
              id="alternativeContact"
              name="alternativeContact"
            />
            <span class="error" id="altCNumbError"></span>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-md-6">
            <label for="currentAddress" class="form-label"
              >Current Address<span class="error">*</span></label
            >
            <textarea
              class="form-control"
              id="currentAddress"
              name="currentAddress"
            ></textarea>
            <span class="error" id="curAddresError"></span>
          </div>
          <div class="col-md-6">
            <label for="permanentAddress" class="form-label"
              >Permanent Address<span class="error">*</span></label
            >
            <textarea
              class="form-control"
              id="permanentAddress"
              name="permanentAddress"
            ></textarea>
            <span class="error" id="perAddresError"></span>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-md-6">
            <label for="panCardNumber" class="form-label"
              >PAN Card Number<span class="error">*</span></label
            >
            <input
              type="text"
              class="form-control"
              id="panCardNumber"
              name="panCardNumber"
              onchange="panCardNumberCheck()"
            />
            <span class="error" id="panError"></span>
          </div>
          <div class="col-md-6">
            <label for="aadharNumber" class="form-label"
              >Aadhar Number<span class="error">*</span></label
            >
            <input
              type="text"
              class="form-control"
              id="aadharNumber"
              name="aadharNumber"
              onchange="aadharNumberCheck()"
            />
            <span class="error" id="aadhNumbError"></span>
          </div>
        </div>
        <div class="row justify-content-center">
          <div class="col-auto">
            <input
              type="submit"
              class="btn btn-outline-danger"
              value="Register"
            />
          </div>
          <div class="col-auto">
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
    <footer class="bg-white text-dark py-4">
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
              style="height: 70px; width: 145px" alt="XWorkz Logo"
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
      function emailcheck() {
        let email = document.getElementById("email").value;
        const request = new XMLHttpRequest();
        request.open(
          "GET",
          "http://localhost:8080/realestate-management//getEmail/" + email
        );
        request.send();
        request.onload = function () {
          let returnValue = this.responseText;
          console.log(returnValue);
          document.getElementById("emailError").innerHTML = returnValue;
        };
      }

      function contactNumberCheck() {
        let contactNumber = document.getElementById("contactNumber").value;
        const request = new XMLHttpRequest();
        request.open(
          "GET",
          "http://localhost:8080/realestate-management//getContactNumber/" +
            contactNumber
        );
        request.send();
        request.onload = function () {
          let returnValue = this.responseText;
          console.log(returnValue);
          document.getElementById("cNumbError").innerHTML = returnValue;
        };
      }

      function panCardNumberCheck() {
        let panCardNumber = document.getElementById("panCardNumber").value;
        const request = new XMLHttpRequest();
        request.open(
          "GET",
          "http://localhost:8080/realestate-management//getPanCardNumber/" +
            panCardNumber
        );
        request.send();
        request.onload = function () {
          let returnValue = this.responseText;
          console.log(returnValue);
          document.getElementById("panError").innerHTML = returnValue;
        };
      }

      function aadharNumberCheck() {
        let aadharNumber = document.getElementById("aadharNumber").value;
        const request = new XMLHttpRequest();
        request.open(
          "GET",
          "http://localhost:8080/realestate-management//getAadharNumber/" +
            aadharNumber
        );
        request.send();
        request.onload = function () {
          let returnValue = this.responseText;
          console.log(returnValue);
          document.getElementById("aadhNumbError").innerHTML = returnValue;
        };
      }

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
            document.getElementById("lNameError").innerText="";
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
    <script
      src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
      integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
      integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
