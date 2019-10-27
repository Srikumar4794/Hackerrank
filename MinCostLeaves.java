class Solution {
    public int mctFromLeafValues(int[] arr) {
        int x = arr.length;
        int[][] dp = new int[x][x];
        for(int len = 1; len < x; len++) {
            for(int l = 0; l < x - len; l++) {
                int r = l + len;
                if(len == 1) {
                    dp[l][r] = arr[l] * arr[r];
                } else {
                    for(int k = l; k < r; k++) {
                        if(dp[l][r] == 0) {
                            dp[l][r] = dp[l][k] + dp[k + 1][r] + getMax(arr, l, k) * getMax(arr, k + 1, r);
                        } else {
                            dp[l][r] = Math.min(dp[l][r], dp[l][k] + dp[k + 1][r] + getMax(arr, l, k) * getMax(arr, k + 1, r));
                        }
                    }
                }
            }
        }
        return dp[0][x - 1];
    }
    private int getMax(int[] arr, int i, int j) {
        int max = arr[i];
        for(; i <= j; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }
}