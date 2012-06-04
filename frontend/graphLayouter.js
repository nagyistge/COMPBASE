var redraw, g, renderer;
function appendGraph(id,width, height, data) {
    var width = width - 20;
    var height = height - 60;
   
    // daten aus json lesen und in Graph einspeisen        
    g = new Graph();
    for( var k=0; k<data.graph.length; k++ ) {
        st = {
            directed: data.graph[k].directed,
            label: data.graph[k].label,
            "label-style" : {
                "font-size": 20
            }            
        }
        g.addEdge(data.graph[k].node1, data.graph[k].node2,st);
       
    }             
  

    /* layout the graph using the Spring layout implementation */
    var layouter = new Graph.Layout.Spring(g);
    /* draw the graph using the RaphaelJS draw implementation */
    renderer = new Graph.Renderer.Raphael(id, g, width, height);
//    layouter.layout();
//    renderer.draw();       
    return g;
}
       
    