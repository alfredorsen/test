package sample;

public class Seat{

    private String row;
    private int col;
    private Family family;

    public Seat(){

    }

    public void setFamily(Family id){
        family = id;
    }

    public Family getFam(){
        return family;
    }

    public void setRow(String row){
        this.row = row;
    }

    public String getRow(){
        return row;
    }

    public void setCol(int col){
        this.col = col;
    }

    public int getCol(){
        return col;
    }

    public static void main(String[] agrs){

    }
}