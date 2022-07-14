package algorithmdesign.locationChoosing;

import java.util.Scanner;

public class Tools {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();//顶点
    int e=sc.nextInt();//边
    int result[][]=new int[n+1][n+1];//有向图
    int a[][]=new int[n+1][n+1];//顶点间的权值

    /**
     * 递推公式：d[i][j]=min(d[i][j],d[j][k]+d[k][j])
     * @param tools
     * @param n
     */
    public void RecordW(Tools tools, int n) {
        //记录两个单位之间的权值
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++) {
                tools.a[i][j] = tools.result[i][j]; //将两顶点的权值 存储到另一个二维数组
            }

//        for(int k=1;k<=n;k++)
//            for (int i = 1; i <= n; i++)
//                for (int j = 1; j <= n; j++) {
//                    tools.a[i][j] = Math.min(tools.a[i][j],tools.a[i][k]+tools.a[k][j]);
//                }



        for(int i=1;i<=n;i++)           //读取图中的数据打印出来
            for (int j = 1; j <= n; j++) {
                if (i != j)
                    System.out.println(Change.changeToStr(i) + "到" + Change.changeToStr(j) + "的权值为" + tools.a[i][j]);
            }
    }



    //计算每一个单位到其他单位的总权值并比较哪个最小，给出最佳选址点
    public void add(Tools tools) {
        int[] sum=new int[n+1];   //创建一维数组存储每个单位的总权值
        for(int i=0;i<n+1;i++)    //初始化一位数组，都为0
            sum[i]=0;
        for(int i=1;i<=n;i++) {   //开始计算每个单位的总权值
            for(int j=1;j<=n;j++) {
                if(i!=j) {
                    sum[i]=sum[i]+ tools.a[i][j];//tools.a[i][j]即表示每个单位到其他单位的权值
                }
            }
            System.out.println();
            if(sum[i]>10000) {           //判断两顶点间是否为通路
                System.out.print(Change.changeToStr(i)+"无法到达各顶点");
            }else {
                System.out.print(Change.changeToStr(i)+"到各顶点的权值总和为"+sum[i]);
            }

            System.out.println();
        }

        //计算出总权值最小的单位
        int address=1;//代表顶点的位置
        for(int i=2;i< sum.length;i++)    //查找最小值
            if(sum[1]>sum[i]) {
                sum[1]=sum[i];
                address=i;
            }
        System.out.println("所以最小权值总和为"+sum[1]+","+"学院超市的最佳选址为顶点"+Change.changeToStr(address));
        System.out.println("(权值总和越小代表该处总体越优)");
    }


}
