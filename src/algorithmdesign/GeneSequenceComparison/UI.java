package algorithmdesign.GeneSequenceComparison;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UI extends JFrame {
    private static Label tishi = new Label("请输入只含有AGTC四个字母的字符串");
    private Label x = new Label("X序列:");
    private Label bank1 = new Label();
    private TextField xContent = new TextField(100);
    private Label y = new Label("Y序列：");
    private TextField yContent = new TextField(100);
    private Label bank2 = new Label();
    private Label guocheng = new Label("过程：");
    private TextArea process = new TextArea(10, 40);
    private Label res = new Label("相似度：");
    private Label bank3 = new Label();
    private Button define = new Button("确定");
    private Button reset = new Button("重置");
    private JPanel m = new JPanel();//主面板

    public UI() {
        m.setBackground(new Color(192, 192, 192, 200));//背景色
        m.setForeground(new Color(0, 0, 0, 200));//字体颜色
        m.setFont(new Font("黑体", Font.BOLD, 18));

        //添加文本，文本框，按钮
        m.add(tishi);
        m.add(x);
        m.add(bank1);
        m.add(xContent);
        m.add(y);
        m.add(yContent);
        m.add(bank2);
        m.add(guocheng);
        m.add(process);
        m.add(res);
        m.add(bank3);
        m.add(define);
        m.add(reset);

        //设置位置
        m.setLayout(null);//绝对布局
        tishi.setBounds(70, 20, 500, 30);
        tishi.setForeground(Color.red);
        x.setBounds(80, 50, 60, 30);
        xContent.setBounds(160, 50, 300, 30);
        bank1.setBounds(70, 20, 500, 30);
        y.setBounds(80, 100, 60, 30);
        yContent.setBounds(160, 100, 300, 30);
        bank2.setBounds(70, 20, 500, 30);
        guocheng.setBounds(80, 160, 60, 30);
        process.setBounds(160, 160, 300, 200);
        process.setEditable(false);
        res.setBounds(80, 400, 120, 30);
        define.setBounds(150, 500, 60, 40);
        reset.setBounds(350, 500, 60, 40);

        //数据交互
        //确认获取x,y的内容
        define.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String x = xContent.getText();
                String y = yContent.getText();

                if (x.length() == 0 && y.length() == 0) {
                    tishi.setText("x和y内容不能同时为空");
                    return;
                }
                if (check(x) && check(y)) {
                    AGTCJudge judge = new AGTCJudge();


                    String path = "D:\\ACTG过程数据临时文本.txt";
                    BufferedReader reader = null;
                    String line =null;//一行数据
                    //逐行读取
                    try {
                        reader=new BufferedReader(new FileReader(path));
                        while ((line = reader.readLine()) != null) {
                            process.append(System.lineSeparator() + line);
                        }

                        reader.close();//关闭数据流
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                res.setText("相似度" + judge.getResult(x, y));

                }

            }
        });

        //重置
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xContent.setText("");
                yContent.setText("");
                process.setText("");
                res.setText("相似度：");
                deleteFile();
            }

        });

        this.add(m);
        this.setSize(600, 600);
        this.setVisible(true);//开始显示图形
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("基因序列比较");

    }

    public static void deleteFile(){
        String path = "D:\\ACTG过程数据临时文本.txt";
        File file = new File(path);
        if(file.exists()){
            if(file.delete()){
                System.out.println("已删除");
            }else{
                System.out.println("未删除");
            }
        }else{
            System.out.println("文件不存在");
        }
    }

    public static boolean check(String input) {
        ArrayList list = new ArrayList();
        list.add("A");
        list.add("C");
        list.add("G");
        list.add("T");
        for (int i = 0; i < input.length(); i++) {
            String s = input.charAt(i) + "";
            if (!list.contains(s)) {
                tishi.setText("请输入正确值");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new UI();
    }
}
    /**
     * 当X与Y最后一个元素相等时有关系：result[i-1][j-1]+getScore(x[i],y[i])
     * 当X与Y的“_”匹配时，result[i-1][j]+getScore(x[i],"_")
     * 当X的“_”与Y匹配时，result[i][j-1]+getScore("_",y[j-1])
     */
    //填表格法
    class AGTCJudge {
        static int [][]res;


//    public static void main(String[] args) {
//        AGTCJudge agtcJudge = new AGTCJudge();
////        String x ="AGTGATG";
////        String y = "GTTAG";
//        String x = "A";
//        String y = "";
//        agtcJudge.getResult(x,y);
//        agtcJudge.printResult(x,y);
//    }

        public int getResult(String x, String y) {

            int m = x.length() + 1;
            int n = y.length() + 1;
            res = new int[m][n];

            String[] X = x.split("");
            String[] Y = y.split("");

            for (int i = 1; i < m; i++) {
                res[i][0] = res[i - 1][0] + getScore(X[i - 1], "-");
            }
            for (int j = 1; j < n; j++) {
                res[0][j] = res[0][j - 1] + getScore("-", Y[j - 1]);
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    int s1 = res[i - 1][j - 1] + getScore(X[i - 1], Y[j - 1]);
                    int s2 = res[i - 1][j] + getScore(X[i - 1], "-");
                    int s3 = res[i][j - 1] + getScore("-", Y[j - 1]);
                    res[i][j] = getMax(s1, s2, s3);
                }
            }
            saveToFile();
            return res[m - 1][n - 1];

        }

        private void saveToFile(){
            File file=new File("D:\\ACTG过程数据临时文本.txt"); //存放数组数据的文件
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }  //文件写入流
            //将数组中的数据写入到文件中。每行各数据之间TAB间隔
            for(int i=0;i<res.length;i++){
                for(int j=0;j<res[0].length;j++){
                    try {
                        fw.write(res[i][j]+"\t");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                try {
                    fw.write("\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private int getMax(int s1, int s2, int s3) {
            int flag = s1;
            if (flag < s2) {
                flag = s2;
            }
            if (flag < s3) {
                flag = s3;
            }
            return flag;
        }


        public void printResult(String s1,String s2){
            int m = s1.length()+1;
            int n = s2.length()+1;
            for (int i = 0; i <m ; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(res[i][j]+"\t" );
                }
                System.out.println();
            }
        }



        public int getScore(String x, String y){
            Map<String, Integer> map = new HashMap<>();
            map.put("A", 0);
            map.put("C", 1);
            map.put("G", 2);
            map.put("T", 3);
            map.put("-", 4);
            int[][] score = {
                    {5, -1, -2, -1, -3},
                    {-1, 5, -3, -2, -4},
                    {-2, -3, 5, -2, -2},
                    {-1, -2, -2, 5, -1},
                    {-3, -4, -2, -1, -10000000}};
            return score[map.get(x)][map.get(y)];
        }

    }