$.fn.qtip.styles.preview = {// Last part is the name of the style
    background: "white",
    opacity: 1,        
    padding: '30px',    
//    "-moz-opacity":1.0,
//    "-moz-border-radius": "15px",
    "padding-top": "60px",    
    margin: "auto",
    
    border: {
         width: 3,
         radius: 15,
         color: '#6699CC'
      },
    
    width: { min: 400 } ,
    height: { min: 400 } ,
    tip: 'rightMiddle',
    name: 'cream' // Inherit the rest of the attributes from the preset dark style
}

/**
 * Diese Funktion hängt eine PreviewSeite an
 * 
 * @param page_url
 *            die Seite die geladen werden soll
 * @param selector
 *            um einen Teil der Seite auszuscheinden
 * @param divwhereto
 *            die id des elements, wo die seite angehängt werden soll
 */
function preview(page_url, selector, divwhereto) {
    $.get(page_url, function(data) {
        $(divwhereto).qtip({
            content: $(data).find(selector).html(),
            style: {
                tip: false, // Give it a speech bubble tip with automatic corner detection                
                name: 'preview'
            }
        });
    })
}

//function preview(page_url,selector,divwhereto) {
//	$.get(page_url, function(data){
//		   $(divwhereto).html($(data).find(selector).html());
//	})	
//}
//
//function removePreview(divwhereto) {
//	$(divwhereto).remove();
//}

function previewdebug(page_url, selector, divwhereto) {
    $(divwhereto).qtip({
        content: 'Some basic content for the tooltip' + page_url, // Give it some content,        
        style: {
            tip: false, // Give it a speech bubble tip with automatic corner detection                
            name: 'preview'
        }
    });

}

// adding some random stuff

