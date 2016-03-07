<?php
	include("config.php");
	include("database.php");

	$database = new Database($db_url, $db_username, $db_password, $db_scheme);

	$mysql_Result;
	switch($_POST['loadPurpose']) {
		case "overview":
			$mysql_Result = $database->getOverview();
			echo json_encode($mysql_Result);
			break;
		case "saveCampaign":
			$database->newCampaign($_POST['Name']);
			$database->createTable($_POST['Name']);
			break;
		case "saveStichVarMeta":
			print_r($_POST);
			$database->saveStichVarMeta($_POST['stich'], $_POST['vari'], $_POST['meta'], $_POST['campaign']);
			break;
		case "loadStichVarMeta":
			$mysql_Result = $database->loadStichVarMeta($_POST['campaign']);
			echo json_encode($mysql_Result);
			break;
	}
?>
