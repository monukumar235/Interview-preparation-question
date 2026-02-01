package OAMock;

import java.util.*;

public class LogAnalizer {
    public static void main(String[] args) {
        List<String> logs = Arrays.asList("2025-10-10 10:00:00 ERROR Failed to connect to DB",
                "2025-10-10 10:01:00 INFO User logged in",
                "2025-10-10 10:02:00 ERROR Failed to connect to DB",
                "2025-10-10 10:03:00 WARN Low disk space",
                "2025-10-10 10:04:00 ERROR Timeout error");
        System.out.println(countLogLevels(logs));
        System.out.println(mostFrequentError(logs));
    }

    private static String mostFrequentError(List<String> logs) {
        Map<String,Integer>errorCount = new HashMap<>();
        for (String log : logs)
        {
            String[] s = log.split(" ", 4);
            if (s.length<4)continue;
            String level = s[2];
            if(!level.equals("ERROR")) continue;
            String msg = s[3];
            errorCount.put(msg,errorCount.getOrDefault(msg,0)+1);
        }
        String mostFreqString = "";
        int maxCount=0;
        for (Map.Entry<String,Integer> entry: errorCount.entrySet()){
            if(entry.getValue()>maxCount){
                maxCount=entry.getValue();
                mostFreqString = entry.getKey();
            }
        }
        return mostFreqString;
    }

    private static Map<String, Integer> countLogLevels(List<String> logs) {

        Map<String,Integer> logLevelCount = new HashMap<>();

        for (String log : logs)
        {
            String[] s = log.split(" ", 4);
            if (s.length<4) continue;
            String level = s[2];
            logLevelCount.put(level,logLevelCount.getOrDefault(level,0)+1);
        }
        return logLevelCount;
    }
}
