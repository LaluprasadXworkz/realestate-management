<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Registered</title>
    <link rel="icon" href="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" type="icon/png" />
    <link rel="icon" href="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" type="icon/png" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
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
    </style>
  </head>

  <body>
    <nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
      <div class="container-fluid">
        <img src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
                            style="height: 53px; width: 110px ;margin-left: 15vh;" alt="XWorkz Logo">

        <div
          class="collapse navbar-collapse justify-content-end"
          id="navbarNav"
          style="margin-right: 50px;"
        >
          <a href="home" class="ml-md-3">
            <button type="button" class="btn btn btn-outline-dark">Home</button>
          </a>
        </div>
      </div>
    </nav>

    <div class="container mt-5">
      <div class="d-flex justify-content-center">
        <div class="col-md-6">
          <div
            class="center-align border border-black shadow-lg p-3 mb-5 bg-body-tertiary rounded"
          >
          <div class="mb-3 text-center" >
            <h3>${message}</h3>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
