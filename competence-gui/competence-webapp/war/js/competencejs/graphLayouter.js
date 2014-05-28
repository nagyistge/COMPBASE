var redraw, g, renderer;
function appendGraph(id, width, height, data) {
    // daten aus json lesen und in Graph einspeisen        
    g = new Graph();
    for (var k = 0; k < data.graph.length; k++) {
        var st = {
            directed: data.graph[k].directed,
            label: data.graph[k].label,
            "label-style": {
                "font-size": 15
            }
        };
        var nodeId1 = data.graph[k].node1id;
        var nodeId2 = data.graph[k].node2id;
        g.addEdge(nodeId1, nodeId2, st);
    }
    /* layout the graph using the Spring layout implementation */
    var layouter = new Graph.Layout.Spring(g);
    /* draw the graph using the RaphaelJS draw implementation */
    renderer = new Graph.Renderer.Raphael(id, g, width, height);
//    layouter.layout();
//    renderer.draw();       
    return g;
}



    