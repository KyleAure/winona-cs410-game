<!DOCTYPE html>
<html>
<head>
	<title>Transition for Team Project</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	
</head>
<body>
	<h1>Transition for Team Project</h1>
	<hr>
	<h2>Table of Contents</h2>
	<ul>
		<li><a href="#Overview">Overview</a></li> 
		<li><a href="#UsersGuide">Users Guide</a></li>
		<li><a href="#WorkingApplicationCode">Working Application Code</a></li> 
		<li><a href="#TestCode">Test Code</a></li> 
		<li><a href="#ContributionsSummary">Contributions Summary</a></li> 
	</ul>
	<?PHP		
		foreach (glob("*.html") as $filename) {
			   echo "<hr>\n"; 
			   $filecontents = file_get_contents($filename); 
		       echo "$filecontents";
		}
	?>	
	<hr>
	<p>This section intentionally left blank. </p> 
	<pre>

	</pre>
	<p>END OF THE DOCUMENT</p>
	<hr>
</body>
</html>
