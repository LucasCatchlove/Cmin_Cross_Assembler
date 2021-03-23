package errorReporters;

import components.Position;

public class ErrorMsg {

    private String msg;
    private Position position;

    public ErrorMsg(String msg, Position position) {
        this.msg = msg;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public String getMsg() {
        return msg;
    }
}