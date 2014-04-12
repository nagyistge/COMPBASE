function preview(page_url,selector,divwhereto) {
	$.get(page_url, function(data){
		   $(divwhereto).html($(data).find(selector).html());
	})	
}

function removePreview(divwhereto) {
	$(divwhereto).remove();
}