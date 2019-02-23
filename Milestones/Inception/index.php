<!DOCTYPE html>
<html>
<head>
	<title>Inception Milestone for Team Project</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
	<h1>Inception Milestone for Team Project</h1>
	<hr>
	<h2>Table of Contents</h2>
	<ul>
		<li><a href="#Overview">Overview</a></li> 
		<li><a href="#Vision">Vision</a></li> 
		<li><a href="#UseCaseModel">Initial Use Case Model</a></li> 
		<li><a href="#Glossary">Glossary</a></li> 
		<li><a href="#SystemArchitecture">System Architecture</a></li> 
		<li><a href="#ContributionsSummary">ContributionsSummary</a></li> 
	</ul>
	<?PHP
				
		foreach (glob("*.html") as $filename) {
			   echo "<hr>\n"; 
			   $filecontents = file_get_contents($filename); 
		       echo "$filecontents";
		}
	?>	
	<hr>
	<p>END OF THE DOCUMENT</p>
	<hr>
</body>
</html>
