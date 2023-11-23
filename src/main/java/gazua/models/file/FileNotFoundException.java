package gazua.models.file;

import gazua.commons.Utils;
import gazua.commons.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends CommonException {

    public FileNotFoundException() {
        super(Utils.getMessage("NotFound.file", "error"), HttpStatus.NOT_FOUND);
    }
}