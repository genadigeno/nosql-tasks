package ua.epam.mishchenko.ticketbooking.model;

public class EventsAggregate {
    private String _id;
    private int count;
    private double average;

    public EventsAggregate() {
    }

    public EventsAggregate(String _id, int count, double average) {
        this._id = _id;
        this.count = count;
        this.average = average;
    }

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public double getAverage() { return Math.round(average * 100.0) / 100.0; }
    public void setAverage(double average) { this.average = average; }
}