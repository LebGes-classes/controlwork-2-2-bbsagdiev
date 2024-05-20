public class BroadcastsTime implements Comparable<BroadcastsTime> {
    private byte hour;
    private byte minute;

    public BroadcastsTime(String time) {
        String[] parts = time.split(":");
        this.hour = Byte.parseByte(parts[0]);
        this.minute = Byte.parseByte(parts[1]);
    }

    public byte hour() {
        return hour;
    }

    public byte minute() {
        return minute;
    }

    public boolean after(BroadcastsTime t) {
        return compareTo(t) > 0;
    }

    public boolean before(BroadcastsTime t) {
        return compareTo(t) < 0;
    }

    public boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return (after(t1) && before(t2));
    }

    @Override
    public int compareTo(BroadcastsTime o) {
        int hourCompare = Byte.compare(this.hour, o.hour);
        if (hourCompare != 0) {
            return hourCompare;
        }
        return Byte.compare(this.minute, o.minute);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }

}
