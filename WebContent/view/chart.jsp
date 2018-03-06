<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<title>Doughnut Chart</title>
	<script src="/HugHug2/assets/assets_chart/js/Chart.bundle.js"></script>
	<script src="/HugHug2/assets/assets_chart/js/utils.js"></script>
	
	<style>
.center {
    margin: auto;
    width: 50%;
    border: 3px solid green;
    padding: 30px;
}

	</style>
</head>

<body>
	<div class="center">
		<canvas id="weekly-chart-area" height="450" width="450"></canvas>
	</div>
	
	<div class="center">
		<canvas id="monthly-chart-area" height="450" width="450"></canvas>
	</div>
	
	<script>
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
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor()
					],
					fill: false,
				}]
			},
			options: {
				// responsive: false를 하지 않으면 크기가 반응형으로 적용됩니다.
				responsive: false,
				title: {
					display: true,
					fontFamily: 'Helvetica',
					fontSize: 30,
					fontStyle: 'bold',
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
							labelString: '7 diarys'
						}
					}],
					yAxes: [{
						display: true,
						scaleLabel: {
							display: true,
							fontSize: 15,
							labelString: 'emotions'
						}
					}]
				}
			}
		};

		// 월간 도넛 차트
		var randomScalingFactor = function() {
			return Math.round(Math.random() * 100);
		};

		var month_config = {
			type: 'doughnut',
			data: {
				datasets: [{
					data: [
						/* 감정 별 비율이 이곳에 들어갑니다. */
						randomScalingFactor(),
						randomScalingFactor(),
						randomScalingFactor(),
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
				responsive: false,
				legend: {
					position: 'top',
				},
				title: {
					display: true,
					fontFamily: 'Helvetica',
					fontSize: 30,
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
