<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Property Details</title>
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

        .center-align {
            text-align: center;
        }

        #myForm {
            width: 75vh;
        }

        .errors ,.error{
            color: red;
        }
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
        <img src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
            style="height: 53px; width: 110px ;margin-left: 15vh;" alt="XWorkz Logo">

        <div class="navbar-collapse justify-content-end grid gap-3" id="navbarNav"
            style="width: 30vh;margin-right: 50px;">
            <a href="toViewProfile?id=${inforef.getRid()}" class="ml-md-3">
                <button type="button" class="btn btn btn-outline-dark">Back</button>
            </a>

        </div>
    </nav>

    <div class="mx-auto p-2 border border-5">

        <form id="myForm" onsubmit="return validateForm()" action="creatingPropertyDto" method="post"  enctype="multipart/form-data"
            class="form-control border-success  shadow p-3 mb-5 bg-body-tertiary rounded container justify-content-center">
            <h3 for="exampleFormControlInput1" class="form-label">
                Property Details
            </h3>

            <div>
                <label for="pType" class="form-label">Property Type <span class="errors">*</span></label>
                <input type="text" class="form-control" id="pType" name="propertyType" />
                <span class="error" id="pTypeError"></span>
            </div>
            <br>
            <div>
                <label for="sFeet" class="form-label">Square Feet <span class="errors">*</span></label>
                <input type="text" class="form-control" id="sFeet" name="squareFeet" />
                <span class="error" id="sFeetError"></span>
            </div>
            <br>
            <div>

                <label for="cost" class="form-label">Price <span class="errors">*</span></label>
                <input type="text" class="form-control" id="cost" name="price" />
                <span class="error" id="costError"></span>


            </div>
            <br>
            <div>

                <label for="loc" class="form-label">Location <span class="errors">*</span></label>
                <textarea class="form-control" id="loc" name="location"></textarea>

                <span class="error" id="locError"></span>

            </div>
            <br>
            <div>
                <label for="pinCode" class="form-label">Pin code <span class="errors">*</span></label>
                <input type="number" class="form-control" id="pinCode" name="pinCode"></input>
                <span class="error" id="pinCodeError"></span>

            </div>
            <br>
            <div>
                <label for="propertyImage" class="form-label">Add Property Images</label>
                <input type="file" class="form-control" id="propertyImage" name="multipartFile" accept="image/*"></input>
            </div>
            <br>
            <div class="mb-3 text-center">
                <input type="submit" class="btn btn-outline-danger" value="Submit" />
                <input type="reset" class="btn btn-outline-dark" value="Clear" onclick="clearForm()" />
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

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
    <script>

        function validateForm() {
            var pType = document.getElementById("pType").value;
            var oName = document.getElementById("oName").value;
            var sFeet = document.getElementById("sFeet").value;
            var cost = document.getElementById("cost").value;
            var loc = document.getElementById("loc").value;
            var pinCode = document.getElementById("pinCode").value;
            var isValid = true;

            // Property Type Validation
            if (pType.trim() === "") {
                document.getElementById("pTypeError").innerText = "Please enter property type.";
                isValid = false;
            } else {
                document.getElementById("pTypeError").innerText = "";
            }
            // Square Feet Validation
            if (sFeet.trim() === "") {
                document.getElementById("sFeetError").innerText = "Please enter square feet.";
                isValid = false;
            } else {
                document.getElementById("sFeetError").innerText = "";
            }

            // Price Validation
            if (cost.trim() === "") {
                document.getElementById("costError").innerText = "Please enter price.";
                isValid = false;
            } else {
                document.getElementById("costError").innerText = "";
            }

            // Location Validation
            if (loc.trim() === "") {
                document.getElementById("locError").innerText = "Please enter location.";
                isValid = false;
            } else {
                document.getElementById("locError").innerText = "";
            }

            // Pin Code Validation
            if (pinCode.trim() === "") {
                document.getElementById("pinCodeError").innerText = "Please enter pin code.";
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

</body>

</html>
