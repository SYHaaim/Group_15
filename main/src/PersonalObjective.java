
import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;


public class PersonalObjective{

	//changes to be made?

	private final String[] objectives;
	private static ArrayList<String> jsonKeys;

	public PersonalObjective() throws IOException {
		this.objectives = new String[6];
		jsonKeys = new ArrayList<>();
		generatePersonal();
	}

	//"x coord. - y coord. - tile type"
	public void generatePersonal() throws IOException {
		parseFromJson();
	}

	private void parseFromJson() throws IOException {
		populateListKeys();
		Random r = new Random();
		String jsonString = new String(Files.readAllBytes(Paths.get("resources/Cards.json")));
		JSONObject jsonObject = new JSONObject(jsonString);
		int keyIndex = (r.nextInt(11)+1);

		jsonObject = jsonObject.getJSONObject(jsonKeys.get(keyIndex));
		JSONArray jsonArray = jsonObject.getJSONArray("tiles");
		jsonKeys.remove(keyIndex);
		for (int i = 0; i<jsonArray.length();i++){
			objectives[i] = jsonArray.getString(i);
		}
	}
	void populateListKeys(){

		for (int i = 0; i < 12; i++){
			jsonKeys.add(("card " + i));
		}
	}
	public void printObj(){
		for (String obj : this.objectives)
			System.out.println(obj);

	}
}


