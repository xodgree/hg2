<canvas id="myChart" width="300" height="200"></canvas>

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/Chart.js/0.2.0/Chart.min.js"></script>
<script>
var data = {
	labels: ["1월", "2월", "3월", "4월", "5월", "6월"],
	datasets: [
		{
			label: "My First dataset",
			fillColor: "rgba(150,200,250,0.5)",
			strokeColor: "rgba(150,200,250,0.8)",
			highlightFill: "rgba(150,200,250,0.75)",
			highlightStroke: "rgba(150,200,250,1)",
			data: [65, 59, 80, 81, 56, 55]
		}
	]
};
var options = {	animation: false };
var ctx = $('#myChart').get(0).getContext('2d');
var myBarChart = new Chart(ctx).Bar(data, options);
</script>
