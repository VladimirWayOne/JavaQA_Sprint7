package dto;

public class OrderByTrackIdRequest {
    private int t; // трек-номер заказа

    public OrderByTrackIdRequest(int track) {
        this.t = track;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }
}
