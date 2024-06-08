<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Profile</title>
    <link
      rel="icon"
      href="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
      type="icon/png"
    />
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
      .custom-navbar {
        background-image: linear-gradient(270deg, #ff5733, #3498db, #000000);
      }

      @media (max-width: 768px) {
        .grid {
          display: block;
        }
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
              <a href="homeToRegister" class="nav-link text-white ml-md-3"
                >Register</a
              >
            </li>
          </ul>
          <ul class="navbar-nav">
            <li class="nav-item">
              <a
                class="nav-link text-white ml-md-3"
                href="homeToLogin"
                tabindex="-1"
                aria-disabled="true"
                >Log in</a
              >
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div>
      <div class="card text-center">
        <div class="card-header">
          <marquee width="100%" direction="right">
            Invest wisely, live beautifully.
          </marquee>
        </div>
        <div
          class="card-body"
          style="
            background: linear-gradient(270deg, #ff5733, #3498db, #000000);
            color: white;
          "
        >
          <h5 class="card-title">
            Buy And Sell Your Residential, Commercial, Industrial, Raw land
            Easily
          </h5>
          <p class="card-text">
            The process of buying and selling a home, while not complicated,
            does involve many stages and processes. The buyer must be well
            advised to make what will be one of the most important purchases of
            his life.
          </p>
          <a
            href="https://www.doorloop.com/definitions/industrial-real-estate"
            class="btn btn btn-outline-dark"
            >Click here to see more</a
          >
        </div>
        <div class="card-footer">
          Real estate can be categorized into residential, commercial,
          industrial, and land
        </div>
      </div>

      <div class="container">
        <div class="row justify-content-center">
          <div class="card" style="width: 18rem">
            <img
              src="https://assets.entrepreneur.com/content/3x2/2000/1691095535-entrepreneur-real-estate-wealth-0823-g912235266.jpg"
              class="card-img-top"
              alt="..."
            />
            <div class="card-body">
              <p class="card-text">
                Property management is the operation, control, maintenance, and
                oversight of real estate and physical property. This can include
                residential, commercial, and land real estate.
              </p>
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
              style="height: 85px; width: 160px"
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
