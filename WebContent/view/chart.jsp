<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<title>Doughnut Chart</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="/HugHug2/assets/assets_chart/js/Chart.bundle.js"></script>
	<script src="/HugHug2/assets/assets_chart/js/utils.js"></script>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
	<style>
	.center {
	    margin: auto;
	    width: 50%;
	    border: 3px solid green;
	    padding: 30px;
	}
	body {
		background-color: #fff;
		background-image: url("../assets/assets_common/images/bg.jpg");
		background-size: cover;
		background-position: top center;
	}
	@font-face {
		font-family: NanumBarunpenR;
  		src: url('../assets/assets_common/fonts/NanumBarunpenR.ttf'); 
	}
	.chart_title {
		display: block;
		font-family: Poppins-Bold, NanumBarunpenR;
		font-size: 40px;
		color: white;
		line-height: 1.2;
		text-align: center;
		padding-top: 100px;
	}
	.txt_ko {
		font-family: NanumBarunpenR;
	}
	</style>
</head>

<script>
function addData() {
	var wChart = document.getElementById('weekly-chart-area');
}

function removeData(chart) {
	/* var wChart = document.getElementById('weekly-chart-area'); */
	wChart.data.labels.pop();
}
</script>

<body class="txt_ko">
	<h1 class="chart_title">통통해</h1>
	<div class="w3-padding center">
		<div class="w3-padding">
			
			<canvas id="weekly-chart-area" width="50%">
				
			</canvas>
		</div>
		
		<div class="w3-padding">
			<canvas id="monthly-chart-area" width="50%"></canvas>
		</div>
	</div>
	
	<button class="w3-button" onclick="removeData(document.getElementById('weekly-chart-area'));">gogo</button>
	
	<script>
		var weeklydatas = ${weeklyGraphList};
		var monthlydatas = ${monthlyGraphList};

		console.log(weeklydatas);
		console.log(monthlydatas);
		
		// 주간 선 차트
		var MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
		var week_config = {
			type: 'line',
			data: {
				labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
				datasets: [{
					label: 'Emotion Graph',
					backgroundColor: window.chartColors.green,
					borderColor: window.chartColors.green,
					data: [
						0,
						10,
						50,
						20,
						75,
						30,
						40
					],
					fill: false,
				}]
			},
			options: {
				legend: {
					labels: {
						fontColor: '#fff'
					}
				},
				// responsive: false를 하지 않으면 크기가 반응형으로 적용됩니다.
				/* responsive: false, */
				title: {
					display: true,
					fontFamily: 'Helvetica',
					fontSize: 30,
					fontStyle: 'bold',
					fontColor: '#fff',
					text: 'Weekly'
				},
				tooltips: {
					mode: 'index',
					intersect: false,
				},
				hover: {
					mode: 'nearest',
					intersect: true
				},
				scales: {
					xAxes: [{
						display: true,
						scaleLabel: {
							display: true,
 							fontSize: 15,
 							fontColor: '#fff',
							labelString: '7 diarys'
						},
						gridLines: {
							color:'#fff',
						},
						ticks: {
							fontColor: '#fff',
						}
					}],
					yAxes: [{
						display: true,
						scaleLabel: {
							display: true,
							fontSize: 15,
							fontColor: '#fff',
							labelString: 'emotions'
						},
						gridLines: {
							color:'#fff',
						},
						ticks: {
							fontColor: '#fff',
						}
					}]
				},
			}
		};

		var month_config = {
			type: 'doughnut',
			data: {
				datasets: [{
					data: [
						/* 감정 별 비율이 이곳에 들어갑니다. */
						6,
						3,
						1
					],
					backgroundColor: [
						window.chartColors.blue,
						window.chartColors.orange,
						window.chartColors.green,
					],
					label: 'Dataset 1',
				}],
				labels: [
					'Happy',
					'So so',
					'Sad',
				]
			},
			options: {
				/* responsive: false, */
				legend: {
					labels: {
						fontColor: '#fff'
					}
				},
				title: {
					display: true,
					fontFamily: 'Helvetica',
					fontSize: 30,
					fontColor: '#fff',
					fontStyle: 'bold',
					text: 'Monthly',
				},
				animation: {
					animateScale: true,
					animateRotate: true
				}
			}
		};

		window.onload = function() {
			var ctx_week = document.getElementById('weekly-chart-area').getContext('2d');
			var ctx_month = document.getElementById('monthly-chart-area').getContext('2d');
			
			window.myLine = new Chart(ctx_week, week_config);
			window.myDoughnut = new Chart(ctx_month, month_config)
		};
	</script>
</body>

</html>
