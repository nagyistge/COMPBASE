// implements client site events for lernprojekte tab
$("[id$='createLearningGoalPanel']").hide();

$("[id$='addLearningGoalButton']").click(function() {
	$("[id$='createLearningGoalPanel']").show();
});

$("[id$='cancelCreatingLearningGoal']").click(function() {
	$("[id$='createLearningGoalPanel']").hide();
});


