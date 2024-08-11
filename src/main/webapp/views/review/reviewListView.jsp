<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600|Open+Sans:400,600,700" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/spur.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
    <script src="resources/js/chart-js-config.js"></script>
    <title>관리자페이지</title>
</head>

<body>
    <div class="dash">
        <div class="dash">
            <div class="dash-nav dash-nav-dark">
                <header>
                    <a href="admin.ad" class="menu-toggle">
                        <i class="fas fa-bars"></i>
                    </a>
                    <a href="admin.ad" class="spur-logo"><i class="fas fa-bolt"></i> <span>관리자 페이지</span></a>
                </header>
                <nav class="dash-nav-list">
                    <a href="admin.ad" class="dash-nav-item">
                        <i class="fas fa-home"></i>대시보드</a>
                    <div class="dash-nav-dropdown">
                        <a href="memberList.ad" class="dash-nav-item">
                            <i class="fas fa-chart-bar"></i>회원관리
                        </a>
                    </div>
                    <div class="dash-nav-dropdown">
                        <a href="productList.ad" class="dash-nav-item">
                            <i class="fas fa-chart-bar"></i>상품관리
                        </a>
                    </div>
                    <div class="dash-nav-dropdown">
                        <a href="reviewList.ad" class="dash-nav-item">
                            <i class="fas fa-chart-bar"></i>리뷰관리
                        </a>
                    </div>
                    <div class="dash-nav-dropdown">
                        <a href="inquiryList.ad" class="dash-nav-item">
                            <i class="fas fa-chart-bar"></i>문의답변
                        </a>
                    </div>
                    <div class="dash-nav-dropdown">
                        <a href="noticeList.ad" class="dash-nav-item">
                            <i class="fas fa-chart-bar"></i>공지사항
                        </a>
                    </div>
                </nav>
            </div>
        <div class="dash-app">
            <main class="dash-content">
                <div class="container-fluid">
                    <h1 class="dash-title">리뷰관리</h1>
                    <div class="row">
                        <div class="col-lg-25">
                            <div class="card spur-card">
                                <div class="card-body ">
                                    <table class="table table-in-card">
                                        <thead>
                                            <tr>
                                                <th scope="col">리뷰번호</th>
                                                <th scope="col">내용</th>
                                                <th scope="col">리뷰이미지수정명</th>
                                                <th scope="col">파일경로</th>
                                                <th scope="col">작성일</th>
                                                <th scope="col">회원번호</th>
                                                <th scope="col">상품번호</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>Mark</td>
                                                <td>Otto</td>
                                                <td>@mdo</td>
                                                <td>@mdo</td>
                                                <td>@mdo</td>
                                                <td>@mdo</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">2</th>
                                                <td>Jacob</td>
                                                <td>Thornton</td>
                                                <td>@fat</td>
                                                <td>@fat</td>
                                                <td>@fat</td>
                                                <td>@fat</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">3</th>
                                                <td>Larry</td>
                                                <td>the Bird</td>
                                                <td>@twitter</td>
                                                <td>@twitter</td>
                                                <td>@twitter</td>
                                                <td>@twitter</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>Mark</td>
                                                <td>Otto</td>
                                                <td>@mdo</td>
                                                <td>@mdo</td>
                                                <td>@mdo</td>
                                                <td>@mdo</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">2</th>
                                                <td>Jacob</td>
                                                <td>Thornton</td>
                                                <td>@fat</td>
                                                <td>@fat</td>
                                                <td>@fat</td>
                                                <td>@fat</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">3</th>
                                                <td>Larry</td>
                                                <td>the Bird</td>
                                                <td>@twitter</td>
                                                <td>@twitter</td>
                                                <td>@twitter</td>
                                                <td>@twitter</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="resources/js/spur.js"></script>
</body>

</html>