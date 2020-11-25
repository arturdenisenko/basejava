package exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume with UUID " + uuid + " are not exists", uuid);
    }
}
