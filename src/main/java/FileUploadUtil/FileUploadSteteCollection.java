package FileUploadUtil;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUploadSteteCollection {
    private static Map<String, FIleUploadStatus> statusLinkedHashMap = Collections.synchronizedMap(new LinkedHashMap<>());

    public static FIleUploadStatus put(String sessionid, String filename, FIleUploadStatus status) {
        String key = sessionid + "-" + filename;
        return statusLinkedHashMap.put(key, status);
    }

    public static FIleUploadStatus get(String sessionid, String filename) {
        String key = sessionid + "-" + filename;
        return statusLinkedHashMap.get(key);
    }

    public static int size() {
        return statusLinkedHashMap.size();
    }

    public static FIleUploadStatus remove(String sessionid, String filename) {
        String key = sessionid + "-" + filename;
        return statusLinkedHashMap.remove(key);
    }
}
