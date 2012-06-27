//$(window).ready(function() {                 
var paintedGraph;
var finalData;

// function getJSON() {
// $.getJSON('TestData.json', function(json) {
// var data = eval(json);
// var graph = appendGraph('canvas', $(document).width() - 20, $(document)
// .height() - 60, data);
// paintedGraph = graph;
// finalData = data;
// });
// }

function setGraph(json) {
	var data = eval(json);
	var graph = appendGraph('canvas', $(document).width() - 20, $(document)
			.height() - 60, data);
	paintedGraph = graph;
	finalData = data;
}

// TODO NULLpointer Exception
function existsNode(x, y) {
	return (!(getNodeAtPosition(x, y) == false));
}

function getNodeAtPosition(x, y) {
	for ( var i = 0; i < finalData.nodes.length; i++) {
		var id = finalData.nodes[i].node;
		var nodeX = paintedGraph.nodes[id].point[0]; // todo insert js
		// position method
		var nodeY = paintedGraph.nodes[id].point[1];
		if (Math.abs(x - nodeX) < 20 && (Math.abs(nodeY - y) < 20)) {
			return paintedGraph.nodes[i];
		}
	}
	return false;
}
