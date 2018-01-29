package ICCharger;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class IDManager {
    static List<StudentCard> IDList = null;

    private static IDManager instance = null;

    public static synchronized IDManager getInstance()
	throws IOException{
	if(instance == null){
	    instance = new IDManager();
	    IDList = new ArrayList<StudentCard>();
	}
	return instance;
    }

    public void setFile(File ds) throws IOException {
	readFromFile(ds);
    }

    private IDManager() throws IOException{
    }

    private static String getAPIURL(String text){
	return "http://ap.mextractr.net/emotion_measure?out=atom&apikey=80674A97F24432F607384FA23B319D55D555E1C6&text=" + text;
    }

    public Map<String, Integer> getEmotion(StudentCard card)
	throws IOException{

	URL url = new URL(getAPIURL(card.getText()));
	InputStreamReader isr = null;
	BufferedReader br = null;
	InputStream is = null;
	String content;
	try{
	    isr = new InputStreamReader( url.openStream() );
	    br = new BufferedReader(isr);
	    String line;
	    StringBuilder sb = new StringBuilder();
	    while((line = br.readLine()) != null){
		sb.append(line);
	    }
	    content = sb.toString();
	    is = new ByteArrayInputStream( content.getBytes("utf-8"));
	}catch(Exception ex){
	    throw new IOException(ex);
	}finally{
	    if(br != null){
		try{
		    br.close();
		}catch(Exception ex){
		    ex.printStackTrace();
		}
	    }
	}

	Document document;
	try{
	    document = DocumentBuilderFactory
		.newInstance()
		.newDocumentBuilder()
		.parse(is);
	}catch(Exception ex){
	    throw new IOException(ex);
	}

	Element root = document.getDocumentElement();
	NodeList axisLike =
	    root.getElementsByTagName("likedislike");
	NodeList axisJoy =
	    root.getElementsByTagName("joysad");
	NodeList axisAnger =
	    root.getElementsByTagName("angerfear");

	if(axisLike.getLength() != 1 ||
	   axisJoy.getLength() != 1 ||
	   axisAnger.getLength() != 1){
	    throw new IOException("Unexpected response: "+content);
	}

	Node like = axisLike.item(0);
	NamedNodeMap lmap = like.getAttributes();
	Node joy = axisJoy.item(0);
	NamedNodeMap jmap = joy.getAttributes();
	Node anger = axisAnger.item(0);
	NamedNodeMap amap = anger.getAttributes();

	Map<String, Integer> ret = new HashMap<String, Integer>();
	Integer lValue;
	Integer jValue;
	Integer aValue;
	try{
	    lValue = Integer.valueOf(lmap.getNamedItem("value").getNodeValue());
	    jValue = Integer.valueOf(jmap.getNamedItem("value").getNodeValue());
	    aValue = Integer.valueOf(amap.getNamedItem("value").getNodeValue());
	    ret.put("likedislike", lValue);
	    ret.put("joysad", jValue);
	    ret.put("angerfear", aValue);
	    return ret;
	}catch(Exception ex){
	    throw new IOException(ex);
	}
    }

    private void readFromFile(File ds) throws IOException {
	if(! ds.exists()){
	    throw new IOException("Cannot find: "+ds.getPath());
	}
	BufferedReader br = null;
	try {
	    br = new BufferedReader(new FileReader(ds));

	    String line = null;
	    while((line = br.readLine()) != null){
		List<String> parsed =
		    Arrays.asList(line.split(",",0));
		Integer id = Integer.parseInt(parsed.get(0));
		Integer am = Integer.parseInt(parsed.get(2));
		File face = new File(parsed.get(3));
		StudentCard card =
		    new StudentCard(id, parsed.get(1), am,
				    face, parsed.get(4));
		IDList.add(card);
	    }
	} catch (Exception ex) {
	    throw new IOException(ex);
	} finally {
	    if(br != null){
		try{
		    br.close();
		}catch(Exception e){
		    e.printStackTrace();
		}
	    }
	}
    }

    public List<Integer> getIDs(){
	List<Integer> ret = new ArrayList<Integer>();
	for (StudentCard cur : IDList) {
	    ret.add(cur.getIdNum());
	}
	return ret;
    }

    public StudentCard getCard(Integer id){
	for (StudentCard cur : IDList) {
	    if(cur.getIdNum() == id){
		return cur;
	    }
	}
	return null;
    }
}
