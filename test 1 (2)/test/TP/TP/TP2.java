public class TP1{
	
	public static void foo(HttpRequest Request, HttpResponse Response)
	{
		String query = "SELECT * FROM user_data WHERE acountNumber = " + accountNumber; 
		
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet results = statement.executeQuery(query);
		String dbData = results.getTimeStamp(1); //the getTimeStamp does not exists in ResultSet class. Should be getTimestamp
		
		Response.Write(dbData);
	}
}