package ICCharger;


import java.util.Calendar;

public class ShopCharger {
    private StudentCard insertedStudentCard;
    private Calendar calendar = null;

    public void insertStudentCard(StudentCard card){
	this.insertedStudentCard = card;
    }

    public void printLastUpdateDate() {
	String[] weekNames =
	    {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	if(calendar != null){
	    Integer year = calendar.get(Calendar.YEAR);
	    Integer month = calendar.get(Calendar.MONTH) + 1;
	    Integer date = calendar.get(Calendar.DATE);
	    Integer weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
	    Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
	    Integer minute = calendar.get(Calendar.MINUTE);
	    Integer second = calendar.get(Calendar.SECOND);

	    StringBuilder sb = new StringBuilder();
	    sb.append("Last update: ");
	    sb.append(String.format("%d/%d/%d ", year, month, date));
	    sb.append(weekNames[weekDay]);
	    sb.append(" ");
	    sb.append(String.format("%dh%dm%ds", hour, minute, second));
	    System.out.println(sb.toString());
	}else{
	    System.out.println("Last update: Not Used");
	}
    }

    public void chargeMoney(Integer value){
	printLastUpdateDate();
	if(insertedStudentCard != null){
	    Integer cur = insertedStudentCard.getAccountBalance();
	    insertedStudentCard.setAccountBalance(cur+value);
	    printAccountBalance();
	    calendar = Calendar.getInstance();
	}else{
	    System.err.println("学生証が挿入されていません");
	}
    }

    public void printAccountBalance(){
	System.out.println("id: "+
			   insertedStudentCard.getIdNum()+
			   " name: "+insertedStudentCard.getName()+
			   " balance: "+insertedStudentCard.getAccountBalance()+".");
    }
}
