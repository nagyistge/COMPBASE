// implements client site events for lernprojekte tab

$("[id$='createLearningGoalPanel']").hide();

$("[id$='addLearningGoalButton']").click(function() {
	$("[id$='createLearningGoalPanel']").show();
});

$("[id$='cancelCreatingLearningGoal']").click(function() {
	$("[id$='createLearningGoalPanel']").hide();
});

$("[id$='saveLearningGoal']").click(function() {
	setTimeout(function() {
		location.reload();
	}, 300);
});

$( "span:contains('löschen')").click(function() {
	setTimeout(function() {
		location.reload();
	}, 300);
});



