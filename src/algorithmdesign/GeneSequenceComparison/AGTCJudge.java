//package algorithmdesign.GeneSequenceComparison;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
////public class AGTCJudge {
//    static int [][]res;
//
//
////    public static void main(String[] args) {
////        AGTCJudge agtcJudge = new AGTCJudge();
//////        String x ="AGTGATG";
//////        String y = "GTTAG";
////        String x = "A";
////        String y = "";
////        agtcJudge.getResult(x,y);
////        agtcJudge.printResult(x,y);
////    }
//
//    public int getResult(String x, String y) {
//
//        int m = x.length() + 1;
//        int n = y.length() + 1;
//        res = new int[m][n];
//
//        String[] X = x.split("");
//        String[] Y = y.split("");
//
//        for (int i = 1; i < m; i++) {
//            res[i][0] = res[i - 1][0] + getScore(X[i - 1], "-");
//        }
//        for (int j = 1; j < n; j++) {
//            res[0][j] = res[0][j - 1] + getScore("-", Y[j - 1]);
//        }
//
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                int s1 = res[i - 1][j - 1] + getScore(X[i - 1], Y[j - 1]);
//                int s2 = res[i - 1][j] + getScore(X[i - 1], "-");
//                int s3 = res[i][j - 1] + getScore("-", Y[j - 1]);
//                res[i][j] = getMax(s1, s2, s3);
//            }
//        }
//        saveToFile();
//        return res[m - 1][n - 1];
//
//    }
//
//    private void saveToFile(){
//        File file=new File("D:\\ACTG过程数据临时文本.txt"); //存放数组数据的文件
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(file);
//        } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }  //文件写入流
//        //将数组中的数据写入到文件中。每行各数据之间TAB间隔
//        for(int i=0;i<res.length;i++){
//            for(int j=0;j<res[0].length;j++){
//                try {
//                    fw.write(res[i][j]+"\t");
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//            try {
//                fw.write("\r\n");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            fw.flush();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private int getMax(int s1, int s2, int s3) {
//        int flag = s1;
//        if (flag < s2) {
//            flag = s2;
//        }
//        if (flag < s3) {
//            flag = s3;
//        }
//        return flag;
//    }
//
//
//    public void printResult(String s1,String s2){
//        int m = s1.length()+1;
//        int n = s2.length()+1;
//        for (int i = 0; i <m ; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(res[i][j]+"\t" );
//            }
//            System.out.println();
//        }
//    }
//
//
//
//    public int getScore(String x, String y){
//        Map<String, Integer> map = new HashMap<>();
//        map.put("A", 0);
//        map.put("C", 1);
//        map.put("G", 2);
//        map.put("T", 3);
//        map.put("-", 4);
//        int[][] score = {
//                {5, -1, -2, -1, -3},
//                {-1, 5, -3, -2, -4},
//                {-2, -3, 5, -2, -2},
//                {-1, -2, -2, 5, -1},
//                {-3, -4, -2, -1, -10000000}};
//        return score[map.get(x)][map.get(y)];
//    }
//
//}
//
