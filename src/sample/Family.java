package sample;

public class Family{

    private int number;
    private int id;
    private Seat seat;
    private String name;

    public Family(int id, int num, String name){
        this.id = id;
        number = num;
        this.name = name;
    }

    public void sit(Seat s){
        s.setFamily(this);
        seat = s;
    }

    public String getName(){
        return name;
    }

    public int getNum(){
        return number;
    }

    public int getId(){
        return id;
    }

    public Seat getSeat(){
        return seat;
    }

    public static void main(String[] agrs){

    }

}