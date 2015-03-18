package main;

public class Place {
    private String name;
    private Position position;
    private double area;
    private int population;
    // lade till lite för att passa filen places.txt,
    //även metoder o toString-metod byggde jag på, Andreas
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
    
    public int getPopulation() {
    	return population;
    }
    
    public String toString() {
    	return name;
//        return "Name: " +name+ " Position: " +position+ " Area (ha): " +area+ " Invånare: " +population;
    }
}
