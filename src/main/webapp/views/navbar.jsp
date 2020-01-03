<jsp:include page="/views/externalLink.jsp"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/add">New Post</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="sortByDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Sort By
                </a>
                <div class="dropdown-menu" aria-labelledby="sortByDropdown">
                    <a class="dropdown-item" href="published-date">Published Date</a>
                    <a class="dropdown-item" href="last-updated">Last Date</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="filterDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Filter
                </a>
                <div class="dropdown-menu" aria-labelledby="filterDropdown">
                    <a class="dropdown-item" href="/filter/1">Technology</a>
                    <a class="dropdown-item" href="/filter/2">Science</a>
                    <a class="dropdown-item" href="/filter/3">Nature</a>
                    <a class="dropdown-item" href="/filter/4">Space</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="get" action="posts/search/">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" name="keyword"/>
            <input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="Search"/>
        </form>
        <a href="/logout">logout</a>
    </div>
</nav>