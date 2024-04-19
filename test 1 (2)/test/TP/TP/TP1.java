public class TP1{
	
	public static void foo(HttpRequest Request, HttpResponse Response)
	{
		String query = "SELECT * FROM user_data WHERE acountNumber = " + accountNumber; 
		
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet results = statement.executeQuery(query);
		String dbData = results.getString(1);
		
		Response.Write(dbData);
	}
}