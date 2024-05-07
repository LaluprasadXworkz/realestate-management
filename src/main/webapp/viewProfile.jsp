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
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                crossorigin="anonymous" />
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
                integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
                crossorigin="anonymous" referrerpolicy="no-referrer" />
            <style>
                .navbar-custom {
                    background: linear-gradient(270deg, #ff5733, White, #3498db, #000000);
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

            <nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
                <img src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
                    style="height: 53px; width: 110px ;margin-left: 15vh;" alt="XWorkz Logo">

                <div class="collapse navbar-collapse justify-content-end grid gap-3" id="navbarNav">
                    <a href="viewProfileToBidding?id=${inforef.getRid()}">
                        <button type="button" class="btn btn btn-outline-dark mr-2">Bidding's</button>
                    </a>
                    <a href="viewProfileToSell?id=${inforef.getRid()}">
                        <button type="button" class="btn btn btn-outline-dark mr-2">Sell</button>
                    </a>
                    <div class="dropdown">

                        <input value="${inforef.getFirstName()}" class="btn btn-secondary dropdown-toggle" type="button"
                            data-bs-toggle="dropdown" aria-expanded="false" style="width: 30vh;margin-right: 50px;">
                        </input>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="view?id=${inforef.getRid()}"><i class="fa-solid fa-user"></i> View Profile</a></li>
                            <li><a class="dropdown-item" href="sold?id=${inforef.getRid()}"><i class="fa-solid fa-building"></i> Property Sold</a></li>
                            <li><a class="dropdown-item" href="buy?id=${inforef.getRid()}"><i class="fa-solid fa-building-circle-check"></i> Property Buy</a></li>
                            <li><a class="dropdown-item" href="update?id=${inforef.getRid()}"><i class="fa-regular fa-pen-to-square"></i> Update Profile</a></li>
                            <li><a class="dropdown-item" href="delete?id=${inforef.getRid()}"><i class="fa-solid fa-delete-left"></i> Delete Account</a></li>
                            <li><a class="dropdown-item" href="home"><i class="fa-solid fa-right-from-bracket"></i> Log out</a></li>
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
                <div style="display:flex;margin-left: 10%;" class="grid gap-3">
                    <c:forEach var="info" items="${infoList}">
                        <div class="card p-2 g-col-6" style="width: 18rem;">
                            <img src="display?imagepath=${info.getPropertyImage()}"
                                class="card-img-top" alt="...">
                            <div class="card-body">
                                <h6 class="card-title">Property Type : ${info.getPropertyType()}</h6>
                                <p class="card-text">Owner Name: ${info.getOwnerName()}, Square Feet:
                                    ${info.getSquareFeet()}, Price: ${info.getPrice()}, Location: ${info.getLocation()},
                                    Pin code: ${info.getPinCode()}</p>
                                <a href="bid?id=${info.getId()}"><button type="button" class="btn btn-outline-warning btn-sm">Bid</button></a>
                            </div>
                        </div>
                    </c:forEach>

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

            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
                integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
                crossorigin="anonymous"></script>
        </body>

        </html>