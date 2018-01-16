
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
			System.err.println("�w���؂��}������Ă��܂���D");
		}
	}

	private static void printAccountBalance(){
		System.out.println("�c����\�����܂�");
		System.out.println("�w����:" + insertedStudentCard.getStudentName());
	    System.out.println("�c��:" + insertedStudentCard.getAccountBalance());
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
		case Calendar.SUNDAY:   dow = "���j��";break;
		case Calendar.MONDAY:   dow ="���j��";break;
		case Calendar.TUESDAY:  dow = "�Ηj��";break;
		case Calendar.WEDNESDAY:dow = "���j��";break;
		case Calendar.THURSDAY: dow = "�ؗj��";break;
		case Calendar.FRIDAY:   dow = "���j��";break;
		case Calendar.SATURDAY:  dow = "�y�j��";break;

		}

		String update_day = year+"�N"+month+"��"+day+"��"+dow+"�j��"+hour+"��"+minute+"��"+second+"�b";

		System.out.print("�X�V��:");
		System.out.println(update_day);
		System.out.println();

		sc.setDay(update_day);

	}

	public static void main(String[] args) {
		//�w���؃C���X�^���X�̍쐬
		StudentCard studentCard1 = new StudentCard(0, "tut");
		StudentCard studentCard2 = new StudentCard(1, "tenpaku");

		ManegementCard manegement = new ManegementCard();

// 		�����c���̐ݒ�
//		studentCard1.setAccountBalance(1000);

		//�G���[�����̕\��
		chargeMoney(200,studentCard1);

		//�w���� 1 ���ڂ̑}���ƃ`���[�W
		insertStudentCard(0);

		//���Z
		chargeMoney(1000,studentCard1);
		//�����o��
		chargeMoney(-300,studentCard1);

		//�w���� 2 ���ڂ̑}���ƃ`���[�W
		insertStudentCard(1);
		//���Z
		chargeMoney(500,studentCard2);
		//�����o��
		chargeMoney(-1000,studentCard2);


		//�X�V�����̕\��
		studentCard1.show_log();
		studentCard2.show_log();
		
		manegement.showCardList();

	}
}
