package OAMock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternRe {
    public static void main(String[] args) {
        List<String> logs = Arrays.asList(
                "userId=123 action=login status=success",
                "userId=124 action=login status=failure",
                "userId=123 action=logout status=success",
                "userId=124 action=login status=failure"
        );
        System.out.println(countLoginFailures(logs));
    }

    private static Map<String,Integer> countLoginFailures(List<String> logs) {

        Map<String,Integer> failuresCounts = new HashMap<>();

        for (String log : logs){
            String[] level = log.split(" ", 3);
            if(level.length<3) continue;

            String users = level[0];
            String[] user = users.split("=",2);

            String status = level[2];
            String[] actualStatus = status.split("=", 2);
            if (!actualStatus[1].equals("failure")) continue;
            failuresCounts.put(user[1],failuresCounts.getOrDefault(user[1],0)+1);
        }
        return failuresCounts;
    }
}
