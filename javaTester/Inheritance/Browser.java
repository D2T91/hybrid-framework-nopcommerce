package Inheritance;

public class Browser {

    int tuoi;

    protected void openURL() {

    }

    protected void back() {

    }

    protected void forward() {

    }

    public int setTuoi(int tuoi) {
        if (tuoi > 0) {
            this.tuoi = tuoi;
            System.out.println(" Tuổi của bạn là " + this.tuoi);
            return tuoi;
        } else {
            System.out.println("Tuổi phải là 1 số Dương");
            return 0;
        }
    }

    public static void main(String[] args) {
        Browser tuoiUser = new Browser();
        tuoiUser.setTuoi(5);
    }

}
