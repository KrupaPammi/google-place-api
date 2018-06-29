package files;

public class resources {

	public static String addPostData()
	{
		String res="/maps/api/place/add/json";
		return res;
	}
	
	public static String getNearbySearchPlace()
	{
		String res="/maps/api/place/nearbysearch/json";
		return res;
	}
	
	public static String deletePlace()
	{
		String res="/maps/api/place/delete/json";
		return res;
	}
}
