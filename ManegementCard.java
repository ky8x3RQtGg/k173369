public class ManegementCard {
 
    ManegementCard(){
 
    }
 
    /*studentCardList_�ɓo�^����Ă��邷�ׂẴJ�[�h����\��*/
    public void showCardList() {
        StudentCard sc = new StudentCard();
 
        System.out.println("�}������Ă��邷�ׂẴJ�[�h����\�����܂�");
        for(int i=0;i<sc.studentCardList_.size();i++) {
            System.out.println(i + ":");
            System.out.println("�w����:" + sc.studentCardList_.get(i).getStudentName());
            System.out.println("�c�� :"  + sc.studentCardList_.get(i).getAccountBalance());
            System.out.println();
        }
    }
}
