<style>
/*omdat je natuurlijk niks met een jsp kan includen we dit style effectt  
in de header zomdat we gebruik kunnen maken van een css image */
body {
	padding-top: 20px;
	padding-bottom: 20px;
	/* Location of the image */
	background: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 1)),
		url("${pageContext.request.contextPath}/images/background.jpg");
	background-position: center center;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
}
</style>