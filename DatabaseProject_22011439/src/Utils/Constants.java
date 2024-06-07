package Utils;

public class Constants {
    private boolean isEdit = false;
    private int reservationId;

    private Constants() {}

    private static class Holder {
        private static final Constants INSTANCE = new Constants();
    }

    public static Constants getInstance() {
        return Holder.INSTANCE;
    }

    public boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
}
