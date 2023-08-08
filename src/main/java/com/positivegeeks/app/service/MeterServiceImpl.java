package com.positivegeeks.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class MeterServiceImpl implements MeterService{

	public List<String> getMeterData(Long id, String startTime, String endTime) throws org.json.simple.parser.ParseException, ParseException {
        	
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
        try (FileReader reader = new FileReader("liczniki-db.json")) {
            Object obj = jsonParser.parse(reader);
//            JSONArray meterData = (JSONArray) jsonObject.get("MeterData");
            System.out.println(obj);
//            
//            File f = new File("liczniki-db.json");
//            InputStream is = new FileInputStream(f);
//            String jsonTxt = IOUtils.toString(is, "UTF-8");
//            System.out.println(jsonTxt);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	
}
