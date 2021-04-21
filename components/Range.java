package components;

/**
 * a class that checks if an operand is within the apropriate range
 */
public class Range {

    int start;
    int end;
    String mncSignStr;

    public Range(char mncSignChar, int mncBitInt) {

        int range = (int) Math.pow(2, mncBitInt);

        if (mncSignChar == 'u') { //Unsigned Bit; char is u
            start = 0;
            end = range-1;
            mncSignStr = "unsigned";
        } else { //Signed Bit; char is i
            start = -range/2;
            end = range/2 -1;
            mncSignStr = "signed";
        }
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getMncSignStr() {
        return mncSignStr;
    }
}
