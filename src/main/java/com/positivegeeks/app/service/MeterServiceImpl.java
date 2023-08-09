package com.positivegeeks.app.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MeterServiceImpl implements MeterService{

	public JSONObject jObject = new JSONObject();
	
	public JSONObject getMeterData(String id, Date begin, Date end) throws ParseException {
		
		Map<String, String> map1 = new TreeMap<>();
		Map<String, String> map2 = new TreeMap<>();
		Map<String, String> map3 = new TreeMap<>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begin);

		int begHour = calendar.get(Calendar.HOUR_OF_DAY);
		int begMinute = calendar.get(Calendar.MINUTE);
		
		calendar.setTime(end);

		int endHour = calendar.get(Calendar.HOUR_OF_DAY);
		int endMinute = calendar.get(Calendar.MINUTE);
		
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String formattedBegin = null;
		formattedBegin = simple.format(begin);
		System.out.println(formattedBegin);
		
		String formattedEnd = null;
		formattedEnd = simple.format(end);
				
        try (FileReader reader = new FileReader("liczniki-db.json")) {
            String file = "liczniki-db.json";
            String json = new String(Files.readAllBytes(Paths.get(file)));
            String jsonString = json ; 
            
            JSONObject obj = new JSONObject(jsonString);
            JSONArray meterData = obj.getJSONArray("MeterData");
            Iterator<Object> iterator = meterData.iterator();
            int counter = 0;
            while (iterator.hasNext()) {
                JSONObject jsonObject = (JSONObject) iterator.next();

                String meterId = (String)jsonObject.get("id");

                JSONArray arr = new JSONArray();
                
                if(meterId.equals(id)) {
                	arr = (JSONArray)jsonObject.get("readings"); 
                	counter++;
                } else {             	
                	if(counter == 0) {
                		jObject.put("Error:", "There is no data for Meter ID " + id);                		
                	}              	
                }
                
                Iterator<Object> iterator2 = arr.iterator();
                JSONObject o2 = new JSONObject();
                while (iterator2.hasNext()) {
                    JSONObject jsonObject2 = (JSONObject) iterator2.next();   
                    for (String key : jsonObject2.keySet()) {

                    	JSONObject values = (JSONObject)jsonObject2.get(key);
                    	if(key.compareTo(formattedBegin) == 0 && key.compareTo(formattedEnd) < 0) {
                    		o2 = (JSONObject)jsonObject2.get(key); 
                    		Set<String> s = o2.keySet();
                    		Set<String> os = new TreeSet<>(s);
                    		Map<String, String> firstMap = new TreeMap<>();
                    		List<String> l = new ArrayList<>(os);
                    		
                    		for(int i = 0; i < os.size(); i++) {
                    			Double g = Double.parseDouble(values.getString(l.get(i)));
                    			DecimalFormat df = new DecimalFormat("#.###");
                    	        df.setRoundingMode(RoundingMode.CEILING);
                    	        String gg = df.format(g);
                    			firstMap.put(l.get(i), gg);
                    		}
                    		
                    		System.out.println("PRINTING KEYS FOR THE FIRST OBJECT WITH CORRECT ID");
                    		for(Map.Entry<String, String> ss : firstMap.entrySet()) {
                    			String[] sar = ss.getKey().split(":");
                    			int hour = Integer.parseInt(sar[0]);
                    			int min = Integer.parseInt(sar[1]);
                    			
                    			if(hour >= begHour && min >= begMinute) {                				
                    				map1.put(ss.getKey(), firstMap.get(ss.getKey()));                  		        
                    			}
                    			
                    		} 
                    		for(Map.Entry<String, String> entry : map1.entrySet()) {
            		        	System.out.println(entry.getKey() + " : " + entry.getValue());
            		        }
                    		jObject.put(key, map1);
            			} else if(key.compareTo(formattedBegin) > 0 && key.compareTo(formattedEnd) < 0){
                    		o2 = (JSONObject)jsonObject2.get(key); 
                    		Set<String> s = o2.keySet();
                    		Set<String> os = new TreeSet<>(s);
                    		
                    		Map<String, String> secondMap = new TreeMap<>();
                    		List<String> l = new ArrayList<>(os);
                    		
                    		for(int i = 0; i < os.size(); i++) {
                    			Double g = Double.parseDouble(values.getString(l.get(i)));
                    			DecimalFormat df = new DecimalFormat("#.###");
                    	        df.setRoundingMode(RoundingMode.CEILING);
                    	        String gg = df.format(g);
                    			secondMap.put(l.get(i), gg);
                    		}
                    		
                    		System.out.println("PRINTING KEYS FOR THE OBJECT WITH CORRECT ID");
                    		map2 = new TreeMap<>();
                    		for(Map.Entry<String, String> ss : secondMap.entrySet()) {
                    			String[] sar = ss.getKey().split(":");                  		
                    			map2.put(ss.getKey(), secondMap.get(ss.getKey()));                   			
                    		}
                    		for(Map.Entry<String, String> entry : map2.entrySet()) {
            		        	System.out.println(entry.getKey() + " : " + entry.getValue());
            		        }
                    		jObject.put(key, map2);

                    	} else if(key.compareTo(formattedEnd) == 0) {
                    		o2 = (JSONObject)jsonObject2.get(key); 
                    		Set<String> s = o2.keySet();
                    		Set<String> os = new TreeSet<>(s);
                    		
                    		Map<String, String> thirdMap = new TreeMap<>();
                    		List<String> l = new ArrayList<>(os);
                    		
                    		for(int i = 0; i < os.size(); i++) {
                    			Double g = Double.parseDouble(values.getString(l.get(i)));
                    			DecimalFormat df = new DecimalFormat("#.###");
                    	        df.setRoundingMode(RoundingMode.CEILING);
                    	        String gg = df.format(g);
//                    			thirdMap.put(l.get(i), values.getString(l.get(i)));
                    	        thirdMap.put(l.get(i), gg);
                    		}
                    		
                    		System.out.println("PRINTING KEYS FOR THE LAST OBJECT WITH CORRECT ID");
                    		map3 = new TreeMap<>();
                    		for(Map.Entry<String, String> ss : thirdMap.entrySet()) {
                    			String[] sar = ss.getKey().split(":");
                    			int hour = Integer.parseInt(sar[0]);
                    			int min = Integer.parseInt(sar[1]);
                    			if(hour <= endHour && min <= endMinute) {
                    				map3.put(ss.getKey(), thirdMap.get(ss.getKey()));
                    			}                    			
                    		}
                    		for(Map.Entry<String, String> entry : map3.entrySet()) {
            		        	System.out.println(entry.getKey() + " : " + entry.getValue());
            		        }
                    		jObject.put(key, map3);
                    	}
                    }
                }
                          
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }    
   
		return jObject;
	}
	
}
