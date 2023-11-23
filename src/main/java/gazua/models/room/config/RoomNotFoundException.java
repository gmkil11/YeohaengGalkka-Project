package gazua.models.room.config;

import gazua.commons.Utils;
import gazua.commons.exceptions.AlertBackException;
import org.springframework.http.HttpStatus;

public class RoomNotFoundException extends AlertBackException {
    public RoomNotFoundException() {
        super(Utils.getMessage("NotFound.room", "error"));
        setStatus(HttpStatus.NOT_FOUND);
    }
}