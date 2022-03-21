    package com.company;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;

    public class P2579_계단오르기 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            int[] dp = new int[N+1];
            int[] step = new int[N+1];
            for(int i = 1; i<=N ;i++){
                step[i] = Integer.parseInt(br.readLine());
            }
            dp[1] = step[1];
            if(N>=2) {
                dp[2] = step[2] + step[1];
            }

            for(int i = 3; i<=N; i++){
                dp[i] = step[i] + Math.max(dp[i-2] , dp[i-3] + step[i-1]);
                //내가 i번째에서 얻을 수 있는 최대값은 i번째 계단 값 + (2칸 전에서 올라온 경우 vs 한칸 전에서 올라온경우 중 최대값)이다
                //단 한칸 전에서올라오려면 2칸전을 밟지않고 3칸전을 무조건 밟고올라와야한다
            }
            System.out.println(dp[N]);

        }
    }
