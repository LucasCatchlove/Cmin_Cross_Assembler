package components;

/**
 * a class used to store labels contained in line statements
 */
public class Label {

    private String name;
    private int addr;

    public Label(String name, int addr) {
        this.name = name;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public int getAddr() {
        return addr;
    }

    public boolean setAddr(int addr) {
        if (this.addr == -1) { //was not set before
            this.addr = addr;
            return true;
        }
        return false;
    }
}
