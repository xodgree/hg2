<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Doughnut Chart</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="/HugHug2/assets/assets_chart/js/Chart.bundle.js"></script>
	<script src="/HugHug2/assets/assets_chart/js/utils.js"></script>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
	<style>
	canvas{
		-moz-user-select: none;
		-webkit-user-select: none;
		-ms-user-select: none;
	}
	.center {
	    margin: auto;
	    width: 50%;
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

<body class="txt_ko">
	<h1 class="chart_title">지금까지 나는</h1>
	<div class="w3-padding center">
		<div class="w3-padding">
			<canvas id="weekly-chart-area" width="50%"></canvas>
		</div>
		<div class="w3-margin"></div>
		<div class="w3-padding">
			<canvas id="monthly-chart-area" width="50%"></canvas>
		</div>
	</div>
	<script>
		var weeklydatas = JSON.parse('${weeklyGraphList}');
		var monthlydatas = JSON.parse('${monthlyGraphList}');
		
		console.log(weeklydatas);
		var weeklyregdate = [];
		var weeklyemotion = [];
		var monthlyScore = [];

		for(var i = 0; i < Object.keys(weeklydatas).length; i++) {
			var obj = weeklydatas[i];
			
			weeklyregdate.push(obj["regdate"]);
			
			var emo = obj["emotion"];
			
			if(emo == "기쁨") {
				weeklyemotion.push(50);
			} else if(emo == "보통") {
				weeklyemotion.push(30);
			} else if(emo == "나쁨"){
				weeklyemotion.push(10);
			}
		}
		
		console.log(weeklyregdate);
		console.log(weeklyemotion);
		
		for(var i = 0; i < Object.keys(monthlydatas).length; i++) {
			monthlyScore.push(monthlydatas[i]);
			console.log(monthlyScore);
		}
		
		var week_config = {
			type: 'line',
			data: {
				labels: [],
				datasets: [],
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
					text: '최근'
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
					labels: [
						'좋아요',
						'괜찮아요',
						'나빠요',
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
						text: '모두',
					},
					animation: {
						animateScale: true,
						animateRotate: true
					}
				}
			};
		
		var ctx_week = document.getElementById('weekly-chart-area').getContext('2d');
		var ctx_month = document.getElementById('monthly-chart-area').getContext('2d');
		window.myLine = new Chart(ctx_week, week_config);
		window.myDoughnut = new Chart(ctx_month, month_config);
		
		window.onload = function() {
			/* ******* weekly ******* */
			
			week_config.data.labels = weeklyregdate;
			week_config.data.datasets.forEach(function(dataset) {
				dataset.data.push(randomScalingFactor());
			});
			
			var newWeekDataset = {
					label: '감정 곡선',
					backgroundColor: '#fcc',
					borderColor: '#fcc',
					data: [],
					fill: false,
			};
			
			for(var i = 0; i < weeklyemotion.length; i++) {
				console.log(weeklyemotion[i]);
				newWeekDataset.data.push(weeklyemotion[i]);
			}
			
			week_config.data.datasets.push(newWeekDataset);
			
			/* ******* monthly ******* */
			
			var newMonthDataset = {
						data: [],
						backgroundColor: [
							window.chartColors.blue,
							window.chartColors.orange,
							window.chartColors.green,
						],
						label: 'Dataset 1',
			};
			
			for(var i = 0; i < monthlyScore.length; i++) {
				console.log(monthlyScore[i]);
				newMonthDataset.data.push(monthlyScore[i]);
			}

			month_config.data.datasets.push(newMonthDataset);

			window.myLine.update();
			window.myDoughnut.update();
		};
	</script>
	
	<div class="w3-margin"></div>
	<div class="w3-row w3-container">
		<div class="w3-col w3-container" style="width:30%"></div>
		<div class="w3-col w3-container" style="width:40%; text-align:center">
			<button class="w3-button w3-sand" onclick="window.location.href='Main'"><b>Main</b></button>
		</div>
		<div class="w3-col w3-container" style="width:30%"></div>
	</div>
	<div class="w3-margin"></div>
</body>

</html>
