<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <title>Man In Black</title>
    <!-- swiper.js 라이브러리추가 (cdn) -->
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
    <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
    <style>
    
        #content {
            height: 596px;
        }

        #content>div {
            width: 100%;
            height: 100%;
        }

        div {
            box-sizing: border-box;
        }

        /* 이미지 영역 사이즈 조절 */
        .swiper {
            width: 100%;
            height: 100%;
        }

        /* 이미지 사이즈 조절 */
        .swiper-slide>img {
            width: 100%;
            height: 100%;
        }

        /* 화살표 버튼색 변경 (기본색은 파란색) */
        div[class^=swiper-button] {
            color: white;
            /* display : none; */
            /* 아니면 안보이게 숨기기도 가능 */
        }
        
        
    </style>
</head>
<body>
    <div class="wrap">
        <div id="content">
            <div id="content_1">
                <!-- Slider main container -->
                <div class="swiper">
                    <!-- Additional required wrapper -->
                    <div class="swiper-wrapper">
                        <!--  해당 슬라이드 이미지에 공지사항 href 하기 -->
                        <div class="swiper-slide"><img src="resources/images/content_image1.jpg" onclick="location.href='detail.bo?bno=1'"></div>
                        <div class="swiper-slide"><img src="resources/images/content_image2.jpg" onclick="location.href='detail.bo?bno=2'"></div>
                        <div class="swiper-slide"><img src="resources/images/content_image3.jpg" onclick="location.href='detail.bo?bno=3'"></div>
                        <div class="swiper-slide"><img src="resources/images/content_image4.jpg" onclick="location.href='detail.bo?bno=4'"></div>
                        <div class="swiper-slide"><img src="resources/images/content_image5.jpg" onclick="location.href='detail.bo?bno=5'"></div>
                    </div>
                    <!-- If we need pagination -->
                    <div class="swiper-pagination"></div>
                    <!-- If we need navigation buttons -->
                    <div class="swiper-button-prev"></div>
                    <div class="swiper-button-next"></div>
                    <!-- If we need scrollbar -->
                    <div class="swiper-scrollbar"></div>
                </div>
            </div>
        </div>
    </div>
    <script>
        // 슬라이더 동작 정의
        const swiper = new Swiper('.swiper', {
            autoplay: {
                delay: 3500
            },
            loop: true, //반복 재생 여부
            slidesPerView: 1, // 이전, 이후 사진 미리보기 갯수
            pagination: { // 페이징 버튼 클릭 시 이미지 이동 가능
                el: '.swiper-pagination',
                clickable: true
            },
            navigation: { // 화살표 버튼 클릭 시 이미지 이동 가능
                prevEl: '.swiper-button-prev',
                nextEl: '.swiper-button-next'
            }
        }); 
    </script>
</body>
</html>