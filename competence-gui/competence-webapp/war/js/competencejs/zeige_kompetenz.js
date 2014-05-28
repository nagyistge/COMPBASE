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

$.fn.qtip.styles.competenceNode = {// Last part is the name of the style
    textAlign: 'center',
    // tip: 'leftBottom',
    name: 'dark' // Inherit the rest of the attributes from the preset dark
            // style
}

function addTooltips(data) {
    var a = document.getElementById("canvas").getElementsByTagName("svg");
    for (var k = 0; k < data.graph.length; k++) {
        var label = data.graph[k].node1;
        $('#' + data.graph[k].node1id, a).qtip({
            content: label, // Give it some
            hide: {
                fixed: true, // Make it fixed so it can be hovered over
                when: {
                    event: 'unfocus'
                }
            },
            show: {
                delay: 800
            },
            style: 'competenceNode'
        });                
    }
    for (var k = 0; k < data.graph.length; k++) {
        var label = data.graph[k].node2;
        $('#' + data.graph[k].node2id, a).qtip({
            content: label, // Give it some
            hide: {
                fixed: true, // Make it fixed so it can be hovered over
                when: {
                    event: 'unfocus'
                }
            },
            show: {
                delay: 1000
            },
            style: 'competenceNode'
        });                
    }    
}
;

function setGraph(json, canvasId, width, height) {
    var data = eval(json);
    var graph = appendGraph(canvasId, width, height, data);
    myPaintedGraphMap[canvasId] = graph;
    myFinalDataMap[canvasId] = data;
    addTooltips(data);
}

function existsNode(x, y, canvasId) {
    return (!(getNodeAtPosition(x, y, canvasId) == false));
}

function getNodeAtPosition(x, y, canvasId) {
    var finalData = myFinalDataMap[canvasId];
    var paintedGraph = myPaintedGraphMap[canvasId];
    for (var i = 0; i < finalData.nodes.length; i++) {
        var id = finalData.nodes[i].node;
        var nodeX = paintedGraph.nodes[id].point[0]; // todo insert js
        // position method
        var nodeY = paintedGraph.nodes[id].point[1];
        if (Math.abs(x - nodeX) < 20 && (Math.abs(nodeY - y) < 20)) {
            var nodeId = paintedGraph.nodes[id];
            return nodeId;
        }
    }
    return false;
}

function getNodeIdAtPosition(x, y, canvasId) {
    var node = getNodeAtPosition(x, y, canvasId);
    return node.id;
}
