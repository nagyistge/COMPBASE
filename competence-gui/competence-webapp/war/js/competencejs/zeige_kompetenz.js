//$(window).ready(function() {                 
var myPaintedGraphMap = {};
var myFinalDataMap = {};

// function getJSON() {
// $.getJSON('TestData.json', function(json) {
// var data = eval(json);
// var graph = appendGraph('canvas', $(document).width() - 20, $(document)
// .height() - 60, data);
// paintedGraph = graph;
// finalData = data;
// });
// }

function setGraph(json,canvasId, width, height) {
	var data = eval(json);
	var graph = appendGraph(canvasId, width, height, data);
	myPaintedGraphMap[canvasId] = graph;
	myFinalDataMap[canvasId] = data;	
}

function existsNode(x, y, canvasId) {
	return (!(getNodeAtPosition(x, y,canvasId) == false));
}

function getNodeAtPosition(x, y,canvasId) {
	var finalData =  myFinalDataMap[canvasId];
	var paintedGraph = myPaintedGraphMap[canvasId];
	for ( var i = 0; i < finalData.nodes.length; i++) {
		var id = finalData.nodes[i].node;
		var nodeX = paintedGraph.nodes[id].point[0]; // todo insert js
		// position method
		var nodeY = paintedGraph.nodes[id].point[1];
		if (Math.abs(x - nodeX) < 20 && (Math.abs(nodeY-y ) < 20)) {
			var nodeId = paintedGraph.nodes[id];
			return nodeId;
		}
	}
	return false;
}

function getNodeIdAtPosition(x,y,canvasId) {
	var node = getNodeAtPosition(x,y,canvasId);
	return node.id;
}
