public class ManegementCard {
 
    ManegementCard(){
 
    }
 
    /*studentCardList_に登録されているすべてのカード情報を表示*/
    public void showCardList() {
        StudentCard sc = new StudentCard();
 
        System.out.println("挿入されているすべてのカード情報を表示します");
        for(int i=0;i<sc.studentCardList_.size();i++) {
            System.out.println(i + ":");
            System.out.println("学生名:" + sc.studentCardList_.get(i).getStudentName());
            System.out.println("残高 :"  + sc.studentCardList_.get(i).getAccountBalance());
            System.out.println();
        }
    }
}
