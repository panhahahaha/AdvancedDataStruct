public class e01 {

    cateen a = new westerncateen();
    public static void main(String []args){
        for (int i = 0 ; i <10;i++){
            System.out.println(i);
        }
    }
}

interface cateen {
    static void eat() {
    }

    ;
}

class westerncateen implements cateen {
    public void eat() {
    }

}