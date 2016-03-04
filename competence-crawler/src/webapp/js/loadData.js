
var LOADDATA = 1
var loaded = 0
function initialLoad() {
		$.ajax("php/handleDb.php", {
			type:"post",
			data: {
				loadPurpose: "overview",
			},
			success: function (res) {
				if (res.length > 0) {
					overviewObj = JSON.parse(res); 
					for (var i = 0; i < overviewObj.length; i++) {
						$("#jobOverview").append("<li><a id=" + overviewObj[i].Name + " href='#' >" + overviewObj[i].Name + " <span class='badge'>" +  overviewObj[i].Count + "</span></a></li>");
					}
				} else {
					console.log("No list elements found");
				}
				readyLoaded();
			},
			error: function(e) {
				console.log(e);
			}
		});
}

function readyLoaded() {
	loaded++;
	if (loaded == LOADDATA) {
		console.log("Loaded");
		elementsToTab();
		usableNavbar();
	}
}

function usableNavbar() {
	$(".nav a").on("click", function(){
		 $(".nav").find(".active").removeClass("active");
		 $(this).parent().addClass("active");
		 $("#addr0").attr("name", this.id);
		 $("#accordion").css("display", "");
		 loadCampaign(this);
	});	
}

function loadCampaign(element) {
		$.ajax("php/handleDb.php", {
			type:"post",
			data: {
				loadPurpose: "loadStichVarMeta",
				campaign: element.id,
			},
			success: function (res) {
				varMetaElements = [];
				if (res.length > 0) {
					var loadedCamp = JSON.parse(res); 
					for (var i = 0; i < loadedCamp.length; i++) {
						varMetaElements.push({Stich: loadedCamp[i].Stichwort, Var: loadedCamp[i].Variable, Meta: loadedCamp[i].Metavariable});
					}
				}
				elementsToTab();
			},
			error: function(e) {
				console.log(e);
			}
		});
	
	console.log(element);
}
