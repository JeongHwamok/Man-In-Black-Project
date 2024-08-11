<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.text.DecimalFormat, java.util.HashMap, java.util.ArrayList"%>

<%
	int countMember = (int)(request.getAttribute("countMember"));
	int countProduct = (int)(request.getAttribute("countProduct"));
	HashMap<String, Integer> salesBymonth = (HashMap<String, Integer>)(request.getAttribute("salesBymonth"));
	
	ArrayList<Integer> salesBymonthList = new ArrayList<Integer>();
	for(int i = 1; i <= 12; i++){
		salesBymonthList.add(salesBymonth.get(String.valueOf(i)));
	}
	
	DecimalFormat formatter = new DecimalFormat("#,###"); // 가격 , 붙여서 포메팅해주는 객체 
	String yearSales = formatter.format(request.getAttribute("yearSales"));
	
	HashMap<Integer, ArrayList<Integer>> salesTop5 = (HashMap<Integer, ArrayList<Integer>>)(request.getAttribute("salesTop5"));
%>
<!doctype html>
<html lang="en">

<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
        integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600|Open+Sans:400,600,700" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/spur.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
    <script src="resources/js/chart-js-config.js"></script>
    <title>관리자페이지</title>
</head>

<body>

    <%@ include file="common/adminCommon.jsp" %>

        <div class="dash">
            <div class="dash-nav dash-nav-dark">
                <header>
                    <a href="admin.ad" class="menu-toggle">
                        <i class="fas fa-bars"></i>
                    </a>
                    <a href="admin.ad" class="spur-logo"><i class="fas fa-bolt"></i> <span>관리자 페이지</span></a>
                </header>
                <nav class="dash-nav-list">
                    <a href="<%=request.getContextPath()%>" class="dash-nav-item">
                        <i class="fas fa-home"></i>메인페이지로
                    </a>
                    <a href="admin.ad" class="dash-nav-item">
                        <i class="fas fa-home"></i>대시보드
                    </a>
                    <a href="memberList.ad" class="dash-nav-item">
                        <i class="fas fa-chart-bar"></i>회원관리
                    </a>
                    <a href="productList.ad" class="dash-nav-item">
                        <i class="fas fa-chart-bar"></i>상품관리
                    </a>
                    <a href="orderList.ad" class="dash-nav-item">
                        <i class="fas fa-chart-bar"></i>주문관리
                    </a>
                    <a href="reviewList.ad" class="dash-nav-item">
                        <i class="fas fa-chart-bar"></i>리뷰관리
                    </a>
                    <a href="inquiryList.ad" class="dash-nav-item">
                        <i class="fas fa-chart-bar"></i>문의답변
                    </a>
                    <a href="noticeList.ad" class="dash-nav-item">
                        <i class="fas fa-chart-bar"></i>공지관리
                    </a>
                </nav>
            </div>
            <div class="dash-app">
        
               

                <main class="dash-content">
                    <div class="container-fluid">
                        <div class="row dash-row">
                            <div class="col-xl-4">
                                <div class="stats stats-primary">
                                    <h3 class="stats-title"> 회원수 </h3>
                                    <div class="stats-content">
                                        <div class="stats-icon">
                                            <i class="fas fa-user"></i>
                                        </div>
                                        <div class="stats-data">
                                            <div class="stats-number"><%= countMember %></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4">
                                <div class="stats stats-success ">
                                    <h3 class="stats-title"> 당해 매출액 </h3>
                                    <div class="stats-content">
                                        <div class="stats-icon">
                                            <i class="fas fa-cart-arrow-down"></i>
                                        </div>
                                        <div class="stats-data">
                                            <div class="stats-number">₩<%= yearSales %></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4">
                                <div class="stats stats-danger">
                                    <h3 class="stats-title"> 상품수 </h3>
                                    <div class="stats-content">
                                        <div class="stats-icon">
                                            <i class="fas fa-box"></i>
                                        </div>
                                        <div class="stats-data">
                                            <div class="stats-number"><%= countProduct %></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="card spur-card">
                                    <div class="card-header">
                                        <div class="spur-card-icon">
                                            <i class="fas fa-chart-bar"></i>
                                        </div>
                                        <div class="spur-card-title"> 월별 매출액 </div>
                                        <div class="spur-card-menu">
                                        </div>
                                    </div>
                                    <div class="card-body spur-card-body-chart">
                                        <canvas id="monthlyRevenueChart"></canvas>
                                        <script>
                                            var ctx = document.getElementById("monthlyRevenueChart").getContext('2d');
                                            var myChart = new Chart(ctx, {
                                                type: 'bar',
                                                data: {
                                                    labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
                                                    datasets: [{
                                                        label: 'Blue',
                                                        data: <%= salesBymonthList %>,
                                                        backgroundColor: window.chartColors.primary,
                                                        borderColor: 'transparent'
                                                    }]
                                                },
                                                options: {
                                                    legend: {
                                                        display: false
                                                    },
                                                    scales: {
                                                        yAxes: [{
                                                            ticks: {
                                                                beginAtZero: true
                                                            }
                                                        }]
                                                    }
                                                }
                                            });
                                        </script>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card spur-card">
                                    <div class="card-header">
                                        <div class="spur-card-icon">
                                            <i class="fas fa-chart-bar"></i>
                                        </div>
                                        <div class="spur-card-title"> 판매량 TOP 5 </div>
                                        <div class="spur-card-menu">
                                        </div>
                                    </div>
                                    <div class="card-body spur-card-body-chart">
                                        <canvas id="salesVolumeChart"></canvas>
                                        <script>
                                            var ctx = document.getElementById("salesVolumeChart").getContext('2d');
                                            var myChart = new Chart(ctx, {
                                                type: 'bar',
                                                data: {
                                                	labels: ["상품번호 : <%= salesTop5.get(1).get(0) %>", "상품번호 : <%= salesTop5.get(2).get(0) %>", "상품번호 : <%= salesTop5.get(3).get(0) %>", "상품번호 : <%= salesTop5.get(4).get(0) %>", "상품번호 : <%= salesTop5.get(5).get(0) %>"],
                                                    datasets: [{
                                                        label: 'Blue',
                                                        data: [<%= salesTop5.get(1).get(1) %>, <%= salesTop5.get(2).get(1) %>, <%= salesTop5.get(3).get(1) %>, <%= salesTop5.get(4).get(1) %>, <%= salesTop5.get(5).get(1) %>],
                                                        backgroundColor: window.chartColors.primary,
                                                        borderColor: 'transparent'
                                                    }]
                                                },
                                                options: {
                                                    legend: {
                                                        display: false
                                                    },
                                                    scales: {
                                                        yAxes: [{
                                                            ticks: {
                                                                beginAtZero: true
                                                            }
                                                        }]
                                                    }
                                                }
                                            });
                                        </script>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
        <script src="resources/js/spur.js"></script>
</body>

</html>