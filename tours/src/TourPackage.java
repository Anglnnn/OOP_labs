class TourPackage {
    private final String type;
    private final String transport;
    private final String food;
    private final int days;

    public int getDays() {
        return days;
    }

    public TourPackage(String type, String transport, String food, int days) {
        this.type = type;
        this.transport = transport;
        this.food = food;
        this.days = days;
    }

    @Override
    public String toString() {
        return "Тип: " + type + ", Транспорт: " + transport + ", Харчування: " + food + ", Кількість днів: " + days;
    }
}