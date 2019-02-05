<!DOCTYPE html>
<html>
<head>
	<title>Orange Team Project</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
	<link rel="icon" href="images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="style.css" />
</head>
<body>
	<table>
		<tr>
			<td style='width: 30%;'>
				<img class = 'newappIcon' src='images/orange.png'>
			</td>
			<td>
				<h1 id = "message"><?php echo "Welcome to the Orange Team project for Winona CS410"; ?></h1>
				<p class='description'> 
					Thanks for visiting.
				</p>
				<p>
					Here are the project milestones
				</p>
				<ul>
					<li><a href="/Milestones/Inception">Inception</a></li>
					<li><a href="/Milestones/Elaboration1">Elaboration1</a></li>
					<li><a href="/Milestones/Elaboration2">Elaboration2</a></li>
					<li><a href="/Milestones/Construction1">Construction1</a></li>
					<li><a href="/Milestones/Construction2">Construction2</a></li>
					<li><a href="/Milestones/Transition">Transition</a></li>
					<li><a href="/Milestones/Presentation">Presentation</a></li>
				</ul>
			</td>
		</tr>
	</table>
	<h2 id = "message">Introductions for this team</h1>
	<p class='description'>
		Create your introduction by creating a web page for your introduction and then adding it to the list below. 
		Please add in alphabetical order by your first name.
	</p>
	<h2>Class List</h2>
	<ol>
	  <li><a href="JohnEberhard.php">John Eberhard</a></li>
	  <li><a href="KyleAure.php">Kyle Aure</a></li>
	  <li><a href="TristinHarvell.php">Tristin Harvell</a></li>
	  <li><a href="ErikaTix.php">Erika Tix</a></li>  
	</ol>	
	<h1>PhP Generated list</h1>
	<table border="1" align="left">
		<tr>
		<th>Student</th>
		<th>Picture</th>
		</tr>
		<?PHP				
		foreach (glob("*.php") as $filename) {
			if ( 0 != strcmp("$filename","index.php")) {
			if ( 0 != strcmp("$filename","connect.php")) {
				
			$name = preg_replace("/.php/","","$filename")	;
			$filecontents = file_get_contents("$filename"); 
			
			echo "<tr><td><a href=\"$filename\">$name</a>   </td>";

			$filecontents = file_get_contents("$filename");
			// echo '<!--';  
			// echo "$filecontents";  
			// echo '-->'; 
			$imagelink = preg_replace('/^.*"(images.[^"]*)".*/s','$1',"$filecontents");
			echo "<td><img src=\"$imagelink\" alt=\"$name\" style=\"height:100px\"></td>"; 
			
			echo "</tr>";
			}
			}
		}	
		?>	
	</table>
</body>
</html>
