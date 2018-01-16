
import java.util.Calendar;

public class MainShopCharger {
	private static StudentCard insertedStudentCard;

	public MainShopCharger(){}

	private static void insertStudentCard(int studentId){
		insertedStudentCard = StudentCard.getStudentCard(studentId);
	}

	private static void chargeMoney(int money,StudentCard sc){
		if(insertedStudentCard != null){
			insertedStudentCard.setAccountBalance(insertedStudentCard.getAccountBalance() + money);
			printAccountBalance();
			printCalendar(sc);

			sc.set_log(money);
		} else {
			System.err.println("学生証が挿入されていません．");
		}
	}

	private static void printAccountBalance(){
		System.out.println("残高を表示します");
		System.out.println("学生名:" + insertedStudentCard.getStudentName());
	    System.out.println("残高:" + insertedStudentCard.getAccountBalance());
	}

	private static void printCalendar(StudentCard sc) {
		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		String dow = new String();
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:   dow = "日曜日";break;
		case Calendar.MONDAY:   dow ="月曜日";break;
		case Calendar.TUESDAY:  dow = "火曜日";break;
		case Calendar.WEDNESDAY:dow = "水曜日";break;
		case Calendar.THURSDAY: dow = "木曜日";break;
		case Calendar.FRIDAY:   dow = "金曜日";break;
		case Calendar.SATURDAY:  dow = "土曜日";break;

		}

		String update_day = year+"年"+month+"月"+day+"日"+dow+"曜日"+hour+"時"+minute+"分"+second+"秒";

		System.out.print("更新日:");
		System.out.println(update_day);
		System.out.println();

		sc.setDay(update_day);

	}

	public static void main(String[] args) {
		//学生証インスタンスの作成
		StudentCard studentCard1 = new StudentCard(0, "tut");
		StudentCard studentCard2 = new StudentCard(1, "tenpaku");

		ManegementCard manegement = new ManegementCard();

// 		初期残高の設定
//		studentCard1.setAccountBalance(1000);

		//エラー処理の表示
		chargeMoney(200,studentCard1);

		//学生証 1 枚目の挿入とチャージ
		insertStudentCard(0);

		//加算
		chargeMoney(1000,studentCard1);
		//引き出し
		chargeMoney(-300,studentCard1);

		//学生証 2 枚目の挿入とチャージ
		insertStudentCard(1);
		//加算
		chargeMoney(500,studentCard2);
		//引き出し
		chargeMoney(-1000,studentCard2);


		//更新履歴の表示
		studentCard1.show_log();
		studentCard2.show_log();
		
		manegement.showCardList();

	}
}
