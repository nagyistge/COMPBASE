var varMetaElements = [];
function addJob(element) {
	$("#jobOverview").append("<li><a href='#' >" + $('#jobName').val() + " <span class='badge'>0</span></a></li>");

		$.ajax("php/handleDb.php", {
			type:"post",
			data: {
				loadPurpose: "saveCampaign",
				Name: $('#jobName').val()
			},
			success: function (res) {
				console.log(res);
				usableNavbar();
				
			},
			error: function(e) {
				console.log(e);
			}

		});
	$("#jobName").val("");
}

function addStichVar(element) {
	console.log(element);
	if (element.id == "addr0") {
		var stich = $("input[name='stich0']").val();
		var vari = $("input[name='var0']").val();
		var meta = $("input[name='meta0']").val();

		varMetaElements.unshift({Stich: stich, Var: vari, Meta:meta});
		console.log(varMetaElements);

		//save this element
		$.ajax("php/handleDb.php", {
			type:"post",
			data: {
				loadPurpose: "saveStichVarMeta",
				stich: stich,
				vari: vari,
				meta: meta,
				campaign: $("#addr0").attr("name")
			},
			success: function (res) {
				console.log(res);
				
			},
			error: function(e) {
				console.log(e);
			}

		});
		
		//write list to table
		elementsToTab();
		$("input[name='stich0']").val("");
		$("input[name='var0']").val("");
		$("input[name='meta0']").val("");

	}
}


function elementsToTab() {
	$("#varMetaTable").find("tr:gt(1)").remove();
	for (var i = 0; i < varMetaElements.length; i++) {
		$("#varMetaTable").append(
				"<tr><td>" + (i + 1) + "</td><td>" + varMetaElements[i].Stich + "</td><td>" + varMetaElements[i].Var + "</td>"
				+ "<td>" + varMetaElements[i].Meta + "</td></tr>");
	}
}
