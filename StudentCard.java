
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class StudentCard {
	public static ArrayList<StudentCard> studentCardList_ = new ArrayList<StudentCard>();
	private int studentId;
	private String studentName;
	private int accountBalance = 0;


	//画像登録用
	private ImageIcon icon;

	//自由テキスト
	private String free_txt;

	//履歴機能
	private String day;//最新の更新日
	private ArrayList<String> day_log = new ArrayList<String>();
	private ArrayList<Integer> price_log = new ArrayList<Integer>();

	StudentCard(){
		
	}
	
	public StudentCard(int studentId, String studentName) {
		this.studentId = studentId;
		this.studentName = studentName;

		icon = new ImageIcon("img.png");

		GUI gui = new GUI();
		gui.registration_panel(this);

		studentCardList_.add(this);
	}

	/*履歴のセット*/
	public void set_log(int price) {
		day_log.add(day);
		price_log.add(price);
	}

	//履歴の表示
	public void show_log() {
		System.out.println(studentName+"の更新履歴を表示します");
		for(int i=0;i<day_log.size();i++) {
			System.out.println(day_log.get(i) + ":___" + price_log.get(i) + "円");
		}
		System.out.println();
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public int setAccountBalance(int accountBalance) {
	    return this.accountBalance = accountBalance;
	}

	public static StudentCard getStudentCard(int studentId){
		return studentCardList_.get(studentId);
	}

	public String getStudentName() {
	    return studentName;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public String getFree_txt() {
		return free_txt;
	}

	public void setFree_txt(String free_txt) {
		this.free_txt = free_txt;
	}

	public ArrayList<StudentCard> getStudentCardList(){
		return studentCardList_;
	}


}
