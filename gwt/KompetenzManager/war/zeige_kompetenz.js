//$(window).ready(function() {                 
function getJSON() {
	$.getJSON('TestData.json', function(json) {                                              
        var data = eval(json);        
        var graph = appendGraph('canvas',$(document).width() - 20, $(document).height() - 60, data);                 
    //        for (var i = 0;i<data.nodes.length;i++) {            
    //            var id = data.nodes[i].node;
    //            var elem = document.getElementById(id);
    //            
    //            //            alert(data.nodes[i].node);             
    //        }                
    });      
    function clicked(elem) {
        var test = elem;
        alert(test.toString());
    }
}






