package main;

public class Place {
    private String name;
    private Position position;
    private double area;
    private int population;
    // lade till lite f�r att passa filen places.txt,
    //�ven metoder o toString-metod byggde jag p�, Andreas
    public Place(String name, double longitude, double latitude, 
    		double area, int population) {
        this.name = name;
        this.position = new Position(longitude, latitude);
        this.area = area;
        this.population = population;
    }
    public double getArea(){
    	return this.area;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
    
    public String toString() {
        return "Name: " +name+ " Position: " +position+ " Area (ha): " +area+ " Invånare: " +population;
    }
}
