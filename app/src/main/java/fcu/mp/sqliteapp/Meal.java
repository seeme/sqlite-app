package fcu.mp.sqliteapp;

public class Meal {
  private int id;
  private String name;
  private String description;
  private double price;
  private int imageId;

  public Meal(int id, String name, String description, double price, int imageId) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.imageId = imageId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public double getPrice() {
    return price;
  }

  public int getImageId() {
    return imageId;
  }
}
