<html>
<body>
<h2>Hello World!</h2>

<p>Let's do some tricks with Java</p>

<p>The following number is the result of a server side calculation:
<%=simpleMath() %> </p>
</body>
</html>

<%!
	int simpleMath() {
		int n1 = 3;
		int n2 = 4;
		int result = n1 + n2;
		return result;
	}
%>