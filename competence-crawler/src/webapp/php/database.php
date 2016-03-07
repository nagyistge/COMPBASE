
<?php
class database {
	private $db;
	private $con;
	private $creds = array();

	//Constructor
	function __construct($db_url, $db_username, $db_password, $db_scheme) {

		$this -> creds = array("url" => $db_url, "username" => $db_username, "password" => $db_password, "scheme" => $db_scheme);
	}

	private function ensure_connection() {
		if (!$this -> con || !$this -> db) {
			$this -> connect();
		}
		mysqli_query($this->con,"SET character_set_results = 'utf8', character_set_client = 'utf8', character_set_connection = 'utf8', character_set_database = 'utf8', character_set_server = 'utf8'");
	}

	private function connect() {
		$this -> con = new mysqli($this -> creds["url"], $this -> creds["username"], $this -> creds["password"],$this -> creds["scheme"]);
		//$this -> con = mysqli_connect($this -> creds["url"], $this -> creds["username"], $this -> creds["password"]);

		if (!$this -> con) {
			die("Cannot connect. " . mysqli_error($this -> con));
		}

		mysqli_query($this->con,"set names 'utf8'");
		// The database name selection.
		$this -> db = mysqli_select_db($this->con,$this -> creds["scheme"]);
		if (!$this -> db) {

			die("Cannot select database " . mysqli_error($this -> con));
		}
	}

	public function getOverview() {
		$query = "SELECT * from Overview";
		return $this->querySelect($query);
	}

	public function newCampaign($name) {
		$query= "INSERT INTO Overview(Name, Count) VALUE ('" . $name . "', 0)";
		$this->query($query);
	}

	public function createTable($name) {
		$query = "CREATE TABLE " . $name . "_Stichwort (Id MEDIUMINT NOT NULL AUTO_INCREMENT, Stichwort VARCHAR(50), Variable VARCHAR(50), Metavariable VARCHAR(50), PRIMARY KEY (Id))";
		return $this->query($query);
	}

	public function getStichVarMeta($camp) {
		$query = "SELECT * from " . $camp . "_Stichwort";
		return $this->querySelect($query);
	}

	public function saveStichVarMeta($stich, $var, $meta, $camp) {
		$query = "INSERT INTO " . $camp . "_Stichwort (Stichwort, Variable, Metavariable) VALUE ('" . $stich . "', '" . $var . "', '" . $meta . "')";
		$this->query($query);
	}
	public function loadStichVarMeta($camp) {
		$query = "SELECT * FROM " . $camp . "_Stichwort";
		return $this->querySelect($query);
	}

	private function querySelect($query) {
		$this->ensure_connection();
		mysqli_query($this->con,"SET NAMES 'utf8'");
		$result = mysqli_query($this->con, $query) or die ("Can't query: " + $query);
		return $this->resultToArray($result);
	}

	private function query($query) {
		$this->ensure_connection();
		mysqli_query($this->con,"SET NAMES 'utf8'");
		$result = mysqli_query($this->con, $query) or die ("Can't query: " + $query);
	}

	private function resultToArray($result) {
		$rows = [];
		while($row = $result->fetch_array()) {
			array_push($rows, $row);
		}
		return $rows;
	}
}
?>
