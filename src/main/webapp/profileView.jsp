<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="icon" href="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" type="icon/png" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
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
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark navbar-custom ">
        <div class="container-fluid">
            <img src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
                                style="height: 53px; width: 110px ;margin-left: 15vh;" alt="XWorkz Logo">

            <div class="navbar-collapse justify-content-end grid gap-3">
                    <a href="toViewProfile?id=${inforef.getRid()}" class="ml-md-3">
                        <button type="button" class="btn btn btn-outline-dark">Back</button>
                    </a>

                    <input value="${inforef.getFirstName()}" class="btn btn-secondary dropdown-toggle" type="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                    </input>
            </div>
        </div>
    </nav>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="center-align border-success shadow p-3 mb-5 bg-body-tertiary rounded">
                <table class="table">
                    <tr>
                        <th>First Name</th>
                        <td>${inforef.getFirstName()}</td>
                    </tr>
                    <tr>
                        <th>Last Name</th>
                        <td>${inforef.getLastName()}</td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td>${inforef.getEmail()}</td>
                    </tr>
                    <tr>
                        <th>Alternative Email</th>
                        <td>${inforef.getAlternativeEmail()}</td>
                    </tr>
                    <tr>
                        <th>Contact Number</th>
                        <td>${inforef.getContactNumber()}</td>
                    </tr>
                    <tr>
                        <th>Alternative Contact Number</th>
                        <td>${inforef.getAlternativeContact()}</td>
                    </tr>
                    <tr>
                        <th>Current Address</th>
                        <td>${inforef.getCurrentAddress()}</td>
                    </tr>
                    <tr>
                        <th>Permanent Address</th>
                        <td>${inforef.getPermanentAddress()}</td>
                    </tr>
                    <tr>
                        <th>Pan Card Number</th>
                        <td>${inforef.getPanCardNumber()}</td>
                    </tr>
                    <tr>
                        <th>Aadhar Number</th>
                        <td>${inforef.getAadharNumber()}</td>
                    </tr>
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

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>

</body>

</html>