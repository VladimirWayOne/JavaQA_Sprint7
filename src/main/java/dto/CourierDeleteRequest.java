package dto;

public class CourierDeleteRequest {
    private int courierId;

    public CourierDeleteRequest() {
    }

    public CourierDeleteRequest(int courierId) {
        this.courierId = courierId;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }
}
